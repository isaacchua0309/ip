package boblet.exception;

/**
 * Represents a custom exception for errors specific to the Boblet application.
 */
public class BobletException extends Exception {
    /**
     * Constructs a new BobletException with the specified error message.
     *
     * @param message The detailed error message explaining the cause of the exception.
     */
    public BobletException(String message) {
        super(message);
    }
}
