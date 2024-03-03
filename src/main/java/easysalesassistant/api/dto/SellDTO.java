package easysalesassistant.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class SellDTO {
    Long idBranch;
    Long idEmployee;
    List<SellProductDTO> productsSell;
}
