// Vehicle.java
package org.example;

class Vehicle {
    private String id;
    private String model;
    private String color;
    private int modelYear;
    private int power;
    private double price;
    private String currency;

    public String getId() {
        return id;
    }

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

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "[" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", yearOfProduction=" + modelYear +
                ", enginePower=" + power +
                ']';
    }
}
