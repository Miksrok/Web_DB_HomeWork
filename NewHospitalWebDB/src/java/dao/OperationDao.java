
package dao;

import static dao.ListOfProceduresDao.FINISH_IT;
import static dao.ListOfProceduresDao.TRUE_PROC;
import static dao.PatientDao.insertPatient;
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
import model.Operation;


public class OperationDao {
    
    DataSource ds;
    final static String insertOperation = "insert into hospital.operations (pacient_number, operation) values (?, ?)";
    
    final static String TRUE_OPER = "select * from hospital.operations where pacient_number=? and operation_status=true";
    final static String FALSE_OPER = "select * from hospital.operations where pacient_number=? and operation_status=false";
    final static String FINISH_THIS_OPERATION = "UPDATE hospital.operations SET operation_status=false WHERE id = ?";
   
    public OperationDao(DataSource ds) {
        this.ds = ds;
    }

    public boolean insert(Operation pa) {
        Connection con = null;
        try {
            int patientNumber = pa.getPatientNumber();
            String operation = pa.getOperation();
            
            con = ds.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(insertOperation);
            ps.setInt(1, patientNumber);
            ps.setString(2, operation);           
            ps.execute();
        } catch (SQLException ex) {
             Logger.getLogger(OperationDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                con.rollback();
                return false;
            } catch (SQLException ex1) {
                Logger.getLogger(OperationDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            if (con != null) {
                try {
                    con.commit();
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OperationDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return true;
    }
    public List<Operation> getTrueOperations(int number){ 
        Connection con=null;
        List<Operation> operations=new ArrayList<>();
        try {
            con = ds.getConnection();
            PreparedStatement st = con.prepareStatement(TRUE_OPER);
            st.setInt(1, number);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Operation op =new Operation();
                op.setId(rs.getInt("id"));
                op.setPatientNumber(rs.getInt("pacient_number"));
                op.setOperation(rs.getString("operation"));
                op.setStatus(rs.getBoolean("operation_status"));
                operations.add(op);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OperationDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(con!=null){
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OperationDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return operations;
    } 
    public List<Operation> getFalseOperations(int number){ 
        Connection con=null;
        List<Operation> operations=new ArrayList<>();
        try {
            con = ds.getConnection();
            PreparedStatement st = con.prepareStatement(FALSE_OPER);
            st.setInt(1, number);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Operation op =new Operation();
                op.setId(rs.getInt("id"));
                op.setPatientNumber(rs.getInt("pacient_number"));
                op.setOperation(rs.getString("operation"));
                op.setStatus(rs.getBoolean("operation_status"));
                operations.add(op);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OperationDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(con!=null){
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OperationDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return operations;
    } 
     public void finishThisOperation(int number) {

        Connection con = null;
        try {
            con = ds.getConnection();
            PreparedStatement stm = con.prepareStatement(FINISH_THIS_OPERATION);
            stm.setInt(1, number);
            stm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(OperationDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(con!=null){
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OperationDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
