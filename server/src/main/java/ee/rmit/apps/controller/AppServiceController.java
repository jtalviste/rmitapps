package ee.rmit.apps.controller;

import ee.rmit.apps.controller.mapper.AppServiceDtoMapper;
import ee.rmit.apps.dto.AppService;
import ee.rmit.apps.dto.AppServiceAddRequest;
import ee.rmit.apps.dto.AppServiceResponse;
import ee.rmit.apps.service.AppServiceService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@CrossOrigin(origins = "${ee.rmit.apps.devHost}")
@RestController
@RequestMapping("/service")
public class AppServiceController {

    private final AppServiceService appServiceService;

    private final AppServiceDtoMapper appServiceDtoMapper;

    public AppServiceController(AppServiceService appServiceService, AppServiceDtoMapper appServiceDtoMapper) {
        this.appServiceService = appServiceService;
        this.appServiceDtoMapper = appServiceDtoMapper;
    }

    @ResponseBody
    @GetMapping("/search")
    public List<AppServiceResponse> search(@RequestParam("application_name") String applicationName) {
        return appServiceService.searchByApplicationName(applicationName)
                .stream().map(appServiceDtoMapper::mapResponse)
                .collect(toList());
    }

    @Transactional
    @PostMapping
    public void save(@RequestBody AppServiceAddRequest appServiceAddRequest) {
        appServiceService.add(appServiceDtoMapper.mapRequest(appServiceAddRequest));
    }

}