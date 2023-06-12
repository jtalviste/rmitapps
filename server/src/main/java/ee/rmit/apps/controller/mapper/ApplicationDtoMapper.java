package ee.rmit.apps.controller.mapper;

import ee.rmit.apps.dto.AppServiceResponse;
import ee.rmit.apps.dto.Application;
import ee.rmit.apps.dto.ApplicationAddRequest;
import ee.rmit.apps.dto.ApplicationResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;

@Component
public class ApplicationDtoMapper {
    public Application mapRequest(ApplicationAddRequest applicationAddRequest) {
        return new Application(
                applicationAddRequest.getCode(),
                applicationAddRequest.getName(),
                applicationAddRequest.getGroup(),
                applicationAddRequest.getType(),
                applicationAddRequest.getDescription(),
                applicationAddRequest.getCost(),
                LocalDateTime.now(),
                Collections.emptyList()
        );
    }

    public ApplicationResponse mapResponse(Application application) {
        return new ApplicationResponse(
                application.getApplicationCode(),
                application.getName(),
                application.getType(),
                application.getGroup(),
                application.getDescription(),
                application.getCost(),
                application.getLastModified()
        );
    }
}
