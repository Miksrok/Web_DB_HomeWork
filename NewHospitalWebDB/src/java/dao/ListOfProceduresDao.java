
package dao;

import static dao.PatientDao.insertPatient;
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
import model.ListOfProcedures;
import model.Operation;


public class ListOfProceduresDao {

    DataSource ds;
    final static String insertProc = "insert into hospital.proc_list (pacient_number, proc) values (?, ?)";
    final static String TRUE_PROC = "select * from hospital.proc_list where pacient_number=? and proc_status=true";
    final static String FALSE_PROC = "select * from hospital.proc_list where pacient_number=? and proc_status=false";
    final static String FINISH_IT = "UPDATE hospital.proc_list SET proc_status=false WHERE id = ?";

    public ListOfProceduresDao(DataSource ds) {
        this.ds = ds;
    }

    public boolean insert(ListOfProcedures pa) {
        Connection con = null;
        try {
            int patientNumber = pa.getPatientNumber();
            String proc = pa.getProcedure();

            con = ds.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(insertProc);
            ps.setInt(1, patientNumber);
            ps.setString(2, proc);
            ps.execute();
        } catch (SQLException ex) {
            try {
                con.rollback();
                return false;
            } catch (SQLException ex1) {
                Logger.getLogger(ListOfProceduresDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            if (con != null) {
                try {
                    con.commit();
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ListOfProceduresDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return true;
    }

    public List<ListOfProcedures> trueProc(int number) {
        Connection con = null;
        List<ListOfProcedures> trueProc = new ArrayList<>();
        try {
            con = ds.getConnection();
            PreparedStatement st = con.prepareStatement(TRUE_PROC);
            st.setInt(1, number);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ListOfProcedures lop = new ListOfProcedures();
                lop.setId(rs.getInt("id"));
                lop.setPatientNumber(rs.getInt("pacient_number"));
                lop.setProcedure(rs.getString("proc"));
                lop.setProcedureStatus(rs.getBoolean("proc_status"));
                trueProc.add(lop);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListOfProceduresDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ListOfProceduresDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return trueProc;
    }

    public List<ListOfProcedures> falseProc(int number) {
        Connection con = null;
        List<ListOfProcedures> falseProc = new ArrayList<>();
        try {
            con = ds.getConnection();
            PreparedStatement st = con.prepareStatement(FALSE_PROC);
            st.setInt(1, number);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ListOfProcedures lop = new ListOfProcedures();
                lop.setId(rs.getInt("id"));
                lop.setPatientNumber(rs.getInt("pacient_number"));
                lop.setProcedure(rs.getString("proc"));
                lop.setProcedureStatus(rs.getBoolean("proc_status"));
                falseProc.add(lop);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListOfProceduresDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ListOfProceduresDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return falseProc;
    }

    public void finishIt(int number) {

        Connection con = null;
        try {
            con = ds.getConnection();
            PreparedStatement stm = con.prepareStatement(FINISH_IT);
            stm.setInt(1, number);
            stm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ListOfProceduresDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(con!=null){
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ListOfProceduresDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
