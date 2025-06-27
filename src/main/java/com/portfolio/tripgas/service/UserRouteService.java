package com.portfolio.tripgas.service;

import com.portfolio.tripgas.exception.NotFoundException;
import com.portfolio.tripgas.external.mapsdto.MapsClient;
import com.portfolio.tripgas.entity.UserRoute;
import com.portfolio.tripgas.repository.UserRouteRepository;
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

        List<UserRoute> userRoutes = findAll();

        if(userRoutes.isEmpty()){
            throw new NotFoundException("There's no saved routes");
        }

        return userRoutes;
    }

    public UserRoute findById(Long id){

        if(userRouteRepository.findById(id).isEmpty()){
            throw new NotFoundException("No routes assign to ID " + id);
        }

        UserRoute userRoute = findById(id);

        return userRoute;
    }

    public List<UserRoute> findByAdress(String adress){

        List<UserRoute> userRoutes = findAll();
        List<Double> searchAdress = mapsClient.getCoords(adress);

        List<UserRoute> response = new ArrayList<>();

        for (UserRoute user : userRoutes) {

            List<Double> start = mapsClient.getCoords(user.getStartPoint());
            List<Double> end = mapsClient.getCoords(user.getEndPoint());

            if (start.equals(searchAdress) || end.equals(searchAdress)) {
                response.add(user);
            }
        }

        if(response.isEmpty()){
            throw new NotFoundException("No routes saved at: " + adress);
        }

        return response;
    }

    public String updateRoute(Long id, String updateStart, String updateEnd){

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

}
