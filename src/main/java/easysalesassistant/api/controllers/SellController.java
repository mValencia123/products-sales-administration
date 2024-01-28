package easysalesassistant.api.controllers;

import easysalesassistant.api.entity.Sell;
import easysalesassistant.api.services.SellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/sell",produces = {MediaType.APPLICATION_JSON_VALUE})
public class SellController {

    @Autowired
    SellService sellService;

    @PostMapping(value = {"","/"})
    public Sell save(@RequestBody Sell sell){
        return sellService.save(sell);
    }
}
