/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.AttributeValues;
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
public class AttributeValuesDAO {
    public List<AttributeValues> getAll() {
        List<AttributeValues> list = new ArrayList<>();
        ResultSet rs = SqlConnection.executeQuery("select abv.*,a.nameattribute from attributevalues abv join attribute a on a.id = abv.idattribute");
        try {
            while (rs.next()) {
                AttributeValues abv = new AttributeValues();
                abv.setIdabv(rs.getInt("idabv"));
                abv.setNameattributevalues(rs.getString("nameattributevalues"));
                abv.setExtraprice(rs.getFloat("extraprice"));
                abv.setIdattribute(rs.getInt("idattribute"));
                abv.setStatus(rs.getBoolean("status"));
                abv.setNameattribute(rs.getString("nameattribute"));
                list.add(abv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttributeValuesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public AttributeValues getById(int id) {
        ResultSet rs = SqlConnection.executeQuery("select abv.*,a.nameattribute from attributevalues abv join attribute a on a.id = abv.idattribute where idabv = ?", id);
        try {
            while (rs.next()) {
                AttributeValues abv = new AttributeValues();
                abv.setIdabv(rs.getInt("idabv"));
                abv.setNameattributevalues(rs.getString("nameattributevalues"));
                abv.setExtraprice(rs.getFloat("extraprice"));
                abv.setIdattribute(rs.getInt("idattribute"));
                abv.setStatus(rs.getBoolean("status"));
                abv.setNameattribute(rs.getString("nameattribute"));

                return abv;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttributeValuesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int add(AttributeValues abv) {
        SqlConnection.executeUpdate("insert into attributevalues values(?,?,?,?,?)", abv.getIdabv(), abv.getNameattributevalues(),abv.getExtraprice(),abv.getIdattribute(),abv.isStatus());
        return 0;
    }

    public int update(AttributeValues abv) {
        SqlConnection.executeUpdate("update attributevalues set nameattributevalues = ?,extraprice = ?, idattribute = ? ,status = ? where idabv = ?",abv.getNameattributevalues(),abv.getExtraprice(),abv.getIdattribute(), abv.isStatus(), abv.getIdabv());
        return 0;
    }

    public int delete(int id) {
        SqlConnection.executeUpdate("delete from attributevalues where idabv = ?", id);
        return 0;
    }
}
