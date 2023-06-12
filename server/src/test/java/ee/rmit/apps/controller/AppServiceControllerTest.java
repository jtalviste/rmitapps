package ee.rmit.apps.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.rmit.apps.ControllerTestConfiguration;
import ee.rmit.apps.dto.*;
import ee.rmit.apps.fixtures.AppServiceFixtureChecker;
import ee.rmit.apps.fixtures.AppServiceListFixtureChecker;
import ee.rmit.apps.fixtures.ApplicationFixtureChecker;
import ee.rmit.apps.repository.AppServiceRepository;
import ee.rmit.apps.repository.ApplicationRepository;
import ee.rmit.apps.service.ApplicationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static ee.rmit.apps.dto.AppServiceSubType.REST;
import static ee.rmit.apps.dto.AppServiceType.HTTP;
import static ee.rmit.apps.fixtures.AppServiceListFixtureChecker.APP_SERVICES_FIXTURE;
import static ee.rmit.apps.fixtures.ApplicationFixtureChecker.APPLICATION_FIXTURE;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This tests all the components of the app service controller
 * whilst still being connected - an integration test.
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(AppServiceController.class)
@ContextConfiguration(classes = {ControllerTestConfiguration.class})
public class AppServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private AppServiceRepository appServiceRepository;

    @Test
    public void searchTest() throws Exception {
        APPLICATION_FIXTURE.setServices(APP_SERVICES_FIXTURE);
        Mockito.when(applicationRepository.findByName(anyString())).thenReturn(APPLICATION_FIXTURE);

        mockMvc.perform(MockMvcRequestBuilders.get("/service/search")
                        .param("application_name","app name")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

        Mockito.verify(applicationRepository, times(1)).findByName(anyString());
    }

    @Test
    public void addServiceTest() throws Exception {
        // Call the controller method through mocked HTTP
        AppServiceAddRequest serviceAddRequest = new AppServiceAddRequest(
        "code", "name", "description", "app code", HTTP, REST
        );
        mockMvc.perform(MockMvcRequestBuilders.post("/service")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(serviceAddRequest))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Verify the interaction with the mock service
        Mockito.verify(appServiceRepository, times(1)).save(any());
    }

}
