package easysalesassistant.api.dto.provider;

import easysalesassistant.api.dto.address.AddressDTO;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class ProviderDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String rfc;
    private AddressDTO address;
}
