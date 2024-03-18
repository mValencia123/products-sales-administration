package easysalesassistant.api.controllers;

import easysalesassistant.api.dto.systemuser.SystemUserDTO;
import easysalesassistant.api.services.ISystemUserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
