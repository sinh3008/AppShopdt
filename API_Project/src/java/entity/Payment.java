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
public class Payment implements Serializable{
    private int id;
    private String namepayment;
    private boolean status;

    public Payment() {
    }

    public Payment(int id, String namepayment, boolean status) {
        this.id = id;
        this.namepayment = namepayment;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamepayment() {
        return namepayment;
    }

    public void setNamepayment(String namepayment) {
        this.namepayment = namepayment;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
