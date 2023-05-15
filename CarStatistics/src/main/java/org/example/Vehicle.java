package org.example;
class Vehicle {
    private String model;
    private String color;
    private int modelYear;
    private int power;
    private double price;

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public int getYearOfProduction() {
        return modelYear;
    }

    public int getPower() {
        return power;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "[" +
                "model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", yearOfProduction=" + modelYear +
                ", enginePower=" + power +
                ']';
    }
}