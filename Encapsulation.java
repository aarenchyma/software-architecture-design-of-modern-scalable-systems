class AccountBalance {

    private double balance;

    public void SetBalance(double balance) {
        this.balance = balance;
    }

    public double GetBalance() {
        return balance;
    }
}

public class Encapsulation {

    public static void main(String[] args) {

        AccountBalance a1 = new AccountBalance();
        a1.SetBalance(500);

        System.out.println("Your balance is " + a1.GetBalance());
    }

}