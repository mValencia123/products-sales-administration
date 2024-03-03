package easysalesassistant.api.services;

import easysalesassistant.api.dto.SellDTO;
import easysalesassistant.api.dto.SellProductDTO;

public interface ISellService {
    void saveSell(SellDTO sellProductDTO);
}
