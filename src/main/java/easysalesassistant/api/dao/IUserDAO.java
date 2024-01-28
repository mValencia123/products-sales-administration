package easysalesassistant.api.dao;

import easysalesassistant.api.entity.SystemUser;
import org.springframework.data.repository.CrudRepository;

public interface IUserDAO extends CrudRepository<SystemUser,Long> {
    SystemUser findByUserName(String userName);
}
