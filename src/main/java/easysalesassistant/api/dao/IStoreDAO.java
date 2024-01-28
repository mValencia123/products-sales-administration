package easysalesassistant.api.dao;

import easysalesassistant.api.entity.Store;
import org.springframework.data.repository.CrudRepository;

public interface IStoreDAO extends CrudRepository<Store,Long> {
}
