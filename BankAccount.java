abstract class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    
    // constructor
    public BankAccount(String accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }
    
    // concrete methods
    public void deposit(double amount){
        if (amount <= 0 ) {
            throw new IllegalArgumentException("Deposit must be greater than zero");
        }
        
        this.balance +=amount;
        System.out.println("Deposited: " + amount + " | New balance: " + this.balance);
        
    }
    public void displayAccountInfo() {
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Account Holder : " + accountHolderName);
        System.out.println("Balance : " + balance);
    }
    
    public abstract void withdraw(double amount);
    public abstract double calculateInterest();
    
    public String getAccountNumber() { return accountNumber; }
    public String getAccountHolderName() { return accountHolderName; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
    
}

class SavingsAccount extends BankAccount {
    private static final double INTEREST_RATE = 0.04;
    private static final int WITHDRAWAL_LIMIT = 3;
    private int withdrawalCount = 0;
    
    public SavingsAccount(String accountNumber, String accountHolderName, double balance) {
        super(accountNumber, accountHolderName, balance);
    }
    
    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than 0");
        }
        
        if (withdrawalCount >= WITHDRAWAL_LIMIT) {
            throw new IllegalArgumentException("Withdrawal limit reached");
        }
        
        if (amount > getBalance()) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        
        setBalance(getBalance() - amount);
        withdrawalCount++;
        System.out.println("Withdrawn: " + amount + " | Remaining balance: " + getBalance());
        System.out.println("Withdrawals used: " + withdrawalCount + "/" + WITHDRAWAL_LIMIT);
    }
    
    @Override
    public double calculateInterest() {
        double interest = getBalance() *INTEREST_RATE;
        return interest;
    }
}

class FixedDepositAccount extends BankAccount {
    private static final double INTEREST_RATE = 0.07;
    private int maturityPeriod; 
    private boolean matured = false;

    public FixedDepositAccount(String accountNumber, String accountHolderName, double balance, int maturityPeriod) {
        super(accountNumber, accountHolderName, balance);
        this.maturityPeriod = maturityPeriod;
    }

    public void setMatured(boolean matured) {
        this.matured = matured;
    }
    
    @Override
    public void withdraw(double amount) {
        if (!matured) {
            throw new IllegalStateException(
                "Withdrawals not allowed before maturity period of " + maturityPeriod + " year(s)"
            );
        }
        if (amount > getBalance()) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        setBalance(getBalance() - amount);
        System.out.println("Withdrawn: " + amount + " | Remaining balance: " + getBalance());
    }
    
     @Override
    public double calculateInterest() {
        double interest = getBalance() * INTEREST_RATE * maturityPeriod;
        System.out.println("Fixed deposit interest (7% over " + maturityPeriod + " year(s)): " + interest);
        return interest;
    }

    @Override
    public void displayAccountInfo() {
        super.displayAccountInfo();
        System.out.println("Maturity Period: " + maturityPeriod + " year(s)");
        System.out.println("Matured        : " + matured);
    }
    
    
}


