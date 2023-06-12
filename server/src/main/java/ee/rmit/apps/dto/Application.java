package ee.rmit.apps.dto;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "Application",
        indexes = {
                @Index(name = "application_name_index", columnList = "Name")
        }
)
public class Application {

    @Id
    @Column(name = "App_code")
    private String applicationCode;

    @Column(name = "Name", nullable = false, unique = true)
    private String name;

    @Column(name = "App_group")
    private String group;

    @Column(name = "App_type")
    private String type;

    @Column(name = "Description", length = 200000)
    private String description;

    @Column(name = "App_cost")
    private BigDecimal cost;

    @Column(name = "Last_modified", nullable = false)
    private LocalDateTime lastModified;

    @OneToMany(mappedBy = "application")
    private List<AppService> services;

}
