package ee.rmit.apps.service;

import ee.rmit.apps.dto.Application;
import ee.rmit.apps.fixtures.ApplicationFixtureChecker;
import ee.rmit.apps.repository.AppServiceRepository;
import ee.rmit.apps.repository.ApplicationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static ee.rmit.apps.fixtures.AppServiceListFixtureChecker.APP_SERVICES_FIXTURE;
import static ee.rmit.apps.fixtures.ApplicationFixtureChecker.APPLICATION_FIXTURE;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;

public class ApplicationServiceTest {

    ApplicationRepository applicationRepository = Mockito.mock(ApplicationRepository.class);

    AppServiceRepository appServiceRepository = Mockito.mock(AppServiceRepository.class);

    ApplicationService applicationService = new ApplicationService(applicationRepository, appServiceRepository);

    @Test
    public void testSearch() {
        Mockito.when(appServiceRepository.findByName(anyString())).thenReturn(Optional.of(APP_SERVICES_FIXTURE.get(0)));
        Optional<Application> optionalApplication = applicationService.searchByAppServiceName("servicename");
        assertTrue(optionalApplication.isPresent());
        Application foundApplication = optionalApplication.get();
        assertTrue(new ApplicationFixtureChecker().matches(foundApplication));
    }

    @Test
    public void testAdd() {
        applicationService.add(
                APPLICATION_FIXTURE
        );
        verify(applicationRepository).save(argThat(
                new ApplicationFixtureChecker()));
    }

}
