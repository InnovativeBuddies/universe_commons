package InnovativeBuddies.patterns.observer;

public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String stockSymbol, double stockPrice);
}
