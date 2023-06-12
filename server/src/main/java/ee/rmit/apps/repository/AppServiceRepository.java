package ee.rmit.apps.repository;

import ee.rmit.apps.dto.AppService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppServiceRepository extends JpaRepository<AppService, Long> {
    Optional<AppService> findByName(String name);

}
