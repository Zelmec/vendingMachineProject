package kinler.joseph.exceptions;

/**
 * Created by Kinle on 12/7/2016.
 */
public class InsufficientPaymentException extends RuntimeException {
    private static final String MESSAGE = "Not enough funds to complete transaction.";

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
