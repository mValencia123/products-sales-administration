package easysalesassistant.api.controllers;

import easysalesassistant.api.dto.StoreDTO;
import easysalesassistant.api.entity.Store;
import easysalesassistant.api.services.IStoreServiceImp;
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
    IStoreServiceImp storeService;

    @PostMapping(value = {"/",""})
    public StoreDTO save(@RequestBody StoreDTO store){
        return storeService.saveStore(store);
    }
}
