package main.javarush.cryptoanalysis.exception;

public class CryptanalysisException extends RuntimeException {
    public CryptanalysisException() {
    }

    public CryptanalysisException(String message) {
        super(message);
    }

    public CryptanalysisException(String message, Throwable cause) {
        super(message, cause);
    }

    public CryptanalysisException(Throwable cause) {
        super(cause);
    }
}
