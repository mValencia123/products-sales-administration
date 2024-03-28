package easysalesassistant.api.services;

import easysalesassistant.api.dao.IStoreDAO;
import easysalesassistant.api.dto.store.StoreDTO;
import easysalesassistant.api.entity.Address;
import easysalesassistant.api.entity.Store;
import easysalesassistant.api.entity.SystemUser;
import easysalesassistant.api.exceptions.NotFoundStoreException;
import easysalesassistant.api.mappers.StoreMapper;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class IStoreServiceImp implements IStoreService {
    IStoreDAO storeDAO;
    IAddressService addressService;
    StoreMapper storeMapper;

    ISystemUserService userService;

    public IStoreServiceImp(IStoreDAO storeDAO,IAddressService addressService,StoreMapper storeMapper,ISystemUserService userService){
        this.storeDAO = storeDAO;
        this.addressService = addressService;
        this.storeMapper = storeMapper;
        this.userService = userService;
    }

    @Override
    public StoreDTO saveStore(StoreDTO storeDTO) {
        Store store = new Store();
        Address address = addressService.createAddress(storeDTO.getAddress());
        SystemUser userCreated = userService.getUserByContext();

        store.setCreatedAt(storeDTO.getCreatedAt());
        store.setDescription(storeDTO.getDescription());
        store.setPhoneNumber(storeDTO.getPhoneNumber());
        store.setIdAddress(address);
        store.setIdUserCreated(userCreated);
        store.setCreatedAt(new Date());

        store = storeDAO.save(store);
        storeDTO.getAddress().setId(store.getIdAddress().getId());
        storeDTO.setId(store.getId());
        return storeDTO;
    }

    @Override
    public StoreDTO updateStore(Long idStore,StoreDTO storeDTO) {
        Store store = existsStoreById(idStore);
        Address address = addressService.updateAddress(storeDTO.getAddress().getId(),storeDTO.getAddress());

        store.setDescription(storeDTO.getDescription());
        store.setPhoneNumber(storeDTO.getPhoneNumber());
        store.setIdAddress(address);

        storeDAO.save(store);

        return storeDTO;
    }

    @Override
    public Store existsStoreById(Long id){
        return storeDAO.findById(id).orElseThrow(() -> new NotFoundStoreException(404,"Store's ID doesn't exists."));
    }

    @Override
    public StoreDTO getStoreById(Long idStore) {
        return this.storeMapper.storeToStoreDTO(existsStoreById(idStore));
    }

    @Override
    public void deleteStoreById(Long idStore) {
        Store store = existsStoreById(idStore);
        SystemUser userDeleted = userService.getUserByContext();

        store.setDeleted(true);
        store.setDeletedAt(new Date());
        store.setIdUserDeleted(userDeleted);

        storeDAO.save(store);
    }
}
