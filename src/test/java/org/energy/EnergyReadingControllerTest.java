package org.energy;

import static org.energy.support.EnergyReadingTestDataBuilder.anEnergyReading;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

class EnergyReadingControllerTest {

    @Test
    void viewReadingsReturnsReadingsViewName() {
        EnergyReadingRepository repository =
                mock(EnergyReadingRepository.class);
        when(repository.findAll())
                .thenReturn(Collections.emptyList());
        EnergyReadingController controller =
                new EnergyReadingController(repository);
        Model model = new ExtendedModelMap();

        String viewName = controller.viewReadings(model);

        assertEquals("readings", viewName);
    }

    @Test
    void viewReadingsAddsAllReadingsToModel() {
        EnergyReadingRepository repository =
                mock(EnergyReadingRepository.class);
        EnergyReading reading = anEnergyReading().build();
        when(repository.findAll()).thenReturn(List.of(reading));
        EnergyReadingController controller =
                new EnergyReadingController(repository);
        Model model = new ExtendedModelMap();

        controller.viewReadings(model);

        verify(repository, times(1)).findAll();
        assertNotNull(model.getAttribute("readings"));
        assertEquals(
                1,
                ((List<?>) model.getAttribute("readings")).size());
    }

    @Test
    void showAddFormReturnsAddViewName() {
        EnergyReadingRepository repository =
                mock(EnergyReadingRepository.class);
        EnergyReadingController controller =
                new EnergyReadingController(repository);
        Model model = new ExtendedModelMap();

        String viewName = controller.showAddForm(model);

        assertEquals("add", viewName);
    }

    @Test
    void showAddFormAddsEmptyReadingToModel() {
        EnergyReadingRepository repository =
                mock(EnergyReadingRepository.class);
        EnergyReadingController controller =
                new EnergyReadingController(repository);
        Model model = new ExtendedModelMap();

        controller.showAddForm(model);

        assertNotNull(model.getAttribute("reading"));
    }

    @Test
    void addReadingSavesToRepositoryAndRedirects() {
        EnergyReadingRepository repository =
                mock(EnergyReadingRepository.class);
        EnergyReadingController controller =
                new EnergyReadingController(repository);
        EnergyReading reading = anEnergyReading().build();

        String viewName = controller.addReading(reading);

        verify(repository, times(1)).save(reading);
        assertEquals("redirect:/", viewName);
    }

    @Test
    void deleteReadingRemovesFromRepositoryAndRedirects() {
        EnergyReadingRepository repository =
                mock(EnergyReadingRepository.class);
        EnergyReadingController controller =
                new EnergyReadingController(repository);

        String viewName = controller.deleteReading("id-123");

        verify(repository, times(1)).deleteById("id-123");
        assertEquals("redirect:/", viewName);
    }
}