package com.portfolio.tripgas.controller;

import com.portfolio.tripgas.exception.InvalidInputException;
import com.portfolio.tripgas.exception.NotFoundException;
import com.portfolio.tripgas.service.UserRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserRouteController {

    @Autowired
    private UserRouteService userRouteService;

    @PostMapping("/save-route")
    public ResponseEntity<String> saveRoute(@RequestParam String startPoint, @RequestParam String endPoint){
        try{
            userRouteService.saveRoute(startPoint, endPoint);
            return ResponseEntity.ok().body("Route saved!"
                    + "\nFrom: " + startPoint
                    + "\nTo: " + endPoint
            );
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InvalidInputException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/user-routes")
    public ResponseEntity<?> getAllUserRoutes(){
        try{
            return ResponseEntity.ok().body(userRouteService.findAll());
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/user-routes/route-id/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try{
            return ResponseEntity.ok().body(userRouteService.findById(id));
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/user-routes/route-adress")
    public ResponseEntity<?> findByAdress(@RequestParam(required = false) String startPoint,
                                          @RequestParam(required = false) String endPoint){
        try{
            return ResponseEntity.ok().body(userRouteService.findByAdress(startPoint, endPoint));
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InvalidInputException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PatchMapping("/user-routes/update-route/{id}")
    public ResponseEntity<String> updateRoute(@PathVariable Long id,
                                              @RequestParam(required = false) String updateStart,
                                              @RequestParam(required = false) String updateEnd){

        String response = "";

        try{
            response = userRouteService.updateRoute(id, updateStart, updateEnd);
            return ResponseEntity.ok().body("Route Updated at ID " + id
                    + "\n" + response);
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/user-routes/delete-route/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){

        String response = "";

        try{
            response = userRouteService.deleteRoute(id);
            return ResponseEntity.ok().body(response);
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
