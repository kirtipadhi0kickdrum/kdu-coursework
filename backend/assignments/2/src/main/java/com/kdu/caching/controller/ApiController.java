package com.kdu.caching.controller;
import com.kdu.caching.dto.GeocodingDTO;
import com.kdu.caching.dto.ReverseGeocodingDTO;
import com.kdu.caching.service.ApiRequestHandleService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ApiController {


    private final ApiRequestHandleService apiRequestHandleService;

    public ApiController(ApiRequestHandleService apiRequestHandleService)
    {
        this.apiRequestHandleService = apiRequestHandleService;
    }


    @GetMapping("/geocoding")
    public ResponseEntity<GeocodingDTO> forwardGeocode(@RequestParam String address)
    {

        GeocodingDTO geoCode = apiRequestHandleService.fetchGeoDataFromApi(address);
        return ResponseEntity.ok(geoCode);

    }


    @GetMapping("/reverse-geocoding")
    public ResponseEntity<ReverseGeocodingDTO> reverseGeocode(@RequestParam Double latitude, @RequestParam Double longitude)
    {
        ReverseGeocodingDTO reverseGeoCode = apiRequestHandleService.fetchReverseGeoDataFromApi(latitude, longitude);
        return ResponseEntity.ok(reverseGeoCode);
    }
}
