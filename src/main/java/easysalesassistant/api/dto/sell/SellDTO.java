package easysalesassistant.api.dto.sell;

import lombok.Data;

import java.util.List;

@Data
public class SellDTO {
    private Long id;
    private Long idBranch;
    private Long idEmployee;
    private List<SellProductDTO> productsSell;
}
