package easysalesassistant.api.controllers;

import easysalesassistant.api.dto.store.StoreDTO;
import easysalesassistant.api.services.IStoreService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = {"/{idStore}"})
    public StoreDTO getStore(@PathVariable(value = "idStore") Long idStore){
        return storeService.getStoreById(idStore);
    }

    @PatchMapping(value = {"/{idStore}"})
    public StoreDTO updateStore(@PathVariable(value = "idStore") Long idStore,StoreDTO storeDTO){
        return storeService.updateStore(idStore,storeDTO);
    }
    @DeleteMapping(value = {"/{idStore}"})
    public void deleteStore(@PathVariable(value = "idStore") Long idStore){
        storeService.deleteStoreById(idStore);
    }
}
