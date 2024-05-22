package easysalesassistant.api.services;

import easysalesassistant.api.dto.systemuser.SystemUserDTO;
import easysalesassistant.api.dto.systemuser.UserDTO;
import easysalesassistant.api.entity.SystemUser;
import easysalesassistant.api.enums.TypeUser;

import java.util.List;

public interface ISystemUserService {
    SystemUserDTO saveUser(SystemUserDTO systemUserDTO);
    SystemUser getUserByContext();
    SystemUserDTO patchUser(Long idUser,SystemUserDTO systemUserDTO);
    SystemUserDTO getUser(Long idUser);
    void deleteUser(Long idUser);
    SystemUser existsSystemUser(Long idUser);
    List<UserDTO> getAllUserByType(TypeUser typeUser);
    SystemUser getSystemUserById(Long idUser);
}
