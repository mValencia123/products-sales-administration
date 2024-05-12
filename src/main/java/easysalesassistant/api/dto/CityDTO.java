package easysalesassistant.api.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CityDTO {
    private Long id;
    private String description;
    private StateDTO state;
}
