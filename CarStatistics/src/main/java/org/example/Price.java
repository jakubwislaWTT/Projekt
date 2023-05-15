package org.example;

public class Price {
    private String id;
    private String currency;
    private double price;

    public Price() {
    }

    public Price(String id, String currency, double price) {
        this.id = id;
        this.currency = currency;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
