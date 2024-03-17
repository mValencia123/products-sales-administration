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

    IProductServiceImp(IProductDAO productDAO, ISystemUserService userService){
        this.productDAO = productDAO;
        this.userService = userService;
    }

    public ProductDTO getProduct(Long id){
        Product product = productDAO.findById(id)
                .orElseThrow(() -> new NotFoundProductException(404,"Product doesn't exists with this Id."));

        ProductDTO productDTO = ProductMapper.INSTANCE.productToProductDTO(product);
        return productDTO;
    }

    public ProductDTO saveProduct(ProductDTO product){
        Product p = ProductMapper.INSTANCE.productDTOToProduct(product);
        SystemUser systemUser = userService.getUserByContext();
        p.setIdUserCreated(systemUser);
        productDAO.save(p);
        return product;
    }

    public ProductDTO updateProduct(Long id, ProductDTO product){
        Product p =  productDAO.findById(id).orElseThrow(() -> new NotFoundProductException(400,"Product's ID doesn't exists."));
        p.setItemCode(product.getItemCode());
        p.setBarCode(product.getBarCode());
        p.setName(product.getName());
        p.setDescription(product.getDescription());
        p.setCost(product.getCost());
        p.setWholesalePrice(product.getWholesalePrice());
        p.setRetailPrice(product.getRetailPrice());
        p.setHasDiscount(product.isHasDiscount());
        p.setPiecesBox(product.getPiecesBox());
        productDAO.save(p);
        ProductDTO productDTO = ProductMapper.INSTANCE.productToProductDTO(p);
        return productDTO;
    }

    public void deleteProduct(Long id){
        Product p = productDAO.findById(id).orElseThrow(() -> new NotFoundProductException(400,"Product's ID doesn't exists."));
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
                .map((p) -> ProductMapper.INSTANCE.productToProductDTO(p))
                .collect(Collectors.toList());
    }
}
