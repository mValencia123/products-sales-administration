package easysalesassistant.api.services;

import easysalesassistant.api.dto.CategoryDTO;

public interface ICategoryService {
    CategoryDTO saveCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(Long idCategory,CategoryDTO categoryDTO);

}
