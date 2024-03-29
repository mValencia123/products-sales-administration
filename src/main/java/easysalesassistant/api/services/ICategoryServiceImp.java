package easysalesassistant.api.services;

import easysalesassistant.api.dao.ICategoryDAO;
import easysalesassistant.api.dto.category.CategoryDTO;
import easysalesassistant.api.entity.Category;
import easysalesassistant.api.entity.SystemUser;
import easysalesassistant.api.exceptions.NotFoundCategoryException;
import easysalesassistant.api.mappers.CategoryMapper;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ICategoryServiceImp implements ICategoryService{
    ICategoryDAO categoryDAO;
    ISystemUserService systemUserService;

    public ICategoryServiceImp(ICategoryDAO categoryDAO,ISystemUserService systemUserService){
        this.categoryDAO   = categoryDAO;
        this.systemUserService  = systemUserService;
    }

    @Override
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        SystemUser systemUser = systemUserService.getUserByContext();

        Category category = new Category();
        category.setDescription(categoryDTO.getDescription());
        category.setIdUserCreated(systemUser);
        categoryDAO.save(category);

        return categoryDTO;
    }

    @Override
    public CategoryDTO updateCategory(Long idCategory,CategoryDTO categoryDTO) {
        Category category = existsCategoryById(idCategory);
        category.setDescription(categoryDTO.getDescription());
        categoryDAO.save(category);
        return categoryDTO;
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        Category category = existsCategoryById(id);
        return CategoryMapper.INSTANCE.categoryToCategoryDTO(category);
    }

    @Override
    public void deleteById(Long idCategory) {
        Category category = existsCategoryById(idCategory);
        SystemUser systemUser = systemUserService.getUserByContext();

        category.setDeleted(true);
        category.setDeletedAt(new Date());
        category.setIdUserDeleted(systemUser);

        categoryDAO.save(category);
    }

    public Category existsCategoryById(Long id){
        return categoryDAO.findById(id).orElseThrow(() -> new NotFoundCategoryException(404,"Category's ID doens't exists."));
    }
}
