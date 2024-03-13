package easysalesassistant.api.services;

import easysalesassistant.api.dao.IStoreDAO;
import easysalesassistant.api.dto.StoreDTO;
import easysalesassistant.api.entity.Store;
import easysalesassistant.api.exceptions.NotFoundStoreException;
import easysalesassistant.api.exceptions.NotFoundTenantException;
import org.springframework.stereotype.Service;

@Service
public class IStoreServiceImp implements IStoreService {
    IStoreDAO storeDAO;

    public IStoreServiceImp(IStoreDAO storeDAO){
        this.storeDAO = storeDAO;
    }

    @Override
    public StoreDTO saveStore(StoreDTO storeDTO) {
        Store store = new Store();
        store.setDescription(storeDTO.getDescription());
        storeDAO.save(store);

        return storeDTO;
    }

    @Override
    public StoreDTO updateStore(StoreDTO storeDTO, Long idStore) {
        Store store = storeDAO.findById(idStore).orElseThrow(() -> new NotFoundStoreException(400,"Store's ID doesn't exists."));
        store.setDescription(storeDTO.getDescription());
        storeDAO.save(store);

        return storeDTO;
    }
}
