package kinler.joseph;

import kinler.joseph.enumTypes.Coin;
import kinler.joseph.enumTypes.Snack;
import kinler.joseph.factories.VendingMachineFactory;
import kinler.joseph.vendingMachines.SnackMachine;

import java.util.Scanner;

public class Main {

    private static final Scanner INPUT_SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        SnackMachine snackMachine = (SnackMachine) VendingMachineFactory.createVendingMachine(VendingMachineFactory.SNACK_MACHINE);
        boolean isRunning = true;

        while(isRunning){

            snackMachine.selectItem(selectSnackPrompt());
            isRunning = false;
        }

        INPUT_SCANNER.close();
    }

    private static Snack selectSnackPrompt(){
        int selection;

        System.out.println("Please select a snack!");
        System.out.println("1. Chips");
        System.out.println("2. Candy");
        System.out.println("3. Cookies");
        System.out.println("4. Crackers");
        System.out.println();

        do {
            String input = INPUT_SCANNER.nextLine();

            try{
                selection = Integer.parseInt(input);

                if(selection > 0 && selection <= Snack.values().length){
                    return Snack.getSnackFromValue(selection);
                } else{
                    System.out.println("The selection you made was not valid. Please enter a different number");
                    System.out.println();
                }
            } catch(NumberFormatException e){
                System.out.println("NumberFormatException: input string \""+input+"\" could not be parsed to an integer.");
                System.out.println();
            }
        } while(true);
    }

    private static Coin insertCoinPrompt(int price, String priceString){
        int selection;

        //TODO: Allow user to insert multiple coins
        return null;
    }
}
