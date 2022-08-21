/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Billdetails;
import java.sql.Connection;
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
public class BilldetailsDAO {

    Connection conn = null;

    public BilldetailsDAO() {
        conn = SqlConnection.getConnect();
    }

    public List<Billdetails> getAll() {
        List<Billdetails> list = new ArrayList<>();
        ResultSet rs = SqlConnection.executeQuery("SELECT b.*,p.nameproduct,p.imagelist FROM billdetails b JOIN product p on p.idpro = b.idproduct");
        try {
            while (rs.next()) {
                Billdetails b = new Billdetails();
                b.setId(rs.getInt("id"));
                b.setIdbill(rs.getInt("idbill"));
                b.setIdproduct(rs.getInt("idproduct"));
                b.setPrice(rs.getInt("price"));
                b.setQuantity(rs.getInt("quantity"));;
                b.setStatus(rs.getBoolean("status"));
                b.setNameproduct(rs.getString("nameproduct"));
                list.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BilldetailsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int add(Billdetails b) {
        try {
            PreparedStatement pst = conn.prepareStatement("INSERT INTO billdetails VALUES (?,?,?,?,?,?)");
            pst.setInt(1, b.getId());
            pst.setInt(2, b.getIdbill());
            pst.setInt(3, b.getIdproduct());
            pst.setFloat(4, b.getPrice());
            pst.setInt(5, b.getQuantity());
            pst.setBoolean(6, b.isStatus());
            return pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BilldetailsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

}
