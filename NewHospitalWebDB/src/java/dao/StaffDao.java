/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.DepartmentDao.allDepartmentsSql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import model.Authorization;
import model.Department;
import model.Staff;

/**
 *
 * @author Залізний Мозок
 */
public class StaffDao {

    DataSource ds;
    final static String userInsertPersonalInfoSql = "insert into hospital.staff(name,specialization,department) values(?,?,?)";
    final static String readById = "SELECT * FROM hospital.staff WHERE id = ?;";
    final static String allDoctorsSql="select * from hospital.staff where specialization like 'doctor'";

    public StaffDao(DataSource ds) {
        this.ds = ds;
    }

    public Staff readById(int key) {
        Connection con = null;
        try {
            con = ds.getConnection();
            PreparedStatement stm = con.prepareStatement(readById);
            stm.setInt(1, key);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Staff staff = new Staff();
                staff.setId(rs.getInt("id"));
                staff.setName(rs.getString("name"));
                staff.setSpecialization(rs.getString("specialization"));
                staff.setDepartment(rs.getInt("department"));
                return staff;
            }

        } catch (SQLException ex) {
            Logger.getLogger(AuthorizationDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StaffDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    public boolean insert(Staff s) {
        Connection con = null;

        try {
            String name = s.getName();
            String specialization = s.getSpecialization();
            int department = s.getDepartment();
            con = ds.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(userInsertPersonalInfoSql);
            ps.setString(1, name);
            ps.setString(2, specialization);
            ps.setInt(3, department);
            ps.execute();
        } catch (SQLException ex) {
            try {
                con.rollback();
                return false;
            } catch (SQLException ex1) {
                Logger.getLogger(StaffDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            if (con != null) {
                try {
                    con.commit();
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StaffDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return true;

    }
    public List<Staff> getAllDoctors(){ 
        Connection con=null;
        List<Staff> staff=new ArrayList<>();
        try {
           
            con=ds.getConnection();
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(allDoctorsSql);
            while(rs.next()){
                Staff u =new Staff();
                u.setId(rs.getInt("id"));               
                u.setName(rs.getString("name"));
                u.setSpecialization(rs.getString("specialization"));
                u.setDepartment(rs.getInt("department"));
                staff.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(con!=null){
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StaffDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return staff;
    } 
}
