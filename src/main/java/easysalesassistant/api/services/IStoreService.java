package easysalesassistant.api.services;

import easysalesassistant.api.dto.store.StoreDTO;
import easysalesassistant.api.entity.Store;

import java.util.List;

public interface IStoreService {
    StoreDTO saveStore(StoreDTO storeDTO);
    StoreDTO updateStore(Long idStore,StoreDTO storeDTO);
    Store existsStoreById(Long idStore);
    StoreDTO getStoreById(Long idStore);
    void deleteStoreById(Long idStore);
    List<StoreDTO> getStores();
}
