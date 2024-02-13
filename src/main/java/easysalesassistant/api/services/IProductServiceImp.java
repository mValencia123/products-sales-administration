package easysalesassistant.api.services;

import easysalesassistant.api.dao.IProductDAO;
import easysalesassistant.api.dto.ProductDTO;
import easysalesassistant.api.entity.Product;
import easysalesassistant.api.exceptions.ProductDoesntExistsException;
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

    @Autowired
    private IProductDAO productDAO;

    private void fillDataProduct(ProductDTO productDTO, Product product){
        product.setDescription(productDTO.getDescription());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setPublicPrice(productDTO.getPublicPrice());
        product.setHasDiscount(productDTO.isHasDiscount());
        product.setPiecesBox(productDTO.getPiecesBox());
        productDAO.save(product);
    }

    public ProductDTO getProduct(Long id){
        Product product = productDAO.findById(id)
                .orElseThrow(() -> new ProductDoesntExistsException(404,"Product doesn't exists with this Id."));
        ProductDTO productDTO = new ProductDTO();
        productDTO.setDescription(product.getDescription());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setPublicPrice(product.getPublicPrice());
        productDTO.setHasDiscount(product.isHasDiscount());
        productDTO.setPiecesBox(product.getPiecesBox());
        return productDTO;
    }

    public ProductDTO saveProduct(ProductDTO product){
        Product p = new Product();
        fillDataProduct(product,p);
        return product;
    }

    public ProductDTO updateProduct(Long id, ProductDTO product){
        Product p =  productDAO.findById(id).orElseThrow(() -> new ProductDoesntExistsException(400,"Product's ID doesn't exists."));
        fillDataProduct(product,p);
        return product;
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
                .map((p) ->
                    new ProductDTO(p.getName(),p.getDescription(),p.getPrice(),p.getPublicPrice(),p.isHasDiscount(),p.getPiecesBox())
                ).collect(Collectors.toList());
    }
}
