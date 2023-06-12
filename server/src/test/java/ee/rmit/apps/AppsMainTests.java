package ee.rmit.apps;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootTest
@ComponentScan(basePackages = "ee.rmit.apps",
		excludeFilters = {
				@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = ControllerTestConfiguration.class)
		})
class AppsMainTests {

	@Test
	void contextLoads() {
	}

}
