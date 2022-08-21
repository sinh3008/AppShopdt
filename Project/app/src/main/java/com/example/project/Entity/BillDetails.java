package com.example.project.Entity;

import java.io.Serializable;

public class BillDetails implements Serializable {
    private int id, idbill,idproduct,quantity;
    private float price;
    private boolean status;
    private String nameproduct;

    public BillDetails() {
    }

    public BillDetails(int id, int idbill, int idproduct, int quantity, float price, boolean status, String nameproduct) {
        this.id = id;
        this.idbill = idbill;
        this.idproduct = idproduct;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
        this.nameproduct = nameproduct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdbill() {
        return idbill;
    }

    public void setIdbill(int idbill) {
        this.idbill = idbill;
    }

    public int getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(int idproduct) {
        this.idproduct = idproduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public String getNameproduct() {
        return nameproduct;
    }

    public void setNameproduct(String nameproduct) {
        this.nameproduct = nameproduct;
    }

}