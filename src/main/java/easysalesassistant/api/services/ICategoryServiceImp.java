package easysalesassistant.api.services;

import easysalesassistant.api.dao.ICategoryDAO;
import easysalesassistant.api.dto.category.CategoryDTO;
import easysalesassistant.api.entity.Category;
import easysalesassistant.api.entity.Provider;
import easysalesassistant.api.entity.SystemUser;
import easysalesassistant.api.exceptions.CategoryIsDeletedException;
import easysalesassistant.api.exceptions.NotFoundCategoryException;
import easysalesassistant.api.exceptions.ProviderIsDeletedException;
import easysalesassistant.api.mappers.CategoryMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        category.setCreatedAt(new Date());
        category.setDeleted(false);
        category.setIdUserCreated(systemUser);
        categoryDAO.save(category);

        return categoryDTO;
    }

    @Override
    public CategoryDTO updateCategory(Long idCategory,CategoryDTO categoryDTO) {
        Category category = existsCategoryById(idCategory);
        this.categoryIsDeleted(category);
        category.setDescription(categoryDTO.getDescription());
        categoryDAO.save(category);
        return categoryDTO;
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        Category category = existsCategoryById(id);
        this.categoryIsDeleted(category);
        return CategoryMapper.INSTANCE.categoryToCategoryDTO(category);
    }

    @Override
    public void deleteById(Long idCategory) {
        Category category = existsCategoryById(idCategory);
        this.categoryIsDeleted(category);
        SystemUser systemUser = systemUserService.getUserByContext();

        category.setDeleted(true);
        category.setDeletedAt(new Date());
        category.setIdUserDeleted(systemUser);

        categoryDAO.save(category);
    }

    @Override
    public List<CategoryDTO> getCategories() {
        return categoryDAO.findByDeletedFalse()
                .stream()
                .filter((b) -> !b.isDeleted())
                .map((c) ->
                    CategoryDTO.builder()
                        .description(c.getDescription())
                        .id(c.getId())
                        .createdAt(c.getCreatedAt())
                    .build())
                .collect(Collectors.toList());
    }

    @Override
    public void categoryIsDeleted(Category category) {
        if(category.isDeleted()) throw new CategoryIsDeletedException(403,"The category is not longer active.");
    }

    public Category existsCategoryById(Long id){
        return categoryDAO.findById(id).orElseThrow(() -> new NotFoundCategoryException(404,"Category's ID doens't exists."));
    }
}
