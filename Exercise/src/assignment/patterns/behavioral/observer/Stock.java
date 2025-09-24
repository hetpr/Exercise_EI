package assignment.patterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

public class Stock {
    private String name;
    private double price;
    private List<Observer> observers = new ArrayList<>();

    public Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void setPrice(double price) {
        this.price = price;
        notifyAllObservers();
    }

    private void notifyAllObservers() {
        for (Observer o : observers) {
            o.update(name, price);
        }
    }
}