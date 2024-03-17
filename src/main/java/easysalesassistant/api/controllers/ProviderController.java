package easysalesassistant.api.controllers;

import easysalesassistant.api.dto.provider.ProviderDTO;
import easysalesassistant.api.services.IProviderService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/provider",produces = {MediaType.APPLICATION_JSON_VALUE})
public class ProviderController {
    IProviderService providerService;

    ProviderController(IProviderService providerService){
        this.providerService = providerService;
    }

    @GetMapping (value = {"/{id}"})
    public ProviderDTO getProvider(@PathVariable(value = "id") Long id){
        return providerService.getProviderById(id);
    }

    @PostMapping(value = {"","/"})
    public ProviderDTO saveProvider(@RequestBody ProviderDTO provider){
        return providerService.saveProvider(provider);
    }

    @PatchMapping(value = {"/{id}"})
    public ProviderDTO patchProvider(@PathVariable(value = "id") Long id,@RequestBody ProviderDTO provider){
        return providerService.patchProvider(id,provider);
    }

    @DeleteMapping(value = {"/{id}"})
    public void deleteProvider(@PathVariable(value = "id") Long id){
        providerService.deleteProvider(id);
    }
}
