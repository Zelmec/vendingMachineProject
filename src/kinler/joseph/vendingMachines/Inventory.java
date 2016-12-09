package kinler.joseph.vendingMachines;

import java.util.HashMap;

/**
 * Created by Joseph Kinler on 12/7/2016.
 */
public class Inventory<T> {
    private HashMap<T, Integer> mInventory = new HashMap<>();

    //Add a single item to stock
    public void addItem(T item){
        int itemCount = mInventory.get(item);
        mInventory.put(item, itemCount + 1);
    }

    //Remove a single item from stock
    public void removeItem(T item){
        int itemCount = mInventory.get(item);
        mInventory.put(item, itemCount - 1);
    }

    //Add an item and it's quantity to the inventory
    public void addItemStock(T item, int quantity){
        mInventory.put(item, quantity);
    }

    //Empty the inventory
    public void clearStock(){
        mInventory.clear();
    }

    //Returns whether the item specified has at least one item in stock
    public boolean hasInStock(T item){
        return getItemStock(item) > 0;
    }

    //Returns the exact amount of stock for a single item type
    public int getItemStock(T item){
        Integer value = mInventory.get(item);
        if(value != null){
            return value;
        } else{
            return 0;
        }
    }
}
