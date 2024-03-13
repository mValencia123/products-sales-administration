package easysalesassistant.api.services;

import easysalesassistant.api.dao.IBranchDAO;
import easysalesassistant.api.dao.IStoreDAO;
import easysalesassistant.api.dto.BranchDTO;
import easysalesassistant.api.dto.BranchGetDTO;
import easysalesassistant.api.entity.Branch;
import easysalesassistant.api.entity.Store;
import easysalesassistant.api.exceptions.NotFoundBranchException;
import easysalesassistant.api.exceptions.NotFoundStoreException;
import easysalesassistant.api.exceptions.NotFoundTenantException;
import org.springframework.stereotype.Service;

@Service
public class IBranchServiceImp implements IBranchService{

    IBranchDAO branchDAO;
    IStoreDAO storeDAO;

    IBranchServiceImp(IBranchDAO branchDAO, IStoreDAO storeDAO){
        this.branchDAO = branchDAO;
        this.storeDAO = storeDAO;
    }

    @Override
    public void saveBranch(BranchDTO branchDTO) {
        Branch branch = new Branch();
        if(branchDTO.getIdStore() == null){
            Store store = new Store();
            store.setDescription(branchDTO.getDescription());
            store = storeDAO.save(store);
            branch.setIdStore(store);
        }
        branch.setDescription(branchDTO.getDescription());
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
