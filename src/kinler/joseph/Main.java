package kinler.joseph;

import kinler.joseph.enumTypes.Coin;
import kinler.joseph.enumTypes.Snack;
import kinler.joseph.exceptions.InsufficientPaymentException;
import kinler.joseph.exceptions.OutOfChangeException;
import kinler.joseph.factories.VendingMachineFactory;
import kinler.joseph.vendingMachines.SnackMachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final SnackMachine SNACK_MACHINE = (SnackMachine) VendingMachineFactory.createVendingMachine(VendingMachineFactory.SNACK_MACHINE);
    private static final int NUM_OPTIONS = 6;
    private static final Scanner INPUT_SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        int selection;
        boolean isRunning = true;
        List<Snack> purchases = new ArrayList<>();

        //Check to make sure that the snack machine exists since it is integral to the program
        if(SNACK_MACHINE != null) {
            //Run loop until the user explicitly cancels it
            while (isRunning) {

                System.out.println("Please select an operation!");
                System.out.println("1. Select a snack");
                System.out.println("2. Insert a coin");
                System.out.println("3. Get transaction info");
                System.out.println("4. Collect Snack");
                System.out.println("5. Refund balance");
                System.out.println("6. Quit");
                System.out.println();

                selection = getUserIntInput(NUM_OPTIONS);

                //Once an option is chosen, this switch will perform the task
                switch (selection) {
                    case 1:
                        SNACK_MACHINE.selectItem(selectSnackPrompt());
                        break;
                    case 2:
                        SNACK_MACHINE.insertCoin(insertCoinPrompt());
                        break;
                    case 3:
                        getTransactionInfo(purchases);
                        break;
                    case 4:
                        Snack purchase = collectItem();
                        if(purchase != null){
                            purchases.add(purchase);
                        }
                        break;
                    case 5:
                        printRefund(SNACK_MACHINE.refund());
                        break;
                    case 6:
                        isRunning = false;
                        break;
                    default:
                }
            }
        }

        INPUT_SCANNER.close();
    }

    //Runs a prompt to allow the user to get their desired snack
    private static Snack selectSnackPrompt(){
        int selection;

        System.out.println("Please select a snack!");
        System.out.println("1. Chips: "+Snack.CHIPS.getPriceString());
        System.out.println("2. Candy: "+Snack.CANDY.getPriceString());
        System.out.println("3. Cookies: "+Snack.COOKIES.getPriceString());
        System.out.println("4. Crackers: "+Snack.CRACKERS.getPriceString());
        System.out.println();

        selection = getUserIntInput(Snack.values().length);
        return Snack.getSnackFromValue(selection);
    }

    private static Coin insertCoinPrompt(){
        int selection;

        System.out.println("Please select a coin to insert!");
        System.out.println("1. Penny: "+Coin.PENNY.getValueString());
        System.out.println("2. Nickle: "+Coin.NICKLE.getValueString());
        System.out.println("3. Dime: "+Coin.DIME.getValueString());
        System.out.println("4. Quarter: "+Coin.QUARTER.getValueString());
        System.out.println();

        selection = getUserIntInput(Coin.values().length);
        return Coin.getCoinFromValue(selection);
    }

    private static Snack collectItem(){
        Snack purchasedSnack;

        if(SNACK_MACHINE.getCurrentSnack() == null){
            System.out.println("You need to select a snack first!");
            System.out.println();
            return null;
        }

        try {
            purchasedSnack = SNACK_MACHINE.collectItem();
            printRefund(SNACK_MACHINE.collectChange());
            return purchasedSnack;
        } catch(Exception e){
            if(e instanceof InsufficientPaymentException){
                System.out.println("You don't have enough money to purchase the selected snack!");
                System.out.println();
            }
            if(e instanceof OutOfChangeException){
                System.out.println("The Vending Machine doesn't have enough coins to make change!");
                System.out.println();
            }
            return null;
        }
    }

    private static void getTransactionInfo(List<Snack> snacks){
        Snack snack = SNACK_MACHINE.getCurrentSnack();
        int balance = SNACK_MACHINE.getCurrentBalance();
        String balanceString = Integer.toString(balance);
        int length = balanceString.length();

        if(balanceString.length() == 1){
            balanceString = ".0" + balanceString;
        } else{
            balanceString = balanceString.substring(0, length - 2) + "." + balanceString.substring(length - 2, length);
        }

        System.out.println("Your Balance: "+balanceString);
        if(snack != null) {
            System.out.println(snack.getName()+" price: "+snack.getPriceString());

            if(balance > snack.getPrice()){
                System.out.println("You have enough money to purchase your snack!");
            }
        }

        printPurchases(snacks);
    }

    private static void printRefund(List<Coin> coins){
        int pennies = 0;
        int nickles = 0;
        int dimes = 0;
        int quarters = 0;

        if(coins != null && coins.size() > 0){
            for(Coin coin: coins){
                switch (coin){
                    case PENNY:
                        pennies++;
                        break;
                    case NICKLE:
                        nickles++;
                        break;
                    case DIME:
                        dimes++;
                        break;
                    case QUARTER:
                        quarters++;
                        break;
                }
            }

            String refundString = "You were refunded ";

            if(pennies > 0)
                refundString += pennies + " pennies, ";
            if(nickles > 0)
                refundString += nickles + " nickles, ";
            if(dimes > 0)
                refundString += dimes + " dimes, ";
            if(quarters > 0)
                refundString += quarters + " quarters.";

            System.out.println(refundString);
            System.out.println();
        } else{
            System.out.println("You have no remaining balance, so no coins were returned.");
            System.out.println();
        }
    }

    private static void printPurchases(List<Snack> snacks){
        int chips = 0;
        int candy = 0;
        int cookies = 0;
        int crackers = 0;

        if(snacks != null && snacks.size() > 0){
            for(Snack snack: snacks){
                switch(snack){
                    case CHIPS:
                        chips++;
                        break;
                    case CANDY:
                        candy++;
                        break;
                    case COOKIES:
                        cookies++;
                        break;
                    case CRACKERS:
                        crackers++;
                        break;
                }
            }

            String purchasesString = "You have bought ";

            if(chips > 0)
                purchasesString += chips + " bags of chips, ";
            if(candy > 0)
                purchasesString += candy + " pieces of candy, ";
            if(cookies > 0)
                purchasesString += cookies + " cookies, ";
            if(crackers > 0)
                purchasesString += crackers + " packs of crackers.";

            System.out.println(purchasesString);
            System.out.println();
        } else{
            System.out.println("You haven't purchased any snacks yet!");
            System.out.println();
        }
    }

    private static int getUserIntInput(int numOfOptions){
        int selection;

        //Require user input until a valid input it given.
        do {
            System.out.print("Enter: ");
            String input = INPUT_SCANNER.nextLine();
            System.out.println();

            try {
                selection = Integer.parseInt(input);

                if (selection > 0 && selection <= numOfOptions) {
                    return selection;
                } else {
                    System.out.println("The selection you made was not valid. Please enter a different number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("NumberFormatException: input string \"" + input + "\" could not be parsed to an integer.");
            }
        } while (true);
    }
}
