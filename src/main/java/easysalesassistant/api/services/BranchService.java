package easysalesassistant.api.services;

import easysalesassistant.api.dao.IBranchDAO;
import easysalesassistant.api.dao.IStoreDAO;
import easysalesassistant.api.entity.Branch;
import easysalesassistant.api.entity.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BranchService {

    @Autowired
    IBranchDAO branchDAO;

    @Autowired
    IStoreDAO storeDAO;

    public Branch saveBranch(Branch branch) {
        if(branch.getIdStore() == null){
            Store store = new Store();
            store.setDescription(branch.getDescription());
            store.setIdTenant(branch.getIdTenant());
            store = storeDAO.save(store);
            branch.setIdStore(store);
        }
        return branchDAO.save(branch);
    }
}
