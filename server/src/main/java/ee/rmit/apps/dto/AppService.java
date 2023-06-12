package ee.rmit.apps.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "App_service",
        indexes = {
            @Index(name = "app_service_name_index", columnList = "Name")
        }
)
public class AppService {

    @ManyToOne
    @JoinColumn(name = "App_code")
    private Application application;

    @Id
    @Column(name = "Service_code")
    private String serviceCode;

    @Column(name = "Name", nullable = false, unique = true)
    private String name;

    @Column(name = "Type")
    private AppServiceType type;

    @Column(name = "Sub_type")
    private AppServiceSubType subType;

    @Column(name = "Description", length = 200000)
    private String description;

    @Column(name = "Last_modified", nullable = false)
    private LocalDateTime lastModified;

}
