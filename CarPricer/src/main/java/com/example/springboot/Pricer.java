package com.example.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;
import com.google.gson.*;

@RestController
public class Pricer {

	@Autowired
	private Gson gson;


	@GetMapping("/priceFor/{id}")
	public JsonObject price(@PathVariable("id") String carId) throws MalformedURLException, URISyntaxException {

		URL url = new URI("http://127.0.0.1:3000/allVehicles").toURL();

		try {
			try (InputStream input = url.openStream()) {
				InputStreamReader isr = new InputStreamReader(input);
				JsonArray arr = gson.fromJson(isr, JsonArray.class);


				URL url2 = new URI("http://127.0.0.1:8000/price").toURL();

				try(InputStream input2 = url2.openStream()){
					InputStreamReader isr2 = new InputStreamReader(input2);
					JsonArray arr2 = gson.fromJson(isr2, JsonArray.class);


					for (Object o : arr) {

						JsonObject car = (JsonObject) o;

						JsonElement id = car.get("id");
						if(Objects.equals(gson.fromJson(carId, JsonElement.class), id)) {

							for(Object a : arr2){
								JsonObject pricer = (JsonObject) a;

								JsonElement id2 = pricer.get("id");

								if(Objects.equals(id, id2)){
									JsonElement price = pricer.get("price");

									JsonElement currency = pricer.get("currency");

									car.add("price",price);
									car.add("currency",currency);
								}
							}
							return car;
						}
					}
				}
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		return new JsonObject();
	}

}