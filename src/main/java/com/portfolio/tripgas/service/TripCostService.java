package com.portfolio.tripgas.service;

import com.portfolio.tripgas.external.cardto.CarClient;
import com.portfolio.tripgas.external.mapsdto.MapsClient;
import com.portfolio.tripgas.model.TripCost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class TripCostService {

    @Autowired
    private MapsClient mapsClient;
    @Autowired
    private CarClient carClient;
    @Autowired
    private TripCost tripCost;

    public TripCost getTripCost(String model, String route){
        DecimalFormat decimalFormat = new DecimalFormat("#,##");

        tripCost.setRoute(mapsClient.getRoute(route).getProperties().getSummary());
        tripCost.setCar(carClient.getDetails(model));

        tripCost.setFuelNedeed(Double.valueOf(decimalFormat.format(tripCost.getRoute().getDistance() / mpgToKml(tripCost.getCar().getEpa_highway_mpg()))));
        tripCost.setEstimatedCost(Double.valueOf(decimalFormat.format(tripCost.getFuelNedeed() * tripCost.getGasolinePrice())));

        System.out.println(tripCost.toString());

        return tripCost;
    }

    public Double gallonsToLiter(double gallons){
        return gallons * 3.78541;
    }
    public Double mpgToKml(double mpg){
        return mpg * 0.42514;
    }

}
