package error;

/**
 * An exception which carries the stack trace of an existing exception.
 */
public class LogReadyTraceableException extends Exception {
    public LogReadyTraceableException() {
        super("Unspecified exception occurred.");
    }
    LogReadyTraceableException(String errorMessage) {
        super(errorMessage);
    }
}
