package easysalesassistant.api.services;

import easysalesassistant.api.dto.systemuser.SystemUserDTO;
import easysalesassistant.api.entity.SystemUser;

public interface ISystemUserService {
    SystemUserDTO saveUser(SystemUserDTO systemUserDTO);
    SystemUser getUserByContext();
}
