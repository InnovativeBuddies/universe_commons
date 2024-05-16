package InnovativeBuddies.patterns.observer.async;

/**
 * Интерфейс субъекта, который управляет наблюдателями и уведомляет их об изменениях.
 */
public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String stockSymbol, double stockPrice);
}
