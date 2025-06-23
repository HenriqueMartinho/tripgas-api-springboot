package com.portfolio.tripgas.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class TripCost {

    @JsonIgnoreProperties({"model_id", "epa_city_mpg", "epa_highway_mpg"})
    private CarDetails car;

    private RouteSummary route;

    @JsonProperty("gasoline_price")
    private double gasolinePrice = 6.19;

    @JsonProperty("fuel_needeed")
    private BigDecimal fuelNedeed;
    @JsonProperty("estimated_cost")
    private BigDecimal estimatedCost;

}
