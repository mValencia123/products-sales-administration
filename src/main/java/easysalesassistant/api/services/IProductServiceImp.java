package easysalesassistant.api.services;

import easysalesassistant.api.context.UserContext;
import easysalesassistant.api.dao.IProductDAO;
import easysalesassistant.api.dao.IUserDAO;
import easysalesassistant.api.dto.product.ProductDTO;
import easysalesassistant.api.entity.Product;
import easysalesassistant.api.entity.SystemUser;
import easysalesassistant.api.exceptions.NotFoundProductException;
import easysalesassistant.api.exceptions.NotFoundSystemUserException;
import easysalesassistant.api.mappers.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class IProductServiceImp implements IProductService {
    IProductDAO productDAO;
    ISystemUserService userService;
    ProductMapper productMapper;

    IProductServiceImp(IProductDAO productDAO, ISystemUserService userService,ProductMapper productMapper){
        this.productDAO = productDAO;
        this.userService = userService;
        this.productMapper = productMapper;
    }

    public ProductDTO getProduct(Long id){
        Product product = existsProductById(id);
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
        SystemUser systemUser = userService.getUserByContext();
        p.setIdUserDeleted(systemUser);
        p.setDeletedAt(new Date());
        p.setDeleted(true);
        productDAO.save(p);
    }

    public List<ProductDTO> getProducts() {
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(
                        productDAO.findAll().iterator(),
                        Spliterator.ORDERED)
                , false)
                .map((p) -> productMapper.productToProductDTO(p))
                .collect(Collectors.toList());
    }

    public Product existsProductById(Long id){
        return productDAO.findById(id).orElseThrow(() -> new NotFoundProductException(400,"Product's ID doesn't exists."));
    }
}
