/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ulti;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author This PC
 */
public class SqlConnection {

    public static Connection getConnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/qlshopdienthoai?useUnicode=true&characterEncoding=utf8", "root", "");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ResultSet executeQuery(String sql, Object... params) {
        Connection conn = getConnect();
        try {
            CallableStatement cs = conn.prepareCall(sql);
            if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    cs.setObject(i + 1, params[i]);
                }
            }
            return cs.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(SqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static int executeUpdate(String sql, Object... params) {
        Connection conn = getConnect();
        try {
            CallableStatement cs = conn.prepareCall(sql);
            if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    cs.setObject(i + 1, params[i]);
                }
            }
            return cs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
