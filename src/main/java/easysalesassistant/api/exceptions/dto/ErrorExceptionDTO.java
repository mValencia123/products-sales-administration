package easysalesassistant.api.exceptions.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ErrorExceptionDTO {
    private String message;
    private int code;
}
