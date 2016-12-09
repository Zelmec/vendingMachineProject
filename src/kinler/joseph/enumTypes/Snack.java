package kinler.joseph.enumTypes;

/**
 * Created by Kinle on 12/7/2016.
 */
public enum Snack {
    CHIPS("chips", 115), CANDY("candy", 125), COOKIES("cookies", 100), CRACKERS("crackers", 75);
    private String mName;
    private int mPrice;

    Snack(String name, int price){
        this.mName = name;
        this.mPrice = price;
    }

    //Return the snack corresponding to the given value, or null.
    public static Snack getSnackFromValue(int value){
        switch (value){
            case 1:
                return Snack.CHIPS;
            case 2:
                return Snack.CANDY;
            case 3:
                return Snack.COOKIES;
            case 4:
                return Snack.CRACKERS;
            default:
                return null;
        }
    }

    //Return the name of the snack
    public String getName(){
        return mName;
    }

    //Return the price of the snack
    public int getPrice(){
        return mPrice;
    }

    //Return the price as a string with a decimal point inserted into it.
    public String getPriceString(){
        String priceString = Integer.toString(mPrice);
        int length = priceString.length();

        if(length == 1){
            return ".0" + priceString;
        } else {
            return priceString.substring(0, length - 2) + "." + priceString.substring(length - 2, length);
        }
    }
}
