package easysalesassistant.api.dto.systemuser;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SystemUserDTO {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    private String rfc;
}
