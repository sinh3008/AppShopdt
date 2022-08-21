/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Account;
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
public class AccountDAO {

    public List<Account> getAll() {
        List<Account> list = new ArrayList<>();
        ResultSet rs = SqlConnection.executeQuery("select * from account");
        try {
            while (rs.next()) {
                Account c = new Account();
                c.setIdacc(rs.getInt("idacc"));
                c.setUsername(rs.getString("username"));
                c.setPassword(rs.getString("password"));
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Account getById(int idacc) {
        ResultSet rs = SqlConnection.executeQuery("select * from account where idacc = ?", idacc);
        try {
            while (rs.next()) {
                Account c = new Account();
                c.setIdacc(rs.getInt("idacc"));
                c.setUsername(rs.getString("username"));
                c.setPassword(rs.getString("password"));

                return c;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int add(Account c) {
        SqlConnection.executeUpdate("insert into account values(?,?,?)",c.getIdacc(), c.getUsername(), c.getPassword());
        return 0;
    }

    public int update(Account c) {
        SqlConnection.executeUpdate("update account set username = ?, password = ? where id = ?", c.getUsername(), c.getPassword(), c.getIdacc());
        return 0;
    }

    public int delete(int id) {
        SqlConnection.executeUpdate("delete from account where id = ?", id);
        return 0;
    }

    public Account login(String username, String password) {
        Account acc = null;
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
                acc = new Account(resultSet.getInt("idacc"),
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
