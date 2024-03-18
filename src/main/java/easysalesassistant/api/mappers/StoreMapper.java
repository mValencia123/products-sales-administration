package easysalesassistant.api.mappers;

import easysalesassistant.api.dto.address.AddressDTO;
import easysalesassistant.api.dto.category.CategoryDTO;
import easysalesassistant.api.dto.store.StoreDTO;
import easysalesassistant.api.entity.Category;
import easysalesassistant.api.entity.Store;
import easysalesassistant.api.mappers.qualifiers.AddressQualifier;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring",uses = {AddressQualifier.class})
public interface StoreMapper {

    StoreDTO storeToStoreDTO(Store store);
    @InheritInverseConfiguration
    @Mapping(source = "address",target = "idAddress",qualifiedByName = "addressDTOToAddress")
    Store storeDTOToStore(StoreDTO storeDTO);
}
