package com.portfolio.tripgas.external.mapsdto.geocode;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CoordinatesDTO {
    private List<Double> coordinates;
}
