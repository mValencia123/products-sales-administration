package easysalesassistant.api.services;

import easysalesassistant.api.dao.ICategoryDAO;
import easysalesassistant.api.dao.ITenantDAO;
import easysalesassistant.api.dto.CategoryDTO;
import easysalesassistant.api.entity.Category;
import easysalesassistant.api.entity.Tenant;
import easysalesassistant.api.exceptions.NotFoundCategoryException;
import easysalesassistant.api.exceptions.NotFoundTenantException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ICategoryServiceImp implements ICategoryService{

    ICategoryDAO categoryDAO;
    ITenantDAO tenantDAO;

    public ICategoryServiceImp(ICategoryDAO categoryDAO, ITenantDAO tenantDAO){
        this.categoryDAO = categoryDAO;
        this.tenantDAO   = tenantDAO;
    }

    @Override
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        Tenant tenant = tenantDAO.findById(categoryDTO.getIdTenant()).orElseThrow(() -> new NotFoundTenantException(400,"Tentant's ID doesn't exists."));
        Category category = new Category();
        category.setDescription(categoryDTO.getDescription());
        category.setIdTenant(tenant);
        categoryDAO.save(category);
        return categoryDTO;
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO,Long idCategory) {
        tenantDAO.findById(categoryDTO.getIdTenant()).orElseThrow(() -> new NotFoundTenantException(400,"Tentant's ID doesn't exists."));

        Category category = categoryDAO.findById(idCategory).orElseThrow(() -> new NotFoundCategoryException(400,"Category's ID doesn't exists."));
        category.setDescription(categoryDTO.getDescription());
        categoryDAO.save(category);
        return categoryDTO;
    }
}
