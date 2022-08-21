/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Attribute;
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
public class AttributeDAO {
    public List<Attribute> getAll() {
        List<Attribute> list = new ArrayList<>();
        ResultSet rs = SqlConnection.executeQuery("select * from attribute");
        try {
            while (rs.next()) {
                Attribute a = new Attribute();
                a.setId(rs.getInt("id"));
                a.setNameattribute(rs.getString("nameattribute"));
                list.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Attribute getById(int id) {
        ResultSet rs = SqlConnection.executeQuery("select * from attribute where id = ?", id);
        try {
            while (rs.next()) {
                Attribute a = new Attribute();
                a.setId(rs.getInt("id"));
                a.setNameattribute(rs.getString("nameattribute"));
                return a;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public int add(Attribute ab) {
        SqlConnection.executeUpdate("insert into attribute values(?,?)", ab.getId(), ab.getNameattribute());
        return 0;
    }

    public int update(Attribute ab) {
        SqlConnection.executeUpdate("update attribute set nameattribute = ? where id = ?",ab.getNameattribute(),ab.getId());
        return 0;
    }

    public int delete(int id) {
        SqlConnection.executeUpdate("delete from attribute where id = ?", id);
        return 0;
    }
}
