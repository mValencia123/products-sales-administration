package easysalesassistant.api.controllers;

import easysalesassistant.api.dto.CategoryDTO;
import easysalesassistant.api.entity.Category;
import easysalesassistant.api.services.ICategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private ICategoryServiceImp categoryService;

    @PostMapping(value = {"","/"})
    public CategoryDTO save(@RequestBody CategoryDTO category){
        return categoryService.saveCategory(category);
    }
}
