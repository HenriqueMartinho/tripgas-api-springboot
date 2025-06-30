package com.portfolio.tripgas.external.mapsdto;

import com.portfolio.tripgas.exception.InvalidInputException;
import com.portfolio.tripgas.exception.NotFoundException;
import com.portfolio.tripgas.external.mapsdto.geocode.FeatureDTO;
import com.portfolio.tripgas.external.mapsdto.geocode.GeocodeResponse;
import com.portfolio.tripgas.external.mapsdto.route.RouteFeatureDTO;
import com.portfolio.tripgas.external.mapsdto.route.RouteResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.io.InvalidClassException;
import java.util.List;

@Controller
public class MapsClient {

    @Value("${keys.external.map-api}")
    private String apiKey;

    private RestTemplate restTemplate = new RestTemplate();

    public FeatureDTO getGeocode(String adress){

        GeocodeResponse response = restTemplate
                .getForObject(String.format("https://api.openrouteservice.org/geocode/search"
                        + "?api_key=" + apiKey
                        + "&text=" + adress), GeocodeResponse.class);

        if(response.getFeatures().isEmpty()){
            throw new InvalidInputException(("'" + adress + "' it's not a valid adress"));
        }

        return response.getFeatures().get(0);
    }

    public RouteFeatureDTO getRoute(String startPoint, String endPoint){
        List<Double> startCoord = getGeocode(startPoint).getGeometry().getCoordinates();
        List<Double> endCoord = getGeocode(endPoint).getGeometry().getCoordinates();

        String start = startCoord.get(0).toString()
                + "," +  startCoord.get(1).toString();
        String end = endCoord.get(0).toString()
                + "," +  endCoord.get(1).toString();

        RouteResponse response = restTemplate
                .getForObject(String.format("https://api.openrouteservice.org/v2/directions/driving-car"
                        + "?api_key=" + apiKey
                        + "&start=" + start
                        + "&end=" +  end), RouteResponse.class);

        return response.getFeatures().get(0);
    }

    public List<Double> getCoords (String adress){
        return getGeocode(adress).getGeometry().getCoordinates();
    }

}
