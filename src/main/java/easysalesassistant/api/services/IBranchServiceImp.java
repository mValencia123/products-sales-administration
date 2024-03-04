package easysalesassistant.api.services;

import easysalesassistant.api.dao.IBranchDAO;
import easysalesassistant.api.dao.IStoreDAO;
import easysalesassistant.api.dao.ITenantDAO;
import easysalesassistant.api.dto.BranchDTO;
import easysalesassistant.api.dto.BranchGetDTO;
import easysalesassistant.api.entity.Branch;
import easysalesassistant.api.entity.Store;
import easysalesassistant.api.entity.Tenant;
import easysalesassistant.api.exceptions.NotFoundBranchException;
import easysalesassistant.api.exceptions.NotFoundStoreException;
import easysalesassistant.api.exceptions.NotFoundTenantException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IBranchServiceImp implements IBranchService{

    IBranchDAO branchDAO;
    IStoreDAO storeDAO;
    ITenantDAO tenantDAO;

    IBranchServiceImp(IBranchDAO branchDAO, IStoreDAO storeDAO, ITenantDAO tenantDAO){
        this.branchDAO = branchDAO;
        this.storeDAO = storeDAO;
        this.tenantDAO = tenantDAO;
    }

    @Override
    public void saveBranch(BranchDTO branchDTO) {
        Tenant tenant = tenantDAO.findById(branchDTO.getIdTenant()).orElseThrow(() -> new NotFoundTenantException(404,"Tenant's ID doesnt exists."));
        Branch branch = new Branch();
        if(branchDTO.getIdStore() == null){
            Store store = new Store();
            store.setDescription(branchDTO.getDescription());
            store.setIdTenant(tenant);
            store = storeDAO.save(store);
            branch.setIdStore(store);
        }
        branch.setDescription(branchDTO.getDescription());
        branch.setIdTenant(tenant);
        branchDAO.save(branch);
    }

    @Override
    public BranchGetDTO getBranchById(Long idBranch) {
        Branch branch = branchDAO.findById(idBranch).orElseThrow(() -> new NotFoundBranchException(404,"Branch's ID doesn't exists."));
        BranchGetDTO branchGetDto = new BranchGetDTO();
        branchGetDto.setIdBranch(branch.getId());
        branchGetDto.setDescription(branch.getDescription());
        branchGetDto.setIdStore(branch.getIdStore().getId());
        branchGetDto.setDescriptionStore(branch.getIdStore().getDescription());
        return branchGetDto;
    }

    @Override
    public BranchGetDTO updateBranch(Long idBranch, BranchDTO branchDTO) {
        Branch branch = branchDAO.findById(idBranch).orElseThrow(() -> new NotFoundBranchException(404,"Branch's ID doesn't exists."));
        Store store = storeDAO.findById(branchDTO.getIdStore()).orElseThrow(() -> new NotFoundStoreException(404,"Store's ID doesn't exists."));

        branch.setDescription(branchDTO.getDescription());
        branch.setIdStore(store);
        branchDAO.save(branch);

        BranchGetDTO branchGetDto = new BranchGetDTO();
        branchGetDto.setIdBranch(branch.getId());
        branchGetDto.setDescription(branch.getDescription());
        branchGetDto.setIdStore(store.getId());
        branchGetDto.setDescriptionStore(store.getDescription());
        return branchGetDto;
    }
}
