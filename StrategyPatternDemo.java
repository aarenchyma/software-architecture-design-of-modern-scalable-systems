interface PaymentStrategy {
    void pay(int amount);
}

class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(int amount) {
        System.out.println("[Credit Card] Paying $" + amount +
                " using card number: " + cardNumber);
    }
}

class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(int amount) {
        System.out.println("[PayPal] Paying $" + amount +
                " using PayPal account: " + email);
    }
}

class GooglePayPayment implements PaymentStrategy {
    private String mobile;

    public GooglePayPayment(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public void pay(int amount) {
        System.out.println("[Google Pay] Paying $" + amount +
                " using GPay number: " + mobile);
    }
}

class CryptoPayment implements PaymentStrategy {
    private String wallet;

    public CryptoPayment(String wallet) {
        this.wallet = wallet;
    }

    @Override
    public void pay(int amount) {
        System.out.println("[Crypto] Paying $" + amount +
                " using wallet address: " + wallet);
    }
}

class PaymentProcessor {
    private PaymentStrategy strategy;

    public PaymentProcessor(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void processPayment(int amount) {
        if (strategy == null) {
            System.out.println("No payment method selected.");
            return;
        }
        strategy.pay(amount);
    }
}

public class StrategyPatternDemo {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor(new CreditCardPayment("1234-5678-9012"));
        processor.processPayment(250);

        processor.setStrategy(new PayPalPayment("user@example.com"));
        processor.processPayment(300);

        processor.setStrategy(new GooglePayPayment("9876543210"));
        processor.processPayment(400);

        processor.setStrategy(new CryptoPayment("0xA123Bc456DEf7890"));
        processor.processPayment(999);
    }
}