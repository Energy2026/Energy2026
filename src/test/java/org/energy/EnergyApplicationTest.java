package org.energy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class EnergyApplicationTest {
    private final InputStreamState inputStreamState =
        new InputStreamState();
    private final OutputStreamState outputStreamState =
        new OutputStreamState();

    @AfterEach
    void restoreSystemStreams() {
        inputStreamState.restore();
        outputStreamState.restore();
    }

    @Test
    void loadFromCsvSavesParsedRowsToRepository()
            throws Exception {
        EnergyReadingRepository repository =
            mock(EnergyReadingRepository.class);
        EnergyApplication app = new EnergyApplication(repository);

        invokePrivate(app, "loadFromCsv");

        verify(repository, times(1)).saveAll(anyList());
        assertTrue(outputStreamState.value().contains("CSV"));
    }

    @Test
    void viewReadingsPrintsCountWhenRepositoryHasData()
            throws Exception {
        EnergyReadingRepository repository =
            mock(EnergyReadingRepository.class);
        EnergyReading reading =
            new org.energy.support
                .EnergyReadingTestDataBuilder().build();
        when(repository.findAll())
            .thenReturn(List.of(reading));
        EnergyApplication app = new EnergyApplication(repository);

        invokePrivate(app, "viewReadings");

        String output = outputStreamState.value();
        assertTrue(output.contains("Знайдено 1"));
        assertTrue(output.contains("EnergyReading"));
        verify(repository, times(1)).findAll();
    }

    @Test
    void viewReadingsPrintsZeroForEmptyRepository()
            throws Exception {
        EnergyReadingRepository repository =
            mock(EnergyReadingRepository.class);
        when(repository.findAll())
            .thenReturn(Collections.emptyList());
        EnergyApplication app = new EnergyApplication(repository);

        invokePrivate(app, "viewReadings");

        assertTrue(outputStreamState.value()
            .contains("Знайдено 0"));
        verify(repository, times(1)).findAll();
    }

    @Test
    void deleteReadingsDeletesDataAndPrintsMessage()
            throws Exception {
        EnergyReadingRepository repository =
            mock(EnergyReadingRepository.class);
        EnergyApplication app = new EnergyApplication(repository);

        invokePrivate(app, "deleteReadings");

        verify(repository, times(1)).deleteAll();
        assertTrue(outputStreamState.value()
            .contains("видалено"));
    }

    @Test
    void runExecutesChoicesAndStopsWhenInputIsExhausted()
            throws Exception {
        EnergyReadingRepository repository =
            mock(EnergyReadingRepository.class);
        when(repository.findAll())
            .thenReturn(Collections.emptyList());
        EnergyApplication app = new EnergyApplication(repository);
        inputStreamState.replace("1\n2\n3\n");

        assertThrows(NoSuchElementException.class,
            () -> app.run());

        verify(repository, times(1)).deleteAll();
        verify(repository, times(1)).saveAll(anyList());
        verify(repository, times(1)).findAll();
    }

    @Test
    void runPrintsMessageForInvalidChoice() throws Exception {
        EnergyReadingRepository repository =
            mock(EnergyReadingRepository.class);
        EnergyApplication app = new EnergyApplication(repository);
        inputStreamState.replace("5\n");

        assertThrows(NoSuchElementException.class,
            () -> app.run());

        assertTrue(outputStreamState.value()
            .contains("Невірна команда"));
    }

    @Test
    void runPrintsMessageForNonNumericInput() throws Exception {
        EnergyReadingRepository repository =
            mock(EnergyReadingRepository.class);
        EnergyApplication app = new EnergyApplication(repository);
        inputStreamState.replace("abc\n");

        assertThrows(NoSuchElementException.class,
            () -> app.run());

        String output = outputStreamState.value();
        assertTrue(output.contains("Невірна команда"));
        assertTrue(output.contains("1 до 4"));
    }

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

    private static void invokePrivate(
            EnergyApplication app,
            String methodName) throws Exception {
        Method method =
            EnergyApplication.class
                .getDeclaredMethod(methodName);
        method.setAccessible(true);
        method.invoke(app);
    }

    private static final class InputStreamState {
        private final java.io.InputStream original = System.in;

        private void replace(String input) {
            System.setIn(new ByteArrayInputStream(
                input.getBytes(StandardCharsets.UTF_8)));
        }

        private void restore() {
            System.setIn(original);
        }
    }

    private static final class OutputStreamState {
        private final PrintStream original = System.out;
        private final ByteArrayOutputStream captured =
            new ByteArrayOutputStream();

        private OutputStreamState() {
            System.setOut(new PrintStream(
                captured, true, StandardCharsets.UTF_8));
        }

        private String value() {
            return captured.toString(StandardCharsets.UTF_8);
        }

        private void restore() {
            System.setOut(original);
        }
    }
}
