package assignment.patterns.behavioral.observer;

public class ObserverDemo {
    public static void main(String[] args) {
        Stock stock = new Stock("Educational Initiatives", 8500);

        stock.addObserver(new Investor("Het"));
        stock.addObserver(new Investor("Mukesh"));

        stock.setPrice(3600);
        stock.setPrice(3700);
    }
}