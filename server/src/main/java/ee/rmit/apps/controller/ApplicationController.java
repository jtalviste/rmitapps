package ee.rmit.apps.controller;

import ee.rmit.apps.controller.mapper.ApplicationDtoMapper;
import ee.rmit.apps.dto.Application;
import ee.rmit.apps.dto.ApplicationAddRequest;
import ee.rmit.apps.dto.ApplicationResponse;
import ee.rmit.apps.service.ApplicationService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "${ee.rmit.apps.devHost}")
@RestController
@RequestMapping("/application")
public class ApplicationController {

    private final ApplicationService applicationService;
    private final ApplicationDtoMapper applicationDtoMapper;

    public ApplicationController(ApplicationService applicationService, ApplicationDtoMapper applicationDtoMapper) {
        this.applicationService = applicationService;
        this.applicationDtoMapper = applicationDtoMapper;
    }

    @ResponseBody
    @GetMapping("/search")
    public ApplicationResponse search(@RequestParam("service_name") String appServiceName) {
        return applicationService.searchByAppServiceName(appServiceName)
                .map(applicationDtoMapper::mapResponse)
                .orElse(null);
    }

    @Transactional
    @PostMapping
    public void save(@RequestBody ApplicationAddRequest applicationAddRequest) {
        applicationService.add(applicationDtoMapper.mapRequest(applicationAddRequest));
    }

}