package easysalesassistant.api.services;

import easysalesassistant.api.dto.category.CategoryDTO;
import easysalesassistant.api.entity.Category;

import java.util.List;

public interface ICategoryService {
    CategoryDTO saveCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(Long idCategory,CategoryDTO categoryDTO);
    CategoryDTO getCategoryById(Long id);
    void deleteById(Long idCategory);
    List<CategoryDTO> getCategories();
    void categoryIsDeleted(Category category);
}
