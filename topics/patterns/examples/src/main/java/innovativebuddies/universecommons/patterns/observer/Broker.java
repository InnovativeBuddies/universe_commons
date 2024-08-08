package innovativebuddies.universecommons.patterns.observer;

public class Broker implements Observer {
    private final String name;

    public Broker(String name) {
        this.name = name;
    }

    @Override
    public void update(String stockSymbol, double stockPrice) {
        System.out.println("Broker " + name + " notified. Stock: " + stockSymbol + " Price: " + stockPrice);
        // Логика принятия решений на основе обновлений цен
    }
}
