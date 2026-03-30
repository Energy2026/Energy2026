# Приклад Java-програми ведення показників електролічильників з використанням СКБД MongoDB

## Кроки для виконання
1. Завантажте і встановіть Java Development Kit 17 (або новішу версію) для Windows.
1. Завантажте Maven з https://dlcdn.apache.org/maven/maven-3/3.8.9/binaries/apache-maven-3.8.9-bin.zip і розпакуйте його на локальний комп'ютер.
1. В Windows в параметрах системи додайте системну змінну `MAVEN_HOME=<шлях до папки з Maven>`
1. В Windows в параметрах системи додайте `;<шлях до папки з Maven>\bin` в системну змінну PATH.
1. Встановіть СКБД MongoDB Community Server, вибравши варіант установки Complete → "Run service as Network Service user".
1. Встановіть MongoDB Compass (GUI).
1. Створіть базу даних `energy-db` і колекцію в ній `energy-readings`.
1. Зберіть програму використовуючи команду: `mvn clean install`.
1. Запустіть програму за допомогою команди: `mvn exec:java`.
1. Якщо термінал відображає знаки питання "?" замість українських символів, зконфігуруйте термінал на відображення шрифту в форматі UTF-8 виконавши команду `chcp 65001`.

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
mvn exec:java
```

PowerShell:
```
$env:MONGO_URI="mongodb+srv://db-username:db-password@cluster0.abcde.mongodb.net/energy-db?retryWrites=true&w=majority"
mvn exec:java
```

Linux/macOS:
```
export MONGO_URI="mongodb+srv://db-username:db-password@cluster0.abcde.mongodb.net/energy-db?retryWrites=true&w=majority"
mvn exec:java
```

> Примітка: якщо пароль містить спеціальні символи, їх потрібно URL-кодувати в connection string. Наприклад, символ `@` потрібно замінити на `%40`.

## Результати виконання програми
```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.5.4)

INFO --- [lication.main()] org.energy.EnergyApplication : Starting EnergyApplication
INFO --- [lication.main()] org.mongodb.driver.cluster   : Adding discovered server cluster0.mongodb.net:27017
INFO --- [lication.main()] org.mongodb.driver.cluster   : Discovered replica set primary cluster0.mongodb.net:27017
INFO --- [lication.main()] org.energy.EnergyApplication : Started EnergyApplication in 2.1 seconds
1. Додати показники з CSV-файлу
2. Подивитись показники
3. Видалити показники
4. Вихід
Введіть номер команди (1-4): 1
12 документів з показниками електролічильників завантажено з CSV.
1. Додати показники з CSV-файлу
2. Подивитись показники
3. Видалити показники
4. Вихід
Введіть номер команди (1-4): 2
Знайдено 12 документів показників:
EnergyReading { id="..."
 consumer="Коваленко Іван Петрович"
 supplier="ЕнергоПостач"
 technician="Сидоренко Олег Миколайович"
 reading_date="2024-09-01"
 reading_value="1250.5"
 address="м. Київ, вул. Шевченка, 12, кв. 5"
 tariff_zone="Денна"
 price_per_kwh="2.85"
 experience_years="5"
 meter_type="Електронний"
}
... (всього 12 документів)
1. Додати показники з CSV-файлу
2. Подивитись показники
3. Видалити показники
4. Вихід
Введіть номер команди (1-4): 3
Показники видалено.
1. Додати показники з CSV-файлу
2. Подивитись показники
3. Видалити показники
4. Вихід
Введіть номер команди (1-4): 4
INFO --- [ionShutdownHook] org.mongodb.driver.connection : Closed connection to cluster0.mongodb.net:27017
```