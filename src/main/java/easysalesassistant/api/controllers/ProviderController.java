package easysalesassistant.api.controllers;

import easysalesassistant.api.dto.ProviderDTO;
import easysalesassistant.api.entity.Provider;
import easysalesassistant.api.services.IProviderService;
import easysalesassistant.api.services.IProviderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/provider",produces = {MediaType.APPLICATION_JSON_VALUE})
public class ProviderController {
    IProviderService providerService;

    ProviderController(IProviderService providerService){
        this.providerService = providerService;
    }

    @PostMapping(value = {"","/"})
    public ProviderDTO save(@RequestBody ProviderDTO provider){
        return providerService.saveProvider(provider);
    }
}
