package easysalesassistant.api.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class NotEnoughStock extends RuntimeException {
    private int code;
    private String message;

    public NotEnoughStock(int code, String message){
        super(message);
        this.code = code;
        this.message = message;
    }
}
