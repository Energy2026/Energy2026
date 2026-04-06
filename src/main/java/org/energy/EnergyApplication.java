package org.energy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Веб-додаток для перегляду та редагування показників електролічильників.
 *
 * <p>@SpringBootApplication - анотація для позначення головного класу Spring Boot додатку.</p>
 *
 * <p>Методи:
 * <ul>
 *   <li>main(String[] args): запускає додаток Spring Boot з вбудованим веб-сервером.</li>
 * </ul>
 * </p>
 *
 * @see EnergyReading
 * @see EnergyReadingRepository
 */
@SpringBootApplication
public class EnergyApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnergyApplication.class, args);
    }
}