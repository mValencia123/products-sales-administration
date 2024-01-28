package easysalesassistant.api.services;

import easysalesassistant.api.dao.ISellDAO;
import easysalesassistant.api.entity.Sell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellService {

    @Autowired
    ISellDAO sellDAO;

    public Sell save(Sell sell) {
        return sellDAO.save(sell);
    }
}
