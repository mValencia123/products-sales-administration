package easysalesassistant.api.dao;

import easysalesassistant.api.entity.City;
import easysalesassistant.api.entity.State;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICityDAO extends CrudRepository<City,Long> {
    List<City> findByIdState(State idState);
}
