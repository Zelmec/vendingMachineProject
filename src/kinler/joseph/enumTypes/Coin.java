package kinler.joseph.enumTypes;

/**
 * Created by Kinle on 12/7/2016.
 */
public enum Coin {
    PENNY(1), NICKLE(5), DIME(10), QUARTER(25);
    private int mCoinValue;

    Coin(int coinValue){
        this.mCoinValue = coinValue;
    }

    //Return the value of the coin
    public int getCoinValue(){
        return mCoinValue;
    }
}
