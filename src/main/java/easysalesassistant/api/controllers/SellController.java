package easysalesassistant.api.controllers;

import easysalesassistant.api.dto.sell.SellDTO;
import easysalesassistant.api.services.ISellService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/sell",produces = {MediaType.APPLICATION_JSON_VALUE})
public class SellController {

    ISellService sellService;

    public SellController(ISellService sellService){
        this.sellService = sellService;
    }

    @PostMapping(value = {"","/"})
    public ResponseEntity<String> save(@RequestBody SellDTO sell){
        sellService.saveSell(sell);
        return ResponseEntity.ok("Sell it's ok");
    }
}
