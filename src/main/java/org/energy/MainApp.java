package org.energy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Головний клас програми для роботи з базою даних обліку електроенергії.
 *
 * <p>Цей клас використовує ORM фреймворк Hibernate для взаємодії з базою даних. Він виконує дві основні операції:
 * <ul>
 *   <li>Перша транзакція: Додавання даних до таблиць Suppliers, TariffZones, MeterTypes, Technicians, Consumers, Meters та MeterReadings.</li>
 *   <li>Друга транзакція: Виконання запиту для отримання денормалізованої таблиці показників електролічильників.</li>
 * </ul>
 * </p>
 *
 * <p>Клас використовує наступні Entity класи таблиць бази даних:
 * <ul>
 *   <li>{@link Supplier}</li>
 *   <li>{@link TariffZone}</li>
 *   <li>{@link MeterType}</li>
 *   <li>{@link Technician}</li>
 *   <li>{@link Consumer}</li>
 *   <li>{@link Meter}</li>
 *   <li>{@link MeterReading}</li>
 * </ul>
 * </p>
 *
 * @see EnergyReadingDTO
 */
public class MainApp {
    public static void main(String[] args) {
        // Створення фабрики сесій Hibernate
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Supplier.class)
                .addAnnotatedClass(TariffZone.class)
                .addAnnotatedClass(MeterType.class)
                .addAnnotatedClass(Technician.class)
                .addAnnotatedClass(Consumer.class)
                .addAnnotatedClass(Meter.class)
                .addAnnotatedClass(MeterReading.class)
                .buildSessionFactory();

        // Перша транзакція: Додавання даних
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            // Постачальники
            Supplier supplier1 = new Supplier();
            supplier1.setName("ЕнергоПостач");

            Supplier supplier2 = new Supplier();
            supplier2.setName("ЕкоЕнергія");

            session.save(supplier1);
            session.save(supplier2);

            // Тарифні зони
            TariffZone zoneDay = new TariffZone();
            zoneDay.setName("Денна");
            zoneDay.setPricePerKwh(new BigDecimal("2.8500"));

            TariffZone zoneNight = new TariffZone();
            zoneNight.setName("Нічна");
            zoneNight.setPricePerKwh(new BigDecimal("1.3200"));

            session.save(zoneDay);
            session.save(zoneNight);

            // Типи лічильників
            MeterType typeElectronic = new MeterType();
            typeElectronic.setName("Електронний");

            MeterType typeInduction = new MeterType();
            typeInduction.setName("Індукційний");

            session.save(typeElectronic);
            session.save(typeInduction);

            // Техніки
            Technician tech1 = new Technician();
            tech1.setFullName("Сидоренко Олег Миколайович");
            tech1.setExperienceYears(5);
            tech1.setSupplier(supplier1); // ЕнергоПостач

            Technician tech2 = new Technician();
            tech2.setFullName("Мельник Петро Олексійович");
            tech2.setExperienceYears(7);
            tech2.setSupplier(supplier1); // ЕнергоПостач

            Technician tech3 = new Technician();
            tech3.setFullName("Іваненко Андрій Васильович");
            tech3.setExperienceYears(3);
            tech3.setSupplier(supplier2); // ЕкоЕнергія

            session.save(tech1);
            session.save(tech2);
            session.save(tech3);

            // Споживачі з адресами
            Consumer c1 = new Consumer();
            c1.setFullName("Коваленко Іван Петрович");
            c1.setAddress("м. Київ, вул. Шевченка, 12, кв. 5");

            Consumer c2 = new Consumer();
            c2.setFullName("Петренко Марія Василівна");
            c2.setAddress("м. Київ, вул. Лесі Українки, 3");

            Consumer c3 = new Consumer();
            c3.setFullName("Бондаренко Дмитро Сергійович");
            c3.setAddress("м. Київ, просп. Перемоги, 33");

            Consumer c4 = new Consumer();
            c4.setFullName("Ткаченко Юлія Ігорівна");
            c4.setAddress("м. Київ, вул. Хрещатик, 25");

            Consumer c5 = new Consumer();
            c5.setFullName("Кравченко Сергій Володимирович");
            c5.setAddress("м. Київ, вул. Саксаганського, 7");

            Consumer c6 = new Consumer();
            c6.setFullName("Марченко Ірина Олегівна");
            c6.setAddress("м. Київ, вул. Богдана Хмельницького, 40");

            Consumer c7 = new Consumer();
            c7.setFullName("Шевчук Павло Михайлович");
            c7.setAddress("м. Київ, вул. Антоновича, 72");

