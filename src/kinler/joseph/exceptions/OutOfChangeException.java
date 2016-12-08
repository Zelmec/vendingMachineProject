package kinler.joseph.exceptions;

/**
 * Created by Kinle on 12/7/2016.
 */
public class OutOfChangeException extends RuntimeException {
    private static final String MESSAGE = "This machine does not have enough change to complete your request.";

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
