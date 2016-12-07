package kinler.joseph;

/**
 * Created by Joe on 12/7/2016.
 */
public interface VendingMachine {
    public Item selectItem();
    public void insertCoin(Coin coin);
    public List<Coin> refund();
}
