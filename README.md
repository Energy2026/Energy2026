# Приклад Java-програми ведення показників електролічильників з використанням СКБД MongoDB

## Кроки для виконання

1. Завантажте і встановіть Java Development Kit 17 (або новішу версію) для Windows.
1. Завантажте Maven з https://dlcdn.apache.org/maven/maven-3/3.8.9/binaries/apache-maven-3.8.9-bin.zip і розпакуйте його на локальний комп'ютер.
1. В Windows в параметрах системи додайте системну змінну `MAVEN_HOME=<шлях до папки з Maven>`
1. В Windows в параметрах системи додайте `;<шлях до папки з Maven>\bin` в системну змінну PATH.
1. Встановіть СКБД MongoDB Community Server, вибравши варіант установки Complete → "Run service as Network Service user".
1. Встановіть MongoDB Compass (GUI).
1. Зберіть програму використовуючи команду: `mvn package`.
1. Запустіть програму за допомогою команди: `mvn spring-boot:run`.
1. Відкрийте браузер і перейдіть за адресою: `http://localhost:8081`.

## Налаштування MONGO_URI

За замовчуванням програма використовує адресу `mongodb://localhost:27017/energy-db` для підключення до локальної бази даних.

## Підключення до MongoDB Atlas

Для підключення MongoDB Atlas потрібно записати connection string кластера в змінну середовища `MONGO_URI` наприклад:
```
mongodb+srv://<db-username>:<db-password>@<cluster-url>/energy-db?retryWrites=true&w=majority
```

Порядок налаштування:

1. Створіть кластер у MongoDB Atlas (тариф Free).
1. Створіть користувача бази даних у розділі Database Access і збережіть пароль.
1. Скопіюйте connection string з кнопки Connect → Drivers і збережіть його.
1. Створіть базу даних `energy-db` і колекцію `energy-readings` в розділі Data Explorer.
1. Підставте в URI свої username, password і назву бази даних `energy-db` та збережіть у змінній середовища `MONGO_URI`.
1. Визначте зовнішню IP-адресу (`curl -s https://api.ipify.org && echo`) і додайте її в Network Access.

Приклад запуску з MongoDB Atlas.

Windows Command Prompt:
```
set MONGO_URI=mongodb+srv://db-username:db-password@cluster0.abcde.mongodb.net/energy-db?retryWrites=true^&w=majority
mvn spring-boot:run
```

PowerShell:
```
$env:MONGO_URI="mongodb+srv://db-username:db-password@cluster0.abcde.mongodb.net/energy-db?retryWrites=true&w=majority"
mvn spring-boot:run
```

Linux/macOS:
```
export MONGO_URI="mongodb+srv://db-username:db-password@cluster0.abcde.mongodb.net/energy-db?retryWrites=true&w=majority"
mvn spring-boot:run
```

> Примітка: якщо пароль містить спеціальні символи, їх потрібно URL-кодувати в connection string. Наприклад, символ `@` потрібно замінити на `%40`.

## Запуск тестів

### Unit-тести

Запускає unit-тести з перевіркою покриття коду (мінімум 75% рядків):

```
mvn package
```

### Інтеграційні тести

Запускає інтеграційні тести з реальною MongoDB у тимчасовому Docker-контейнері (Testcontainers).
Потребує встановленого та запущеного Docker.

```
mvn verify -DskipUTs=true
```

> На Windows з Docker Desktop необхідно увімкнути параметр "Expose daemon on tcp://localhost:2375 without TLS" у налаштуваннях Docker Desktop → General.

## Правила покриття коду

| Тип тестів | Область | Мінімальне покриття |
|---|---|---|
| Unit (Surefire) | Весь проєкт (BUNDLE) | 75% рядків |
| Integration (Failsafe) | `EnergyReading` | 100% рядків |
| Integration (Failsafe) | `EnergyReadingController` | 100% рядків |

## CI/CD Pipeline

```
Static Analysis → Build (unit tests) → Integration Tests → Publish Package
```

| Крок | Що робить |
|---|---|
| Static Analysis | Перевірка стилю коду (Checkstyle) |
| Build | Збірка проєкту, unit-тести, JaCoCo coverage ≥ 75% |
| Integration Tests | Запуск Testcontainers + MockMvc, JaCoCo coverage = 100% для EnergyReading і EnergyReadingController |
| Publish Package | Публікація артефакту в GitHub Packages (тести не повторюються) |

## Результати запуску застосунку

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.5.4)

INFO --- [main] org.energy.EnergyApplication : Starting EnergyApplication
INFO --- [main] org.mongodb.driver.cluster   : Adding discovered server localhost:27017
INFO --- [main] org.energy.EnergyApplication : Started EnergyApplication in 2.3 seconds (JVM running for 3.1)
```

Після запуску відкрийте браузер за адресою: `http://localhost:8081`

## Результати збірки

```
[INFO] --- surefire:3.5.1:test (default-test) @ energy2026 ---
[INFO] Tests run: 14, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] --- jacoco:0.8.14:check (check) @ energy2026 ---
[INFO] All coverage checks have been met.
[INFO]
[INFO] BUILD SUCCESS
```

## Результати інтеграційних тестів

```
[INFO] --- failsafe:3.5.1:integration-test (default) @ energy2026 ---
[INFO] Running org.energy.EnergyReadingFlowIntegrationTests
[INFO] Tests run: 7, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] --- jacoco:0.8.14:check (check-integration) @ energy2026 ---
[INFO] All coverage checks have been met.
[INFO]
[INFO] BUILD SUCCESS
```
