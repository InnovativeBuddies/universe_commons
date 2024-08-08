package innovativebuddies.universecommons.patterns.observer;

public class Main {
    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket();

        Broker broker1 = new Broker("Broker1");
        Broker broker2 = new Broker("Broker2");
        StockAnalyst analyst1 = new StockAnalyst("Analyst1");

        stockMarket.registerObserver(broker1);
        stockMarket.registerObserver(broker2);
        stockMarket.registerObserver(analyst1);

        stockMarket.setStockPrice("AAPL", 150.0);
        stockMarket.setStockPrice("GOOGL", 2800.0);
        stockMarket.setStockPrice("AMZN", 3400.0);
    }
}
