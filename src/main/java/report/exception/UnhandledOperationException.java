package report.exception;

public class UnhandledOperationException extends RuntimeException {
    public UnhandledOperationException(String message) {
        super(message);
    }
}
