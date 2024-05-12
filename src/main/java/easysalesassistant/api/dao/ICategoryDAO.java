package easysalesassistant.api.dao;

import easysalesassistant.api.entity.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ICategoryDAO extends CrudRepository<Category,Long> {
    List<Category> findByDeletedFalse();
}
