package com.portfolio.tripgas.controller;

import com.portfolio.tripgas.model.TripCost;
import com.portfolio.tripgas.service.TripCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TripCostController {

    @Autowired
    private TripCostService tripCostService;

    @GetMapping("trip-gas")
    public ResponseEntity<TripCost> getTripCost(@RequestParam String car, @RequestParam String route){
        return ResponseEntity.ok().body(tripCostService.getTripCost(car, route));
    }

}
