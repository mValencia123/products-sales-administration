package easysalesassistant.api.mappers;

import easysalesassistant.api.dto.systemuser.SystemUserDTO;
import easysalesassistant.api.entity.Branch;
import easysalesassistant.api.entity.SystemUser;
import easysalesassistant.api.mappers.qualifiers.BranchQualifier;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring",uses = {BranchQualifier.class})
public interface SystemUserMapper {
    SystemUserDTO systemUserToSystemUserDTO(SystemUser systemUser);
    @InheritInverseConfiguration
    @Mapping(source = "idBranch",target = "idBranch",qualifiedByName = {"branchDTOToBranch"})
    SystemUser systemUserDTOToSystemUser(SystemUserDTO systemUserDTO);
    default Long branchToLong(Branch value){
        return value.getId();
    }
}
