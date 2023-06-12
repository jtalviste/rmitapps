package ee.rmit.apps.fixtures;

import ee.rmit.apps.dto.AppService;
import org.mockito.ArgumentMatcher;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static ee.rmit.apps.dto.AppServiceSubType.REST;
import static ee.rmit.apps.dto.AppServiceType.HTTP;

public class AppServiceListFixtureChecker implements ArgumentMatcher<List<AppService>> {

    ApplicationFixtureChecker applicationFixtureChecker = new ApplicationFixtureChecker();

    public static final List<AppService> APP_SERVICES_FIXTURE = Arrays.asList(
            new AppService(
                    ApplicationFixtureChecker.APPLICATION_FIXTURE,
                    "codeA",
                    "nameA",
                    HTTP,
                    REST,
                    "descriptionA",
                    LocalDateTime.MIN
            ),
            new AppService(
                    ApplicationFixtureChecker.APPLICATION_FIXTURE,
                    "codeB",
                    "nameB",
                    HTTP,
                    REST,
                    "descriptionB",
                    LocalDateTime.MIN)
    );

    @Override
    public boolean matches(List<AppService> appServices) {
        return appServices.size() == 2 &&
                checkAppService(appServices.get(0),"A") &&
                checkAppService(appServices.get(1),"B");
    }

    private boolean checkAppService(AppService appService, String suffix) {
        return applicationFixtureChecker.matches(appService.getApplication()) &&
                appService.getServiceCode().equals("code"+suffix) &&
                appService.getName().equals("name"+suffix) &&
                appService.getType().equals(HTTP) &&
                appService.getSubType().equals(REST) &&
                appService.getDescription().equals("description"+suffix) &&
                appService.getLastModified().equals(LocalDateTime.MIN);
    }

}
