package InnovativeBuddies.patterns.observer.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Наблюдатель, который реагирует на обновления цен акций.
 */
@Component
public class Broker implements Observer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String name;

    // Внедрение значения из конфигурационного файла
    public Broker(@Value("${broker.name:DefaultBroker}") String name) {
        this.name = name;
    }

    @Override
    public void update(String stockSymbol, double stockPrice) {
        logger.info("Broker " + name + " notified. Stock: " + stockSymbol + " Price: " + stockPrice);
        // Логика принятия решений на основе обновлений цен
    }

    @Override
    public String toString() {
        return "Broker{" + "name='" + name + '\'' + '}';
    }
}
