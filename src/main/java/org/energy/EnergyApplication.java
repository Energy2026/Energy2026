package org.energy;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Головний клас програми для роботи з базою даних обліку електроенергії MongoDB.
 *
 * <p>Програма надає інтерактивне меню з командами:
 * <ul>
 *   <li>1 - Додати показники з CSV-файлу</li>
 *   <li>2 - Подивитись показники</li>
 *   <li>3 - Видалити показники</li>
 *   <li>4 - Вихід</li>
 * </ul>
 * </p>
 *
 * @see EnergyReading
 * @see EnergyReadingRepository
 */
@SpringBootApplication
public class EnergyApplication implements CommandLineRunner {

    private final EnergyReadingRepository repository;

    public EnergyApplication(EnergyReadingRepository repository) {
        this.repository = repository;
    }

    public static void main(String[] args) {
        SpringApplication.run(EnergyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in, "UTF-8");

        while (true) {
            System.out.println("1. Додати показники з CSV-файлу");
            System.out.println("2. Подивитись показники");
            System.out.println("3. Видалити показники");
            System.out.println("4. Вихід");
            System.out.print("Введіть номер команди (1-4): ");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    loadFromCsv();
                    break;
                case "2":
                    viewReadings();
                    break;
                case "3":
                    deleteReadings();
                    break;
                case "4":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Невірна команда. Введіть число від 1 до 4.");
            }
        }
    }

    /**
     * Завантажує показники з CSV-файлу readings.csv і зберігає в MongoDB.
     */
    private void loadFromCsv() {
        List<EnergyReading> readings = new ArrayList<>();

        try (CSVReader csvReader = new CSVReader(new InputStreamReader(
                getClass().getResourceAsStream("/readings.csv"), StandardCharsets.UTF_8))) {

            // Пропускаємо заголовок
            csvReader.readNext();

            String[] line;
            while ((line = csvReader.readNext()) != null) {
                EnergyReading reading = new EnergyReading(
                        line[0],  // consumer
                        line[1],  // supplier
                        line[2],  // technician
                        line[3],  // reading_date
                        line[4],  // reading_value
                        line[5],  // address
                        line[6],  // tariff_zone
                        line[7],  // price_per_kwh
                        line[8],  // experience_years
                        line[9]   // meter_type
                );
                readings.add(reading);
            }

            repository.saveAll(readings);
            System.out.println(readings.size() + " документів з показниками електролічильників завантажено з CSV.");

        } catch (IOException | CsvValidationException e) {
            System.out.println("Помилка читання CSV: " + e.getMessage());
        }
    }

    /**
     * Виводить усі показники з MongoDB.
     */
    private void viewReadings() {
        List<EnergyReading> readings = repository.findAll();
        System.out.println("Знайдено " + readings.size() + " документів показників:");
        for (EnergyReading reading : readings) {
            System.out.println(reading);
        }
    }

    /**
     * Видаляє всі показники з MongoDB.
     */
    private void deleteReadings() {
        repository.deleteAll();
        System.out.println("Показники видалено.");
    }
}
