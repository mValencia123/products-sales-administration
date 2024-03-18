package easysalesassistant.api.mappers;

import easysalesassistant.api.dto.category.CategoryDTO;
import easysalesassistant.api.entity.Category;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);
    @InheritInverseConfiguration
    Category categoryDTOToCategory(CategoryDTO categoryDTO);
}
