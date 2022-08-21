/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Product;
import java.sql.Connection;
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
public class ProductDAO {

    Connection conn = null;

    public ProductDAO() {
        conn = SqlConnection.getConnect();

    }

    public List<Product> getAll() {
        List<Product> list = new ArrayList<>();
        ResultSet rs = SqlConnection.executeQuery("SELECT p.*,c.namecategory FROM product p JOIN category c on c.id = p.idcategory");
        try {
            while (rs.next()) {
                Product p = new Product();
                p.setIdpro(rs.getInt("idpro"));
                p.setNameproduct(rs.getString("nameproduct"));
                p.setIdcategory(rs.getInt("idcategory"));
                p.setPrice(rs.getFloat("price"));
                p.setQuantity(rs.getInt("quantity"));
                p.setSizecreen(rs.getString("sizecreen"));
                p.setScreentechnology(rs.getString("screentechnology"));
                p.setRearcamera(rs.getString("rearcamera"));
                p.setFrontcamera(rs.getString("frontcamera"));
                p.setChipset(rs.getString("chipset"));
                p.setSim(rs.getString("sim"));
                p.setOs(rs.getString("os"));
                p.setImagelist(rs.getString("imagelist"));
                p.setOtherparameters(rs.getString("otherparameters"));
                p.setStatus(rs.getBoolean("status"));
                p.setNamecategory(rs.getString("namecategory"));

                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
    public List<Product> getById(int id) {
        List<Product> list = new ArrayList<>();
        ResultSet rs = SqlConnection.executeQuery("SELECT p.*,c.namecategory FROM product p JOIN category c on c.id = p.idcategory where idpro = ?", id);
        try {
            while (rs.next()) {
                Product p = new Product();
                p.setIdpro(rs.getInt("idpro"));
                p.setNameproduct(rs.getString("nameproduct"));
                p.setIdcategory(rs.getInt("idcategory"));
                p.setPrice(rs.getFloat("price"));
                p.setQuantity(rs.getInt("quantity"));
                p.setSizecreen(rs.getString("sizecreen"));
                p.setScreentechnology(rs.getString("screentechnology"));
                p.setRearcamera(rs.getString("rearcamera"));
                p.setFrontcamera(rs.getString("frontcamera"));
                p.setChipset(rs.getString("chipset"));
                p.setSim(rs.getString("sim"));
                p.setOs(rs.getString("os"));
                p.setImagelist(rs.getString("imagelist"));
                p.setOtherparameters(rs.getString("otherparameters"));
                p.setStatus(rs.getBoolean("status"));
                p.setNamecategory(rs.getString("namecategory"));

                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    
    //đặc biệt
    public List<Product> search(String nameproduct) {
        List<Product> result = new ArrayList<>();
        try {
            java.sql.PreparedStatement pst = conn.prepareStatement("SELECT p.*,c.namecategory FROM product p JOIN category c on c.id = p.idcategory WHERE nameproduct like ?");
            pst.setString(1, "%" + nameproduct + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getInt("idpro"),
                        rs.getInt("idcategory"),
                        rs.getInt("quantity"),
                        rs.getString("nameproduct"),
                        rs.getString("sizecreen"),
                        rs.getString("screentechnology"),
                        rs.getString("rearcamera"),
                        rs.getString("frontcamera"),
                        rs.getString("chipset"),
                        rs.getString("sim"),
                        rs.getString("os"),
                        rs.getString("imagelist"),
                        rs.getString("otherparameters"),
                        rs.getString("namecategory"),
                        rs.getFloat("price"),
                        rs.getBoolean("status")
                );
                result.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int add(Product p) {
        SqlConnection.executeUpdate("insert into product values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                p.getIdpro(), p.getNamecategory(), p.getIdcategory(), p.getPrice(), p.getQuantity(), p.getSizecreen(), p.getScreentechnology(),
                p.getRearcamera(), p.getFrontcamera(), p.getChipset(), p.getSim(), p.getOs(), p.getImagelist(), p.getOtherparameters(), p.isStatus());
        return 0;
    }

    public int update(Product p) {
        SqlConnection.executeUpdate("update product set nameproduct = ?, idcategory = ?,price = ?,quantity = ?,"
                + "sizecreen = ?,screentechnology = ?,rearcamera = ?,frontcamera = ?,chipset = ?,"
                + "sim=?,imagelist = ?,otherparameters = ?,status=? where idpro = ?",
                p.getIdpro(), p.getNamecategory(), p.getIdcategory(), p.getPrice(), p.getQuantity(), p.getSizecreen(), p.getScreentechnology(),
                p.getRearcamera(), p.getFrontcamera(), p.getChipset(), p.getSim(), p.getOs(), p.getImagelist(), p.getOtherparameters(), p.isStatus());
        return 0;
    }

    public int delete(int id) {
        SqlConnection.executeUpdate("delete from product where idpro = ?", id);
        return 0;
    }
}
