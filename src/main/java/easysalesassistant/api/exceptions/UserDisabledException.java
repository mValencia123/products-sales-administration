package easysalesassistant.api.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserDisabledException extends RuntimeException {

    int code;
    String message;
    public UserDisabledException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
