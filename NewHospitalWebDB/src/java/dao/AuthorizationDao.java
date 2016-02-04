
package dao;

import static dao.StaffDao.userInsertPersonalInfoSql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import model.Authorization;
import model.Staff;


public class AuthorizationDao {

    DataSource ds;
    final static String userSql = "select * from hospital.authorization where login=? and password=?";
    final static String addRegInfo = "insert into hospital.authorization (login, password, id_staff) values (?, ?, ?)";
    final static String maxId = "select max(id) from hospital.staff;";
    final static String readByLogin = "SELECT * FROM hospital.authorization WHERE login = ?;";

    public AuthorizationDao(DataSource ds) {
        this.ds = ds;
    }

    public boolean isAuthorization(Authorization u) {
        Connection con = null;
        try {
            String name = u.getLogin();
            String password = u.getPassword();
            con = ds.getConnection();
            PreparedStatement ps = con.prepareStatement(userSql);
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            System.out.println("warning!!!");

        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    //Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return false;

    }

    public Authorization readByLogin(String key) {
        
        Connection con = null;
        
        try {
            con = ds.getConnection();
            PreparedStatement stm = con.prepareStatement(readByLogin);
            stm.setString(1, key);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Authorization au = new Authorization();
                au.setLogin(rs.getString("login"));
                au.setPassword(rs.getString("password"));
                au.setStaff(rs.getInt("id_staff"));
                return au;
            }

        } catch (SQLException ex) {
            Logger.getLogger(AuthorizationDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AuthorizationDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    public boolean insert(Authorization au) {
        Connection con = null;

        try {
            String name = au.getLogin();
            String specialization = au.getPassword();
            //int staffId = au.getStaff();
            con = ds.getConnection();
            PreparedStatement p = con.prepareStatement(maxId);
            ResultSet rs = p.executeQuery();
            rs.next();
            au.setStaff(rs.getInt("max(id)"));
            int staffId = au.getStaff();
            p.close();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(addRegInfo);
            ps.setString(1, name);
            ps.setString(2, specialization);
            ps.setInt(3, staffId);
            ps.execute();
        } catch (SQLException ex) {
            try {
                con.rollback();
                return false;

            } catch (SQLException ex1) {
                Logger.getLogger(AuthorizationDao.class
                        .getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            if (con != null) {
                try {
                    con.commit();
                    con.close();

                } catch (SQLException ex) {
                    Logger.getLogger(AuthorizationDao.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return true;

    }
}
