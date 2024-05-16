package InnovativeBuddies.patterns.observer.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Наблюдатель, который анализирует обновления цен акций.
 */
@Component
public class StockAnalyst implements Observer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String name;

    // Внедрение значения из конфигурационного файла
    public StockAnalyst(@Value("${analyst.name:DefaultAnalyst}") String name) {
        this.name = name;
    }

    @Override
    public void update(String stockSymbol, double stockPrice) {
        logger.debug("Analyst " + name + " notified. Stock: " + stockSymbol + " Price: " + stockPrice);
        // Логика анализа на основе обновлений цен
    }

    @Override
    public String toString() {
        return "StockAnalyst{" + "name='" + name + '\'' + '}';
    }
}
