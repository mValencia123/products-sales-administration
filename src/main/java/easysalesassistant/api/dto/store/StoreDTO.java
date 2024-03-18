package easysalesassistant.api.dto.store;

import easysalesassistant.api.dto.address.AddressDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class StoreDTO {
    private Long id;
    private String description;
    private Date createdAt;
    private Long phoneNumber;
    private AddressDTO address;
}
