package com.portfolio.tripgas.external.mapsdto.geocode;

import lombok.Data;

import java.util.List;

@Data
public class GeocodeResponse {
    private List<FeatureDTO> features;
}
