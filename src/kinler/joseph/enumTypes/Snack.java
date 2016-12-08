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

    //Return the name of the snack
    public String getName(){
        return mName;
    }

    //Return the price of the snack
    public int getPrice(){
        return mPrice;
    }
}
