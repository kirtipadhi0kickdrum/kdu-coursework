package com.kdu.caching.wrapper;

import com.kdu.caching.dto.ReverseGeocodingDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ReverseGeocodingResponse {

    @JsonProperty("data")
    private List<ReverseGeocodingDTO> data;

    public List<ReverseGeocodingDTO> getData() {
        return data;
    }

    public void setData(List<ReverseGeocodingDTO> data) {
        this.data = data;
    }
}
