# Логирование с цветовым выделением уровней в Logback

## Введение

> Цветовое выделение уровней логирования делает логи более читабельными, особенно при выводе в консоль.
> Различные уровни логирования, такие как INFO, DEBUG, ERROR, выделяются разными цветами, что упрощает их визуальное различение.

## Как это работает

> Logback предоставляет `%highlight` и `%style` спецификаторы, которые позволяют раскрашивать различные части лог-сообщений в зависимости от уровня логирования.
> Эти спецификаторы применяются к шаблону форматирования логов.

### Шаги для настройки цветового выделения уровней логирования

1. Определите консольный appender в конфигурации Logback:
   * Настройте шаблон форматирования с использованием спецификаторов %highlight или %style для цветового выделения.

2. Свяжите appender с root-логгером или конкретными логгерами:
   * Используйте элемент <appender-ref ref="AppenderName" /> для связывания appender с логгерами.

#### Примеры использования

###### Пример конфигурации Logback для цветового выделения с %highlight

```xml

<configuration>

   <!-- Консольный Appender с цветовым выделением -->
   <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
      <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
         <pattern>%highlight(%d{yyyy-MM-dd HH:mm:ss.SSS}) [%thread] %-5level %logger{36} - %msg%n</pattern>
      </encoder>
   </appender>

   <!-- Настройка корневого логгера -->
   <root level="INFO">
      <appender-ref ref="STDOUT"/>
   </root>

</configuration>
```

###### Пример конфигурации Logback для цветового выделения с %style

```xml

<configuration>

   <!-- Консольный Appender с цветовым выделением -->
   <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
      <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
         <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %style(%-5level){-color} %logger{36} - %msg%n</pattern>
      </encoder>
   </appender>

   <!-- Настройка корневого логгера -->
   <root level="INFO">
      <appender-ref ref="STDOUT"/>
   </root>

</configuration>
```

#### Пояснение

- `%highlight:` Автоматически применяет цветовое выделение к заданной части шаблона в зависимости от уровня логирования.
   * `INFO`: Зеленый цвет.
   * `WARN`: Желтый цвет.
   * `ERROR`: Красный цвет.

- `%style:` Позволяет более гибко настраивать цвета для различных частей шаблона.
   * `{-color}`: Задает использование цвета.

### Заключение

> Цветовое выделение уровней логирования в Logback делает логи более читабельными и упрощает их анализ.
> Используя спецификаторы %highlight и %style, вы можете легко настроить консольный appender для цветового выделения лог-сообщений.
> Следуя приведенным шагам и примерам, вы можете настроить логирование с цветовым выделением для вашего приложения.
