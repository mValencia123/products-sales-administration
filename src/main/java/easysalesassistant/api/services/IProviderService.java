package easysalesassistant.api.services;

import easysalesassistant.api.dto.ProviderDTO;

public interface IProviderService {
    ProviderDTO saveProvider(ProviderDTO providerDTO);
    ProviderDTO updaterProvider(ProviderDTO providerDTO, Long idProvider);
}
