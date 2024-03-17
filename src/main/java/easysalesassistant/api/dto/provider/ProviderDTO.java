package easysalesassistant.api.dto.provider;

import easysalesassistant.api.dto.address.AddressDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProviderDTO {
    private String firstName;
    private String lastName;
    private String rfc;
    private AddressDTO address;
}
