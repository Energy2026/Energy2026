package org.energy;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Method;
import org.junit.jupiter.api.Test;

class EnergyApplicationTest {

    @Test
    void mainMethodExists() {
        try {
            Method mainMethod =
                    EnergyApplication.class.getDeclaredMethod(
                            "main", String[].class);
            assertTrue(mainMethod != null);
        } catch (NoSuchMethodException e) {
            throw new AssertionError(
                    "main method should exist");
        }
    }
}