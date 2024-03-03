package easysalesassistant.api.dao;

import easysalesassistant.api.entity.Product;
import easysalesassistant.api.entity.Stock;
import easysalesassistant.api.entity.Store;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStockDAO extends CrudRepository<Stock,Long> {
    Stock findByIdProductAndIdStore(Product idProduct, Store idStore);
}
