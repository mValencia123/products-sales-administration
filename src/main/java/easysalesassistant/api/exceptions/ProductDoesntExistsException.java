package easysalesassistant.api.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ProductDoesntExistsException extends RuntimeException{
    private int code;
    private String message;

    public ProductDoesntExistsException(int code, String message){
        super(message);
        this.code = code;
        this.message = message;
    }
}
