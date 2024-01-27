package com.caching.service;
import com.caching.dto.GeocodingDTO;
import com.caching.dto.ReverseGeocodingDTO;
import com.caching.wrapper.GeocodingResponse;
import com.caching.wrapper.ReverseGeocodingResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;



@Service
public class ApiRequestHandleService {
    private static final Logger logger = LoggerFactory.getLogger(ApiRequestHandleService.class);

    @Value("${api-key}")
    private String positionstackApiKey;

    @Value("${api.url}")
    private String positionstackApiUrl;

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;


    public ApiRequestHandleService(RestTemplate restTemplate, ObjectMapper objectMapper)
    {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;

    }


    @Cacheable(value = "geocoding", key = "#address", unless = "#result.name.toLowerCase() == 'goa'")
    public GeocodingDTO fetchGeoDataFromApi(String address)
    {
        logger.debug("Fetching geocoding data from the given Address using API : {}", address);
        String apiUrl = positionstackApiUrl + "/forward";
        String apiKeyParam = "access_key=" + positionstackApiKey;
        String queryParam = "query=" + address;
        String completeUrl = apiUrl + "?" + apiKeyParam + "&"  + queryParam;

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(completeUrl, String.class);
        String geoCodeResult =  responseEntity.getBody();

        try{
            GeocodingResponse geocodingResponse =  objectMapper.readValue(geoCodeResult, GeocodingResponse.class);

            List<GeocodingDTO> geoList = geocodingResponse.getData();
            logger.debug("Geocoding data fetched and stored successfully");
            return geoList.isEmpty() ? null : geoList.get(0);
        }catch (IOException e)
        {

            logger.error("Error occurred while fetching geocoding data from API", e);
            return null;
        }

    }


    @Cacheable(value = "reverse-geocoding", key = "{#latitude, #longitude}")
    public ReverseGeocodingDTO fetchReverseGeoDataFromApi(Double latitude, Double longitude) {
        logger.debug("Fetching reverse geocoding data from given latitude and longitude from the API : {}, {}", latitude, longitude);
        String apiUrl = positionstackApiUrl + "/reverse";
        String apiKeyParam = "access_key=" + positionstackApiKey;
        String latitudeParam = String.valueOf(latitude);
        String longitudeParam = String.valueOf(longitude);
        String completeUrl = apiUrl + "?" + apiKeyParam + "&query="  + latitudeParam +","+longitudeParam;

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(completeUrl, String.class);
        String reverseGeoCodeResult =  responseEntity.getBody();

        try{
            ReverseGeocodingResponse reverseGeocodingResponse = objectMapper.readValue(reverseGeoCodeResult, ReverseGeocodingResponse.class);
            List<ReverseGeocodingDTO> geoList = reverseGeocodingResponse.getData();
            logger.debug("Reverse geocoding data fetched and stored successfully.");
            return geoList.isEmpty() ? null : geoList.get(0);
        }catch(IOException e)
        {
            logger.error("Error occurred while fetching reverse geocoding data from the API", e);
            return null;
        }
    }

    @CacheEvict(value = "geocoding", key = "#address")
    public void removeGeoDataFromCache(String address)
    {
        logger.info("Removing data from geocoding cache for address: {}", address);
    }
}
