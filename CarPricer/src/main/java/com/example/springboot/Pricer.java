package com.example.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URI;
import java.net.URI.*;
import java.util.Objects;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@RestController
public class Pricer {

	@GetMapping("/priceFor/{id}")
	// return JSON
	public JSONObject price(@PathVariable("id") String carId) throws URISyntaxException,  MalformedURLException{
		JSONParser parser = new JSONParser();

		// 1. create new endpoint to return JSON data with id, price and currency
		// 2. this endpoint should take Piotr's endpoint and the endpoint from point 1 and should merge the data for requested id
		URL url = new URI("http://127.0.0.1:3000/allVehicles").toURL();
		URL url2 = new URI("http://127.0.0.1:8000/price").toURL();

			try (InputStream input = url.openStream()) {
				InputStreamReader isr = new InputStreamReader(input);
				BufferedReader read = new BufferedReader(isr);
				JSONArray arr = (JSONArray) parser.parse(read);

				InputStream input2 = url2.openStream();
				InputStreamReader isr2 = new InputStreamReader(input2);
				BufferedReader read2 = new BufferedReader(isr2);
				JSONArray arr2 = (JSONArray) parser.parse(read2);

				for (Object o : arr) {

					JSONObject car = (JSONObject) o;

					String id = (String) car.get("id");

					for (Object p : arr2){
						JSONObject pr = (JSONObject) p;

						String id2 = (String) pr.get("id");

						long price = (long) pr.get("price");

						String currency = (String)pr.get("currency");

						if(Objects.equals(id, id2)){
							car.put("price", price);
							car.put("currency",currency);
						}

					}

					if(Objects.equals(carId, id)){
						return car;
					}

				}
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
		return new JSONObject();
	}

}