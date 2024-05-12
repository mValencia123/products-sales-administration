package easysalesassistant.api.exceptions;

import lombok.Data;

@Data
public class NotFoundStateException extends RuntimeException{
    private int code;
    private String message;

    public NotFoundStateException(int code, String message){
        super(message);
        this.code = code;
        this.message = message;
    }
}