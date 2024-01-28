package easysalesassistant.api.controllers;

import easysalesassistant.api.dao.ITenantDAO;
import easysalesassistant.api.entity.Category;
import easysalesassistant.api.entity.Tenant;
import easysalesassistant.api.services.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(value = {"","/"})
    public Category save(@RequestBody Category category){
        return categoryService.saveCategory(category);
    }
}
