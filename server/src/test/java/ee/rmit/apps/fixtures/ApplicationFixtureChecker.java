package ee.rmit.apps.fixtures;

import ee.rmit.apps.dto.Application;
import org.mockito.ArgumentMatcher;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;

public class ApplicationFixtureChecker implements ArgumentMatcher<Application> {

    public static final Application APPLICATION_FIXTURE = new Application(
            "code",
            "name",
            "group",
            "type",
            "description",
            BigDecimal.ONE,
            LocalDateTime.MIN,
            Collections.emptyList()
    );

    @Override
    public boolean matches(Application application) {
        return application.getApplicationCode().equals("code") &&
                application.getName().equals("name") &&
                application.getGroup().equals("group") &&
                application.getType().equals("type") &&
                application.getDescription().equals("description") &&
                application.getCost().doubleValue() == 1 &&
                application.getLastModified() != null;
    }

}
