package ee.rmit.apps.fixtures;

import ee.rmit.apps.dto.AppService;
import org.mockito.ArgumentMatcher;

import java.time.LocalDateTime;

import static ee.rmit.apps.dto.AppServiceSubType.REST;
import static ee.rmit.apps.dto.AppServiceType.HTTP;

public class AppServiceFixtureChecker implements ArgumentMatcher<AppService> {

    private final String suffix;

    ApplicationFixtureChecker applicationFixtureChecker = new ApplicationFixtureChecker();

    public AppServiceFixtureChecker(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public boolean matches(AppService appService) {
        return applicationFixtureChecker.matches(appService.getApplication()) &&
                appService.getServiceCode().equals("code"+suffix) &&
                appService.getName().equals("name"+suffix) &&
                appService.getType().equals(HTTP) &&
                appService.getSubType().equals(REST) &&
                appService.getDescription().equals("description"+suffix) &&
                appService.getLastModified().equals(LocalDateTime.MIN);
    }

}
