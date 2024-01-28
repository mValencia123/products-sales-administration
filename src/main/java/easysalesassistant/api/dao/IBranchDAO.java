package easysalesassistant.api.dao;

import easysalesassistant.api.entity.Branch;
import org.springframework.data.repository.CrudRepository;


public interface IBranchDAO extends CrudRepository<Branch,Long> {
}
