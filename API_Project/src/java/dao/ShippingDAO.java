/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Shipping;
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
public class ShippingDAO {
    
    public List<Shipping> getAll() {
        List<Shipping> list = new ArrayList<>();
        ResultSet rs = SqlConnection.executeQuery("select * from shipping");
        try {
            while (rs.next()) {
                Shipping s = new Shipping();
               s.setId(rs.getInt("id"));
               s.setNameshipping(rs.getString("nameshipping"));
               s.setPrice(rs.getFloat("price"));
               s.setStatus(rs.getBoolean("status"));
                list.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShippingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Shipping getById(int id) {
        ResultSet rs = SqlConnection.executeQuery("select * from shipping where id = ?", id);
        try {
            while (rs.next()) {
                Shipping s = new Shipping();
               s.setId(rs.getInt("id"));
               s.setNameshipping(rs.getString("nameshipping"));
               s.setPrice(rs.getFloat("price"));
               s.setStatus(rs.getBoolean("status"));

                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShippingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int add(Shipping s) {
        SqlConnection.executeUpdate("insert into shipping values(?,?,?,?)",s.getId(),s.getNameshipping(),s.getPrice(),s.isStatus());
        return 0;
    }

    public int update(Shipping s) {
        SqlConnection.executeUpdate("update shipping set nameshipping = ?,price = ?, status = ? where id = ?",s.getNameshipping(),s.getPrice(),s.isStatus(),s.getId());
        return 0;
    }

    public int delete(int id) {
        SqlConnection.executeUpdate("delete from shipping where id = ?", id);
        return 0;
    }
}
