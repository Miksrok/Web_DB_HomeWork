
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import model.Department;


public class DepartmentDao {
    DataSource ds;
    final static String allDepartmentsSql="select * from hospital.departments";
    public DepartmentDao(DataSource ds){
        this.ds=ds;
    }
    
    public List<Department> getAllDepartments(){ 
        Connection con=null;
        List<Department> departments=new ArrayList<>();
        try {
           
            con=ds.getConnection();
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(allDepartmentsSql);
            while(rs.next()){
                Department u =new Department();
                u.setNumber(rs.getInt("number"));               
                u.setName(rs.getString("name"));
                departments.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(con!=null){
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DepartmentDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return departments;
    } 
    
}
