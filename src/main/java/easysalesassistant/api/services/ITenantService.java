package easysalesassistant.api.services;

import easysalesassistant.api.dto.TenantDTO;
import easysalesassistant.api.entity.Provider;
import easysalesassistant.api.entity.Tenant;

import java.util.List;

public interface ITenantService {
    TenantDTO saveTenant(TenantDTO tenant);
    TenantDTO findTenantById(Long idTenant);
    List<Provider> getProvidersByTenant(Long idTenant);

    Tenant patchTenant(Long idTenant, Tenant tenant);
}
