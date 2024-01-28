package easysalesassistant.api.dao;

import easysalesassistant.api.entity.Tenant;
import org.springframework.data.repository.CrudRepository;

public interface ITenantDAO extends CrudRepository<Tenant,Long> {
}
