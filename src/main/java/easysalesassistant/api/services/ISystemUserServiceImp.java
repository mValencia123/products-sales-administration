package easysalesassistant.api.services;

import easysalesassistant.api.dao.ITenantDAO;
import easysalesassistant.api.dao.IUserDAO;
import easysalesassistant.api.dto.SystemUserDTO;
import easysalesassistant.api.entity.Role;
import easysalesassistant.api.entity.SystemUser;
import easysalesassistant.api.entity.Tenant;
import easysalesassistant.api.exceptions.NotFoundTenantException;
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
import java.util.List;

@Service
public class ISystemUserServiceImp implements UserDetailsService, ISystemUserService {
    IUserDAO userDao;
    ITenantDAO tenantDAO;
    BCryptPasswordEncoder passwordEncoder;

    ISystemUserServiceImp(IUserDAO userDao, ITenantDAO tenantDAO, BCryptPasswordEncoder passwordEncoder){
        this.userDao = userDao;
        this.tenantDAO = tenantDAO;
        this.passwordEncoder = passwordEncoder;
    }

    private Logger logger = LoggerFactory.getLogger(ISystemUserServiceImp.class);


    @Transactional(readOnly = false)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        SystemUser user = userDao.findByUserName(username);

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
        Tenant idTenant = tenantDAO.findById(systemUserDTO.getIdTenant())
                .orElseThrow(() -> new NotFoundTenantException(400,"Tenant's ID doesn't exists."));

        String encodedPassword = passwordEncoder.encode(systemUserDTO.getPassword());
        SystemUser systemUser = new SystemUser();

        systemUser.setName(systemUserDTO.getName());
        systemUser.setLastName(systemUserDTO.getLastName());
        systemUser.setEmail(systemUserDTO.getEmail());
        systemUser.setEnabled(true);
        systemUser.setRfc(systemUserDTO.getRfc());
        systemUser.setPassword(encodedPassword);
        systemUser.setIdTenant(idTenant);
        userDao.save(systemUser);

        systemUserDTO.setPassword("");
        return systemUserDTO;
    }
}