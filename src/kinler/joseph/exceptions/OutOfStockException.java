package kinler.joseph.exceptions;

/**
 * Created by Kinle on 12/7/2016.
 */
public class OutOfStockException extends RuntimeException {
    private static final String MESSAGE = "Sorry, this item is currently out of stock.";

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
