package easysalesassistant.api.dao;

import easysalesassistant.api.entity.Provider;
import org.springframework.data.repository.CrudRepository;

public interface IProviderDAO extends CrudRepository<Provider,Long> {
}
