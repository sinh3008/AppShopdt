/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Acer
 */
public class Bills implements Serializable{
    private int id;
    private int idcustomer;
    private int quantity;
    private float totalprice;
    private int idpayment ;
    private int idshipping;
    private boolean status;
    private Date createddate;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Bills() {
    }

    public Bills(int id, int idcustomer, int quantity, float totalprice, int idpayment, int idshipping, boolean status, Date createddate) {
        this.id = id;
        this.idcustomer = idcustomer;
        this.quantity = quantity;
        this.totalprice = totalprice;
        this.idpayment = idpayment;
        this.idshipping = idshipping;
        this.status = status;
        this.createddate = createddate;
    }

   

    public Bills(int id, int idcustomer, int quantity, float totalprice) {
        this.id = id;
        this.idcustomer = idcustomer;
        this.quantity = quantity;
        this.totalprice = totalprice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdcustomer() {
        return idcustomer;
    }

    public void setIdcustomer(int idcustomer) {
        this.idcustomer = idcustomer;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(float totalprice) {
        this.totalprice = totalprice;
    }

    public int getIdpayment() {
        return idpayment;
    }

    public void setIdpayment(int idpayment) {
        this.idpayment = idpayment;
    }

    public int getIdshipping() {
        return idshipping;
    }

    public void setIdshipping(int idshipping) {
        this.idshipping = idshipping;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }
    
}
