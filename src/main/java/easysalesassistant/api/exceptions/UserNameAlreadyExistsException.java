package easysalesassistant.api.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserNameAlreadyExistsException extends RuntimeException {
    int code;
    String message;
    public UserNameAlreadyExistsException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
