package easysalesassistant.api.dao;

import easysalesassistant.api.entity.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICityDAO extends CrudRepository<City,Long> {
}
