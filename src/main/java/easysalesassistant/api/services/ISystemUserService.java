package easysalesassistant.api.services;

import easysalesassistant.api.dto.SystemUserDTO;

public interface ISystemUserService {
    SystemUserDTO saveUser(SystemUserDTO systemUserDTO);
}