            Consumer c8 = new Consumer();
            c8.setFullName("Гончарук Наталія Вікторівна");
            c8.setAddress("м. Київ, вул. Велика Васильківська, 5");

            Consumer c9 = new Consumer();
            c9.setFullName("Лисенко Артем Олегович");
            c9.setAddress("м. Київ, вул. Дегтярівська, 33");

            Consumer c10 = new Consumer();
            c10.setFullName("Павленко Катерина Сергіївна");
            c10.setAddress("м. Київ, вул. Євгена Коновальця, 18");

            Consumer c11 = new Consumer();
            c11.setFullName("Ковальчук Михайло Іванович");
            c11.setAddress("м. Київ, вул. Дорогожицька, 2");

            Consumer c12 = new Consumer();
            c12.setFullName("Бондаренко Дмитро Сергійович");
            c12.setAddress("м. Київ, вул. Нижній Вал, 15");

            session.save(c1); session.save(c2); session.save(c3);
            session.save(c4); session.save(c5); session.save(c6);
            session.save(c7); session.save(c8); session.save(c9);
            session.save(c10); session.save(c11); session.save(c12);

            // Лічильники
            Meter m1 = new Meter(); m1.setConsumer(c1); m1.setMeterType(typeElectronic); m1.setInstalledAt(LocalDate.of(2024,1,1));
            Meter m2 = new Meter(); m2.setConsumer(c2); m2.setMeterType(typeElectronic); m2.setInstalledAt(LocalDate.of(2024,1,1));
            Meter m3 = new Meter(); m3.setConsumer(c3); m3.setMeterType(typeInduction);  m3.setInstalledAt(LocalDate.of(2024,1,1));
            Meter m4 = new Meter(); m4.setConsumer(c4); m4.setMeterType(typeInduction);  m4.setInstalledAt(LocalDate.of(2024,1,1));
            Meter m5 = new Meter(); m5.setConsumer(c5); m5.setMeterType(typeElectronic); m5.setInstalledAt(LocalDate.of(2024,1,1));
            Meter m6 = new Meter(); m6.setConsumer(c6); m6.setMeterType(typeElectronic); m6.setInstalledAt(LocalDate.of(2024,1,1));
            Meter m7 = new Meter(); m7.setConsumer(c7); m7.setMeterType(typeInduction);  m7.setInstalledAt(LocalDate.of(2024,1,1));
            Meter m8 = new Meter(); m8.setConsumer(c8); m8.setMeterType(typeInduction);  m8.setInstalledAt(LocalDate.of(2024,1,1));
            Meter m9 = new Meter(); m9.setConsumer(c9); m9.setMeterType(typeElectronic); m9.setInstalledAt(LocalDate.of(2024,1,1));
            Meter m10 = new Meter(); m10.setConsumer(c10); m10.setMeterType(typeElectronic); m10.setInstalledAt(LocalDate.of(2024,1,1));
            Meter m11 = new Meter(); m11.setConsumer(c11); m11.setMeterType(typeInduction);  m11.setInstalledAt(LocalDate.of(2024,1,1));
            Meter m12 = new Meter(); m12.setConsumer(c12); m12.setMeterType(typeElectronic); m12.setInstalledAt(LocalDate.of(2024,1,1));

            session.save(m1); session.save(m2); session.save(m3);
            session.save(m4); session.save(m5); session.save(m6);
            session.save(m7); session.save(m8); session.save(m9);
            session.save(m10); session.save(m11); session.save(m12);

            // Показники лічильників
            MeterReading r1 = new MeterReading();
            r1.setMeter(m1); r1.setTechnician(tech1); r1.setTariffZone(zoneDay);
            r1.setReadingDate(LocalDate.of(2024,9,1)); r1.setReadingValue(new BigDecimal("1250.5"));

            MeterReading r2 = new MeterReading();
            r2.setMeter(m2); r2.setTechnician(tech1); r2.setTariffZone(zoneNight);
            r2.setReadingDate(LocalDate.of(2024,9,1)); r2.setReadingValue(new BigDecimal("875.2"));

            MeterReading r3 = new MeterReading();
            r3.setMeter(m3); r3.setTechnician(tech3); r3.setTariffZone(zoneDay);
            r3.setReadingDate(LocalDate.of(2024,9,1)); r3.setReadingValue(new BigDecimal("2100.8"));

            MeterReading r4 = new MeterReading();
            r4.setMeter(m4); r4.setTechnician(tech3); r4.setTariffZone(zoneDay);
            r4.setReadingDate(LocalDate.of(2024,9,1)); r4.setReadingValue(new BigDecimal("1560.3"));

