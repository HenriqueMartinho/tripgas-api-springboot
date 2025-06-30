package com.portfolio.tripgas.service;

import com.portfolio.tripgas.exception.InvalidInputException;
import com.portfolio.tripgas.exception.NotFoundException;
import com.portfolio.tripgas.external.mapsdto.MapsClient;
import com.portfolio.tripgas.entity.UserRoute;
import com.portfolio.tripgas.repository.UserRouteRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserRouteService {

    @Autowired
    private UserRouteRepository userRouteRepository;

    @Autowired
    private MapsClient mapsClient;

    public void saveRoute(String startPoint, String endPoint){
        userRouteRepository.save(new UserRoute(
                startPoint,
                endPoint,
                mapsClient.getCoords(startPoint),
                mapsClient.getCoords(endPoint))
        );
    }

    public List<UserRoute> findAll(){

        List<UserRoute> userRoutes = userRouteRepository.findAll();

        if(userRoutes.isEmpty()) throw new NotFoundException("There's no saved routes");

        return userRoutes;
    }

    public UserRoute findById(Long id){

        if(userRouteRepository.findById(id).isEmpty()) throw new NotFoundException("No routes assign to ID " + id);

        Optional<UserRoute> userRoute = userRouteRepository.findById(id);

        return userRoute.get();
    }

    public List<UserRoute> findByAdress(String startPoint, String endPoint){

        List<UserRoute> userRoutes = findAll();
        List<Double> searchAdress = mapsClient.getCoords(startPoint);

        List<UserRoute> response = new ArrayList<>();

        if(!endPoint.isEmpty() && !startPoint.isEmpty()){

            List<Double> searchA = mapsClient.getCoords(endPoint);

            for(UserRoute user : userRoutes){
                if(user.getStartCoord().equals(searchAdress) && user.getEndCoord().equals(searchA)) response.add(user);
                else throw new NotFoundException("No routes found"
                        + "From: " + startPoint
                        + "To: " + endPoint);
            }
        }

        for (UserRoute user : userRoutes) {

            List<Double> start = user.getStartCoord();
            List<Double> end = user.getEndCoord();

            if (start.equals(searchAdress) || end.equals(searchAdress)) response.add(user);
        }

        if(response.isEmpty()) throw new NotFoundException("No routes saved at: " + startPoint);

        return response;
    }

    public String updateRoute(Long id, String updateStart, String updateEnd){

//        if(!(id >= 0)) throw new InvalidInputException(id + "is not a valid ID");

        UserRoute userRoute = findById(id);
        String response = "";

        if (updateStart != null && !updateStart.equals(userRoute.getStartPoint())) {
            userRoute.setStartPoint(updateStart);
            userRoute.setStartCoord(mapsClient.getCoords(updateStart));

            response += "Start point setted to: " + updateStart + "\n";
        }

        if (updateEnd != null && !updateEnd.equals(userRoute.getEndPoint())) {
            userRoute.setEndPoint(updateEnd);
            userRoute.setEndCoord(mapsClient.getCoords(updateEnd));

            response += "End point setted to: " + updateEnd;
        }

        userRouteRepository.save(userRoute);

        return response;
    }

    public String deleteRoute(Long id){

        if(findById(id) == null) throw new NotFoundException("No routes assign to ID: " + id);

        userRouteRepository.delete(findById(id));

        return "Route " + id + " deleted!";
    }

}
