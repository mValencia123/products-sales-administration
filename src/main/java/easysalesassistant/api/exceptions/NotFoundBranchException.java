package easysalesassistant.api.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class NotFoundBranchException extends RuntimeException {
    private int code;
    private String message;

    public NotFoundBranchException(int code, String message){
        super(message);
        this.code = code;
        this.message = message;
    }
}
