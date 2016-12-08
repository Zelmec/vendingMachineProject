package kinler.joseph;

import kinler.joseph.factories.VendingMachineFactory;
import kinler.joseph.vendingMachines.SnackMachine;

public class Main {

    public static void main(String[] args) {
        SnackMachine snackMachine = (SnackMachine) VendingMachineFactory.createVendingMachine(VendingMachineFactory.SNACK_MACHINE);

        System.out.print("Test");
    }
}
