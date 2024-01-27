package com.kdu.caching.wrapper;


import com.kdu.caching.dto.GeocodingDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GeocodingResponse {

    @JsonProperty("data")
    private List<GeocodingDTO> data;
    public List<GeocodingDTO> getData()
    {
        return data;
    }

    public void setData(List<GeocodingDTO> data)
    {
        this.data = data;
    }
}
