package easysalesassistant.api.services;

import easysalesassistant.api.dao.ICategoryDAO;
import easysalesassistant.api.dao.IStoreDAO;
import easysalesassistant.api.dao.ITenantDAO;
import easysalesassistant.api.dto.StoreDTO;
import easysalesassistant.api.entity.Category;
import easysalesassistant.api.entity.Store;
import easysalesassistant.api.entity.Tenant;
import easysalesassistant.api.exceptions.NotFoundStoreException;
import easysalesassistant.api.exceptions.NotFoundTenantException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IStoreServiceImp implements IStoreService {
    IStoreDAO storeDAO;
    ITenantDAO tenantDAO;

    public IStoreServiceImp(IStoreDAO storeDAO, ITenantDAO tenantDAO){
        this.storeDAO = storeDAO;
        this.tenantDAO = tenantDAO;
    }

    @Override
    public StoreDTO saveStore(StoreDTO storeDTO) {
        Tenant tenant = tenantDAO.findById(storeDTO.getIdTenant()).orElseThrow(() -> new NotFoundTenantException(400,"Tentant's ID doesn't exists."));
        Store store = new Store();
        store.setDescription(storeDTO.getDescription());
        store.setIdTenant(tenant);
        storeDAO.save(store);

        return storeDTO;
    }

    @Override
    public StoreDTO updateStore(StoreDTO storeDTO, Long idStore) {
        tenantDAO.findById(storeDTO.getIdTenant()).orElseThrow(() -> new NotFoundTenantException(400,"Tentant's ID doesn't exists."));
        Store store = storeDAO.findById(idStore).orElseThrow(() -> new NotFoundStoreException(400,"Store's ID doesn't exists."));
        store.setDescription(storeDTO.getDescription());
        storeDAO.save(store);

        return storeDTO;
    }
}
