package easysalesassistant.api.controllers;

import easysalesassistant.api.dao.IProductDAO;
import easysalesassistant.api.dto.ProductDTO;
import easysalesassistant.api.entity.Product;
import easysalesassistant.api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/products",produces = {MediaType.APPLICATION_JSON_VALUE})
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = {"/{id}"})
    public ProductDTO getProduct(@PathVariable(value = "id") Long id){
        return productService.getProduct(id);
    }

    @PostMapping(value = {"/",""})
    public ProductDTO saveProduct(@RequestBody ProductDTO product){
        return productService.saveProduct(product);
    }

    @PatchMapping(value = {"/{id}"})
    public Product updateProduct(Product product){
        return productService.updateProduct(product);
    }

    @DeleteMapping(value = {"/{id}"})
    public void deleteProduct(@PathVariable(value = "id") Long id){
        productService.deleteProduct(id);
    }

    @GetMapping(value = {"/",""})
    public List<Product> getProducts(){
        return productService.getProducts();
    }
}
