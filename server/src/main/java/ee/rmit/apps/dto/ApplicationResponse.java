package ee.rmit.apps.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ApplicationResponse {
    private final String applicationCode;
    private final String name;
    private final String type;
    private final String group;
    private final String description;
    private final BigDecimal cost;
    private final LocalDateTime lastModified;

    public ApplicationResponse(String applicationCode, String name, String type, String group, String description, BigDecimal cost, LocalDateTime lastModified) {
        this.applicationCode = applicationCode;
        this.name = name;
        this.type = type;
        this.group = group;
        this.description = description;
        this.cost = cost;
        this.lastModified = lastModified;
    }
}
