// Interface 
interface VendingState {
    void insertCoin();
    void selectItem();
    void dispenseItem();
}
// Context Class
class VendingMachine {
    private VendingState noCoinState, hasCoinState, dispenseState,soldOutState,currentState;
    private int itemCount;

    public VendingMachine(int itemCount) {
        this.itemCount = itemCount;
        noCoinState = new NoCoinState(this);
        hasCoinState = new HasCoinState(this);
        dispenseState = new DispenseState(this);
        soldOutState = new SoldOutState(this);

        currentState = (itemCount > 0) ? noCoinState : soldOutState;
    }

    public void insertCoin() { currentState.insertCoin(); }
    public void selectItem() { currentState.selectItem(); }
    public void dispenseItem() { currentState.dispenseItem(); }

    public void setState(VendingState state) { 
        currentState = state; 
    }
    public VendingState getNoCoinState() {
         return noCoinState; 
        }
    public VendingState getHasCoinState() { 
        return hasCoinState; 
    }
    public VendingState getDispenseState() { 
        return dispenseState; 
    }
    public VendingState getSoldOutState() {
         return soldOutState; 
        }

    public int getItemCount() { 
        return itemCount; 
    }
    public void releaseItem() {
        if (itemCount > 0) {
            System.out.println("Dispensing item...");
            itemCount--;
        }
    }
}

// Concrete Implementation
class NoCoinState implements VendingState {
    private VendingMachine vm;
    public NoCoinState(VendingMachine vm) { this.vm = vm; }
    public void insertCoin() {
        System.out.println("Coin inserted.");

        vm.setState(vm.getHasCoinState());
    }
    public void selectItem() { System.out.println("Insert coin first!"); }
    public void dispenseItem() { System.out.println("Insert coin first!"); }
}

class HasCoinState implements VendingState {
    private VendingMachine vm;
    public HasCoinState(VendingMachine vm) { this.vm = vm; }

    public void insertCoin() { System.out.println("Coin already inserted!"); }
    public void selectItem() {
        System.out.println("Item selected.");
        vm.setState(vm.getDispenseState());
    }
    public void dispenseItem() { System.out.println("Select item first!"); }
}

class DispenseState implements VendingState {
    private VendingMachine vm;
    public DispenseState(VendingMachine vm) { this.vm = vm; }

    public void insertCoin() { System.out.println("Please wait..."); }
    public void selectItem() { System.out.println("Already dispensing."); }
    public void dispenseItem() {
        vm.releaseItem();
        if (vm.getItemCount() > 0)
            vm.setState(vm.getNoCoinState());
        else
            vm.setState(vm.getSoldOutState());
    }
}

class SoldOutState implements VendingState {
    private VendingMachine vm;
    public SoldOutState(VendingMachine vm) { this.vm = vm; }

    public void insertCoin() { System.out.println("Machine is sold out."); }
    public void selectItem() { System.out.println("Machine is sold out."); }
    public void dispenseItem() { System.out.println("No items to dispense."); }
}

// Client class
public class VendingMachineMainStateDesignPattern {
    public static void main(String[] args) {
        VendingMachine vm = new VendingMachine(2);

        vm.insertCoin();
        vm.selectItem();
        vm.dispenseItem();

        vm.insertCoin();
        vm.selectItem();
        vm.dispenseItem();

        vm.insertCoin(); 
    }
}