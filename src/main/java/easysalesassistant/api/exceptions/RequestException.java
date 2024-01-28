package easysalesassistant.api.exceptions;

import lombok.Data;

@Data
public class RequestException extends RuntimeException{
    private int code;
    private String message;

    public RequestException(int code, String message){
        super(message);
        this.code = code;
        this.message = message;
    }
}
