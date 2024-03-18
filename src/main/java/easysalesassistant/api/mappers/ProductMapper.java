package easysalesassistant.api.mappers;

import easysalesassistant.api.dto.product.ProductDTO;
import easysalesassistant.api.entity.Category;
import easysalesassistant.api.entity.Product;
import easysalesassistant.api.entity.Provider;
import easysalesassistant.api.mappers.qualifiers.CategoryQualifier;
import easysalesassistant.api.mappers.qualifiers.ProviderQualifier;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring",uses = {CategoryQualifier.class, ProviderQualifier.class})
public interface ProductMapper {

    ProductDTO productToProductDTO(Product product);
    @InheritInverseConfiguration
    @Mapping(source = "idCategory", target = "idCategory", qualifiedByName = "longToCategory")
    @Mapping(source = "idProvider", target = "idProvider", qualifiedByName = "longToProvider")
    Product productDTOToProduct(ProductDTO productDTO);
    default Long fromProviderToLong(Provider value){
        return value.getId();
    }
    default Long fromCategoryToLong(Category value){
        return value.getId();
    }
}
