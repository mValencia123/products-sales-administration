package easysalesassistant.api.services;

import easysalesassistant.api.dao.ICategoryDAO;
import easysalesassistant.api.dao.IUserDAO;
import easysalesassistant.api.dto.CategoryDTO;
import easysalesassistant.api.entity.Category;
import easysalesassistant.api.entity.SystemUser;
import easysalesassistant.api.exceptions.NotFoundCategoryException;
import easysalesassistant.api.exceptions.NotFoundSystemUserException;
import easysalesassistant.api.exceptions.NotFoundTenantException;
import org.springframework.stereotype.Service;

@Service
public class ICategoryServiceImp implements ICategoryService{

    ICategoryDAO categoryDAO;
    IUserDAO systemUserDAO;

    public ICategoryServiceImp(ICategoryDAO categoryDAO,IUserDAO systemUserDAO){
        this.categoryDAO   = categoryDAO;
        this.systemUserDAO = systemUserDAO;
    }

    @Override
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        SystemUser user = systemUserDAO.findById(categoryDTO.getIdUser()).orElseThrow(() -> new NotFoundSystemUserException(404,"System User's ID doesn't exists."));

        Category category = new Category();
        category.setDescription(categoryDTO.getDescription());
        category.setIdUserCreated(user);
        categoryDAO.save(category);

        return categoryDTO;
    }

    @Override
    public CategoryDTO updateCategory(Long idCategory,CategoryDTO categoryDTO) {
        Category category = categoryDAO.findById(idCategory).orElseThrow(() -> new NotFoundCategoryException(400,"Category's ID doesn't exists."));
        category.setDescription(categoryDTO.getDescription());
        categoryDAO.save(category);
        return categoryDTO;
    }
}
