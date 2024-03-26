package easysalesassistant.api.services;

import easysalesassistant.api.context.UserContext;
import easysalesassistant.api.dao.IBranchDAO;
import easysalesassistant.api.dao.IUserDAO;
import easysalesassistant.api.dto.systemuser.SystemUserDTO;
import easysalesassistant.api.entity.Address;
import easysalesassistant.api.entity.Branch;
import easysalesassistant.api.entity.Role;
import easysalesassistant.api.entity.SystemUser;
import easysalesassistant.api.exceptions.NotFoundBranchException;
import easysalesassistant.api.exceptions.NotFoundSystemUserException;
import easysalesassistant.api.mappers.SystemUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Service
public class ISystemUserServiceImp implements UserDetailsService, ISystemUserService {
    IUserDAO userDAO;
    BCryptPasswordEncoder passwordEncoder;
    IAddressService addressService;
    IBranchDAO branchDAO;
    SystemUserMapper systemUserMapper;

    ISystemUserServiceImp(IUserDAO userDao, BCryptPasswordEncoder passwordEncoder, IAddressService addressService, IBranchDAO branchDAO, SystemUserMapper systemUserMapper){
        this.userDAO = userDao;
        this.passwordEncoder = passwordEncoder;
        this.addressService = addressService;
        this.branchDAO = branchDAO;
        this.systemUserMapper = systemUserMapper;
    }

    private Logger logger = LoggerFactory.getLogger(ISystemUserServiceImp.class);


    @Transactional(readOnly = false)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser user = userDAO.findByUserName(username);
        if (user == null){
            logger.error("Usurio nullo");
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for(Role role : user.getAuthorities()){
            authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }

        return new User(user.getUserName(),user.getPassword(),user.isEnabled(),true,true,true,authorities);
    }

    @Override
    public SystemUserDTO saveUser(SystemUserDTO systemUserDTO) {
        SystemUser systemUserCreated = getUserByContext();
        String encodedPassword = passwordEncoder.encode(systemUserDTO.getPassword());
        Address address = addressService.createAddress(systemUserDTO.getAddress());

        SystemUser systemUser = new SystemUser();
        systemUser.setFirstName(systemUserDTO.getName());
        systemUser.setLastName(systemUserDTO.getLastName());
        systemUser.setEmail(systemUserDTO.getEmail());
        systemUser.setEnabled(true);
        systemUser.setRfc(systemUserDTO.getRfc());
        systemUser.setPassword(encodedPassword);
        systemUser.setUserName(systemUserDTO.getUserName());
        systemUser.setIdUserCreated(systemUserCreated);
        systemUser.setIdAddress(address);
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
        SystemUser systemUser = userDAO.findById(idUser).orElseThrow(() -> new NotFoundSystemUserException(404,"User's Id doesn't exists."));
        Address address = addressService.updateAddress(systemUser.getIdAddress().getId(),systemUserDTO.getAddress());
        Branch branch = branchDAO.findById(systemUserDTO.getIdBranch()).orElseThrow(() -> new NotFoundBranchException(404,"Branch's Id doesn't exists."));

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

}