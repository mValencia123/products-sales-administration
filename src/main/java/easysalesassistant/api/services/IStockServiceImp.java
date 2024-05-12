package easysalesassistant.api.services;

import easysalesassistant.api.dao.IStockDAO;
import easysalesassistant.api.entity.Product;
import easysalesassistant.api.entity.Stock;
import easysalesassistant.api.entity.Store;
import easysalesassistant.api.exceptions.NotEnoughStock;
import org.springframework.stereotype.Service;

@Service
public class IStockServiceImp implements IStockService {
    IStockDAO stockDAO;

    IStockServiceImp(IStockDAO stockDAO){
        this.stockDAO = stockDAO;
    }

    @Override
    public boolean productHasStockAt(Product idProduct, Store idStore, int amount) {
        Stock stock = stockDAO.findByIdProductAndIdStore(idProduct,idStore);
        if(stock == null){
            throw new NotEnoughStock(403,"Product: ".concat(idProduct.toString()).concat(" doesn't exists in this branch."));
        }
        if(stock.getAmount() < amount){
            throw new NotEnoughStock(403,"Product: ".concat(idProduct.toString()).concat(" doesn't have enough stock in this branch."));
        }
        return true;
    }

    @Override
    public void reduceStockOfProductAt(Product idProduct, Store idStore, int amount) {
        try{
            Stock stock = stockDAO.findByIdProductAndIdStore(idProduct,idStore);
            stock.setAmount(stock.getAmount() - amount);
            stockDAO.save(stock);
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
