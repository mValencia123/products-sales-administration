package easysalesassistant.api.services;

import easysalesassistant.api.dao.IProviderDAO;
import easysalesassistant.api.dao.ITenantDAO;
import easysalesassistant.api.dto.ProviderDTO;
import easysalesassistant.api.entity.Provider;
import easysalesassistant.api.entity.Tenant;
import easysalesassistant.api.exceptions.NotFoundProviderException;
import easysalesassistant.api.exceptions.NotFoundTenantException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IProviderServiceImp implements IProviderService {
    IProviderDAO providerDAO;

    ITenantDAO tenantDAO;

    public IProviderServiceImp(IProviderDAO providerDAO, ITenantDAO tenantDAO){
        this.providerDAO = providerDAO;
        this.tenantDAO = tenantDAO;
    }

    @Override
    public ProviderDTO saveProvider(ProviderDTO providerDTO) {
        Tenant tenant = tenantDAO.findById(providerDTO.getIdTenant()).orElseThrow(() -> new NotFoundTenantException(400,"Tentant's ID doesn't exists."));

        Provider provider = new Provider();
        provider.setDescription(providerDTO.getDescription());
        provider.setIdTenant(tenant);
        providerDAO.save(provider);
        return providerDTO;
    }

    @Override
    public ProviderDTO updaterProvider(ProviderDTO providerDTO,Long idProvider) {
        tenantDAO.findById(providerDTO.getIdTenant()).orElseThrow(() -> new NotFoundTenantException(400,"Tentant's ID doesn't exists."));
        Provider provider = providerDAO.findById(idProvider).orElseThrow(() -> new NotFoundProviderException(400,"Provider's ID doesnt exists."));

        provider.setDescription(providerDTO.getDescription());
        providerDAO.save(provider);
        return providerDTO;
    }
}
