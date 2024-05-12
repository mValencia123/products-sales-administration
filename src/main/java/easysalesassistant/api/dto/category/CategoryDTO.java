package easysalesassistant.api.dto.category;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class CategoryDTO {
    private Long id;
    private String description;
    private Date createdAt;
}
