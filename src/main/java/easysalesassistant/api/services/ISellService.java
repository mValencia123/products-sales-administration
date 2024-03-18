package easysalesassistant.api.services;

import easysalesassistant.api.dto.sell.SellDTO;

public interface ISellService {
    void saveSell(SellDTO sellProductDTO);
}
