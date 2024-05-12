package easysalesassistant.api.mappers;


import easysalesassistant.api.dto.transaction.ProductTransactionDTO;
import easysalesassistant.api.entity.ProductTransaction;
import easysalesassistant.api.mappers.qualifiers.ProductQualifier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring",uses = {ProductQualifier.class})
public interface ProductTransactionMapper {

    @Mapping(source = "idProduct",target = "idProduct",qualifiedByName = "longToProduct")
    ProductTransaction productTransactionDTOToProductTransaction(ProductTransactionDTO productTransactionDTO);
}
