package easysalesassistant.api.dto.address;

import lombok.Data;

@Data
public class AddressDTO {
    private Long idState;
    private Long idCity;
    private String street;
    private Long number;
    private Long postalCode;
    private String suburb;
}
