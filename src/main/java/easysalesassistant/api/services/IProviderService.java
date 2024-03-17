package easysalesassistant.api.services;

import easysalesassistant.api.dto.provider.ProviderDTO;
import easysalesassistant.api.entity.Provider;

public interface IProviderService {
    ProviderDTO saveProvider(ProviderDTO providerDTO);
    ProviderDTO getProviderById(Long id);
    ProviderDTO patchProvider(Long id, ProviderDTO provider);
    void deleteProvider(Long id);
    Provider existsProviderById(Long id);
}
