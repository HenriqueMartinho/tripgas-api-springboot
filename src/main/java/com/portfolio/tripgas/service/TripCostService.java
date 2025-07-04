package com.portfolio.tripgas.service;

import com.portfolio.tripgas.external.cardto.CarClient;
import com.portfolio.tripgas.external.mapsdto.MapsClient;
import com.portfolio.tripgas.model.CarDetails;
import com.portfolio.tripgas.model.RouteSummary;
import com.portfolio.tripgas.model.TripCost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class TripCostService {

    @Autowired
    private MapsClient mapsClient;
    @Autowired
    private CarClient carClient;

    public TripCost getTripCost(String car, String startPoint, String endPoint){
        CarDetails carDetails = new CarDetails(
                carClient.getDetails(car).getModel(),
                BigDecimal.valueOf(gallonsToLiter(carClient.getDetails(car).getFuel_tank_capacity()))
                        .setScale(2, RoundingMode.HALF_UP),
                BigDecimal.valueOf(mpgToKml(carClient.getDetails(car).getEpa_highway_mpg()))
                        .setScale(2, RoundingMode.HALF_UP)
        );

        RouteSummary routeSummary = new RouteSummary(
                BigDecimal.valueOf(metersToKm(mapsClient.getRoute(startPoint, endPoint).getProperties().getSummary().getDistance()))
                        .setScale(2, RoundingMode.HALF_UP),
                secondsToHours(mapsClient.getRoute(startPoint, endPoint).getProperties().getSummary().getDuration())
        );

        double gasolinePrice = 6.19;

        BigDecimal fuelNeeded = routeSummary.getDistance()
                .divide(carDetails.getEpa_highway_mpg(), RoundingMode.HALF_UP)
                .setScale(2, RoundingMode.HALF_UP);
        BigDecimal estimatedCost = fuelNeeded
                .multiply(BigDecimal.valueOf(gasolinePrice))
                .setScale(2, RoundingMode.HALF_UP);

        TripCost tripCost = new TripCost(carDetails, routeSummary, gasolinePrice, fuelNeeded, estimatedCost);

        return tripCost;
    }

    public Double gallonsToLiter(double gallons){
        return gallons * 3.78541;
    }
    public Double metersToKm(double meter){
        return meter / 1000;
    }
    public Double mpgToKml(double mpg){
        return mpg * 0.42514;
    }
    public String secondsToHours(double seconds){
        String hrs = String.format("%.0f", seconds / 3600);
        String mins = String.format("%.0f", (seconds % 3600) / 60);
        String secs = String.format("%.0f", ((seconds % 3600) / 60) % 60);
        return hrs + "h " + mins + "m " + secs + "s";
    }

}
