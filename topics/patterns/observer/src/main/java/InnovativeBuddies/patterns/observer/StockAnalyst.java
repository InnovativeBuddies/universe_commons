package InnovativeBuddies.patterns.observer;

public class StockAnalyst implements Observer {
    private final String name;

    public StockAnalyst(String name) {
        this.name = name;
    }

    @Override
    public void update(String stockSymbol, double stockPrice) {
        System.out.println("Analyst " + name + " notified. Stock: " + stockSymbol + " Price: " + stockPrice);
        // Логика анализа на основе обновлений цен
    }
}
