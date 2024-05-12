package easysalesassistant.api.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StateDTO {
    private Long id;
    private String description;
}
