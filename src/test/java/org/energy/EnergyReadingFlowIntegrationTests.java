package org.energy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * Інтеграційні тести для перевірки повного HTTP-потоку
 * через EnergyReadingController із реальною MongoDB у тимчасовому контейнері.
 * Перевіряє 100% покриття EnergyReading та EnergyReadingController.
 */
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class EnergyReadingFlowIntegrationTests {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0");

    @DynamicPropertySource
    static void mongoProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EnergyReadingRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void viewReadings_whenEmpty_showsReadingsView() throws Exception {
        mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(view().name("readings"))
            .andExpect(model().attributeExists("readings"));
    }

    @Test
    void showAddForm_returnsAddViewWithEmptyReading() throws Exception {
        mockMvc.perform(get("/add"))
            .andExpect(status().isOk())
            .andExpect(view().name("add"))
            .andExpect(model().attributeExists("reading"));
    }

    @Test
    void addReading_savesToDatabaseAndRedirects() throws Exception {
        mockMvc.perform(post("/add")
            .param("consumer", "Споживач Тест")
            .param("supplier", "Постачальник Тест")
            .param("technician", "Технік Тест")
            .param("reading_date", "2024-01-01")
            .param("reading_value", "1000.0")
            .param("address", "м. Київ, вул. Тестова, 1")
            .param("tariff_zone", "Денна")
            .param("price_per_kwh", "2.85")
            .param("experience_years", "5")
            .param("meter_type", "Електронний"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/"));

        List<EnergyReading> all = repository.findAll();
        assertEquals(1, all.size());
        assertEquals("Споживач Тест", all.get(0).getConsumer());
    }

    @Test
    void viewReadings_withData_showsAllReadingsInModel() throws Exception {
        repository.save(new EnergyReading(
            "Споживач", "Постачальник", "Технік",
            "2024-01-01", "500.0", "м. Київ, вул. Хрещатик, 1",
            "Нічна", "1.32", "3", "Індукційний"
        ));

        mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(view().name("readings"))
            .andExpect(model().attributeExists("readings"));
    }

    @Test
    void deleteReading_removesFromDatabaseAndRedirects() throws Exception {
        EnergyReading saved = repository.save(new EnergyReading(
            "Споживач", "Постачальник", "Технік",
            "2024-01-01", "500.0", "м. Київ, вул. Хрещатик, 1",
            "Нічна", "1.32", "3", "Індукційний"
        ));

        mockMvc.perform(post("/delete/" + saved.getId()))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/"));

        assertTrue(repository.findAll().isEmpty());
    }

    @Test
    void energyReading_noArgConstructorAndSetters() {
        EnergyReading reading = new EnergyReading();

        assertNull(reading.getId());
        assertNull(reading.getConsumer());

        reading.setId("test-id");
        reading.setConsumer("Споживач");
        reading.setSupplier("Постачальник");
        reading.setTechnician("Технік");
        reading.setReading_date("2024-01-01");
        reading.setReading_value("100.0");
        reading.setAddress("Адреса");
        reading.setTariff_zone("Денна");
        reading.setPrice_per_kwh("2.85");
        reading.setExperience_years("5");
        reading.setMeter_type("Електронний");

        assertEquals("test-id", reading.getId());
        assertEquals("Споживач", reading.getConsumer());
        assertEquals("Постачальник", reading.getSupplier());
        assertEquals("Технік", reading.getTechnician());
        assertEquals("2024-01-01", reading.getReading_date());
        assertEquals("100.0", reading.getReading_value());
        assertEquals("Адреса", reading.getAddress());
        assertEquals("Денна", reading.getTariff_zone());
        assertEquals("2.85", reading.getPrice_per_kwh());
        assertEquals("5", reading.getExperience_years());
        assertEquals("Електронний", reading.getMeter_type());
    }

    @Test
    void energyReading_fullConstructorAndToString() {
        EnergyReading reading = new EnergyReading(
            "Споживач", "Постачальник", "Технік",
            "2024-01-01", "100.0", "Адреса",
            "Денна", "2.85", "5", "Електронний"
        );

        assertEquals("Споживач", reading.getConsumer());
        assertEquals("Постачальник", reading.getSupplier());
        assertEquals("Технік", reading.getTechnician());
        assertEquals("2024-01-01", reading.getReading_date());
        assertEquals("100.0", reading.getReading_value());
        assertEquals("Адреса", reading.getAddress());
        assertEquals("Денна", reading.getTariff_zone());
        assertEquals("2.85", reading.getPrice_per_kwh());
        assertEquals("5", reading.getExperience_years());
        assertEquals("Електронний", reading.getMeter_type());

        String text = reading.toString();
        assertNotNull(text);
        assertTrue(text.contains("EnergyReading"));
        assertTrue(text.contains("consumer=\"Споживач\""));
        assertTrue(text.contains("supplier=\"Постачальник\""));
    }
}
