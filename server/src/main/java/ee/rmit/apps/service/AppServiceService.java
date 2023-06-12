package ee.rmit.apps.service;

import ee.rmit.apps.dto.AppService;
import ee.rmit.apps.repository.AppServiceRepository;
import ee.rmit.apps.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppServiceService {

    private final AppServiceRepository appServiceRepository;
    private final ApplicationRepository applicationRepository;

    @Autowired
    public AppServiceService(ApplicationRepository applicationRepository,
                             AppServiceRepository appServiceRepository) {
        this.appServiceRepository = appServiceRepository;
        this.applicationRepository = applicationRepository;
    }

    public List<AppService> searchByApplicationName(String applicationName) {
        return applicationRepository.findByName(applicationName).getServices();
    }

    public void add(AppService appService) {
        appServiceRepository.save(appService);
    }
}
