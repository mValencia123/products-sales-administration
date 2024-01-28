package easysalesassistant.api.services;

import easysalesassistant.api.dao.IProductDAO;
import easysalesassistant.api.dto.ProductDTO;
import easysalesassistant.api.entity.Product;
import easysalesassistant.api.exceptions.ProductDoesntExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private IProductDAO productDAO;

    public ProductDTO getProduct(Long id){
        Product product = productDAO.findById(id)
                .orElseThrow(() -> new ProductDoesntExistsException(404,"Product doesn't exists with this Id."));

        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setPublicPrice(product.getPublicPrice());
        productDTO.setHasDiscount(product.isHasDiscount());
        productDTO.setPiecesBox(product.getPiecesBox());

        return productDTO;
    }

    public ProductDTO saveProduct(ProductDTO product){
        Product p = new Product();
        p.setDescription(product.getDescription());
        p.setName(product.getName());
        p.setPrice(product.getPrice());
        p.setPublicPrice(product.getPublicPrice());
        p.setHasDiscount(product.isHasDiscount());
        p.setPiecesBox(p.getPiecesBox());
        productDAO.save(p);
        return product;
    }

    public Product updateProduct(Product product){
        return productDAO.save(product);
    }

    public void deleteProduct(Long id){
        if(!productDAO.existsById(id)){
            new Exception("No existe el producto.");
        }
        productDAO.deleteById(id);
    }

    public List<Product> getProducts() {
        return (List<Product>) productDAO.findAll();
    }
}
