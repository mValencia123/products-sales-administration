package easysalesassistant.api.controllers;

import easysalesassistant.api.dto.store.StoreDTO;
import easysalesassistant.api.services.IStoreService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/store",produces = {MediaType.APPLICATION_JSON_VALUE})
public class StoreController {
    IStoreService storeService;

    StoreController(IStoreService storeService){
        this.storeService = storeService;
    }

    @PostMapping(value = {"/",""})
    public StoreDTO save(@RequestBody StoreDTO store){
        return storeService.saveStore(store);
    }
}
