# Кроки для виконання
1. Встановіть локально PostgreSQL, Maven та Java.
1. Створіть базу даних `postgres`.
1. Вкажіть правильне значення властивості `hibernate.connection.password` у файлі `src\main\resources\hibernate.cfg.xml`.
1. Зберіть програму за допомогою команди: `mvn clean install`.
1. Запустіть програму за допомогою команди: `mvn exec:java -D"exec.mainClass=org.energy.MainApp"`.
1. Якщо термінал відображає знаки питання "?" замість українських символів, зконфігуруйте термінал на відображення шрифту в форматі UTF-8 виконавши команду `chcp 65001`.

# Результати виконання програми
```
лют. 19, 2026 1:43:49 ПП org.hibernate.Version logVersion
INFO: HHH000412: Hibernate ORM core version 5.6.14.Final
лют. 19, 2026 1:43:49 ПП org.hibernate.boot.jaxb.internal.stax.LocalXmlResourceResolver resolveEntity
WARN: HHH90000012: Recognized obsolete hibernate namespace http://hibernate.sourceforge.net/hibernate-configuration. Use namespace http://www.hibernate.org/dtd/hibernate-configuration instead.  Support for obsolete DTD/XSD namespaces may be removed at any time.
лют. 19, 2026 1:43:50 ПП org.hibernate.annotations.common.reflection.java.JavaReflectionManager <clinit>
INFO: HCANN000001: Hibernate Commons Annotations {5.1.2.Final}
лют. 19, 2026 1:43:50 ПП org.hibernate.engine.jdbc.connections.internal.ConnectionProviderInitiator instantiateC3p0Provider
WARN: HHH000022: c3p0 properties were encountered, but the c3p0 provider class was not found on the classpath; these properties are going to be ignored.
лют. 19, 2026 1:43:50 ПП org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl configure
WARN: HHH10001002: Using Hibernate built-in connection pool (not for production use!)
лют. 19, 2026 1:43:50 ПП org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001005: using driver [org.postgresql.Driver] at URL [jdbc:postgresql://localhost:5432/postgres]
лют. 19, 2026 1:43:50 ПП org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001001: Connection properties: {user=postgres, password=****}
лют. 19, 2026 1:43:50 ПП org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001003: Autocommit mode: false
лют. 19, 2026 1:43:50 ПП org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PooledConnections <init>
INFO: HHH000115: Hibernate connection pool size: 20 (min=1)
лют. 19, 2026 1:43:50 ПП org.hibernate.dialect.Dialect <init>
INFO: HHH000400: Using dialect: org.hibernate.dialect.PostgreSQLDialect
Hibernate: 
    
    alter table meter_readings 
       drop constraint FKnalaulqjlf29g1dlukdeyg0g4
Hibernate: 
    
    alter table meter_readings 
       drop constraint FK5ocodser4gy7mopxcli1wi6h5
Hibernate: 
    
    alter table meter_readings 
       drop constraint FKmulw21o2i8mjv5oln48xc9vwk
Hibernate: 
    
    alter table meters 
       drop constraint FKft2e4ckms5or0ohcw4p4os307
Hibernate: 
    
    alter table meters 
       drop constraint FK651xq90jh5n75y5l8cea3cl6m
Hibernate: 
    
    alter table technicians 
       drop constraint FK13ocsbkrk7ketkmr0pm0dcx4d
Hibernate: 
    
    drop table if exists consumers cascade
лют. 19, 2026 1:43:51 ПП org.hibernate.resource.transaction.backend.jdbc.internal.DdlTransactionIsolatorNonJtaImpl getIsolatedConnection
INFO: HHH10001501: Connection obtained from JdbcConnectionAccess [org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator$ConnectionProviderJdbcConnectionAccess@181b8c4b] for (non-JTA) DDL execution was not in auto-commit mode; the Connection 'local transaction' will be committed and the Connection will be set into auto-commit mode.
Hibernate: 
    
    drop table if exists meter_readings cascade
Hibernate: 
    
    drop table if exists meter_types cascade
Hibernate: 
    
    drop table if exists meters cascade
Hibernate: 
    
    drop table if exists suppliers cascade
Hibernate: 
    
    drop table if exists tariff_zones cascade
Hibernate: 
    
    drop table if exists technicians cascade
Hibernate: 
    
    create table consumers (
       id  serial not null,
        address varchar(255),
        full_name varchar(255),
        primary key (id)
    )
лют. 19, 2026 1:43:51 ПП org.hibernate.resource.transaction.backend.jdbc.internal.DdlTransactionIsolatorNonJtaImpl getIsolatedConnection
INFO: HHH10001501: Connection obtained from JdbcConnectionAccess [org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator$ConnectionProviderJdbcConnectionAccess@18b8d173] for (non-JTA) DDL execution was not in auto-commit mode; the Connection 'local transaction' will be committed and the Connection will be set into auto-commit mode.
Hibernate: 
    
    create table meter_readings (
       id  serial not null,
        reading_date date,
        reading_value numeric(19, 2),
        meter_id int4,
        tariff_zone_id int4,
        technician_id int4,
        primary key (id)
    )
Hibernate: 
    
    create table meter_types (
       id  serial not null,
        name varchar(255),
        primary key (id)
    )
Hibernate: 
    
    create table meters (
       id  serial not null,
        installed_at date,
        consumer_id int4,
        meter_type_id int4,
        primary key (id)
    )
Hibernate: 
    
    create table suppliers (
       id  serial not null,
        name varchar(255),
        primary key (id)
    )
Hibernate: 
    
    create table tariff_zones (
       id  serial not null,
        name varchar(255),
        price_per_kwh numeric(19, 2),
        primary key (id)
    )
Hibernate: 
    
    create table technicians (
       id  serial not null,
        experience_years int4,
        full_name varchar(255),
        supplier_id int4,
        primary key (id)
    )
Hibernate: 
    
    alter table meter_readings 
       add constraint FKnalaulqjlf29g1dlukdeyg0g4 
       foreign key (meter_id) 
       references meters
Hibernate: 
    
    alter table meter_readings 
       add constraint FK5ocodser4gy7mopxcli1wi6h5 
       foreign key (tariff_zone_id) 
       references tariff_zones
Hibernate: 
    
    alter table meter_readings 
       add constraint FKmulw21o2i8mjv5oln48xc9vwk 
       foreign key (technician_id) 
       references technicians
Hibernate: 
    
    alter table meters 
       add constraint FKft2e4ckms5or0ohcw4p4os307 
       foreign key (consumer_id) 
       references consumers
Hibernate: 
    
    alter table meters 
       add constraint FK651xq90jh5n75y5l8cea3cl6m 
       foreign key (meter_type_id) 
       references meter_types
Hibernate: 
    
    alter table technicians 
       add constraint FK13ocsbkrk7ketkmr0pm0dcx4d 
       foreign key (supplier_id) 
       references suppliers
Hibernate: 
    insert 
    into
        suppliers
        (name) 
    values
        (?)
Hibernate: 
    insert 
    into
        suppliers
        (name) 
    values
        (?)
Hibernate: 
    insert 
    into
        tariff_zones
        (name, price_per_kwh) 
    values
        (?, ?)
Hibernate: 
    insert 
    into
        tariff_zones
        (name, price_per_kwh) 
    values
        (?, ?)
Hibernate: 
    insert 
    into
        meter_types
        (name) 
    values
        (?)
Hibernate: 
    insert 
    into
        meter_types
        (name) 
    values
        (?)
Hibernate: 
    insert 
    into
        technicians
        (experience_years, full_name, supplier_id) 
    values
        (?, ?, ?)
Hibernate: 
    insert 
    into
        technicians
        (experience_years, full_name, supplier_id) 
    values
        (?, ?, ?)
Hibernate: 
    insert 
    into
        technicians
        (experience_years, full_name, supplier_id) 
    values
        (?, ?, ?)
Hibernate: 
    insert 
    into
        consumers
        (address, full_name) 
    values
        (?, ?)
Hibernate: 
    insert 
    into
        consumers
        (address, full_name) 
    values
        (?, ?)
Hibernate: 
    insert 
    into
        consumers
        (address, full_name) 
    values
        (?, ?)
Hibernate: 
    insert 
    into
        consumers
        (address, full_name) 
    values
        (?, ?)
Hibernate: 
    insert 
    into
        consumers
        (address, full_name) 
    values
        (?, ?)
Hibernate: 
    insert 
    into
        consumers
        (address, full_name) 
    values
        (?, ?)
Hibernate: 
    insert 
    into
        consumers
        (address, full_name) 
    values
        (?, ?)
Hibernate: 
    insert 
    into
        consumers
        (address, full_name) 
    values
        (?, ?)
Hibernate: 
    insert 
    into
        consumers
        (address, full_name) 
    values
        (?, ?)
Hibernate: 
    insert 
    into
        consumers
        (address, full_name) 
    values
        (?, ?)
Hibernate: 
    insert 
    into
        consumers
        (address, full_name) 
    values
        (?, ?)
Hibernate: 
    insert 
    into
        consumers
        (address, full_name) 
    values
        (?, ?)
Hibernate: 
    insert 
    into
        meters
        (consumer_id, installed_at, meter_type_id) 
    values
        (?, ?, ?)
Hibernate: 
    insert 
    into
        meters
        (consumer_id, installed_at, meter_type_id) 
    values
        (?, ?, ?)
Hibernate: 
    insert 
    into
        meters
        (consumer_id, installed_at, meter_type_id) 
    values
        (?, ?, ?)
Hibernate: 
    insert 
    into
        meters
        (consumer_id, installed_at, meter_type_id) 
    values
        (?, ?, ?)
Hibernate: 
    insert 
    into
        meters
        (consumer_id, installed_at, meter_type_id) 
    values
        (?, ?, ?)
Hibernate: 
    insert 
    into
        meters
        (consumer_id, installed_at, meter_type_id) 
    values
        (?, ?, ?)
Hibernate: 
    insert 
    into
        meters
        (consumer_id, installed_at, meter_type_id) 
    values
        (?, ?, ?)
Hibernate: 
    insert 
    into
        meters
        (consumer_id, installed_at, meter_type_id) 
    values
        (?, ?, ?)
Hibernate: 
    insert 
    into
        meters
        (consumer_id, installed_at, meter_type_id) 
    values
        (?, ?, ?)
Hibernate: 
    insert 
    into
        meters
        (consumer_id, installed_at, meter_type_id) 
    values
        (?, ?, ?)
Hibernate: 
    insert 
    into
        meters
        (consumer_id, installed_at, meter_type_id) 
    values
        (?, ?, ?)
Hibernate: 
    insert 
    into
        meters
        (consumer_id, installed_at, meter_type_id) 
    values
        (?, ?, ?)
Hibernate: 
    insert 
    into
        meter_readings
        (meter_id, reading_date, reading_value, tariff_zone_id, technician_id) 
    values
        (?, ?, ?, ?, ?)
Hibernate: 
    insert 
    into
        meter_readings
        (meter_id, reading_date, reading_value, tariff_zone_id, technician_id) 
    values
        (?, ?, ?, ?, ?)
Hibernate: 
    insert 
    into
        meter_readings
        (meter_id, reading_date, reading_value, tariff_zone_id, technician_id) 
    values
        (?, ?, ?, ?, ?)
Hibernate: 
    insert 
    into
        meter_readings
        (meter_id, reading_date, reading_value, tariff_zone_id, technician_id) 
    values
        (?, ?, ?, ?, ?)
Hibernate: 
    insert 
    into
        meter_readings
        (meter_id, reading_date, reading_value, tariff_zone_id, technician_id) 
    values
        (?, ?, ?, ?, ?)
Hibernate: 
    insert 
    into
        meter_readings
        (meter_id, reading_date, reading_value, tariff_zone_id, technician_id) 
    values
        (?, ?, ?, ?, ?)
Hibernate: 
    insert 
    into
        meter_readings
        (meter_id, reading_date, reading_value, tariff_zone_id, technician_id) 
    values
        (?, ?, ?, ?, ?)
Hibernate: 
    insert 
    into
        meter_readings
        (meter_id, reading_date, reading_value, tariff_zone_id, technician_id) 
    values
        (?, ?, ?, ?, ?)
Hibernate: 
    insert 
    into
        meter_readings
        (meter_id, reading_date, reading_value, tariff_zone_id, technician_id) 
    values
        (?, ?, ?, ?, ?)
Hibernate: 
    insert 
    into
        meter_readings
        (meter_id, reading_date, reading_value, tariff_zone_id, technician_id) 
    values
        (?, ?, ?, ?, ?)
Hibernate: 
    insert 
    into
        meter_readings
        (meter_id, reading_date, reading_value, tariff_zone_id, technician_id) 
    values
        (?, ?, ?, ?, ?)
Hibernate: 
    insert 
    into
        meter_readings
        (meter_id, reading_date, reading_value, tariff_zone_id, technician_id) 
    values
        (?, ?, ?, ?, ?)
Дані успішно збережено!
Hibernate: 
    select
        consumer2_.full_name as col_0_0_,
        supplier5_.name as col_1_0_,
        technician4_.full_name as col_2_0_,
        meterreadi0_.reading_date as col_3_0_,
        meterreadi0_.reading_value as col_4_0_,
        consumer2_.address as col_5_0_,
        tariffzone6_.name as col_6_0_,
        tariffzone6_.price_per_kwh as col_7_0_,
        technician4_.experience_years as col_8_0_,
        metertype3_.name as col_9_0_ 
    from
        meter_readings meterreadi0_ 
    inner join
        meters meter1_ 
            on meterreadi0_.meter_id=meter1_.id 
    inner join
        consumers consumer2_ 
            on meter1_.consumer_id=consumer2_.id 
    inner join
        meter_types metertype3_ 
            on meter1_.meter_type_id=metertype3_.id 
    inner join
        technicians technician4_ 
            on meterreadi0_.technician_id=technician4_.id 
    inner join
        suppliers supplier5_ 
            on technician4_.supplier_id=supplier5_.id 
    inner join
        tariff_zones tariffzone6_ 
            on meterreadi0_.tariff_zone_id=tariffzone6_.id 
    order by
        meterreadi0_.reading_date,
        consumer2_.full_name
=== Денормалізована таблиця показників електролічильників ===
Знайдено записів: 12
--------------------------------------------------
Споживач:    Бондаренко Дмитро Сергійович
Постачальник:ЕкоЕнергія
Технік:      Іваненко Андрій Васильович
Дата:        2024-09-01
Показник:    2100.80 кВт
Адреса:      м. Київ, просп. Перемоги, 33
Тариф:       Денна
Ціна/кВт:    2.85
Стаж техніка:3 р.
Тип лічільника: Індукційний
--------------------------------------------------
Споживач:    Коваленко Іван Петрович
Постачальник:ЕнергоПостач
Технік:      Сидоренко Олег Миколайович
Дата:        2024-09-01
Показник:    1250.50 кВт
Адреса:      м. Київ, вул. Шевченка, 12, кв. 5
Тариф:       Денна
Ціна/кВт:    2.85
Стаж техніка:5 р.
Тип лічільника: Електронний
--------------------------------------------------
Споживач:    Петренко Марія Василівна
Постачальник:ЕнергоПостач
Технік:      Сидоренко Олег Миколайович
Дата:        2024-09-01
Показник:    875.20 кВт
Адреса:      м. Київ, вул. Лесі Українки, 3
Тариф:       Нічна
Ціна/кВт:    1.32
Стаж техніка:5 р.
Тип лічільника: Електронний
--------------------------------------------------
Споживач:    Ткаченко Юлія Ігорівна
Постачальник:ЕкоЕнергія
Технік:      Іваненко Андрій Васильович
Дата:        2024-09-01
Показник:    1560.30 кВт
Адреса:      м. Київ, вул. Хрещатик, 25
Тариф:       Денна
Ціна/кВт:    2.85
Стаж техніка:3 р.
Тип лічільника: Індукційний
--------------------------------------------------
Споживач:    Кравченко Сергій Володимирович
Постачальник:ЕнергоПостач
Технік:      Мельник Петро Олексійович
Дата:        2024-09-02
Показник:    950.70 кВт
Адреса:      м. Київ, вул. Саксаганського, 7
Тариф:       Нічна
Ціна/кВт:    1.32
Стаж техніка:7 р.
Тип лічільника: Електронний
--------------------------------------------------
Споживач:    Марченко Ірина Олегівна
Постачальник:ЕнергоПостач
Технік:      Мельник Петро Олексійович
Дата:        2024-09-02
Показник:    1850.40 кВт
Адреса:      м. Київ, вул. Богдана Хмельницького, 40
Тариф:       Денна
Ціна/кВт:    2.85
Стаж техніка:7 р.
Тип лічільника: Електронний
--------------------------------------------------
Споживач:    Шевчук Павло Михайлович
Постачальник:ЕнергоПостач
Технік:      Сидоренко Олег Миколайович
Дата:        2024-09-02
Показник:    1120.60 кВт
Адреса:      м. Київ, вул. Антоновича, 72
Тариф:       Денна
Ціна/кВт:    2.85
Стаж техніка:5 р.
Тип лічільника: Індукційний
--------------------------------------------------
Споживач:    Гончарук Наталія Вікторівна
Постачальник:ЕкоЕнергія
Технік:      Іваненко Андрій Васильович
Дата:        2024-09-03
Показник:    1980.90 кВт
Адреса:      м. Київ, вул. Велика Васильківська, 5
Тариф:       Денна
Ціна/кВт:    2.85
Стаж техніка:3 р.
Тип лічільника: Індукційний
--------------------------------------------------
Споживач:    Лисенко Артем Олегович
Постачальник:ЕнергоПостач
Технік:      Мельник Петро Олексійович
Дата:        2024-09-03
Показник:    765.20 кВт
Адреса:      м. Київ, вул. Дегтярівська, 33
Тариф:       Нічна
Ціна/кВт:    1.32
Стаж техніка:7 р.
Тип лічільника: Електронний
--------------------------------------------------
Споживач:    Павленко Катерина Сергіївна
Постачальник:ЕнергоПостач
Технік:      Сидоренко Олег Миколайович
Дата:        2024-09-03
Показник:    1420.80 кВт
Адреса:      м. Київ, вул. Євгена Коновальця, 18
Тариф:       Денна
Ціна/кВт:    2.85
Стаж техніка:5 р.
Тип лічільника: Електронний
--------------------------------------------------
Споживач:    Бондаренко Дмитро Сергійович
Постачальник:ЕнергоПостач
Технік:      Мельник Петро Олексійович
Дата:        2024-09-04
Показник:    2050.10 кВт
Адреса:      м. Київ, вул. Нижній Вал, 15
Тариф:       Денна
Ціна/кВт:    2.85
Стаж техніка:7 р.
Тип лічільника: Електронний
--------------------------------------------------
Споживач:    Ковальчук Михайло Іванович
Постачальник:ЕкоЕнергія
Технік:      Іваненко Андрій Васильович
Дата:        2024-09-04
Показник:    1750.30 кВт
Адреса:      м. Київ, вул. Дорогожицька, 2
Тариф:       Денна
Ціна/кВт:    2.85
Стаж техніка:3 р.
Тип лічільника: Індукційний
--------------------------------------------------
лют. 19, 2026 1:43:51 ПП org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PoolState stop
INFO: HHH10001008: Cleaning up connection pool [jdbc:postgresql://localhost:5432/postgres]
```