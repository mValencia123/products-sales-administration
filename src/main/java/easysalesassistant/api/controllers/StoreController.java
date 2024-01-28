package easysalesassistant.api.controllers;

import easysalesassistant.api.entity.Store;
import easysalesassistant.api.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/store",produces = {MediaType.APPLICATION_JSON_VALUE})
public class StoreController {

    @Autowired
    StoreService storeService;

    @PostMapping(value = {"/",""})
    public Store save(@RequestBody Store store){
        return storeService.save(store);
    }
}
