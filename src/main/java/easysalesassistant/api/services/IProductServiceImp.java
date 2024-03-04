package easysalesassistant.api.services;

import easysalesassistant.api.dao.IProductDAO;
import easysalesassistant.api.dto.ProductDTO;
import easysalesassistant.api.entity.Product;
import easysalesassistant.api.exceptions.ProductDoesntExistsException;
import easysalesassistant.api.mappers.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class IProductServiceImp implements IProductService {
    IProductDAO productDAO;

    IProductServiceImp(IProductDAO productDAO){
        this.productDAO = productDAO;
    }

    public ProductDTO getProduct(Long id){
        Product product = productDAO.findById(id)
                .orElseThrow(() -> new ProductDoesntExistsException(404,"Product doesn't exists with this Id."));
        /*ProductDTO productDTO = new ProductDTO();
        productDTO.setDescription(product.getDescription());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setPublicPrice(product.getPublicPrice());
        productDTO.setHasDiscount(product.isHasDiscount());
        productDTO.setPiecesBox(product.getPiecesBox());*/
        ProductDTO productDTO = ProductMapper.INSTANCE.productToProductDTO(product);
        return productDTO;
    }

    public ProductDTO saveProduct(ProductDTO product){
        Product p = ProductMapper.INSTANCE.productDTOToProduct(product);
        productDAO.save(p);
        return product;
    }

    public ProductDTO updateProduct(Long id, ProductDTO product){
        Product p =  productDAO.findById(id).orElseThrow(() -> new ProductDoesntExistsException(400,"Product's ID doesn't exists."));

        p.setName(product.getName());
        p.setDescription(product.getDescription());
        p.setPrice(product.getPrice());
        p.setPublicPrice(product.getPublicPrice());
        p.setHasDiscount(product.isHasDiscount());
        p.setPiecesBox(product.getPiecesBox());

        productDAO.save(p);
        ProductDTO productDTO = ProductMapper.INSTANCE.productToProductDTO(p);
        return productDTO;
    }

    public void deleteProduct(Long id){
        productDAO.findById(id).orElseThrow(() -> new ProductDoesntExistsException(400,"Product's ID doesn't exists."));
        productDAO.deleteById(id);
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
