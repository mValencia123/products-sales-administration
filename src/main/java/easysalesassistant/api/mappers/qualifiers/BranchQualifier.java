package easysalesassistant.api.mappers.qualifiers;

import easysalesassistant.api.dao.IAddressDAO;
import easysalesassistant.api.dao.IBranchDAO;
import easysalesassistant.api.dto.address.AddressDTO;
import easysalesassistant.api.entity.Address;
import easysalesassistant.api.entity.Branch;
import org.mapstruct.Named;

public class BranchQualifier {
    IBranchDAO branchDAO;

    BranchQualifier(IBranchDAO branchDAO){
        this.branchDAO = branchDAO;
    }

    @Named("branchDTOToBranch")
    public Branch branchDTOToBranch(Long idBranch) {
        return branchDAO.findById(idBranch).orElse(null);
    }
}