            MeterReading r5 = new MeterReading();
            r5.setMeter(m5); r5.setTechnician(tech2); r5.setTariffZone(zoneNight);
            r5.setReadingDate(LocalDate.of(2024,9,2)); r5.setReadingValue(new BigDecimal("950.7"));

            MeterReading r6 = new MeterReading();
            r6.setMeter(m6); r6.setTechnician(tech2); r6.setTariffZone(zoneDay);
            r6.setReadingDate(LocalDate.of(2024,9,2)); r6.setReadingValue(new BigDecimal("1850.4"));

            MeterReading r7 = new MeterReading();
            r7.setMeter(m7); r7.setTechnician(tech1); r7.setTariffZone(zoneDay);
            r7.setReadingDate(LocalDate.of(2024,9,2)); r7.setReadingValue(new BigDecimal("1120.6"));

            MeterReading r8 = new MeterReading();
            r8.setMeter(m8); r8.setTechnician(tech3); r8.setTariffZone(zoneDay);
            r8.setReadingDate(LocalDate.of(2024,9,3)); r8.setReadingValue(new BigDecimal("1980.9"));

            MeterReading r9 = new MeterReading();
            r9.setMeter(m9); r9.setTechnician(tech2); r9.setTariffZone(zoneNight);
            r9.setReadingDate(LocalDate.of(2024,9,3)); r9.setReadingValue(new BigDecimal("765.2"));

            MeterReading r10 = new MeterReading();
            r10.setMeter(m10); r10.setTechnician(tech1); r10.setTariffZone(zoneDay);
            r10.setReadingDate(LocalDate.of(2024,9,3)); r10.setReadingValue(new BigDecimal("1420.8"));

            MeterReading r11 = new MeterReading();
            r11.setMeter(m11); r11.setTechnician(tech3); r11.setTariffZone(zoneDay);
            r11.setReadingDate(LocalDate.of(2024,9,4)); r11.setReadingValue(new BigDecimal("1750.3"));

            MeterReading r12 = new MeterReading();
            r12.setMeter(m12); r12.setTechnician(tech2); r12.setTariffZone(zoneDay);
            r12.setReadingDate(LocalDate.of(2024,9,4)); r12.setReadingValue(new BigDecimal("2050.1"));

            session.save(r1); session.save(r2); session.save(r3);
            session.save(r4); session.save(r5); session.save(r6);
            session.save(r7); session.save(r8); session.save(r9);
            session.save(r10); session.save(r11); session.save(r12);

            session.getTransaction().commit();
            System.out.println("Дані успішно збережено!");

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Друга транзакція: Виконання запиту - денормалізована таблиця
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            String hql = "SELECT new org.energy.EnergyReadingDTO(" +
                    "con.fullName, " +
                    "s.name, " +
                    "t.fullName, " +
                    "mr.readingDate, " +
                    "mr.readingValue, " +
                    "con.address, " +
                    "tz.name, " +
                    "tz.pricePerKwh, " +
                    "t.experienceYears, " +
                    "mt.name) " +
                    "FROM MeterReading mr " +
                    "JOIN mr.meter m " +
                    "JOIN m.consumer con " +
                    "JOIN m.meterType mt " +
                    "JOIN mr.technician t " +
                    "JOIN t.supplier s " +
                    "JOIN mr.tariffZone tz " +
                    "ORDER BY mr.readingDate, con.fullName";

            List<EnergyReadingDTO> results = session.createQuery(hql, EnergyReadingDTO.class).getResultList();

            System.out.println("=== Денормалізована таблиця показників електролічильників ===");
            System.out.println("Знайдено записів: " + results.size());
            System.out.println("--------------------------------------------------");

            for (EnergyReadingDTO info : results) {
                System.out.println("Споживач:    " + info.getConsumerName());
                System.out.println("Постачальник:" + info.getSupplierName());
                System.out.println("Технік:      " + info.getTechnicianName());
                System.out.println("Дата:        " + info.getReadingDate());
                System.out.println("Показник:    " + info.getReadingValue() + " кВт");
                System.out.println("Адреса:      " + info.getAddress());
                System.out.println("Тариф:       " + info.getTariffZone());
                System.out.println("Ціна/кВт:    " + info.getPricePerKwh());
                System.out.println("Стаж техніка:" + info.getExperienceYears() + " р.");
                System.out.println("Тип лічільника: " + info.getMeterType());
                System.out.println("--------------------------------------------------");
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }
}
