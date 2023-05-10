package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
                .max(Comparator.comparingInt(Vehicle::getEnginePower));
        System.out.println("Najmocniejsze auto: " + mostPowerfulVehicle.get().getName());

        Map<String, List<Vehicle>> vehiclesByColor = vehicles.stream()
                .collect(Collectors.groupingBy(Vehicle::getColor));
        vehiclesByColor.forEach((color, list) -> System.out.println(color + ": " + list));

        List<Vehicle> sortedVehicles = vehicles.stream()
                .sorted(Comparator.comparingInt(Vehicle::getYearOfProduction).reversed())
                .collect(Collectors.toList());
        sortedVehicles.forEach(System.out::println);

        vehicles.forEach(vehicle -> System.out.println(vehicle.getName() + " - " + vehicle.getPrice()));
    }

    private static String getJSONFromAPI() throws Exception {
        URL url = new URL(API_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("Failed to retrieve data from API. Response code: " + responseCode);
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

class Vehicle {
    private String name;
    private String color;
    private int yearOfProduction;
    private int enginePower;
    private double price;

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public int getEnginePower() {
        return enginePower;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", yearOfProduction=" + yearOfProduction +
                ", enginePower=" + enginePower +
                ", price=" + price +
                '}';
    }
}
