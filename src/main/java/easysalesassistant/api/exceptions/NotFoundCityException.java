package easysalesassistant.api.exceptions;

public class NotFoundCityException extends RuntimeException {
    private  int code;
    private String message;
    public NotFoundCityException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
