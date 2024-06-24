package easysalesassistant.api.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CategoryIsDeletedException extends RuntimeException {
    private int code;
    private String message;
    public CategoryIsDeletedException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
