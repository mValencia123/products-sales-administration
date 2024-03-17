package easysalesassistant.api.mappers;

import easysalesassistant.api.dto.product.ProductDTO;
import easysalesassistant.api.dto.provider.ProviderDTO;
import easysalesassistant.api.entity.Product;
import easysalesassistant.api.entity.Provider;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProviderMapper {
    ProviderMapper INSTANCE = Mappers.getMapper( ProviderMapper.class );

    ProviderDTO providerToProviderDTO(Provider provider);
    @InheritInverseConfiguration
    Provider providerDTOToProvider(ProviderDTO providerDTO);
}
