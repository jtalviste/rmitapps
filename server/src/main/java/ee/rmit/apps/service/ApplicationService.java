package ee.rmit.apps.service;

import ee.rmit.apps.dto.AppService;
import ee.rmit.apps.dto.Application;
import ee.rmit.apps.repository.AppServiceRepository;
import ee.rmit.apps.repository.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final AppServiceRepository appServiceRepository;

    public ApplicationService(ApplicationRepository applicationRepository, AppServiceRepository appServiceRepository) {
        this.applicationRepository = applicationRepository;
        this.appServiceRepository = appServiceRepository;
    }

    public Optional<Application> searchByAppServiceName(String appServiceName) {
        return appServiceRepository.findByName(appServiceName).map(AppService::getApplication);
    }

    public void add(Application application) {
        applicationRepository.save(application);
    }
}
