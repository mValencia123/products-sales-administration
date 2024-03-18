package easysalesassistant.api.services;

import easysalesassistant.api.dao.IStoreDAO;
import easysalesassistant.api.dto.store.StoreDTO;
import easysalesassistant.api.entity.Address;
import easysalesassistant.api.entity.Store;
import easysalesassistant.api.exceptions.NotFoundStoreException;
import org.springframework.stereotype.Service;

@Service
public class IStoreServiceImp implements IStoreService {
    IStoreDAO storeDAO;
    IAddressService addressService;

    public IStoreServiceImp(IStoreDAO storeDAO,IAddressService addressService){
        this.storeDAO = storeDAO;
        this.addressService = addressService;
    }

    @Override
    public StoreDTO saveStore(StoreDTO storeDTO) {
        Store store = new Store();
        Address address = addressService.createAddress(storeDTO.getAddress());
        store.setCreatedAt(storeDTO.getCreatedAt());
        store.setDescription(storeDTO.getDescription());
        store.setPhoneNumber(storeDTO.getPhoneNumber());
        store.setIdAddress(address);
        store = storeDAO.save(store);
        storeDTO.getAddress().setId(store.getIdAddress().getId());
        storeDTO.setId(store.getId());
        return storeDTO;
    }

    @Override
    public StoreDTO updateStore(Long idStore,StoreDTO storeDTO) {
        Store store = existsStoreById(idStore);
        store.setDescription(storeDTO.getDescription());
        storeDAO.save(store);

        return storeDTO;
    }

    @Override
    public Store existsStoreById(Long id){
        return storeDAO.findById(id).orElseThrow(() -> new NotFoundStoreException(404,"Store's ID doesn't exists."));
    }
}
