package InnovativeBuddies.patterns.observer.async;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import java.util.Map;

/**
 * Реализация субъекта, управляющего списком наблюдателей и уведомляющего их об изменениях цен акций.
 */
@Component
public class StockMarket implements Subject {
    private final List<Observer> observers = new CopyOnWriteArrayList<>();
    private final Map<String, Double> stockPrices = new ConcurrentHashMap<>();

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
        logger.info("Observer registered: " + observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
        logger.info("Observer removed: " + observer);
    }

    @Override
    public void notifyObservers(String stockSymbol, double stockPrice) {
        for (Observer observer : observers) {
            observer.update(stockSymbol, stockPrice);
        }
    }

    public void setStockPrice(String stockSymbol, double stockPrice) {
        stockPrices.put(stockSymbol, stockPrice);
        logger.info("Stock price updated: " + stockSymbol + " = " + stockPrice);
        notifyObservers(stockSymbol, stockPrice);
    }

    public Double getStockPrice(String stockSymbol) {
        return stockPrices.get(stockSymbol);
    }
}
