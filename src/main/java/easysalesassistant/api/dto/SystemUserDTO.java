package easysalesassistant.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SystemUserDTO {
    String name;
    String lastName;
    String email;
    String tenant;
    String userName;
    String password;
    String rfc;
    Long idTenant;
}
