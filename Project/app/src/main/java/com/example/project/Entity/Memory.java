package com.example.project.Entity;

import java.io.Serializable;

public class Memory implements Serializable {

    private int idmem;
    private String namememory;
    private float extraprice;
    private boolean status;

    public Memory() {
    }

    public Memory(int idmem, String namememory, float extraprice, boolean status) {
        this.idmem = idmem;
        this.namememory = namememory;
        this.extraprice = extraprice;
        this.status = status;
    }

    public Memory(int idmem, String namememory) {
        this.idmem = idmem;
        this.namememory = namememory;
    }

    public int getIdmem() {
        return idmem;
    }

    public void setIdmem(int idmem) {
        this.idmem = idmem;
    }

    public String getNamememory() {
        return namememory;
    }

    public void setNamememory(String namememory) {
        this.namememory = namememory;
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
        return this.namememory;
    }
}
