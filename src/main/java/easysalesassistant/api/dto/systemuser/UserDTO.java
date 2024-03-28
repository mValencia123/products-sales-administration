package easysalesassistant.api.dto.systemuser;

import lombok.*;

@Getter
@Builder
public class UserDTO {
    Long idUser;
    String name;
    String userName;
    String email;
}
