package easysalesassistant.api.services;

import easysalesassistant.api.dao.IProviderDAO;
import easysalesassistant.api.dto.ProviderDTO;
import easysalesassistant.api.entity.Provider;
import easysalesassistant.api.exceptions.NotFoundProviderException;
import easysalesassistant.api.exceptions.NotFoundTenantException;
import org.springframework.stereotype.Service;

@Service
public class IProviderServiceImp implements IProviderService {
    IProviderDAO providerDAO;

    public IProviderServiceImp(IProviderDAO providerDAO){
        this.providerDAO = providerDAO;
    }

    @Override
    public ProviderDTO saveProvider(ProviderDTO providerDTO) {

        Provider provider = new Provider();
        provider.setFirstName(providerDTO.getDescription());
        providerDAO.save(provider);
        return providerDTO;
    }

    @Override
    public ProviderDTO updaterProvider(ProviderDTO providerDTO,Long idProvider) {
        Provider provider = providerDAO.findById(idProvider).orElseThrow(() -> new NotFoundProviderException(400,"Provider's ID doesnt exists."));

        provider.setFirstName(providerDTO.getDescription());
        providerDAO.save(provider);
        return providerDTO;
    }
}
