package com.example.project.Entity;

import java.io.Serializable;

public class Category {
    private int id;
    private String namecategory;
    private boolean status;

    public Category() {
    }

    public Category(int id, String namecategory, boolean status) {
        this.id = id;
        this.namecategory = namecategory;
        this.status = status;
    }

    public Category(String namecategory, boolean status) {
        this.namecategory = namecategory;
        this.status = status;
    }


    public Category(int id, String namecategory) {
        this.id = id;
        this.namecategory = namecategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamecategory() {
        return namecategory;
    }

    public void setNamecategory(String namecategory) {
        this.namecategory = namecategory;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.namecategory;
    }
}
