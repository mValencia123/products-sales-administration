package easysalesassistant.api.mappers;

import easysalesassistant.api.dto.product.ProductDTO;
import easysalesassistant.api.entity.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper( ProductMapper.class );

    ProductDTO productToProductDTO(Product product);
    @InheritInverseConfiguration
    Product productDTOToProduct(ProductDTO productDTO);
}
