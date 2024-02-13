package easysalesassistant.api.services;

import easysalesassistant.api.dto.ProductDTO;
import easysalesassistant.api.entity.Product;

import java.util.List;

public interface IProductService {
    ProductDTO getProduct(Long id);
    ProductDTO saveProduct(ProductDTO product);
    ProductDTO updateProduct(Long id, ProductDTO product);
    void deleteProduct(Long id);
    List<ProductDTO> getProducts();
}
