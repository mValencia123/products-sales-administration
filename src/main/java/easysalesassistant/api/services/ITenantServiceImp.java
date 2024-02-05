package easysalesassistant.api.services;

import easysalesassistant.api.dao.ITenantDAO;
import easysalesassistant.api.dto.TenantDTO;
import easysalesassistant.api.entity.Provider;
import easysalesassistant.api.entity.Tenant;
import easysalesassistant.api.exceptions.NotFoundTenantException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ITenantServiceImp implements ITenantService {

    @Autowired
    ITenantDAO tenantDAO;

    @Override
    public TenantDTO saveTenant(TenantDTO tenant) {
        Tenant t = new Tenant();
        t.setDescription(tenant.getDescription());
        tenantDAO.save(t);
        return tenant;
    }

    @Override
    public TenantDTO findTenantById(Long idTenant) {
        Tenant tenant = tenantDAO.findById(idTenant).orElse(null);
        if(tenant == null){
            throw new NotFoundTenantException(404,"This Tenant doesn't exist.");
        }
        TenantDTO tenantDTO = new TenantDTO();
        tenantDTO.setDescription(tenant.getDescription());
        return tenantDTO;
    }

    @Override
    public List<Provider> getProvidersByTenant(Long idTenant) {
        Tenant tenant = tenantDAO.findById(idTenant).orElse(null);
        return tenant.getProviders();
    }

    @Override
    public TenantDTO updateTenant(TenantDTO tenant,Long idTenant) {
        Tenant tenantFind = tenantDAO.findById(idTenant).orElseThrow(() -> new NotFoundTenantException(400,"tenant doesn't exists."));
        tenantFind.setDescription(tenant.getDescription());
        tenantDAO.save(tenantFind);
        TenantDTO tenantDTO = new TenantDTO();
        tenantDTO.setDescription(tenantFind.getDescription());
        return tenantDTO;
    }


}
