package easysalesassistant.api.dto;

import lombok.Data;

@Data
public class BranchGetDTO {
    private Long idBranch;
    private String description;
    private String descriptionStore;
    private Long idStore;
}
