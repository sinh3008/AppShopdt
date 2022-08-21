/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Category;
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
public class CategoryDAO {

    public List<Category> getAll() {
        List<Category> list = new ArrayList<>();
        ResultSet rs = SqlConnection.executeQuery("select * from category");
        try {
            while (rs.next()) {
                Category c = new Category();
                c.setId(rs.getInt("id"));
                c.setNamecategory(rs.getString("namecategory"));
                c.setStatus(rs.getBoolean("status"));
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Category getById(int id) {
        ResultSet rs = SqlConnection.executeQuery("select * from category where id = ?", id);
        try {
            while (rs.next()) {
                Category c = new Category();
                c.setId(rs.getInt("id"));
                c.setNamecategory(rs.getString("namecategory"));
                c.setStatus(rs.getBoolean("status"));

                return c;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int add(Category c) {
        SqlConnection.executeUpdate("insert into category values(?,?,?)", c.getId(), c.getNamecategory(), c.isStatus());
        return 0;
    }

    public int update(Category c) {
        SqlConnection.executeUpdate("update category set namecategory = ?, status = ? where id = ?", c.getNamecategory(), c.isStatus(), c.getId());
        return 0;
    }

    public int delete(int id) {
        SqlConnection.executeUpdate("delete from category where id = ?", id);
        return 0;
    }
}
