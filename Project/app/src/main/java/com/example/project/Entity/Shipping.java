package com.example.project.Entity;

import java.io.Serializable;

public class Shipping implements Serializable {
    private int id;
    private String nameshipping;
    private float price;
    private boolean status;

    public Shipping() {
    }

    public Shipping(int id, String nameshipping, float price, boolean status) {
        this.id = id;
        this.nameshipping = nameshipping;
        this.price = price;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameshipping() {
        return nameshipping;
    }

    public void setNameshipping(String nameshipping) {
        this.nameshipping = nameshipping;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Shipping{" +
                "id=" + id +
                ", nameshipping='" + nameshipping + '\'' +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}
