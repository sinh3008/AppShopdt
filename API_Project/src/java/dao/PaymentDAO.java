/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Payment;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ulti.SqlConnection;

/**
 *
 * @author This PC
 */
public class PaymentDAO {
    public List<Payment> getAll() {
        List<Payment> list = new ArrayList<>();
        ResultSet rs = SqlConnection.executeQuery("select * from payment");
        try {
            while (rs.next()) {
                Payment p = new Payment();
                p.setId(rs.getInt("id"));
                p.setNamepayment(rs.getString("namepayment"));
                p.setStatus(rs.getBoolean("status"));
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaymentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Payment getById(int id) {
        ResultSet rs = SqlConnection.executeQuery("select * from payment where id = ?", id);
        try {
            while (rs.next()) {
                Payment p = new Payment();
                p.setId(rs.getInt("id"));
                p.setNamepayment(rs.getString("namepayment"));
                p.setStatus(rs.getBoolean("status"));

                return p;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaymentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int add(Payment p) {
        SqlConnection.executeUpdate("insert into payment values(?,?,?)", p.getId(), p.getNamepayment(), p.isStatus());
        return 0;
    }

    public int update(Payment p) {
        SqlConnection.executeUpdate("update payment set namepayment = ?, status = ? where id = ?", p.getNamepayment(), p.isStatus(), p.getId());
        return 0;
    }

    public int delete(int id) {
        SqlConnection.executeUpdate("delete from payment where id = ?", id);
        return 0;
    }
}
