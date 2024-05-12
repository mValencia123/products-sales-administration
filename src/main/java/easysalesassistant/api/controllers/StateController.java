package easysalesassistant.api.controllers;

import easysalesassistant.api.dao.IStateDAO;
import easysalesassistant.api.dto.CityDTO;
import easysalesassistant.api.dto.StateDTO;
import easysalesassistant.api.entity.City;
import easysalesassistant.api.services.IStateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/states")
public class StateController {

    IStateService stateService;

    StateController(IStateService stateService){
        this.stateService = stateService;
    }

    @GetMapping(value = {"","/"})
    public List<StateDTO> getStates(){
        return stateService.getStates();
    }

    @GetMapping(value = "/{idState}/cities")
    public List<CityDTO> getCitiesByIdState(@PathVariable() Long idState){
        return stateService.getCitiesByIdState(idState);
    }
}
