package easysalesassistant.api.mappers.qualifiers;

import easysalesassistant.api.dao.IProductDAO;
import easysalesassistant.api.entity.Category;
import easysalesassistant.api.entity.Product;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
public class ProductQualifier {

    IProductDAO productDAO;

    ProductQualifier(IProductDAO productDAO){
        this.productDAO = productDAO;
    }

    @Named("longToProduct")
    public Product longToProduct(Long id) {
        return productDAO.findById(id).orElse(null);
    }
}
