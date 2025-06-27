package com.portfolio.tripgas.external.cardto;

import com.portfolio.tripgas.exception.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@Controller
public class CarClient {

    public DataDTO getDetails(String model){
        RestTemplate restTemplate = new RestTemplate();

        CarResponse response =
                restTemplate.getForObject(
                        String.format("https://carapi.app/api/mileages/v2?model=%s&limit=1", model),
                        CarResponse.class);

        if (response.getData().isEmpty()){
            throw new NotFoundException("The car: '" + model + "' was not found");
        }

        return response.getData().get(0);
    }

}
