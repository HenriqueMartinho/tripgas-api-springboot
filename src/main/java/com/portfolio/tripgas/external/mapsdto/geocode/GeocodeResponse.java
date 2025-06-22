package com.portfolio.tripgas.external.mapsdto.geocode;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class GeocodeResponse {
    private List<FeatureDTO> features;
}
