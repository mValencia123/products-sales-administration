package easysalesassistant.api.exceptions;

public class NotFoundAddressException extends RuntimeException {
    private int code;
    private String message;
    public NotFoundAddressException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
