package dao;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DaoFactory {
   private static DaoFactory instance=new DaoFactory();
   DataSource ds;
   private DaoFactory(){
       try {
           InitialContext initContext= new InitialContext(); 
           ds = (DataSource) initContext.lookup("java:comp/env/jdbc/dbname");
       } catch (NamingException ex) {
           Logger.getLogger(DaoFactory.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   public static DaoFactory getDaoFactory(){
       return instance;
   }
   public AuthorizationDao getAuthorizationDao(){
       return new AuthorizationDao(ds);
   }
   public DepartmentDao getDepartmentDao(){
       return new DepartmentDao(ds);
   }
   public StaffDao getStaffDao(){
       return new StaffDao(ds);
   }
   public PatientDao getPatientDao(){
       return new PatientDao(ds);
   }
   public OperationDao getOperationDao(){
       return new OperationDao(ds);
   }
   public ListOfProceduresDao getListOfProceduresDao(){
       return new ListOfProceduresDao(ds);
   }
}
