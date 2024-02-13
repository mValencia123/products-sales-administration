package easysalesassistant.api.controllers;

import easysalesassistant.api.dto.SystemUserDTO;
import easysalesassistant.api.entity.SystemUser;
import easysalesassistant.api.services.ISystemUserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/employee",produces = MediaType.APPLICATION_JSON_VALUE)
public class SystemUserController {

    @Autowired
    ISystemUserServiceImp ISystemUserServiceImp;

    @PostMapping(value = {"","/"})
    public SystemUserDTO save(@RequestBody SystemUserDTO systemUserDTO){
        return ISystemUserServiceImp.saveUser(systemUserDTO);
    }
}
