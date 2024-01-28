package easysalesassistant.api.controllers;

import easysalesassistant.api.entity.Provider;
import easysalesassistant.api.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/provider",produces = {MediaType.APPLICATION_JSON_VALUE})
public class ProviderController {

    @Autowired
    ProviderService providerService;

    @PostMapping(value = {"","/"})
    public Provider save(@RequestBody Provider provider){
        return providerService.save(provider);
    }
}
