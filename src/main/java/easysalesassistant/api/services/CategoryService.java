package easysalesassistant.api.services;

import easysalesassistant.api.dao.ICategoryDAO;
import easysalesassistant.api.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    ICategoryDAO categoryDAO;

    public Category saveCategory(Category category) {
        return categoryDAO.save(category);
    }
}
