package easysalesassistant.api.services;

import easysalesassistant.api.dao.IBranchDAO;
import easysalesassistant.api.dao.IStoreDAO;
import easysalesassistant.api.dto.branch.BranchDTO;
import easysalesassistant.api.dto.branch.BranchGetDTO;
import easysalesassistant.api.dto.store.StoreDTO;
import easysalesassistant.api.entity.Branch;
import easysalesassistant.api.entity.Store;
import easysalesassistant.api.entity.SystemUser;
import easysalesassistant.api.exceptions.NotFoundBranchException;
import easysalesassistant.api.exceptions.NotFoundStoreException;
import easysalesassistant.api.mappers.StoreMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class IBranchServiceImp implements IBranchService{

    IBranchDAO branchDAO;
    IStoreDAO storeDAO;
    ISystemUserService userService;
    IStoreService storeService;
    StoreMapper storeMapper;

    IBranchServiceImp(IBranchDAO branchDAO, IStoreDAO storeDAO,ISystemUserService userService,IStoreService storeService,StoreMapper storeMapper){
        this.branchDAO = branchDAO;
        this.storeDAO = storeDAO;
        this.userService = userService;
        this.storeService = storeService;
        this.storeMapper = storeMapper;
    }

    @Override
    @Transactional
    public BranchDTO saveBranch(BranchDTO branchDTO) {
        Branch branch = new Branch();
        SystemUser systemUser = userService.getUserByContext();
        if(branchDTO.getIdStore() == null){
            StoreDTO storeDTO = new StoreDTO();

            storeDTO.setDescription(branch.getDescription());
            storeDTO.setCreatedAt(branchDTO.getCreatedAt());
            storeDTO.setPhoneNumber(branch.getPhoneNumber());
            storeDTO.setAddress(branchDTO.getAddress());
            storeDTO = storeService.saveStore(storeDTO);

            Store store = storeMapper.storeDTOToStore(storeDTO);
            branch.setIdStore(store);
        }
        branch.setIdUserCreated(systemUser);
        branch.setCreatedAt(branchDTO.getCreatedAt());
        branch.setDescription(branchDTO.getDescription());
        branchDAO.save(branch);
        return branchDTO;
    }

    @Override
    public BranchGetDTO getBranchById(Long idBranch) {
        Branch branch = existsBranchById(idBranch);
        BranchGetDTO branchGetDto = new BranchGetDTO();
        branchGetDto.setIdBranch(branch.getId());
        branchGetDto.setDescription(branch.getDescription());
        branchGetDto.setIdStore(branch.getIdStore().getId());
        branchGetDto.setDescriptionStore(branch.getIdStore().getDescription());
        return branchGetDto;
    }

    @Override
    public BranchGetDTO updateBranch(Long idBranch, BranchDTO branchDTO) {
        Branch branch = existsBranchById(idBranch);
        Store store = storeService.existsStoreById(branchDTO.getIdStore());

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

    @Override
    public void deleteBranch(Long idBranch) {
        Branch branch = existsBranchById(idBranch);
        SystemUser systemUser = userService.getUserByContext();

        branch.setDeleted(true);
        branch.setDeletedAt(new Date());
        branch.setIdUserDeleted(systemUser);

        branchDAO.save(branch);
    }

    public Branch existsBranchById(Long id){
        return branchDAO.findById(id).orElseThrow(() -> new NotFoundBranchException(404,"Branch's ID doesn't exists."));
    }
}
