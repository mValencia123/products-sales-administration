package easysalesassistant.api.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CategoryDTO {
    String description;
    Long idTenant;
    Long idUser;
    Date createdAt;
}
