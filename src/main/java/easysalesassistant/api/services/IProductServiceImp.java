package easysalesassistant.api.services;

import easysalesassistant.api.dao.IProductDAO;
import easysalesassistant.api.dto.product.ProductDTO;
import easysalesassistant.api.entity.Product;
import easysalesassistant.api.entity.Store;
import easysalesassistant.api.entity.SystemUser;
import easysalesassistant.api.exceptions.NotFoundProductException;
import easysalesassistant.api.exceptions.ProductIsDeletedException;
import easysalesassistant.api.mappers.ProductMapper;
import easysalesassistant.api.utils.StreamOperation;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IProductServiceImp implements IProductService {
    IProductDAO productDAO;
    ISystemUserService userService;
    ProductMapper productMapper;
    IStockService stockService;

    public IProductServiceImp(IProductDAO productDAO, ISystemUserService userService,ProductMapper productMapper, IStockService stockService){
        this.productDAO = productDAO;
        this.userService = userService;
        this.productMapper = productMapper;
        this.stockService = stockService;
    }

    public ProductDTO getProduct(Long id){
        Product product = existsProductById(id);
        productIsDeleted(product);
        ProductDTO productDTO = productMapper.productToProductDTO(product);
        return productDTO;
    }

    public ProductDTO saveProduct(ProductDTO product){
        Product p = productMapper.productDTOToProduct(product);
        SystemUser systemUser = userService.getUserByContext();
        p.setIdUserCreated(systemUser);
        productDAO.save(p);
        return product;
    }

    public ProductDTO updateProduct(Long id, ProductDTO product){
        Product p = existsProductById(id);
        productIsDeleted(p);
        p.setItemCode(product.getItemCode());
        p.setBarCode(product.getBarCode());
        p.setName(product.getName());
        p.setDescription(product.getDescription());
        p.setCost(product.getCost());
        p.setWholesalePrice(product.getWholesalePrice());
        p.setRetailPrice(product.getRetailPrice());
        p.setHasDiscount(product.isHasDiscount());
        p.setPiecesBox(product.getPiecesBox());
        p = productDAO.save(p);
        ProductDTO productDTO = productMapper.productToProductDTO(p);
        return productDTO;
    }

    public void deleteProduct(Long id){
        Product p = existsProductById(id);
        productIsDeleted(p);
        SystemUser systemUser = userService.getUserByContext();
        p.setIdUserDeleted(systemUser);
        p.setDeletedAt(new Date());
        p.setDeleted(true);
        productDAO.save(p);
    }

    public List<ProductDTO> getProducts() {
        return StreamOperation
                .getStreamFromIterable(productDAO.findAll())
                .filter((p) -> !p.isDeleted())
                .map(productMapper::productToProductDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean productHasStock(Long id, Store idStore, int amount) {
        Product product = existsProductById(id);
        return stockService.productHasStockAt(product,idStore,amount);
    }

    @Override
    public void productIsDeleted(Product product) {
        if(!product.isDeleted()){
            throw new ProductIsDeletedException(404,"The product you try to modify is not longer active.");
        }
    }

    public Product existsProductById(Long id){
        return productDAO.findById(id).orElseThrow(() -> new NotFoundProductException(400,"Product's ID doesn't exists."));
    }
}
