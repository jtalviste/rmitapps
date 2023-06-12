package ee.rmit.apps.controller;

import ee.rmit.apps.ControllerTestConfiguration;
import ee.rmit.apps.dto.AppServiceAddRequest;
import ee.rmit.apps.dto.ApplicationAddRequest;
import ee.rmit.apps.repository.AppServiceRepository;
import ee.rmit.apps.repository.ApplicationRepository;
import ee.rmit.apps.service.ApplicationService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.Optional;

import static ee.rmit.apps.dto.AppServiceSubType.REST;
import static ee.rmit.apps.dto.AppServiceType.HTTP;
import static ee.rmit.apps.fixtures.AppServiceListFixtureChecker.APP_SERVICES_FIXTURE;
import static ee.rmit.apps.fixtures.ApplicationFixtureChecker.APPLICATION_FIXTURE;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This tests all the components of the application controller
 * whilst still being connected - an integration test.
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(ApplicationController.class)
@ContextConfiguration(classes = {ControllerTestConfiguration.class})
public class ApplicationControllerTest {

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
        Mockito.when(appServiceRepository.findByName(anyString()))
                .thenReturn(Optional.of(APP_SERVICES_FIXTURE.get(0)));

        mockMvc.perform(MockMvcRequestBuilders.get("/application/search")
                        .param("service_name","nameA")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));

        Mockito.verify(appServiceRepository, times(1)).findByName(anyString());
    }

    @Test
    public void addApplicationTest() throws Exception {
        // Call the controller method through mocked HTTP
        ApplicationAddRequest applicationAddRequest = new ApplicationAddRequest(
                "code", "name", "group", "type", "description",
                BigDecimal.ONE
        );
        mockMvc.perform(MockMvcRequestBuilders.post("/application")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(applicationAddRequest))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        // Verify the interaction with the mock service
        Mockito.verify(applicationRepository, times(1)).save(any());
    }

}
