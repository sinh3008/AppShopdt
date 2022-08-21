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
public class Customer implements Serializable{
    private int id;
    private String namecustomer;
    private String username;
    private String password;
    private Date birthday;
    private String phone;
    private String email;
    private String address;
    private boolean gender;
    private boolean status;

    public Customer() {
    }

    public Customer(int id, String namecustomer, String username, String password, Date birthday, String phone, String email, String address, boolean gender, boolean status) {
        this.id = id;
        this.namecustomer = namecustomer;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.status = status;
    }

    public Customer(String username, String password) {
        this.username = username;
        this.password = password;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamecustomer() {
        return namecustomer;
    }

    public void setNamecustomer(String namecustomer) {
        this.namecustomer = namecustomer;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
