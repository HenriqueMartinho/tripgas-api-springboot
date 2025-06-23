package com.portfolio.tripgas.external.cardto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@Data
public class DataDTO {
    private int model_id;
    private String model;
    private double fuel_tank_capacity;
    private double epa_city_mpg;
    private double epa_highway_mpg;
}
