package InnovativeBuddies.patterns.observer.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Сервис для обновления цен акций и уведомления наблюдателей.
 */
@Service
public class StockMarketService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final StockMarket stockMarket;

    @Autowired
    public StockMarketService(StockMarket stockMarket) {
        this.stockMarket = stockMarket;
    }

    /**
     * Асинхронное обновление цен акций.
     *
     * @param stockSymbol символ акции
     * @param stockPrice новая цена акции
     */
    @Async
    public void updateStockPrice(String stockSymbol, double stockPrice) {
        logger.info("Updating stock price asynchronously: " + stockSymbol + " = " + stockPrice);
        stockMarket.setStockPrice(stockSymbol, stockPrice);
    }
}
