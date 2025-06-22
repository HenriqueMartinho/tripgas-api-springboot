package com.portfolio.tripgas.external.mapsdto.route;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class RouteResponse {
    private List<RouteFeatureDTO> features;
}
