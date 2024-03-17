package easysalesassistant.api.dao;

import easysalesassistant.api.entity.State;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStateDAO extends CrudRepository<State,Long> {
}
