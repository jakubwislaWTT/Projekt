package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;

public class VehicleApp {
    private static final String API_URL = "http://localhost:3000/allVehicles";
    private static final String API_PRICES_URL = "http://localhost:8000/price";

    public static void main(String[] args) throws Exception {
        String json = getJSONFromAPI(API_URL);
        List<Vehicle> vehicles = new Gson().fromJson(json, new TypeToken<List<Vehicle>>() {}.getType());

        assignPricesToVehicles(vehicles);

        Optional<Vehicle> mostPowerfulVehicle = vehicles.stream()
                .max(Comparator.comparingInt(Vehicle::getPower));
        System.out.println("Najmocniejsze auto: " + mostPowerfulVehicle.orElse(new Vehicle()).getModel() + "\n");

        Map<String, List<Vehicle>> vehiclesByColor = vehicles.stream()
                .collect(Collectors.groupingBy(Vehicle::getColor));
        vehiclesByColor.forEach((color, list) -> System.out.println(color + ": " + list));

        System.out.print("\n");

        List<Vehicle> sortedVehicles = vehicles.stream()
                .sorted(Comparator.comparingInt(Vehicle::getYearOfProduction).reversed())
                .toList();
        sortedVehicles.forEach(System.out::println);

        System.out.print("\n");

        vehicles.forEach(vehicle -> System.out.println(vehicle.getModel() + " - " + String.format("%.2f" , vehicle.getPrice())  + " " + vehicle.getCurrency()));
    }

    private static void assignPricesToVehicles(List<Vehicle> vehicles) throws Exception {
        String json = getJSONFromAPI(API_PRICES_URL);
        List<Price> prices = new Gson().fromJson(json, new TypeToken<List<Price>>() {}.getType());

        for (Vehicle vehicle : vehicles) {
            Optional<Price> matchingPrice = prices.stream()
                    .filter(price -> price.getId() != null && price.getId().equals(vehicle.getId()))
                    .findFirst();

            if (matchingPrice.isPresent()) {
                vehicle.setPrice(matchingPrice.get().getPrice());
                vehicle.setCurrency(matchingPrice.get().getCurrency());
            }
        }
    }

    private static String getJSONFromAPI(String apiUrl) throws Exception {
        URI uri = new URI(apiUrl);
        HttpURLConnection con = (HttpURLConnection) uri.toURL().openConnection();
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("Błąd: " + responseCode);
        }

        StringBuilder content = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
        } catch (IOException e) {
            throw new RuntimeException("Błąd odczytu danych z API", e);
        } finally {
            con.disconnect();
        }
        return content.toString();
    }
}
