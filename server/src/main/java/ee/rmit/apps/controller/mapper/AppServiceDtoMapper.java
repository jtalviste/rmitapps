package ee.rmit.apps.controller.mapper;

import ee.rmit.apps.dto.*;
import ee.rmit.apps.repository.ApplicationRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AppServiceDtoMapper {
    private final ApplicationRepository applicationRepository;

    public AppServiceDtoMapper(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public AppService mapRequest(AppServiceAddRequest appServiceAddRequest) {
        Application application = applicationRepository
                .findById(appServiceAddRequest.getApplicationCode())
                .orElse(null);//database validation should take effect

        String code = appServiceAddRequest.getCode();
        String name = appServiceAddRequest.getName();
        AppServiceType type = appServiceAddRequest.getType();
        AppServiceSubType subType = appServiceAddRequest.getSubType();
        String description = appServiceAddRequest.getDescription();
        return new AppService(application,code,name,type,subType,description, LocalDateTime.now());
    }

    public AppServiceResponse mapResponse(AppService appService) {
        return new AppServiceResponse(
                appService.getServiceCode(),
                appService.getName(),
                appService.getType(),
                appService.getSubType(),
                appService.getDescription(),
                appService.getLastModified()
        );
    }
}
