package ee.rmit.apps;

import ee.rmit.apps.controller.mapper.AppServiceDtoMapper;
import ee.rmit.apps.controller.mapper.ApplicationDtoMapper;
import ee.rmit.apps.repository.AppServiceRepository;
import ee.rmit.apps.repository.ApplicationRepository;
import ee.rmit.apps.service.AppServiceService;
import ee.rmit.apps.service.ApplicationService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ControllerTestConfiguration {

    @Bean
    public ApplicationRepository applicationRepository() {
        return Mockito.mock(ApplicationRepository.class);
    }

    @Bean
    public AppServiceRepository appServiceRepository() {
        return Mockito.mock(AppServiceRepository.class);
    }

    @Autowired
    @Bean
    public ApplicationService applicationService(ApplicationRepository applicationRepository, AppServiceRepository appServiceRepository) {
        return new ApplicationService(applicationRepository, appServiceRepository);
    }

    @Autowired
    @Bean
    public AppServiceService appServiceService(ApplicationRepository applicationRepository, AppServiceRepository appServiceRepository) {
        return new AppServiceService(applicationRepository, appServiceRepository);
    }

    @Autowired
    @Bean
    public AppServiceDtoMapper appServiceDtoMapper(ApplicationRepository applicationRepository) {
        return new AppServiceDtoMapper(applicationRepository);
    }

    @Autowired
    @Bean
    public ApplicationDtoMapper applicationDtoMapper() {
        return new ApplicationDtoMapper();
    }

}
