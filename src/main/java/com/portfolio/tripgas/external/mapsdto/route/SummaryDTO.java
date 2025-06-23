package com.portfolio.tripgas.external.mapsdto.route;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class SummaryDTO {
    private double distance;
    private double duration;
}
