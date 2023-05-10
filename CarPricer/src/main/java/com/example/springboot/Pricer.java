package com.example.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@RestController
public class Pricer {

	@GetMapping("/")
	public String index() {
		return "Hello World!";
	}

	@GetMapping("/getPrice/{id}")
	public String price(@PathVariable("id") String carId) throws MalformedURLException{
		JSONParser parser = new JSONParser();

		URL url = new URL("http://127.0.0.1:3000/allVehicles");

		try {
			try (InputStream input = url.openStream()) {
				InputStreamReader isr = new InputStreamReader(input);
				BufferedReader read = new BufferedReader(isr);
				JSONArray arr = (JSONArray) parser.parse(read);

				for (Object o : arr) {

					JSONObject car = (JSONObject) o;

					String id = (String) car.get("id");

					String model = (String) car.get("model");

					long modelYear = (long) car.get("modelYear");

					long power = (long) car.get("power");

					String color = (String) car.get("color");

					long price = (long) car.get("price");

					if(Objects.equals(carId, id)) {
						return "id : "+id+" price : "+price+" currency : PLN";
					}
				}
			}
		}  catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return "Nie znaleziono";
	}

}