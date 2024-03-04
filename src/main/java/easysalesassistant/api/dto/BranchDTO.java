package easysalesassistant.api.dto;

import lombok.Data;

@Data
public class BranchDTO {
    private String description;
    private Long idTenant;
    private Long idStore;
}
