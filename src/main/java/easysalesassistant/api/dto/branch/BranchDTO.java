package easysalesassistant.api.dto.branch;

import easysalesassistant.api.dto.address.AddressDTO;
import lombok.Data;

import java.util.Date;

@Data
public class BranchDTO {
    private Long id;
    private String description;
    private Date createdAt;
    private Long phoneNumber;
    private boolean allowSellNegativeStock;
    private Long idStore;
    private AddressDTO address;
}
