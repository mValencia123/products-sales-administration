package easysalesassistant.api.services;

import easysalesassistant.api.dao.IStoreDAO;
import easysalesassistant.api.entity.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

    @Autowired
    IStoreDAO storeDAO;

    public Store save(Store store) {
        return storeDAO.save(store);
    }
}
