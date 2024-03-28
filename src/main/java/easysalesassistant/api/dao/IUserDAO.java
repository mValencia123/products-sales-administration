package easysalesassistant.api.dao;

import easysalesassistant.api.entity.SystemUser;
import easysalesassistant.api.enums.TypeUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IUserDAO extends CrudRepository<SystemUser,Long> {
    SystemUser findByUserName(String userName);
    List<SystemUser> findByTypeUserAndEnabledTrue(TypeUser typeUser);
}
