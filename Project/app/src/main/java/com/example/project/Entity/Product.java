package com.example.project.Entity;

import java.io.Serializable;

public class Product implements Serializable{
    private int idpro,idcategory,idcolor,idmemory,quantity;
    private String nameproduct,sizecreen,screentechnology,rearcamera,frontcamera,
            chipset,sim,os,imagelist,otherparameters,namecategory,namecolor,namememory;
    private float price;
    private boolean status;

    public Product() {
    }

    public Product(int idpro, int idcategory, int idcolor, int idmemory, int quantity, String nameproduct, String sizecreen, String screentechnology, String rearcamera, String frontcamera, String chipset, String sim, String os, String imagelist, String otherparameters, String namecategory, String namecolor, String namememory, float price, boolean status) {
        this.idpro = idpro;
        this.idcategory = idcategory;
        this.idcolor = idcolor;
        this.idmemory = idmemory;
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
        this.namecolor = namecolor;
        this.namememory = namememory;
        this.price = price;
        this.status = status;
    }
    public Product(int idpro, String nameproduct, float price, int quantity, String imagelist, String otherparameters) {
        this.idpro = idpro;
        this.quantity = quantity;
        this.nameproduct = nameproduct;
        this.imagelist = imagelist;
        this.price = price;
        this.otherparameters = otherparameters;
    }

    public Product(int idpro, String nameproduct, float price, String sizecreen, String screentechnology, String rearcamera, String frontcamera, String chipset, String sim, String os, String namecolor, String namememory, String imagelist, String otherparameters) {
        this.idpro = idpro;
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
        this.namecolor = namecolor;
        this.namememory = namememory;
        this.price = price;
    }

    public Product(int idpro, String nameproduct, float price, int quantity, String sizecreen, String screentechnology, String rearcamera, String frontcamera, String chipset, String sim, String os, String namecategory, String namecolor, String namememory, String imagelist, String otherparameters) {
        this.idpro = idpro;
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
        this.namecolor = namecolor;
        this.namememory = namememory;
        this.price = price;
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

    public int getIdcolor() {
        return idcolor;
    }

    public void setIdcolor(int idcolor) {
        this.idcolor = idcolor;
    }

    public int getIdmemory() {
        return idmemory;
    }

    public void setIdmemory(int idmemory) {
        this.idmemory = idmemory;
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

    public String getNamecolor() {
        return namecolor;
    }

    public void setNamecolor(String namecolor) {
        this.namecolor = namecolor;
    }

    public String getNamememory() {
        return namememory;
    }

    public void setNamememory(String namememory) {
        this.namememory = namememory;
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
        return "Product{" +
                "idpro=" + idpro +
                ", idcategory=" + idcategory +
                ", idcolor=" + idcolor +
                ", idmemory=" + idmemory +
                ", quantity=" + quantity +
                ", nameproduct='" + nameproduct + '\'' +
                ", sizecreen='" + sizecreen + '\'' +
                ", screentechnology='" + screentechnology + '\'' +
                ", rearcamera='" + rearcamera + '\'' +
                ", frontcamera='" + frontcamera + '\'' +
                ", chipset='" + chipset + '\'' +
                ", sim='" + sim + '\'' +
                ", os='" + os + '\'' +
                ", imagelist='" + imagelist + '\'' +
                ", otherparameters='" + otherparameters + '\'' +
                ", namecategory='" + namecategory + '\'' +
                ", namecolor='" + namecolor + '\'' +
                ", namememory='" + namememory + '\'' +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}
