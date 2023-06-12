package ee.rmit.apps.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppServiceResponse {

    private final String serviceCode;
    private final String name;
    private final AppServiceType type;
    private final AppServiceSubType subType;
    private final String description;
    private final LocalDateTime lastModified;

    public AppServiceResponse(String serviceCode, String name, AppServiceType type, AppServiceSubType subType, String description, LocalDateTime lastModified) {
        this.serviceCode = serviceCode;
        this.name = name;
        this.type = type;
        this.subType = subType;
        this.description = description;
        this.lastModified = lastModified;
    }
}
