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
public class Attribute implements Serializable{

    private int id;
    private String nameattribute;

    public Attribute() {
    }

    public Attribute(int id, String nameattribute) {
        this.id = id;
        this.nameattribute = nameattribute;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameattribute() {
        return nameattribute;
    }

    public void setNameattribute(String nameattribute) {
        this.nameattribute = nameattribute;
    }

}
