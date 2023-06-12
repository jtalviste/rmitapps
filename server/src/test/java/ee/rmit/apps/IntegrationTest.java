package ee.rmit.apps;

import ee.rmit.apps.dto.AppService;
import ee.rmit.apps.dto.Application;
import ee.rmit.apps.fixtures.AppServiceListFixtureChecker;
import ee.rmit.apps.fixtures.ApplicationFixtureChecker;
import ee.rmit.apps.service.AppServiceService;
import ee.rmit.apps.service.ApplicationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static ee.rmit.apps.fixtures.AppServiceListFixtureChecker.APP_SERVICES_FIXTURE;
import static ee.rmit.apps.fixtures.ApplicationFixtureChecker.APPLICATION_FIXTURE;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test the whole system below service layer.
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
@ComponentScan(basePackages = "ee.rmit.apps",
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = ControllerTestConfiguration.class)
        })
public class IntegrationTest {

    private final ApplicationService applicationService;

    private final AppServiceService appServiceService;

    private final ApplicationFixtureChecker applicationFixtureChecker = new ApplicationFixtureChecker();
    private final AppServiceListFixtureChecker appServiceListFixtureChecker = new AppServiceListFixtureChecker();

    @Autowired
    public IntegrationTest(ApplicationService applicationService, AppServiceService appServiceService) {
        this.applicationService = applicationService;
        this.appServiceService = appServiceService;
    }

    @Transactional
    @Test
    void servicesIntegrationTest() {
        applicationService.add(APPLICATION_FIXTURE);
        appServiceService.add(APP_SERVICES_FIXTURE.get(0));
        appServiceService.add(APP_SERVICES_FIXTURE.get(1));
        Optional<Application> applicationOfA = applicationService.searchByAppServiceName("nameA");
        Optional<Application> applicationOfB = applicationService.searchByAppServiceName("nameB");
        List<AppService> services = appServiceService.searchByApplicationName(APPLICATION_FIXTURE.getName());
        assertTrue(applicationOfA.isPresent());
        boolean matches = applicationFixtureChecker.matches(applicationOfA.get());
        assertTrue(matches);
        assertTrue(applicationOfB.isPresent());
        assertTrue(applicationFixtureChecker.matches(applicationOfB.get()));
        appServiceListFixtureChecker.matches(services);
    }

}
