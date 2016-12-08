package kinler.joseph.factories;

import kinler.joseph.vendingMachines.SnackMachine;
import kinler.joseph.vendingMachines.VendingMachine;

/**
 * Created by Kinle on 12/7/2016.
 */
public class VendingMachineFactory {
    public static VendingMachine createVendingMachine(String machineType){
        if(machineType == null){
            return null;
        }

        if(machineType.equalsIgnoreCase("SnackMachine")){
            return new SnackMachine();
        }
        return null;
    }
}
