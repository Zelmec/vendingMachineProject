package kinler.joseph.vendingMachines;

import kinler.joseph.enumTypes.Coin;
import kinler.joseph.enumTypes.Snack;
import kinler.joseph.exceptions.InsufficientPaymentException;
import kinler.joseph.exceptions.OutOfChangeException;
import kinler.joseph.exceptions.OutOfStockException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kinle on 12/7/2016.
 */
public class SnackMachine implements VendingMachine {
    private Inventory<Coin> mCoinInventory;
    private Inventory<Snack> mSnackInventory;
    private int mTotalSales;
    private Snack mCurrentSnack;
    private int mCurrentBalance;

    //initialize all of the necessary fields
    public SnackMachine(){
        mCoinInventory = new Inventory<>();
        mSnackInventory = new Inventory<>();
        reset();
        stockMachine();
    }

    //Set up the Snack Machine with a base stock of items and coins
    private void stockMachine(){
        for(Coin coin: Coin.values()){
            mCoinInventory.addItemStock(coin, 10);
        }
        for(Snack snack: Snack.values()){
            mSnackInventory.addItemStock(snack, 5);
        }
    }

    //Set the current snack and return the snack selected if it is in stock
    @Override
    public Snack selectItem(Snack snack) {
        if(mSnackInventory.hasInStock(snack)){
            mCurrentSnack = snack;
            return snack;
        }
        throw new OutOfStockException();
    }

    //Add a coin into the snack machine, increasing the current balance and coin count
    @Override
    public void insertCoin(Coin coin) {
        mCurrentBalance = mCurrentBalance + coin.getCoinValue();
        mCoinInventory.addItem(coin);
    }

    //Refund the user change equal to their current balance and reset the current snack and balance fields
    @Override
    public List<Coin> refund() {
        List<Coin> refundedCoins = getChange(mCurrentBalance);
        mCurrentBalance = 0;
        mCurrentSnack = null;
        return refundedCoins;
    }


    @Override
    public Snack collectItem() throws InsufficientPaymentException, OutOfChangeException {
        if(hasEnoughMoney()){
            if(hasEnoughChange()){
                mSnackInventory.removeItem(mCurrentSnack);
                return mCurrentSnack;
            }
            throw new OutOfChangeException();
        }
        throw new InsufficientPaymentException();
    }

    @Override
    public List<Coin> collectChange() {
        int remainingBalance = mCurrentBalance - mCurrentSnack.getPrice();
        List<Coin> coins;
        try {
            coins = getChange(remainingBalance);
        } catch(OutOfChangeException e){
            return null;
        }
        mCurrentBalance = 0;
        mCurrentSnack = null;
        return coins;
    }

    @Override
    public void reset() {
        mCoinInventory.clearStock();
        mSnackInventory.clearStock();
        mTotalSales = 0;
        mCurrentSnack = null;
        mCurrentBalance = 0;
    }

    private boolean hasEnoughMoney(){
        return mCurrentBalance >= mCurrentSnack.getPrice();
    }

    private boolean hasEnoughChange(){
        int remainingBalance = mCurrentBalance - mCurrentSnack.getPrice();
        try{
            getChange(remainingBalance);
        } catch(OutOfChangeException e){
            return false;
        }

        return true;
    }

    private List<Coin> getChange(int balance) throws OutOfChangeException{
        List<Coin> coinList = new ArrayList<>();

        if(balance > 0){
            int newBalance = balance;

            //This loop is designed to give the user their change in the fewest amount of coins possible
            while(newBalance > 0){
                //If the remaining balance is greater than the value of a quarter, add a quarter to the change.
                if(newBalance >= Coin.QUARTER.getCoinValue() && mCoinInventory.hasInStock(Coin.QUARTER)){
                    coinList.add(Coin.QUARTER);
                    mCoinInventory.removeItem(Coin.QUARTER);
                    newBalance = newBalance - Coin.QUARTER.getCoinValue();

                //else, If the remaining balance is greater than the value of a dime, add a dime to the change.
                } else if(newBalance >= Coin.DIME.getCoinValue() && mCoinInventory.hasInStock(Coin.DIME)){
                    coinList.add(Coin.DIME);
                    mCoinInventory.removeItem(Coin.DIME);
                    newBalance = newBalance - Coin.DIME.getCoinValue();

                //else, If the remaining balance is greater than the value of a nickle, add a nickle to the change.
                } else if(newBalance >- Coin.NICKLE.getCoinValue() && mCoinInventory.hasInStock(Coin.NICKLE)){
                    coinList.add(Coin.NICKLE);
                    mCoinInventory.removeItem(Coin.NICKLE);
                    newBalance = newBalance - Coin.NICKLE.getCoinValue();

                //else, If the remaining balance is greater than the value of a penny, add a penny to the change.
                } else if(newBalance >= Coin.PENNY.getCoinValue() && mCoinInventory.hasInStock(Coin.PENNY)){
                    coinList.add(Coin.PENNY);
                    mCoinInventory.removeItem(Coin.PENNY);
                    newBalance = newBalance - Coin.PENNY.getCoinValue();

                //else, we have run out of coins to make correct change. Throw exception.
                } else{
                    //Add all of the coins we removed and did not use back into the inventory
                    for(Coin coin: coinList){
                        mCoinInventory.addItem(coin);
                    }
                    throw new OutOfChangeException();
                }
            }
        }
        return coinList;
    }

    public Snack getCurrentSnack() {
        return mCurrentSnack;
    }
}
