package com.example.project.Entity;

import java.io.Serializable;

public class Colors implements Serializable{

    private int idcol;
    private String namecolor;
    private float extraprice;
    private boolean status;

    public Colors() {
    }

    public Colors(int idcol, String namecolor, float extraprice, boolean status) {
        this.idcol = idcol;
        this.namecolor = namecolor;
        this.extraprice = extraprice;
        this.status = status;
    }

    public Colors(int idcol, String namecolor) {
        this.idcol = idcol;
        this.namecolor = namecolor;
    }

    public int getIdcol() {
        return idcol;
    }

    public void setIdcol(int idcol) {
        this.idcol = idcol;
    }

    public String getNamecolor() {
        return namecolor;
    }

    public void setNamecolor(String namecolor) {
        this.namecolor = namecolor;
    }

    public float getExtraprice() {
        return extraprice;
    }

    public void setExtraprice(float extraprice) {
        this.extraprice = extraprice;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.namecolor;
    }
}
