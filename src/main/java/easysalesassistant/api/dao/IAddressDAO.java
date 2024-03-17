package easysalesassistant.api.dao;

import easysalesassistant.api.entity.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAddressDAO extends CrudRepository<Address,Long> {
}
