package easysalesassistant.api.controllers;

import easysalesassistant.api.dto.systemuser.SystemUserDTO;
import easysalesassistant.api.dto.systemuser.UserDTO;
import easysalesassistant.api.enums.TypeUser;
import easysalesassistant.api.services.ISystemUserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users",produces = MediaType.APPLICATION_JSON_VALUE)
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

    @GetMapping(value = {"/{idUser}"})
    public SystemUserDTO getUser(@PathVariable(value = "idUser") Long idUser){
        return userService.getUser(idUser);
    }

    @GetMapping(value = {"/employees"})
    public List<UserDTO> getAllEmployees(){
        return userService.getAllUserByType(TypeUser.EMPLOYEE);
    }

    @GetMapping(value = {"/customer"})
    public List<UserDTO> getAllCustomers(){
        return userService.getAllUserByType(TypeUser.CUSTOMER);
    }
}
