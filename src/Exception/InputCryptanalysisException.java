package Exception;

public class InputCryptanalysisException extends RuntimeException{
    public InputCryptanalysisException() {
    }

    public InputCryptanalysisException(String message) {
        super(message);
    }

    public InputCryptanalysisException(String message, Throwable cause) {
        super(message, cause);
    }

    public InputCryptanalysisException(Throwable cause) {
        super(cause);
    }
}
