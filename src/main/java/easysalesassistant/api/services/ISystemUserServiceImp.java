package easysalesassistant.api.services;

import easysalesassistant.api.context.UserContext;
import easysalesassistant.api.dao.IBranchDAO;
import easysalesassistant.api.dao.IUserDAO;
import easysalesassistant.api.dto.systemuser.SystemUserDTO;
import easysalesassistant.api.dto.systemuser.UserDTO;
import easysalesassistant.api.entity.Address;
import easysalesassistant.api.entity.Branch;
import easysalesassistant.api.entity.Role;
import easysalesassistant.api.entity.SystemUser;
import easysalesassistant.api.enums.TypeUser;
import easysalesassistant.api.exceptions.NotFoundBranchException;
import easysalesassistant.api.exceptions.NotFoundSystemUserException;
import easysalesassistant.api.exceptions.UserDisabledException;
import easysalesassistant.api.exceptions.UserNameAlreadyExistsException;
import easysalesassistant.api.mappers.SystemUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ISystemUserServiceImp implements UserDetailsService, ISystemUserService {
    IUserDAO userDAO;
    BCryptPasswordEncoder passwordEncoder;
    IAddressService addressService;
    IBranchService branchService;
    SystemUserMapper systemUserMapper;

    //KafkaTemplate<String,Object> kafkaTemplate;

    ISystemUserServiceImp(IUserDAO userDao, BCryptPasswordEncoder passwordEncoder, IAddressService addressService, SystemUserMapper systemUserMapper,@Lazy IBranchService branchService){
        this.userDAO = userDao;
        this.passwordEncoder = passwordEncoder;
        this.addressService = addressService;
        this.systemUserMapper = systemUserMapper;
        this.branchService = branchService;
        //this.kafkaTemplate = kafkaTemplate;
    }

    private Logger logger = LoggerFactory.getLogger(ISystemUserServiceImp.class);


    @Transactional(readOnly = false)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser user = userDAO.findByUserName(username);
        if (user == null){
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        //kafkaTemplate.send("test-topic",user);
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for(Role role : user.getAuthorities()){
            authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }

        return new User(user.getUserName(),user.getPassword(),user.isEnabled(),true,true,true,authorities);
    }

    @Override
    public SystemUserDTO saveUser(SystemUserDTO systemUserDTO) {
        SystemUser userExists = userDAO.findByUserName(systemUserDTO.getUserName());
        if(userExists != null){
            throw new UserNameAlreadyExistsException(403,"User name is not available.");
        }

        SystemUser systemUserCreated = getUserByContext();
        Branch branch = branchService.existsBranchById(systemUserDTO.getIdBranch());
        if(branch.isDeleted()){
            throw new NotFoundBranchException(404,"Branch's ID doesnt exists, verify this branch is not deleted.");
        }
        Address address = addressService.createAddress(systemUserDTO.getAddress());

        String encodedPassword = passwordEncoder.encode(systemUserDTO.getPassword());
        SystemUser systemUser = new SystemUser();
        systemUser.setFirstName(systemUserDTO.getName());
        systemUser.setLastName(systemUserDTO.getLastName());
        systemUser.setEmail(systemUserDTO.getEmail());
        systemUser.setEnabled(true);
        systemUser.setRfc(systemUserDTO.getRfc());
        systemUser.setPassword(encodedPassword);
        systemUser.setUserName(systemUserDTO.getUserName());
        systemUser.setTypeUser(systemUserDTO.getTypeUser());
        systemUser.setGender(systemUserDTO.getGender());
        systemUser.setIdUserCreated(systemUserCreated);
        systemUser.setIdAddress(address);
        systemUser.setIdBranch(branch);
        userDAO.save(systemUser);
        systemUserDTO.setPassword("");
        return systemUserDTO;
    }

    @Override
    public SystemUser getUserByContext() {
        SystemUser systemUser = userDAO.findByUserName(UserContext.getCurrentUser());
        if(systemUser == null) throw new NotFoundSystemUserException(404,"System user doesn't exists.");
        return systemUser;
    }

    @Override
    public SystemUserDTO patchUser(Long idUser, SystemUserDTO systemUserDTO) {
        SystemUser systemUser = existsSystemUser(idUser);
        Address address = addressService.updateAddress(systemUser.getIdAddress().getId(),systemUserDTO.getAddress());
        Branch branch = branchService.existsBranchById(systemUserDTO.getIdBranch());

        systemUser.setFirstName(systemUserDTO.getName());
        systemUser.setLastName(systemUserDTO.getLastName());
        systemUser.setRfc(systemUserDTO.getRfc());
        systemUser.setEmail(systemUserDTO.getEmail());
        systemUser.setBirthday(systemUserDTO.getBirthday());
        systemUser.setGender(systemUserDTO.getGender());
        systemUser.setPhoneNumber(systemUserDTO.getPhoneNumber());
        systemUser.setIdAddress(address);
        systemUser.setIdBranch(branch);

        userDAO.save(systemUser);

        return systemUserDTO;
    }

    @Override
    public SystemUserDTO getUser(Long idUser) {
        SystemUser systemUser = existsSystemUser(idUser);
        if(!systemUser.isEnabled()) throw new UserDisabledException(403,"User is disabled.");
        return systemUserMapper.systemUserToSystemUserDTO(systemUser);
    }

    @Override
    public void deleteUser(Long idUser) {
        SystemUser systemUser = userDAO.findById(idUser).orElseThrow(() -> new NotFoundSystemUserException(404,"User's Id doesn't exists."));
        SystemUser systemUserDeleted = getUserByContext();

        systemUser.setEnabled(false);
        systemUser.setDeletedAt(new Date());
        systemUser.setIdUserDeleted(systemUserDeleted);

        userDAO.save(systemUser);
    }

    @Override
    public SystemUser existsSystemUser(Long idUser) {
        SystemUser systemUser = userDAO.findById(idUser).orElseThrow(() -> new NotFoundSystemUserException(404,"User's Id doesn't exists."));
        return systemUser;
    }

    @Override
    public List<UserDTO> getAllUserByType(TypeUser typeUser) {
        return userDAO.findByTypeUserAndEnabledTrue(typeUser)
                .stream()
                .map((u) -> UserDTO.builder()
                        .idUser(u.getId())
                        .name(u.getLastName() + " " + u.getFirstName())
                        .email(u.getEmail())
                        .userName(u.getUserName())
                        .build())
                .collect(Collectors.toList());
    }

    /*@KafkaListener(topics = "test-topic",groupId = "default")
    public void listerUser(SystemUser user){
        System.out.println("New Message: "+user.getFirstName());
    }*/

}