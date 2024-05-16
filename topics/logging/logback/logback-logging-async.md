# Асинхронное логирование в Logback

## Введение

> Асинхронное логирование позволяет записывать логи в отдельном потоке, что помогает снизить влияние логирования на производительность основного приложения.
> Это особенно полезно в высоконагруженных системах, где синхронное логирование может стать узким местом.

## Как это работает

> Logback поддерживает асинхронное логирование с использованием AsyncAppender.
> Этот appender буферизует сообщения и записывает их в один или несколько целевых appenders в отдельном потоке.
> Вы можете настроить размер очереди, время ожидания и другие параметры для управления поведением асинхронного логирования.

### Шаги для настройки асинхронного логирования

1. Определите целевой appender:
    * Настройте appender, который будет обрабатывать лог-сообщения (например, FileAppender).

2. Создайте AsyncAppender:
    * Свяжите AsyncAppender с целевым appender.
    * Настройте параметры очереди и поведения при переполнении.

3. Свяжите AsyncAppender с root-логгером или конкретными логгерами:
    * Используйте элемент <appender-ref ref="AsyncAppenderName" /> для связывания AsyncAppender с логгерами.

#### Примеры использования

###### Пример с асинхронным файловым appender

```xml

<configuration>

    <!-- Файловый Appender -->
    <appender name="FILE" class="ch.qos.logback.core.FileAppender">
        <file>logs/app.log</file>
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <!-- Асинхронный Appender -->
    <appender name="ASYNC" class="ch.qos.logback.classic.AsyncAppender">
        <appender-ref ref="FILE"/>
        <!-- Размер очереди для лог-сообщений -->
        <queueSize>500</queueSize>
        <!-- Поведение при переполнении очереди (0 означает сбрасывать все сообщения) -->
        <discardingThreshold>0</discardingThreshold>
        <!-- Максимальное время ожидания для завершения записи логов при завершении работы -->
        <maxFlushTime>1000</maxFlushTime>
        <!-- Флаг, указывающий, должна ли выполняться логика сброса данных -->
        <includeCallerData>false</includeCallerData>
    </appender>

    <!-- Настройка корневого логгера -->
    <root level="INFO">
        <appender-ref ref="ASYNC"/>
    </root>

</configuration>

```

##### Параметры AsyncAppender

- `queueSize`: Размер очереди для буферизации лог-сообщений. По умолчанию 256.
- `discardingThreshold`: Процент заполнения очереди, при котором начинают отбрасываться менее важные сообщения. Значение 0 означает сбрасывать все сообщения при переполнении.
- `maxFlushTime`: Максимальное время ожидания (в миллисекундах) для завершения записи логов при завершении работы приложения.
- `includeCallerData`: Флаг, указывающий, должна ли выполняться логика сброса данных о вызывающем методе.

### Заключение

> Асинхронное логирование с использованием AsyncAppender в Logback позволяет значительно повысить производительность логирования в высоконагруженных приложениях.
> Это достигается за счет буферизации и обработки лог-сообщений в отдельном потоке.
> Следуя приведенным шагам и примерам, вы можете настроить асинхронное логирование в вашем приложении для эффективного и быстрого управления логами.