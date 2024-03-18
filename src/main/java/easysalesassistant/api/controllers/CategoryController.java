package easysalesassistant.api.controllers;

import easysalesassistant.api.dto.category.CategoryDTO;
import easysalesassistant.api.services.ICategoryService;
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

    @GetMapping(value = "/{idCategory}")
    public CategoryDTO getCategory(@PathVariable(value = "idCategory") Long idCategory){
        return categoryService.getCategoryById(idCategory);
    }

    @DeleteMapping(value = "/{idCategory}")
    public void deleteCategory(@PathVariable(value = "idCategory") Long idCategory){
        categoryService.deleteById(idCategory);
    }
}

