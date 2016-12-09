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

    //Return the snack corresponding to the given value, or null.
    public static Coin getCoinFromValue(int value){
        switch (value){
            case 1:
                return Coin.PENNY;
            case 2:
                return Coin.NICKLE;
            case 3:
                return Coin.DIME;
            case 4:
                return Coin.QUARTER;
            default:
                return null;
        }
    }

    //Return the value of the coin
    public int getCoinValue(){
        return mCoinValue;
    }

    //Return the coin value as a string with a decimal point inserted into it.
    public String getValueString(){
        String priceString = Integer.toString(mCoinValue);
        int length = priceString.length();

        if(length == 1){
            return ".0 " + priceString;
        } else{
            return priceString.substring(0, length - 2) + "." + priceString.substring(length - 2, length);
        }
    }
}
