package easysalesassistant.api.services;

import easysalesassistant.api.entity.Product;
import easysalesassistant.api.entity.Stock;
import easysalesassistant.api.entity.Store;
import org.springframework.stereotype.Service;

public interface IStockService {
    boolean productHasStockAt(Product idProduct, Store idStore, int amount);
    void reduceStockOfProductAt(Product idProduct, Store idStore, int amount);
}
