package com.portfolio.tripgas;

import com.portfolio.tripgas.external.cardto.CarClient;
import com.portfolio.tripgas.external.mapsdto.MapsClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TripgasApplication {

	public static void main(String[] args) {
		SpringApplication.run(TripgasApplication.class, args);
//
//		CarClient cac = new CarClient();
//		System.out.println(cac.getDetails("F-150"));
//
//		MapsClient mac = new MapsClient();
//		System.out.println(mac.getGeocode("Vila Guilherme"));

	}

}
