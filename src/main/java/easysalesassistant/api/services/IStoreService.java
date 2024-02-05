package easysalesassistant.api.services;

import easysalesassistant.api.dto.StoreDTO;

public interface IStoreService {
    StoreDTO saveStore(StoreDTO storeDTO);
    StoreDTO updateStore(StoreDTO storeDTO, Long idStore);
}
