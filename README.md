# Кроки для виконання
1. Завантажте і встановіть Java Development Kit 23 for Windows.
1. Завантажте Maven з https://dlcdn.apache.org/maven/maven-3/3.8.9/binaries/apache-maven-3.8.9-bin.zip і розпакуйте його на локальний комп'ютер.
1. В Windows в параметрах системи додайте системну змінну `MAVEN_HOME=<шлях до папки з Maven>`
1. В Windows в параметрах системи додайте `;<шлях до папки з Maven>\bin` в системну змінну PATH.
1. Встановіть СКБД MongoDB Community Server, вибравши варіант установки Complete → "Run service as Network Service user".
1. Встановіть MongoDB Compass (GUI).
1. Створіть базу даних `energy-db` і колекцію в ній `energy-readings`.
1. Зберіть програму використовуючи команду: `mvn clean install`.
1. Запустіть програму за допомогою команди: `mvn exec:java -D"exec.mainClass=com.energy.EnergyApplication"`.
1. Якщо термінал відображає знаки питання "?" замість українських символів, зконфігуруйте термінал на відображення шрифту в форматі UTF-8 виконавши команду `chcp 65001`.

# Результати виконання програми
```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.5.4)

INFO --- [lication.main()] com.energy.EnergyApplication : Starting EnergyApplication
INFO --- [lication.main()] com.energy.EnergyApplication : Started EnergyApplication in 2.1 seconds
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
EnergyReading { id="..."
 consumer="Петренко Марія Василівна"
 supplier="ЕнергоПостач"
 technician="Сидоренко Олег Миколайович"
 reading_date="2024-09-01"
 reading_value="875.2"
 address="м. Київ, вул. Лесі Українки, 3"
 tariff_zone="Нічна"
 price_per_kwh="1.32"
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
Введіть номер команди (1-4): 1
12 документів з показниками електролічильників завантажено з CSV.
1. Додати показники з CSV-файлу
2. Подивитись показники
3. Видалити показники
4. Вихід
Введіть номер команди (1-4): 4
INFO --- [ionShutdownHook] org.mongodb.driver.connection : Closed connection to localhost:27017
```
