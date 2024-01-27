package com.caching.wrapper;

import com.caching.dto.GeocodingDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GeocodingData {
    @JsonProperty("results")
    private List<GeocodingDTO> results;

    public List<GeocodingDTO> getResults()
    {
        return results;
    }

    public void setResults(List<GeocodingDTO> results) {
        this.results = results;
    }
}
