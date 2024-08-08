package innovativebuddies.universecommons.patterns.observer.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Класс, выполняющийся при запуске приложения.
 */
@Component
public class ApplicationRunner implements CommandLineRunner {

    private final StockMarket stockMarket;
    private final StockMarketService stockMarketService;
    private final Broker broker;
    private final StockAnalyst stockAnalyst;

    @Autowired
    public ApplicationRunner(StockMarket stockMarket, StockMarketService stockMarketService, Broker broker, StockAnalyst stockAnalyst) {
        this.stockMarket = stockMarket;
        this.stockMarketService = stockMarketService;
        this.broker = broker;
        this.stockAnalyst = stockAnalyst;
    }

    @Override
    public void run(String... args) throws Exception {
        // Регистрируем наблюдателей
        stockMarket.registerObserver(broker);
        stockMarket.registerObserver(stockAnalyst);

        // Асинхронное обновление цен акций
        stockMarketService.updateStockPrice("AAPL", 150.0);
        stockMarketService.updateStockPrice("GOOGL", 2800.0);
        stockMarketService.updateStockPrice("AMZN", 3400.0);
    }
}
