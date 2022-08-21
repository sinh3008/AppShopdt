/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.user;
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
public class UserDAO {

    public List<user> getAll() {
        List<user> list = new ArrayList<>();
        ResultSet rs = SqlConnection.executeQuery("select * from user");
        try {
            while (rs.next()) {
                user c = new user();
                c.setUsername(rs.getString("username"));
                c.setPassword(rs.getString("password"));
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public user getById(String username) {
        ResultSet rs = SqlConnection.executeQuery("select * from user where username = ?", username);
        try {
            while (rs.next()) {
                user c = new user();

                c.setUsername(rs.getString("username"));
                c.setPassword(rs.getString("password"));

                return c;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int add(user c) {
        SqlConnection.executeUpdate("insert into user values(?,?)", c.getUsername(), c.getPassword());
        return 0;
    }

    public int update(user c) {
        SqlConnection.executeUpdate("update account set username = ?, password = ? username = ?", c.getUsername(), c.getPassword(), c.getUsername());
        return 0;
    }

    public int delete(String username) {
        SqlConnection.executeUpdate("delete from account where username = ?", username);
        return 0;
    }

    public user login(String username, String password) {
        user acc = null;
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = SqlConnection.getConnect();
            String sql = "select * from account where username = ? and password = ? ";
            statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                acc = new user(
                        resultSet.getString("username"),
                        resultSet.getString("password")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return acc;

    }

    
}
