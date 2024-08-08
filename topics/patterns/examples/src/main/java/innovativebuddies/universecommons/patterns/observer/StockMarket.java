package innovativebuddies.universecommons.patterns.observer;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class StockMarket implements Subject {
    private final CopyOnWriteArrayList<Observer> observers = new CopyOnWriteArrayList<>();
    private final ConcurrentHashMap<String, Double> stockPrices = new ConcurrentHashMap<>();

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String stockSymbol, double stockPrice) {
        for (Observer observer : observers) {
            observer.update(stockSymbol, stockPrice);
        }
    }

    public void setStockPrice(String stockSymbol, double stockPrice) {
        stockPrices.put(stockSymbol, stockPrice);
        notifyObservers(stockSymbol, stockPrice);
    }

    public Double getStockPrice(String stockSymbol) {
        return stockPrices.get(stockSymbol);
    }
}
