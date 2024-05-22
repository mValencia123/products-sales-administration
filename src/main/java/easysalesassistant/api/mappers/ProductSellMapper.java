package easysalesassistant.api.mappers;

import easysalesassistant.api.dto.sell.ProductSellDTO;
import easysalesassistant.api.entity.ProductSell;
import easysalesassistant.api.mappers.qualifiers.ProductQualifier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {ProductQualifier.class})
public interface ProductSellMapper {
    @Mapping(qualifiedByName = "longToProduct",target = "idProduct",source = "idProduct")
    ProductSell productSellDTOToProductSell(ProductSellDTO productSellDTO);
}
