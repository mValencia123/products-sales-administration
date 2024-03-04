package easysalesassistant.api.services;

import easysalesassistant.api.dto.BranchDTO;
import easysalesassistant.api.dto.BranchGetDTO;

public interface IBranchService {
    void saveBranch(BranchDTO branchDTO);

    BranchGetDTO getBranchById(Long idBranch);

    BranchGetDTO updateBranch(Long idBranch, BranchDTO branch);
}
