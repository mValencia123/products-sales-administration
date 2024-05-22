package easysalesassistant.api.mappers.qualifiers;

import easysalesassistant.api.entity.SystemUser;
import easysalesassistant.api.services.ISystemUserService;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
public class SystemUserQualifier {
    ISystemUserService systemUserService;
    public SystemUserQualifier(ISystemUserService systemUserService){
        this.systemUserService = systemUserService;
    }

    @Named("longToSystemUser")
    public SystemUser longToSystemUser(Long idUser){
        return systemUserService.getSystemUserById(idUser);
    }
}
