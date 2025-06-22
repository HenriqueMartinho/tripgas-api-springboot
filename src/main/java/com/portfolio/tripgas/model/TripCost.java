package com.portfolio.tripgas.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.portfolio.tripgas.external.cardto.DataDTO;
import com.portfolio.tripgas.external.mapsdto.route.SummaryDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class TripCost {

    @JsonIgnoreProperties({"model_id", "epa_city_mpg", "epa_highway_mpg"})
    private DataDTO car;

    private SummaryDTO route;

    @JsonProperty("gasoline_price")
    private double gasolinePrice = 6.19;

    @JsonProperty("fuel_needeed")
    private double fuelNedeed;
    @JsonProperty("estimated_cost")
    private double estimatedCost;

}
