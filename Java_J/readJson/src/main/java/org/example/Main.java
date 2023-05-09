package org.example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {
    public static void main(String[] args){

        JSONParser parser = new JSONParser();

        try {
            JSONArray arr = (JSONArray) parser.parse(new FileReader("cars.json"));

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
        }  catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
