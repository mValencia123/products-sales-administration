package easysalesassistant.api.services;

import easysalesassistant.api.dto.branch.BranchDTO;
import easysalesassistant.api.dto.branch.BranchGetDTO;
import easysalesassistant.api.entity.Branch;

public interface IBranchService {
    BranchDTO saveBranch(BranchDTO branchDTO);

    BranchGetDTO getBranchById(Long idBranch);

    BranchGetDTO updateBranch(Long idBranch, BranchDTO branch);

    void deleteBranch(Long idBranch);

    Branch existsBranchById(Long idBranch);
}
