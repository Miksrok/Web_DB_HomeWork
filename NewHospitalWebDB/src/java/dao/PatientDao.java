/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.StaffDao.allDoctorsSql;
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
import model.Patient;
import model.Staff;

/**
 *
 * @author Залізний Мозок
 */
public class PatientDao {

    DataSource ds;
    final static String insertPatient = "insert into hospital.patient(name,diagnosis,department,primary_doctor) values(?,?,?,?)";
    final static String allPatients="select * from hospital.patient";
    public PatientDao(DataSource ds) {
        this.ds = ds;
    }

    public boolean insert(Patient pa) {
        Connection con = null;
        try {
            String name = pa.getName();
            String diagnosis = pa.getDiagnosis();
            int department = pa.getDepartment();
            int primaryDoctor = pa.getPrimaryDoctor();
            con = ds.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(insertPatient);
            ps.setString(1, name);
            ps.setString(2, diagnosis);
            ps.setInt(3, department);
            ps.setInt(4, primaryDoctor);
            ps.execute();
        } catch (SQLException ex) {
            try {
                con.rollback();
                return false;
            } catch (SQLException ex1) {
                Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            if (con != null) {
                try {
                    con.commit();
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return true;
    }

     public List<Patient> getAllPatients(){ 
        Connection con=null;
        List<Patient> patients=new ArrayList<>();
        try {
           
            con=ds.getConnection();
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(allPatients);
            while(rs.next()){
                Patient u =new Patient();
                u.setClinicalRecord(rs.getInt("clinical_record"));               
                u.setName(rs.getString("name"));
                patients.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(con!=null){
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return patients;
    } 
}
