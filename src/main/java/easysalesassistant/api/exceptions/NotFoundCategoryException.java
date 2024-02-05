package easysalesassistant.api.exceptions;

import lombok.Data;

@Data
public class NotFoundCategoryException extends RuntimeException{
    private int code;
    private String message;

    public NotFoundCategoryException(int code, String message){
        super(message);
        this.code = code;
        this.message = message;
    }
}