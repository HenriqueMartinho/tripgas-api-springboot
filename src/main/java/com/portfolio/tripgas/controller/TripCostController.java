package com.portfolio.tripgas.controller;

import com.portfolio.tripgas.exception.InvalidInputException;
import com.portfolio.tripgas.exception.NotFoundException;
import com.portfolio.tripgas.service.TripCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TripCostController {

    @Autowired
    private TripCostService tripCostService;

    @GetMapping("trip-cost")
    public ResponseEntity<?> getTripCost(@RequestParam String car, @RequestParam String startPoint, @RequestParam String endPoint){
        try{
            return ResponseEntity.ok().body(tripCostService.getTripCost(car, startPoint, endPoint));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InvalidInputException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }





}
