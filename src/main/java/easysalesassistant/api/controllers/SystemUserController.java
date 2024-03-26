package easysalesassistant.api.controllers;

import easysalesassistant.api.dto.systemuser.SystemUserDTO;
import easysalesassistant.api.services.ISystemUserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/employee",produces = MediaType.APPLICATION_JSON_VALUE)
public class SystemUserController {
    ISystemUserService userService;

    SystemUserController(ISystemUserService userService){
        this.userService = userService;
    }

    @PostMapping(value = {"","/"})
    public SystemUserDTO save(@RequestBody SystemUserDTO systemUserDTO){
        return userService.saveUser(systemUserDTO);
    }

    @PatchMapping(value = "/{idUser}")
    public SystemUserDTO patch(@PathVariable(value = "idUser") Long idUser,@RequestBody SystemUserDTO systemUserDTO){
        return userService.patchUser(idUser,systemUserDTO);
    }

    @DeleteMapping(value = "/{idUser}")
    public void deleteUser(@PathVariable(value = "idUser") Long idUser){
        userService.deleteUser(idUser);
    }
}
