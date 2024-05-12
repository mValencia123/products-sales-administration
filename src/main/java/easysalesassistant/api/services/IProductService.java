package easysalesassistant.api.services;

import easysalesassistant.api.dto.product.ProductDTO;
import easysalesassistant.api.entity.Provider;
import easysalesassistant.api.entity.Store;

import java.util.List;

public interface IProductService {
    ProductDTO getProduct(Long id);
    ProductDTO saveProduct(ProductDTO product);
    ProductDTO updateProduct(Long id, ProductDTO product);
    void deleteProduct(Long id);
    List<ProductDTO> getProducts();
    boolean productHasStock(Long id, Store idStore, int amount);
}
