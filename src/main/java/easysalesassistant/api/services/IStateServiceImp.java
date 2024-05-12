package easysalesassistant.api.services;

import easysalesassistant.api.dao.ICityDAO;
import easysalesassistant.api.dao.IStateDAO;
import easysalesassistant.api.dto.CityDTO;
import easysalesassistant.api.dto.StateDTO;
import easysalesassistant.api.entity.State;
import easysalesassistant.api.exceptions.NotFoundStateException;
import easysalesassistant.api.utils.StreamOperation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IStateServiceImp implements IStateService{
    IStateDAO stateDAO;
    ICityDAO cityDAO;

    IStateServiceImp(IStateDAO stateDAO,ICityDAO cityDAO){
        this.stateDAO = stateDAO;
        this.cityDAO = cityDAO;
    }

    @Override
    public List<StateDTO> getStates() {
        return StreamOperation
                .getStreamFromIterable(stateDAO.findAll())
                .map((s) -> StateDTO.builder()
                        .description(s.getDescription())
                        .id(s.getId())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<CityDTO> getCitiesByIdState(Long id) {
        State state = existsStateById(id);
        return cityDAO.findByIdState(state)
                .stream()
                .map((c) -> CityDTO.builder()
                        .id(c.getId())
                        .description(c.getDescription())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public State existsStateById(Long id) {
        State state = stateDAO.findById(id).orElseThrow(() -> new NotFoundStateException(404,"State doesnt exists"));
        return state;
    }
}
