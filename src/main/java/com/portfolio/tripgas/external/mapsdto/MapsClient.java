package com.portfolio.tripgas.external.mapsdto;

import com.portfolio.tripgas.external.mapsdto.geocode.CoordinatesDTO;
import com.portfolio.tripgas.external.mapsdto.geocode.FeatureDTO;
import com.portfolio.tripgas.external.mapsdto.geocode.GeocodeResponse;
import com.portfolio.tripgas.external.mapsdto.route.RouteFeatureDTO;
import com.portfolio.tripgas.external.mapsdto.route.RouteResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@Controller
public class MapsClient {

    public FeatureDTO getGeocode(String adress){
        RestTemplate restTemplate = new RestTemplate();
        GeocodeResponse response = restTemplate
                .getForObject(String.format("https://api.openrouteservice.org/geocode/search?api_key=5b3ce3597851110001cf6248b27d1bfc471d4a588bc0afc3716fc088"
                        + "&text=%s", adress), GeocodeResponse.class);

        return response.getFeatures().get(0);
    }

    public RouteFeatureDTO getRoute(String route){
        RestTemplate restTemplate = new RestTemplate();

        CoordinatesDTO coordStart = getGeocode(route.substring(0, route.indexOf("-"))).getGeometry();
        CoordinatesDTO coordEnd = getGeocode(route.substring(route.indexOf("-") + 1, route.length())).getGeometry();

        String start = coordStart.getCoordinates().get(0).toString() + "," +  coordStart.getCoordinates().get(1).toString();
        String end = coordEnd.getCoordinates().get(0).toString() + "," + coordEnd.getCoordinates().get(1).toString();

        RouteResponse response = restTemplate
                .getForObject(String.format("https://api.openrouteservice.org/v2/directions/driving-car?api_key=5b3ce3597851110001cf6248b27d1bfc471d4a588bc0afc3716fc088"
                     + "&start=%s&end=%s", start, end), RouteResponse.class);

        return response.getFeatures().get(0);
    }

}
