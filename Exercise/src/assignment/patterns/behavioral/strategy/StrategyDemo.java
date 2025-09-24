package assignment.patterns.behavioral.strategy;

public class StrategyDemo {
    public static void main(String[] args) {
        PaymentStrategy payment = new CreditCardPayment();
        payment.pay(500);

        payment = new UpiPayment();
        payment.pay(300);

        payment = new NetBankingPayment();
        payment.pay(700);
    }
}