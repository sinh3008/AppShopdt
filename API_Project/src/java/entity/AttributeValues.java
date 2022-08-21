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
public class AttributeValues implements Serializable{

    private int idabv;
    private String nameattributevalues;
    private float extraprice;
    private int idattribute;
    private boolean status;
    private String nameattribute;

    public AttributeValues() {
    }

    public AttributeValues(int idabv, String nameattributevalues, float extraprice, int idattribute, boolean status, String nameattribute) {
        this.idabv = idabv;
        this.nameattributevalues = nameattributevalues;
        this.extraprice = extraprice;
        this.idattribute = idattribute;
        this.status = status;
        this.nameattribute = nameattribute;
    }

    public int getIdabv() {
        return idabv;
    }

    public void setIdabv(int idabv) {
        this.idabv = idabv;
    }
    
    public String getNameattributevalues() {
        return nameattributevalues;
    }

    public void setNameattributevalues(String nameattributevalues) {
        this.nameattributevalues = nameattributevalues;
    }

    public float getExtraprice() {
        return extraprice;
    }

    public void setExtraprice(float extraprice) {
        this.extraprice = extraprice;
    }

    public int getIdattribute() {
        return idattribute;
    }

    public void setIdattribute(int idattribute) {
        this.idattribute = idattribute;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getNameattribute() {
        return nameattribute;
    }

    public void setNameattribute(String nameattribute) {
        this.nameattribute = nameattribute;
    }


}
