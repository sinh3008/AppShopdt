/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author This PC
 */
public class Product implements Serializable{
    private int idpro,idcategory,quantity;
    private String nameproduct,sizecreen,screentechnology,rearcamera,frontcamera,chipset,sim,os,imagelist,otherparameters,namecategory;
    private float price;
    private boolean status;

    public Product() {
    }

    public Product(int idpro, int idcategory, int quantity, String nameproduct, String sizecreen, String screentechnology, String rearcamera, String frontcamera, String chipset, String sim, String os, String imagelist, String otherparameters, String namecategory, float price, boolean status) {
        this.idpro = idpro;
        this.idcategory = idcategory;
        this.quantity = quantity;
        this.nameproduct = nameproduct;
        this.sizecreen = sizecreen;
        this.screentechnology = screentechnology;
        this.rearcamera = rearcamera;
        this.frontcamera = frontcamera;
        this.chipset = chipset;
        this.sim = sim;
        this.os = os;
        this.imagelist = imagelist;
        this.otherparameters = otherparameters;
        this.namecategory = namecategory;
        this.price = price;
        this.status = status;
    }

    public int getIdpro() {
        return idpro;
    }

    public void setIdpro(int idpro) {
        this.idpro = idpro;
    }

    public int getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(int idcategory) {
        this.idcategory = idcategory;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getNameproduct() {
        return nameproduct;
    }

    public void setNameproduct(String nameproduct) {
        this.nameproduct = nameproduct;
    }

    public String getSizecreen() {
        return sizecreen;
    }

    public void setSizecreen(String sizecreen) {
        this.sizecreen = sizecreen;
    }

    public String getScreentechnology() {
        return screentechnology;
    }

    public void setScreentechnology(String screentechnology) {
        this.screentechnology = screentechnology;
    }

    public String getRearcamera() {
        return rearcamera;
    }

    public void setRearcamera(String rearcamera) {
        this.rearcamera = rearcamera;
    }

    public String getFrontcamera() {
        return frontcamera;
    }

    public void setFrontcamera(String frontcamera) {
        this.frontcamera = frontcamera;
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public String getSim() {
        return sim;
    }

    public void setSim(String sim) {
        this.sim = sim;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getImagelist() {
        return imagelist;
    }

    public void setImagelist(String imagelist) {
        this.imagelist = imagelist;
    }

    public String getOtherparameters() {
        return otherparameters;
    }

    public void setOtherparameters(String otherparameters) {
        this.otherparameters = otherparameters;
    }

    public String getNamecategory() {
        return namecategory;
    }

    public void setNamecategory(String namecategory) {
        this.namecategory = namecategory;
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

 
}
