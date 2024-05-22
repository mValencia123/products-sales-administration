package easysalesassistant.api.services;

import easysalesassistant.api.dto.CityDTO;
import easysalesassistant.api.dto.StateDTO;
import easysalesassistant.api.entity.State;

import java.util.List;

public interface IStateService {
    List<StateDTO> getStates();
    List<CityDTO> getCitiesByIdState(Long id);
    State existsStateById(Long id);
}
