package easysalesassistant.api.services;

import easysalesassistant.api.dto.sell.SellDTO;
import easysalesassistant.api.entity.Sell;

public interface ISellService {
    void saveSell(SellDTO sellProductDTO);
    void processSell(SellDTO sell);
    void deleteSell(Long idSell);
    void updateSell(Long idSell, SellDTO sell);
    Sell existsSellById(Long idSell);
}
