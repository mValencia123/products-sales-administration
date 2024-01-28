package easysalesassistant.api.dao;

import easysalesassistant.api.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface ICategoryDAO extends CrudRepository<Category,Long> {
}
