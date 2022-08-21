/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Customer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ulti.SqlConnection;

/**
 *
 * @author Acer
 */
public class CustomerDAO {

    public List<Customer> getAll() {
        List<Customer> list = new ArrayList<>();
        ResultSet rs = SqlConnection.executeQuery("select * from customer");
        try {
            while (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getInt("id"));
                c.setNamecustomer(rs.getString("namecustomer"));
                c.setUsername(rs.getString("username"));
                c.setPassword(rs.getString("password"));
                c.setBirthday(rs.getDate("birthday"));
                c.setPhone(rs.getString("phone"));
                c.setEmail(rs.getString("email"));
                c.setAddress(rs.getString("address"));
                c.setGender(rs.getBoolean("gender"));
                c.setStatus(rs.getBoolean("status"));
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Customer getById(int id) {
        ResultSet rs = SqlConnection.executeQuery("select * from customer where id = ?", id);
        try {
            while (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getInt("id"));
                c.setNamecustomer(rs.getString("namecustomer"));
                c.setUsername(rs.getString("username"));
                c.setPassword(rs.getString("password"));
                c.setBirthday(rs.getDate("birthday"));
                c.setPhone(rs.getString("phone"));
                c.setEmail(rs.getString("email"));
                c.setAddress(rs.getString("address"));
                c.setGender(rs.getBoolean("gender"));
                c.setStatus(rs.getBoolean("status"));
                return c;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Customer> getByNameTT(String username) {
        List<Customer> list = new ArrayList<>();
        ResultSet rs = SqlConnection.executeQuery("select * from customer where username = ?", username);
        try {
            while (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getInt("id"));
                c.setNamecustomer(rs.getString("namecustomer"));
                c.setUsername(rs.getString("username"));
                c.setPassword(rs.getString("password"));
                c.setBirthday(rs.getDate("birthday"));
                c.setPhone(rs.getString("phone"));
                c.setEmail(rs.getString("email"));
                c.setAddress(rs.getString("address"));
                c.setGender(rs.getBoolean("gender"));
                c.setStatus(rs.getBoolean("status"));
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int update(Customer c) {
        SqlConnection.executeUpdate("update customer set namecustomer = ?,birthday =?,phone =?,email =?,address =?,gender =?,status =? where id = ?", c.getNamecustomer(), c.getBirthday(), c.getPhone(), c.getEmail(), c.getAddress(), c.isGender(), c.isStatus(), c.getId());
        return 0;
    }

    public int delete(int id) {
        SqlConnection.executeUpdate("delete from customer where id = ?", id);
        return 0;
    }

    public int add(Customer c) {
        SqlConnection.executeUpdate("insert into customer(username,password) values(?,?)", c.getUsername(), c.getPassword());
        return 0;
    }

//    public Customer login(String username, String password) {
//        ResultSet rs = SqlConnection.executeQuery("SELECT * FROM customer WHERE username= ? and password = ?", username, password);
//        try {
//            while (rs.next()) {
//                Customer c = new Customer();
//                c.setId(rs.getInt("id"));
//                c.setNamecustomer(rs.getString("namecustomer"));
//                c.setUsername(rs.getString("username"));
//                c.setPassword(rs.getString("password"));
//                c.setBirthday(rs.getDate("birthday"));
//                c.setPhone(rs.getString("phone"));
//                c.setEmail(rs.getString("email"));
//                c.setAddress(rs.getString("address"));
//                c.setGender(rs.getBoolean("gender"));
//                c.setStatus(rs.getBoolean("status"));
//                return c;
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("Loi");
//        }
//        return null;
//    }
    public String loginn(String username, String password) {
        String check = "false";
        ResultSet rs = SqlConnection.executeQuery("SELECT * FROM customer WHERE username= ? and password = ?", username, password);
        try {
            while (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getInt("id"));
                c.setNamecustomer(rs.getString("namecustomer"));
                c.setUsername(rs.getString("username"));
                c.setPassword(rs.getString("password"));
                c.setBirthday(rs.getDate("birthday"));
                c.setPhone(rs.getString("phone"));
                c.setEmail(rs.getString("email"));
                c.setAddress(rs.getString("address"));
                c.setGender(rs.getBoolean("gender"));
                c.setStatus(rs.getBoolean("status"));
                check = "true";
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Loi");
        }
        return check;
    }

    public String getByName(String username) {
        String check = "false";
        ResultSet rs = SqlConnection.executeQuery("SELECT * FROM customer WHERE username= ?", username);
        try {
            while (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getInt("id"));
                c.setNamecustomer(rs.getString("namecustomer"));
                c.setUsername(rs.getString("username"));
                c.setPassword(rs.getString("password"));
                c.setBirthday(rs.getDate("birthday"));
                c.setPhone(rs.getString("phone"));
                c.setEmail(rs.getString("email"));
                c.setAddress(rs.getString("address"));
                c.setGender(rs.getBoolean("gender"));
                c.setStatus(rs.getBoolean("status"));
                check = "true";
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Loi");
        }
        return check;
    }

}
