package easysalesassistant.api.mappers.qualifiers;

import easysalesassistant.api.dao.ICategoryDAO;
import easysalesassistant.api.entity.Category;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
public class CategoryQualifier {
    ICategoryDAO categoryDAO;

    public CategoryQualifier(ICategoryDAO categoryDAO){
        this.categoryDAO = categoryDAO;
    }

    @Named("longToCategory")
    public Category longToCategory(Long id) {
        return categoryDAO.findById(id).orElse(null);
    }
}
