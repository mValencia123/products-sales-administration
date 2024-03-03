package easysalesassistant.api.services;

import easysalesassistant.api.dao.*;
import easysalesassistant.api.dto.ProductDTO;
import easysalesassistant.api.dto.SellDTO;
import easysalesassistant.api.dto.SellProductDTO;
import easysalesassistant.api.entity.*;
import easysalesassistant.api.exceptions.NotEnoughStock;
import easysalesassistant.api.exceptions.NotFoundBranchException;
import easysalesassistant.api.exceptions.NotFoundStoreException;
import easysalesassistant.api.exceptions.ProductDoesntExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ISellServiceImp implements ISellService{
    ISellDAO sellDAO;
    IStoreDAO storeDAO;
    IStockService stockService;
    IBranchDAO branchDAO;

    IProductDAO productDAO;

    ISellServiceImp(ISellDAO sellDAO,IStoreDAO storeDAO,IBranchDAO branchDAO,IStockService stockService, IProductDAO productDAO){
        this.sellDAO = sellDAO;
        this.storeDAO = storeDAO;
        this.stockService = stockService;
        this.branchDAO = branchDAO;
        this.productDAO = productDAO;
    }

    @Transactional()
    @Override
    public void saveSell(SellDTO sellDTO) {
        Branch branch = branchDAO.findById(sellDTO.getIdBranch()).orElseThrow(() -> new NotFoundBranchException(404,"Branch doesn't exists."));
        Store idStore = branch.getIdStore();
        if(idStore == null){
            throw new NotFoundStoreException(400,"Branch doesn't have a linked store.");
        }
        sellDTO.getProductsSell().forEach((p) -> {
            Product product = productDAO.findById(p.getIdProduct()).orElseThrow(() -> new ProductDoesntExistsException(404,"Doens't exists product validating stocks."));
            stockService.productHasStockAt(product,idStore,p.getAmount());
        });
        sellDTO.getProductsSell().forEach((p) -> {
            Product product = productDAO.findById(p.getIdProduct()).orElseThrow(() -> new ProductDoesntExistsException(404,"Doens't exists product reducing stocks."));
            stockService.reduceStockOfProductAt(product,idStore,p.getAmount());
        });
    }
}
