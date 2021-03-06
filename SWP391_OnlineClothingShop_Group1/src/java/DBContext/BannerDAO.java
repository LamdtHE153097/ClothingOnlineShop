/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBContext;

import entity.Banner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ChauBNMHE153019
 */
public class BannerDAO {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    private String query;
    public Banner getBanner(int id){
        Banner banner = null;
        try {
            query = "SELECT * FROM dbo.Banner WHERE ID = ?";
            conn = DBcontext.open();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                banner = new Banner(rs.getInt("ID"),rs.getString("Img"));
            }
        } catch (SQLException e) {
            Logger.getLogger(BannerDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        finally{
            DBcontext.close(conn, ps, rs);
        }
        return banner;
    }
    public ArrayList<Banner> getAllBanner() {
        ArrayList<Banner> list = new ArrayList<>();
        try {
            query = "SELECT * FROM dbo.Banner";
            conn = DBcontext.open();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Banner(rs.getInt("ID"),rs.getString("Img")));
            }
        } catch (SQLException e) {
            Logger.getLogger(BannerDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        finally{
            DBcontext.close(conn, ps, rs);
        }
        return list;
    }
    public void addBanner(Banner banner) {
        try {
            query = "INSERT INTO dbo.Banner VALUES ( ? )"   ;
            conn = DBcontext.open();
            ps = conn.prepareStatement(query);
            ps.setString(1, banner.getImg());
            ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(BannerDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        finally{
            DBcontext.close(conn, ps, rs);
        }
    }
    public void editBanner(Banner banner) {
        try {
            query = "UPDATE dbo.Banner SET Img = ? WHERE ID = ?";
            conn = DBcontext.open();
            ps = conn.prepareStatement(query);
            ps.setString(1, banner.getImg());
            ps.setInt(2, banner.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(BannerDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        finally{
            DBcontext.close(conn, ps, rs);
        }
    }
    public void deleteBanner(int id) {
        try {
            query = "DELETE FROM dbo.Banner WHERE ID = ?";
            conn = DBcontext.open();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(BannerDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        finally{
            DBcontext.close(conn, ps, rs);
        }
    }
}
