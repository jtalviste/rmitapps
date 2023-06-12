package ee.rmit.apps.repository;

import ee.rmit.apps.dto.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, String> {

    Application findByName(String name);

}
