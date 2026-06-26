
interface IAccount {

    void createAccount(int Account_no, double Initial_Balence);
    void Deposit(double amount);
    void Withdraw(double amount);
    void Check_Balence();
}


class Saving_Account implements IAccount {

    private int Account_no;
    private double Balence;

    public void createAccount(int Account_no, double Initial_Balence) {
        this.Account_no = Account_no;
        this.Balence = Initial_Balence;
        System.out.println("Saving Account created successfully.");
    }

    public void Deposit(double amount) {
        Balence += amount;
        System.out.println("The amount: " + amount + " is successfully deposited.");
    }

    public void Withdraw(double amount) {
        if (amount > Balence) {
            System.out.println("Insufficient funds in Saving Account.");
        } else {
            Balence -= amount;
        }
    }

    public void Check_Balence() {
        System.out.println("Saving Account balance: " + Balence);
    }
}


class Current_Account implements IAccount {

    private int Account_no;
    private double Balence;

    public void createAccount(int Account_no, double Initial_Balence) {
        this.Account_no = Account_no;
        this.Balence = Initial_Balence;
        System.out.println("Current Account created successfully.");
    }

    public void Deposit(double amount) {
        Balence += amount;
        System.out.println("The amount: " + amount + " is successfully deposited.");
    }

    public void Withdraw(double amount) {
        if (amount > Balence) {
            System.out.println("Insufficient funds in Current Account.");
        } else {
            Balence -= amount;
        }
    }

    public void Check_Balence() {
        System.out.println("Current Account balance: " + Balence);
    }
}


class Chequing_Account implements IAccount {

    private int Account_no;
    private double Balence;

    public void createAccount(int Account_no, double Initial_Balence) {
        this.Account_no = Account_no;
        this.Balence = Initial_Balence;
        System.out.println("Chequing Account created successfully.");
    }

    public void Deposit(double amount) {
        Balence += amount;
        System.out.println("The amount: " + amount + " is successfully deposited.");
    }

    public void Withdraw(double amount) {
        if (amount > Balence) {
            System.out.println("Insufficient funds in Chequing Account.");
        } else {
            Balence -= amount;
        }
    }

    public void Check_Balence() {
        System.out.println("Chequing Account balance: " + Balence);
    }
}


class Bank_Facade {

    private Saving_Account saving = new Saving_Account();
    private Current_Account current = new Current_Account();
    private Chequing_Account Cheq = new Chequing_Account();

    public void CreateCurrentAccount(int Acc, double Ib) {
        current.createAccount(Acc, Ib);
    }

    public void DepositToCurrentAccount(double amount) {
        current.Deposit(amount);
    }

    public void CurrentCheckBalence() {
        current.Check_Balence();
    }
}

public class Facade {

    public static void main(String[] args) {

        Bank_Facade f1 = new Bank_Facade();

        f1.CreateCurrentAccount(123456, 12000);
        f1.DepositToCurrentAccount(3000);

        f1.CurrentCheckBalence();
    }
}
