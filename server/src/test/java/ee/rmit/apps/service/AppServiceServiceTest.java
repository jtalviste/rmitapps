package ee.rmit.apps.service;

import ee.rmit.apps.dto.AppService;
import ee.rmit.apps.fixtures.AppServiceFixtureChecker;
import ee.rmit.apps.fixtures.AppServiceListFixtureChecker;
import ee.rmit.apps.fixtures.ApplicationFixtureChecker;
import ee.rmit.apps.repository.AppServiceRepository;
import ee.rmit.apps.repository.ApplicationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static ee.rmit.apps.fixtures.AppServiceListFixtureChecker.APP_SERVICES_FIXTURE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;

public class AppServiceServiceTest {

    ApplicationRepository applicationRepository = Mockito.mock(ApplicationRepository.class);

    AppServiceRepository appServiceRepository = Mockito.mock(AppServiceRepository.class);

    AppServiceService appServiceService = new AppServiceService(applicationRepository, appServiceRepository);

    @Test
    public void testSearch() {
        ApplicationFixtureChecker.APPLICATION_FIXTURE.setServices(APP_SERVICES_FIXTURE);
        Mockito.when(applicationRepository.findByName(anyString())).thenReturn(ApplicationFixtureChecker.APPLICATION_FIXTURE);
        List<AppService> services = appServiceService.searchByApplicationName("appname");
        assertEquals(services.size(), 2);
        assertTrue(new AppServiceListFixtureChecker().matches(services));
    }

    @Test
    public void testAdd() {
        appServiceService.add(
                APP_SERVICES_FIXTURE.get(0)
        );
        verify(appServiceRepository).save(argThat(
                new AppServiceFixtureChecker("A")));
    }

}
