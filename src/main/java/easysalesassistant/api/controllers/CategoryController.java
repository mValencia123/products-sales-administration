package easysalesassistant.api.controllers;

import easysalesassistant.api.dto.CategoryDTO;
import easysalesassistant.api.entity.Category;
import easysalesassistant.api.services.ICategoryService;
import easysalesassistant.api.services.ICategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private ICategoryService categoryService;

    CategoryController(ICategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping(value = {"","/"})
    public CategoryDTO save(@RequestBody CategoryDTO category){
        return categoryService.saveCategory(category);
    }
    @PatchMapping(value = {"/{idCategory}"})
    public CategoryDTO updateCategory(@RequestBody CategoryDTO category,@PathVariable("idCategory") Long idCategory){
        return categoryService.updateCategory(idCategory,category);
    }
}
