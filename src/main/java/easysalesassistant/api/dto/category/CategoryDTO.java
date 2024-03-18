package easysalesassistant.api.dto.category;

import lombok.Data;

import java.util.Date;

@Data
public class CategoryDTO {
    private Long id;
    private String description;
    private Date createdAt;
}
