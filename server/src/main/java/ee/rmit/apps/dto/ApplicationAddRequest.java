package ee.rmit.apps.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ApplicationAddRequest {

    private String code,name,group,type,description;
    private BigDecimal cost;

}
