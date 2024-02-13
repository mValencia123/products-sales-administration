package easysalesassistant.api.controllers;

import easysalesassistant.api.dao.IProductDAO;
import easysalesassistant.api.dto.ProductDTO;
import easysalesassistant.api.entity.Product;
import easysalesassistant.api.services.IProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/products",produces = {MediaType.APPLICATION_JSON_VALUE})
public class ProductController {

    private IProductServiceImp productService;

    ProductController(IProductServiceImp productService){
        this.productService = productService;
    }

    @GetMapping(value = {"/{id}"})
    public ProductDTO getProduct(@PathVariable(value = "id") Long id){
        return productService.getProduct(id);
    }

    @PostMapping(value = {"/",""})
    public ProductDTO saveProduct(@RequestBody ProductDTO product){
        return productService.saveProduct(product);
    }

    @PatchMapping(value = {"/{id}"})
    public ProductDTO updateProduct(ProductDTO product,@PathVariable(value = "id") Long id){
        return productService.updateProduct(id,product);
    }

    @DeleteMapping(value = {"/{id}"})
    public void deleteProduct(@PathVariable(value = "id") Long id){
        productService.deleteProduct(id);
    }

    @GetMapping(value = {"/",""})
    public List<ProductDTO> getProducts(){
        return productService.getProducts();
    }
}
