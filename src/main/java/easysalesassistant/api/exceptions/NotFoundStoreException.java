package easysalesassistant.api.exceptions;

import lombok.Data;

@Data
public class NotFoundStoreException extends RuntimeException{
    private int code;
    private String message;

    public NotFoundStoreException(int code, String message){
        super(message);
        this.code = code;
        this.message = message;
    }
}