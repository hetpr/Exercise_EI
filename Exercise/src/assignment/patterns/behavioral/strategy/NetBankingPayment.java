package assignment.patterns.behavioral.strategy;

public class NetBankingPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using NetBanking.");
    }
}