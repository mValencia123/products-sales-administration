package easysalesassistant.api.services;

import easysalesassistant.api.dao.IUserDAO;
import easysalesassistant.api.entity.Role;
import easysalesassistant.api.entity.SystemUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    IUserDAO userDao;

    private Logger logger = LoggerFactory.getLogger(UserService.class);

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

    public SystemUser save(SystemUser user){
        return userDao.save(user);
    }
}