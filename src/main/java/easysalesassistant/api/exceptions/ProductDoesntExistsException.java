package easysalesassistant.api.exceptions;

import lombok.Data;

@Data
public class ProductDoesntExistsException extends RuntimeException{
    private int code;
    private String message;

    public ProductDoesntExistsException(int code, String message){
        super(message);
        this.code = code;
        this.message = message;
    }
}
