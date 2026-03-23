package org.energy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import org.junit.jupiter.api.Test;

class EnergyReadingTest {

    @Test
    void toStringContainsAllImportantFields() throws Exception {
        EnergyReading reading = new EnergyReading(
            "Коваленко Іван Петрович",
            "ЕнергоПостач",
            "Сидоренко Олег Миколайович",
            "2024-09-01",
            "1250.5",
            "м. Київ, вул. Шевченка, 12, кв. 5",
            "Денна",
            "2.85",
            "5",
            "Електронний"
        );

        Field idField = EnergyReading.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(reading, "id-123");

        String value = reading.toString();

        assertTrue(value.contains("id=\"id-123\""));
        assertTrue(value.contains("consumer=\"Коваленко Іван Петрович\""));
        assertTrue(value.contains("supplier=\"ЕнергоПостач\""));
        assertTrue(value.contains(
            "technician=\"Сидоренко Олег Миколайович\""));
        assertTrue(value.contains("reading_date=\"2024-09-01\""));
        assertTrue(value.contains("reading_value=\"1250.5\""));
        assertTrue(value.contains(
            "address=\"м. Київ, вул. Шевченка, 12, кв. 5\""));
        assertTrue(value.contains("tariff_zone=\"Денна\""));
        assertTrue(value.contains("price_per_kwh=\"2.85\""));
        assertTrue(value.contains("experience_years=\"5\""));
        assertTrue(value.contains("meter_type=\"Електронний\""));
    }

    @Test
    void constructorStoresGivenValuesForSecondCsvRow() {
        EnergyReading reading = new EnergyReading(
            "Петренко Марія Василівна",
            "ЕнергоПостач",
            "Сидоренко Олег Миколайович",
            "2024-09-01",
            "875.2",
            "м. Київ, вул. Лесі Українки, 3",
            "Нічна",
            "1.32",
            "5",
            "Електронний"
        );

        String value = reading.toString();
        assertTrue(value.contains(
            "consumer=\"Петренко Марія Василівна\""));
        assertTrue(value.contains("tariff_zone=\"Нічна\""));
        assertTrue(value.contains("price_per_kwh=\"1.32\""));
    }

    @Test
    void noArgConstructorCreatesEmptyObject() {
        EnergyReading reading = new EnergyReading();
        assertNull(reading.getId());
        assertNull(reading.getConsumer());
        assertNull(reading.getSupplier());
        assertNull(reading.getMeter_type());
    }

    @Test
    void gettersReturnCorrectValues() {
        EnergyReading reading = new EnergyReading(
            "Бондаренко Дмитро Сергійович",
            "ЕкоЕнергія",
            "Іваненко Андрій Васильович",
            "2024-09-01",
            "2100.8",
            "м. Київ, просп. Перемоги, 33",
            "Денна",
            "2.85",
            "3",
            "Індукційний"
        );

        assertEquals("Бондаренко Дмитро Сергійович",
            reading.getConsumer());
        assertEquals("ЕкоЕнергія", reading.getSupplier());
        assertEquals("Іваненко Андрій Васильович",
            reading.getTechnician());
        assertEquals("2024-09-01", reading.getReading_date());
        assertEquals("2100.8", reading.getReading_value());
        assertEquals("м. Київ, просп. Перемоги, 33",
            reading.getAddress());
        assertEquals("Денна", reading.getTariff_zone());
        assertEquals("2.85", reading.getPrice_per_kwh());
        assertEquals("3", reading.getExperience_years());
        assertEquals("Індукційний", reading.getMeter_type());
    }

    @Test
    void settersUpdateValues() {
        EnergyReading reading = new EnergyReading();

        reading.setId("test-id");
        reading.setConsumer("Тестовий Споживач");
        reading.setSupplier("Тестовий Постачальник");
        reading.setTechnician("Тестовий Технік");
        reading.setReading_date("2025-01-01");
        reading.setReading_value("999.9");
        reading.setAddress("м. Тест, вул. Тестова, 1");
        reading.setTariff_zone("Нічна");
        reading.setPrice_per_kwh("1.32");
        reading.setExperience_years("10");
        reading.setMeter_type("Електронний");

        assertEquals("test-id", reading.getId());
        assertEquals("Тестовий Споживач", reading.getConsumer());
        assertEquals("Тестовий Постачальник",
            reading.getSupplier());
        assertEquals("Тестовий Технік", reading.getTechnician());
        assertEquals("2025-01-01", reading.getReading_date());
        assertEquals("999.9", reading.getReading_value());
        assertEquals("м. Тест, вул. Тестова, 1",
            reading.getAddress());
        assertEquals("Нічна", reading.getTariff_zone());
        assertEquals("1.32", reading.getPrice_per_kwh());
        assertEquals("10", reading.getExperience_years());
        assertEquals("Електронний", reading.getMeter_type());
    }

    @Test
    void toStringContainsClassName() {
        EnergyReading reading = new EnergyReading();
        assertNotNull(reading.toString());
        assertTrue(reading.toString().contains("EnergyReading"));
    }
}
