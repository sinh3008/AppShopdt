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
public class Category implements Serializable{
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
        return this.namecategory; //To change body of generated methods, choose Tools | Templates.
    }
    
}
