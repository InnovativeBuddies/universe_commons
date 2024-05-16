package logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class LoggingExample {

    private static final Logger logger = LoggerFactory.getLogger(LoggingExample.class);

    public static void main(String[] args) {
        // Установка пользовательских свойств MDC
        MDC.put("userId", "user123");
        MDC.put("transactionId", "txn456");
        MDC.put("sessionId", "session789");

        MDC.put("request_uid", "req123");
        MDC.put("request_address", "192.168.0.1");
        MDC.put("request_time", "2023-05-16T12:34:56.789");
        MDC.put("request_method", "GET");
        MDC.put("request_url", "/api/resource");
        MDC.put("user_name", "john_doe");
        MDC.put("native_id", "native123");
        MDC.put("native_type", "typeA");
        MDC.put("action_code", "codeXYZ");
        MDC.put("action_status", "success");
        MDC.put("response_status", "200");
        MDC.put("request", "{\"param\":\"value\"}");
        MDC.put("response", "{\"result\":\"ok\"}");

        try {
            // Пример логирования различных уровней
            logger.trace("This is a TRACE level message");
            logger.debug("This is a DEBUG level message");
            logger.info("This is an INFO level message");
            logger.warn("This is a WARN level message");
            logger.error("This is an ERROR level message");
            simulateError();
        } catch (Exception e) {
            logger.error("An exception occurred: ", e);
        } finally {
            // Очистка MDC
            MDC.clear();
        }
    }

    private static void simulateError() throws Exception {
        throw new Exception("Simulated exception");

    }
}
