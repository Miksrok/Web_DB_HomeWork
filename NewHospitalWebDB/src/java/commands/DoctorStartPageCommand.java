
package commands;

import dao.DaoFactory;
import dao.DepartmentDao;
import dao.StaffDao;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Department;
import model.Staff;

/**
 *
 * @author Залізний Мозок
 */
public class DoctorStartPageCommand implements Command {
    /*
    *   get all doctors from staff table and all departments
    *   redirect to newPatient.jsp 
    */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        DaoFactory factory = DaoFactory.getDaoFactory();
        StaffDao sdao = factory.getStaffDao();
        DepartmentDao ddao = factory.getDepartmentDao();
        List<Staff> doctors = sdao.getAllDoctors();
        List<Department> departments = ddao.getAllDepartments();
        request.setAttribute("doctors", doctors);
        request.setAttribute("departments", departments);
        return "/views/newPatient.jsp";
    }

}
