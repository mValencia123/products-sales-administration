package easysalesassistant.api.dao;

import easysalesassistant.api.entity.Reception;
import easysalesassistant.api.entity.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface IReceptionDAO extends CrudRepository<Reception,Long>  {
}
