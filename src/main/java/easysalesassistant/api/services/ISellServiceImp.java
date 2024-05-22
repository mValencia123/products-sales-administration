package easysalesassistant.api.services;

import easysalesassistant.api.dao.*;
import easysalesassistant.api.dto.sell.SellDTO;
import easysalesassistant.api.entity.*;
import easysalesassistant.api.exceptions.NotFoundBranchException;
import easysalesassistant.api.exceptions.NotFoundProductException;
import easysalesassistant.api.exceptions.NotFoundSellException;
import easysalesassistant.api.exceptions.NotFoundStoreException;
import easysalesassistant.api.mappers.ProductSellMapper;
import easysalesassistant.api.mappers.SellMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ISellServiceImp implements ISellService{
    ISellDAO sellDAO;
    IStoreDAO storeDAO;
    IStockService stockService;
    IBranchService branchService;
    IProductService productService;
    SellMapper sellMapper;
    ProductSellMapper productSellMapper;
    ISystemUserService systemUserService;

    ISellServiceImp(ISellDAO sellDAO,IStoreDAO storeDAO,IBranchService branchService,IStockService stockService, IProductService productService,SellMapper sellMapper,ProductSellMapper productSellMapper,ISystemUserService systemUserService){
        this.sellDAO = sellDAO;
        this.storeDAO = storeDAO;
        this.stockService = stockService;
        this.branchService = branchService;
        this.productService = productService;
        this.sellMapper = sellMapper;
        this.productSellMapper = productSellMapper;
        this.systemUserService = systemUserService;
    }

    @Transactional()
    @Override
    public void saveSell(SellDTO sellDTO) {
        Branch branch = branchService.existsBranchById(sellDTO.getIdBranch());
        Store idStore = branch.getIdStore();
        if(idStore == null){
            throw new NotFoundStoreException(400,"Branch doesn't have a linked store.");
        }
        Sell sell = sellMapper.sellDTOToSell(sellDTO);
        Set<ProductSell> setProducts = sellDTO.getProductsSell()
                .stream()
                .map((p) -> productSellMapper.productSellDTOToProductSell(p))
                .collect(Collectors.toSet());
        sell.setProductSellSet(setProducts);
        sellDAO.save(sell);
    }

    @Override
    public void processSell(SellDTO sell) {

    }

    @Override
    public void deleteSell(Long idSell) {
        Sell sell = existsSellById(idSell);
        if(sell.getDeletedAt() != null) throw new NotFoundSellException(404,"Sell's ID doesn't exists or maybe sell is already deleted");
        sell.setDeletedAt(new Date());
        SystemUser systemUser = systemUserService.getUserByContext();
        sell.setIdUserDeleted(systemUser);
        sellDAO.save(sell);
    }

    @Override
    public void updateSell(Long idSell, SellDTO sell) {

    }

    @Override
    public Sell existsSellById(Long idSell) {
        Sell sell = sellDAO.findById(idSell).orElseThrow(() -> new NotFoundSellException(404,"Sell's ID doesn't exists."));
        return sell;
    }
}
