package easysalesassistant.api.dao;

import easysalesassistant.api.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface IProductDAO extends CrudRepository<Product,Long> {

}
