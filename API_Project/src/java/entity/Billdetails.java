/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author Acer
 */
public class Billdetails implements Serializable {

    private int id;
    private int idbill;
    private int idproduct;
    private float price;
    private int quantity;
    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Billdetails(int id, int idbill, int idproduct, float price, int quantity, boolean status, String nameproduct) {
        this.id = id;
        this.idbill = idbill;
        this.idproduct = idproduct;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.nameproduct = nameproduct;
    }
    private String nameproduct;

    public String getNameproduct() {
        return nameproduct;
    }

    public void setNameproduct(String nameproduct) {
        this.nameproduct = nameproduct;
    }

    public Billdetails(int id, int idbill, int idproduct, float price, int quantity, Boolean status, String nameproduct) {
        this.id = id;
        this.idbill = idbill;
        this.idproduct = idproduct;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.nameproduct = nameproduct;
    }

    public Billdetails() {
    }

    public Billdetails(int id, int idbill, int idproduct, float price, int quantity, Boolean status) {
        this.id = id;
        this.idbill = idbill;
        this.idproduct = idproduct;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
