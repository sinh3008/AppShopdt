/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author Acer
 */
public class Account implements Serializable{
    private int idacc;
    private String username;
    private String password;


    public Account() {
    }

    public Account(int idacc, String username, String password) {
        this.idacc = idacc;
        this.username = username;
        this.password = password;

    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }
    

    public int getIdacc() {
        return idacc;
    }

    public void setIdacc(int idacc) {
        this.idacc = idacc;
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

    
}
