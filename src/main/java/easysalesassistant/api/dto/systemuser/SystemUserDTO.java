package easysalesassistant.api.dto.systemuser;

import easysalesassistant.api.dto.address.AddressDTO;
import easysalesassistant.api.enums.Gender;
import easysalesassistant.api.enums.TypeUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
    private AddressDTO address;
    private Date birthday;
    private Gender gender;
    private Long phoneNumber;
    private Long idBranch;
    private TypeUser typeUser;
}
