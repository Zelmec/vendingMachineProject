package kinler.joseph;

import kinler.joseph.enumTypes.Coin;
import kinler.joseph.enumTypes.Snack;
import kinler.joseph.factories.VendingMachineFactory;
import kinler.joseph.vendingMachines.SnackMachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final int NUM_OPTIONS = 3;
    private static final Scanner INPUT_SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        SnackMachine snackMachine = (SnackMachine) VendingMachineFactory.createVendingMachine(VendingMachineFactory.SNACK_MACHINE);
        int selection = 0;
        boolean isRunning = true;
        List<Snack> purchases = new ArrayList<>();

        while(isRunning){

            System.out.println("Please select an operation!");
            System.out.println("1. Select a snack");
            System.out.println("2. Insert a coin");
            System.out.println("3. Get payment status");
            System.out.println("4. Refund balance");
            System.out.println("5. Quit");
            System.out.println();

            do {
                System.out.print("Enter: ");
                String input = INPUT_SCANNER.nextLine();
                System.out.println();

                try{
                    selection = Integer.parseInt(input);

                    if(selection > 0 && selection <= NUM_OPTIONS){
                        break;
                    } else{
                        System.out.println("The selection you made was not valid. Please enter a different number.");
                    }
                } catch(NumberFormatException e){
                    System.out.println("NumberFormatException: input string \""+input+"\" could not be parsed to an integer.");
                }
            } while(true);

            switch (selection){
                case 1:
                    if(snackMachine != null) {
                        snackMachine.selectItem(selectSnackPrompt());
                    }
                    break;
                case 2:
                    if(snackMachine != null && snackMachine.getCurrentSnack() != null) {
                        snackMachine.insertCoin(insertCoinPrompt());
                    }
                    break;
                case 3:
                    if(snackMachine != null) {
                        getPaymentStatus(snackMachine.getCurrentBalance(), snackMachine.getCurrentSnack());
                    }
                    break;
                case 4:
                    if(snackMachine != null){
                        getRefund(snackMachine.refund());
                    }
                    break;
                case 5:
                    isRunning = false;
                    break;
                default:
            }

        }

        INPUT_SCANNER.close();
    }

    private static Snack selectSnackPrompt(){
        int selection;

        System.out.println("Please select a snack!");
        System.out.println("1. Chips: "+Snack.CHIPS.getPriceString());
        System.out.println("2. Candy: "+Snack.CANDY.getPriceString());
        System.out.println("3. Cookies: "+Snack.COOKIES.getPriceString());
        System.out.println("4. Crackers: "+Snack.CRACKERS.getPriceString());
        System.out.println();

        do {
            System.out.print("Enter: ");
            String input = INPUT_SCANNER.nextLine();
            System.out.println();

            if(input.trim().equalsIgnoreCase("e")){
                return null;
            }

            try{
                selection = Integer.parseInt(input);

                if(selection > 0 && selection <= Snack.values().length){
                    return Snack.getSnackFromValue(selection);
                } else{
                    System.out.println("The selection you made was not valid. Please enter a different number or 'E' to exit.");
                }
            } catch(NumberFormatException e){
                System.out.println("NumberFormatException: input string \""+input+"\" could not be parsed to an integer.");
            }
        } while(true);
    }

    private static Coin insertCoinPrompt(){
        int selection;

        System.out.println("Please select a coin to insert!");
        System.out.println("1. Penny: "+Coin.PENNY.getValueString());
        System.out.println("2. Nickle: "+Coin.NICKLE.getValueString());
        System.out.println("3. Dime: "+Coin.DIME.getValueString());
        System.out.println("4. Quarter: "+Coin.QUARTER.getValueString());
        System.out.println();

        do {
            System.out.print("Enter: ");
            String input = INPUT_SCANNER.nextLine();
            System.out.println();

            if(input.trim().equalsIgnoreCase("e")){
                return null;
            }

            try{
                selection = Integer.parseInt(input);

                if(selection > 0 && selection <= Coin.values().length){
                    return Coin.getCoinFromValue(selection);
                } else{
                    System.out.println("The selection you made was not valid. Please enter a different number or 'E' to exit.");
                }
            } catch(NumberFormatException e){
                System.out.println("NumberFormatException: input string \""+input+"\" could not be parsed to an integer.");
            }
        } while(true);
    }

    private static void getPaymentStatus(int balance, Snack snack){
        String balanceString = Integer.toString(balance);
        int length = balanceString.length();

        if(balanceString.length() == 1){
            balanceString = ".0" + balanceString;
        } else{
            balanceString = balanceString.substring(0, length - 2) + "." + balanceString.substring(length - 2, length);
        }

        System.out.println("Your Balance: "+balanceString);
        if(snack != null && balance > snack.getPrice()){
            System.out.println("Snack price: "+snack.getPriceString());
            System.out.println("You have enough money to purchase your snack!");
        }
        System.out.println();
    }

    private static void getRefund(List<Coin> coins){
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

            System.out.print("You have " + pennies + " pennies, ");
            System.out.print(nickles + " nickles, ");
            System.out.print(dimes + " dimes, ");
            System.out.print("and "+ quarters + "quarters.");
            System.out.println();
        }
    }
}
