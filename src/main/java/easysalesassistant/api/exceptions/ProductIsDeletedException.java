package easysalesassistant.api.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ProductIsDeletedException extends RuntimeException {
    private int code;
    private String message;
    public ProductIsDeletedException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
