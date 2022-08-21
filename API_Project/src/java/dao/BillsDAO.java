/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Billdetails;
import entity.Bills;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
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
public class BillsDAO {

    Connection conn = null;

    public BillsDAO() {
        conn = SqlConnection.getConnect();
    }

    public List<Bills> getAll() {
        List<Bills> list = new ArrayList<>();
        ResultSet rs = SqlConnection.executeQuery("SELECT * FROM bills");
        try {
            while (rs.next()) {
                Bills b = new Bills();
                b.setId(rs.getInt("id"));
                b.setIdcustomer(rs.getInt("idcustomer"));
                b.setQuantity(rs.getInt("quantity"));
                b.setTotalprice(rs.getFloat("totalprice"));
                b.setIdpayment(rs.getInt("idpayment"));;
                b.setIdshipping(rs.getInt("idshipping"));
                b.setStatus(rs.getBoolean("status"));
                b.setCreateddate(rs.getDate("createddate"));
                list.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BillsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Bills> getById(int idcustomer) {
        List<Bills> list = new ArrayList<>();
        ResultSet rs = SqlConnection.executeQuery("SELECT * FROM bills  WHERE idcustomer = ?", idcustomer);
        try {
            while (rs.next()) {
                Bills b = new Bills();
                b.setId(rs.getInt("id"));
                b.setIdcustomer(rs.getInt("idcustomer"));
                b.setQuantity(rs.getInt("quantity"));
                b.setTotalprice(rs.getFloat("totalprice"));
                b.setIdpayment(rs.getInt("idpayment"));;
                b.setIdshipping(rs.getInt("idshipping"));
                b.setStatus(rs.getBoolean("status"));
                b.setCreateddate(rs.getDate("createddate"));
                list.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BillsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int add(Bills b) {
        try {
            PreparedStatement pst = conn.prepareStatement("INSERT INTO bills VALUES (?,?,?,?,?,?,?,?)");
            pst.setInt(1, b.getId());
            pst.setInt(2, b.getIdcustomer());
            pst.setInt(3, b.getQuantity());
            pst.setFloat(4, b.getTotalprice());
            pst.setInt(5, b.getIdpayment());
            pst.setInt(6, b.getIdshipping());
            pst.setBoolean(7, b.isStatus());
            pst.setDate(8, (Date) b.getCreateddate());
            return pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BillsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

}
