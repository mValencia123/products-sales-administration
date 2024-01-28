package easysalesassistant.api.dao;

import easysalesassistant.api.entity.Sell;
import org.springframework.data.repository.CrudRepository;

public interface ISellDAO extends CrudRepository<Sell,Long> {
}
