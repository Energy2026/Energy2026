package org.energy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * E2E тести — перевіряють повний user flow через реальний браузер (Playwright/Chromium).
 */
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class EnergyReadingHappyPathE2ETests {

    private static final String BASE_URL = "http://localhost:8081";
    private static final int VIDEO_WIDTH = 1280;
    private static final int VIDEO_HEIGHT = 720;
    private static final int VIEWPORT_WIDTH = 1440;
    private static final int VIEWPORT_HEIGHT = 1200;

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0");

    @DynamicPropertySource
    static void mongoProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private EnergyReadingRepository repository;

    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;

    @BeforeEach
    void setUp() throws IOException {
        repository.deleteAll();

        Path videosDir = Paths.get("target/e2e-artifacts/videos");
        Files.createDirectories(videosDir);

        playwright = Playwright.create();

        String cdpUrl = System.getenv("E2E_SELENIUM_CDP_URL");
        if (cdpUrl != null && !cdpUrl.isBlank()) {
            browser = playwright.chromium().connectOverCDP(cdpUrl);
        } else {
            browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(true));
        }

        context = browser.newContext(new Browser.NewContextOptions()
                .setRecordVideoDir(videosDir)
                .setRecordVideoSize(VIDEO_WIDTH, VIDEO_HEIGHT)
                .setViewportSize(VIEWPORT_WIDTH, VIEWPORT_HEIGHT));
        page = context.newPage();
    }

    @AfterEach
    void tearDown() {
        context.close();
        browser.close();
        playwright.close();
    }

    private void addEntry(String consumer) {
        page.navigate(BASE_URL + "/add");
        page.fill("[name='consumer']", consumer);
        page.fill("[name='supplier']", "Постачальник");
        page.fill("[name='technician']", "Технік");
        page.locator("input[name='reading_date']").fill("2024-01-01");
        page.fill("[name='reading_value']", "500");
        page.fill("[name='address']", "м. Київ, вул. Тестова, 1");
        page.fill("[name='tariff_zone']", "Денна");
        page.fill("[name='price_per_kwh']", "2.85");
        page.fill("[name='experience_years']", "5");
        page.fill("[name='meter_type']", "Електронний");
        page.click("button[type='submit']");
    }

    @Test
    void energyReadingHappyPathCreatesAndDeletesEntry() {
        addEntry("Коваленко Тест");

        Locator rows = page.locator("tbody tr");
        assertEquals(1, rows.count());

        page.click("button.btn-danger");

        assertEquals(0, page.locator("tbody tr").count());
    }

    @Test
    void energyReadingHappyPathEditsEntry() {
        addEntry("Коваленко Тест");

        page.click("a.btn-warning");

        page.fill("[name='consumer']", "Оновлений Споживач");
        page.click("button[type='submit']");

        Locator rows = page.locator("tbody tr");
        assertEquals(1, rows.count());
        assertTrue(rows.first().textContent().contains("Оновлений Споживач"));
    }
}