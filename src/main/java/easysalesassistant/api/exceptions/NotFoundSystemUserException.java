package easysalesassistant.api.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class NotFoundSystemUserException extends RuntimeException {
    private int code;
    private String message;

    public NotFoundSystemUserException(int code, String message){
        super(message);
        this.code = code;
        this.message = message;
    }
}
