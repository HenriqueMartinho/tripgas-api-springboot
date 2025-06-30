package com.portfolio.tripgas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class CarDetails {
    private String model;
    private BigDecimal fuel_tank_capacity;
    private BigDecimal epa_highway_mpg;
}
