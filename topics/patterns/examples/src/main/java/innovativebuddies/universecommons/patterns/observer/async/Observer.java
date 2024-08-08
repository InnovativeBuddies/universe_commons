package innovativebuddies.universecommons.patterns.observer.async;

/**
 * Интерфейс наблюдателя, который будет получать обновления от субъекта.
 */
public interface Observer {
    void update(String stockSymbol, double stockPrice);
}
