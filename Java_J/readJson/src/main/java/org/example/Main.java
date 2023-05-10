package org.example;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {
    public static void main(String[] args) throws MalformedURLException{

        JSONParser parser = new JSONParser();

        URL url = new URL("http://127.0.0.1:3000/allVehicles");

        try (InputStream input = url.openStream()){
            InputStreamReader isr = new InputStreamReader(input);
            BufferedReader read = new BufferedReader(isr);
            JSONArray arr = (JSONArray) parser.parse(read);

            for (Object o : arr) {
                System.out.println();

                JSONObject car = (JSONObject) o;

                String id = (String) car.get("id");
                System.out.println(id);

                String model = (String) car.get("model");
                System.out.println(model);

                String modelYear = (String) car.get("modelYear");
                System.out.println(modelYear);

                String power = (String) car.get("power");
                System.out.println(power);

                String color = (String) car.get("color");
                System.out.println(color);

            }
        }  catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
