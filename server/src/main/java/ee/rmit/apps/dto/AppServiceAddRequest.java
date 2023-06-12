package ee.rmit.apps.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppServiceAddRequest {

    private String code, name, description, applicationCode;
    private AppServiceType type;
    private AppServiceSubType subType;

}
