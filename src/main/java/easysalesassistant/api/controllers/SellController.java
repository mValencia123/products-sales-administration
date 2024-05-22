package easysalesassistant.api.controllers;

import easysalesassistant.api.dto.sell.SellDTO;
import easysalesassistant.api.services.ISellService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/sell",produces = {MediaType.APPLICATION_JSON_VALUE})
public class SellController {

    ISellService sellService;

    public SellController(ISellService sellService){
        this.sellService = sellService;
    }

    @PostMapping(value = {"createSell"})
    public ResponseEntity<String> processSell(@RequestBody SellDTO sell){
        sellService.processSell(sell);
        return ResponseEntity.ok("Sell it's ok");
    }

    @PostMapping(value = "saveSell")
    public ResponseEntity<String> saveSell(@RequestBody SellDTO sell){
        sellService.saveSell(sell);
        return ResponseEntity.ok("Sell it's ok");
    }

    @DeleteMapping(value = "deleteSell/{idSell}")
    public ResponseEntity<String> deleteSell(@PathVariable(name = "idSell") Long idSell){
        sellService.deleteSell(idSell);
        return ResponseEntity.ok("Sell it's ok");
    }

    @PatchMapping(value = "updateSell/{idSell}")
    public ResponseEntity<String> updateSell(@PathVariable(name = "idSell") Long idSell,@RequestBody SellDTO sell){
        sellService.updateSell(idSell,sell);
        return ResponseEntity.ok("Sell it's ok");
    }
}
