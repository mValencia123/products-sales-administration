package easysalesassistant.api.controllers;

import easysalesassistant.api.dto.TenantDTO;
import easysalesassistant.api.entity.Provider;
import easysalesassistant.api.services.ITenantServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/tenant",produces = {MediaType.APPLICATION_JSON_VALUE})
public class TenantController {

    @Autowired
    ITenantServiceImp tenantService;

    @PostMapping( value = {"","/"})
    private ResponseEntity<TenantDTO> saveTenant(@Valid @RequestBody TenantDTO tenant){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(tenantService.saveTenant(tenant));
    }

    @GetMapping(value = "/{idTenant}")
    private ResponseEntity<TenantDTO> getTenant(@PathVariable Long idTenant){
        return ResponseEntity.status(HttpStatus.OK)
                .body(tenantService.findTenantById(idTenant));
    }

    @GetMapping(value = "/{idTenant}/providers")
    private List<Provider> getListProviders(@PathVariable Long idTenant){
        return tenantService.getProvidersByTenant(idTenant);
    }

    @PatchMapping(value = "/{idTenant}")
    private TenantDTO patchTenant(@PathVariable Long idTenant,@RequestBody TenantDTO tenant){
        return tenantService.patchTenant(idTenant,tenant);
    }
}
