package com.portfolio.tripgas.controller;

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

    @GetMapping("/user-routes/route-id")
    public ResponseEntity<?> findById(@RequestParam Long id){
        try{
            return ResponseEntity.ok().body(userRouteService.findById(id));
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/user-routes/route-adress")
    public ResponseEntity<?> findByAdress(@RequestParam String adress){
        try{
            return ResponseEntity.ok().body(userRouteService.findByAdress(adress));
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("user-routes/update-route")
    public ResponseEntity<String> updateRoute(@RequestParam Long id,
                                              @RequestParam(required = false) String updateStart,
                                              @RequestParam(required = false) String updateEnd){

        String responseMessage = userRouteService.updateRoute(id, updateStart, updateEnd);

        return ResponseEntity.ok().body("Route Updated at ID " + id
                + "\n" + responseMessage);
    }

}
