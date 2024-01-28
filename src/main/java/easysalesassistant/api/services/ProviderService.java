package easysalesassistant.api.services;

import easysalesassistant.api.dao.IProviderDAO;
import easysalesassistant.api.entity.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderService {

    @Autowired
    IProviderDAO providerDAO;

    public Provider save(Provider provider) {
        return providerDAO.save(provider);
    }
}
