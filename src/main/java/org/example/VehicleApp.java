package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class VehicleApp {
    private static final String API_URL = "http://localhost:3000/allVehicles";

    public static void main(String[] args) throws Exception {

        String json = getJSONFromAPI();

        List<Vehicle> vehicles = new Gson().fromJson(json, new TypeToken<List<Vehicle>>() {}.getType());

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

        vehicles.forEach(vehicle -> System.out.println(vehicle.getModel() + " - " + vehicle.getPrice()));
    }

    private static String getJSONFromAPI() throws Exception{
        URI uri = new URI(API_URL);
        HttpURLConnection con = (HttpURLConnection) uri.toURL().openConnection();
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("Błąd: " + responseCode);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();

        return content.toString();
    }

}
