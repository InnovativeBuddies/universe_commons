# Использование нескольких appenders в Logback

## Введение

> В Logback вы можете использовать несколько appenders для управления логированием.
> Appenders - это компоненты, которые обрабатывают выходные данные логов.
> Использование нескольких appenders позволяет направлять логи в разные места, такие как консоль, файлы, базы данных и удаленные серверы.
> Это полезно для разделения логов на разные каналы и обеспечения их доступности в различных форматах.

## Как это работает

> Каждый appender в Logback отвечает за определенную задачу, например, вывод логов на консоль или запись логов в файл.
> Вы можете комбинировать несколько appenders, чтобы записывать логи одновременно в несколько мест.
> Logback поддерживает различные типы appenders, такие как ConsoleAppender, FileAppender, RollingFileAppender и DBAppender.

### Шаги для настройки нескольких appenders

1. Определите каждый appender в конфигурации Logback:
   * Настройте каждый appender с нужными параметрами, такими как форматирование, местоположение файла или параметры подключения к базе данных.

2. Свяжите каждый appender с root-логгером или конкретными логгерами:
   * Используйте элемент <appender-ref ref="AppenderName" /> для связывания appender с логгерами.

#### Примеры использования

```xml

<configuration>

   <!-- Консольный Appender -->
   <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
      <encoder>
         <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
      </encoder>
   </appender>

   <!-- Rolling File Appender -->
   <appender name="ROLLING" class="ch.qos.logback.core.rolling.RollingFileAppender">
      <file>logs/app.log</file>
      <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
         <!-- Ротация файлов каждый день -->
         <fileNamePattern>logs/app.%d{yyyy-MM-dd}.log</fileNamePattern>
         <!-- Хранить логи за последние 30 дней -->
         <maxHistory>30</maxHistory>
      </rollingPolicy>
      <encoder>
         <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
      </encoder>
   </appender>

   <!-- Настройка корневого логгера -->
   <root level="INFO">
      <appender-ref ref="STDOUT"/>
      <appender-ref ref="ROLLING"/>
   </root>

</configuration>
```

### Заключение

> Использование нескольких appenders в Logback позволяет вам направлять логи в разные места и форматы одновременно.
> Это дает вам гибкость и контроль над логированием, делая его более эффективным и удобным для анализа.
> Следуя приведенным шагам и используя пример конфигурации, вы можете настроить и использовать несколько appenders для вашего приложения.
