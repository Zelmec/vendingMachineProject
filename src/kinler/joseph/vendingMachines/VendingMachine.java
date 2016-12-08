package kinler.joseph.vendingMachines;

import kinler.joseph.enumTypes.Coin;
import kinler.joseph.enumTypes.Snack;

import java.util.List;

/**
 * Created by Joe on 12/7/2016.
 */
public interface VendingMachine {
    Snack selectItem(Snack snack);
    void insertCoin(Coin coin);
    List<Coin> refund();
    Snack collectItem();
    List<Coin> collectChange();
    void reset();
}
