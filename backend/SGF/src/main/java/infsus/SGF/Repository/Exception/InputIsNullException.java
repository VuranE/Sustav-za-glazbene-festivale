package infsus.SGF.Repository.Exception;

public class InputIsNullException extends RuntimeException
{
    public InputIsNullException() {
    }

    public InputIsNullException(String message) {
        super(message);
    }

    public InputIsNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public InputIsNullException(Throwable cause) {
        super(cause);
    }
}