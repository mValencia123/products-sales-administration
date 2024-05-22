package easysalesassistant.api.mappers;

import easysalesassistant.api.dto.sell.SellDTO;
import easysalesassistant.api.entity.Sell;
import easysalesassistant.api.mappers.qualifiers.BranchQualifier;
import easysalesassistant.api.mappers.qualifiers.SystemUserQualifier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {SystemUserQualifier.class, BranchQualifier.class})
public interface SellMapper {
    @Mapping(qualifiedByName = "longToSystemUser",target = "idUserSold",source = "idUserSold")
    @Mapping(qualifiedByName = "longToSystemUser",target = "idCustomer",source = "idCustomer")
    @Mapping(qualifiedByName = "longToSystemUser",target = "idUserDeleted",source = "idUserDeleted")
    @Mapping(qualifiedByName = "branchDTOToBranch",target = "idBranch",source = "idBranch")
    Sell sellDTOToSell(SellDTO sellDTO);
}
