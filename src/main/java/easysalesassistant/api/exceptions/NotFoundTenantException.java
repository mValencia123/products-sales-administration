package easysalesassistant.api.exceptions;

import lombok.Data;

@Data
public class NotFoundTenantException extends RuntimeException{
    private int code;
    private String message;

    public NotFoundTenantException(int code, String message){
        super(message);
        this.code = code;
        this.message = message;
    }
}
