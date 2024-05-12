package easysalesassistant.api.services;

import easysalesassistant.api.dto.provider.ProviderDTO;
import easysalesassistant.api.entity.Provider;

import java.util.List;

public interface IProviderService {
    ProviderDTO saveProvider(ProviderDTO providerDTO);
    ProviderDTO getProviderDTOById(Long id);
    ProviderDTO patchProvider(Long id, ProviderDTO provider);
    void deleteProvider(Long id);
    Provider existsProviderById(Long id);
    List<ProviderDTO> getProviders();
}
