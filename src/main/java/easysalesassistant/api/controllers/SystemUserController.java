package easysalesassistant.api.controllers;

import easysalesassistant.api.entity.SystemUser;
import easysalesassistant.api.services.UserService;
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
    UserService userService;

    @PostMapping(value = {"","/"})
    public SystemUser save(@RequestBody SystemUser employee){
        return userService.save(employee);
    }
}
