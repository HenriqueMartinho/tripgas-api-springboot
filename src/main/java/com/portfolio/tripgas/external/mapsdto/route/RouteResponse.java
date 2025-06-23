package com.portfolio.tripgas.external.mapsdto.route;

import lombok.Data;

import java.util.List;

@Data
public class RouteResponse {
    private List<RouteFeatureDTO> features;
}
