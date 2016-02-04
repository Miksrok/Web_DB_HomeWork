package commands;

import dao.AuthorizationDao;
import dao.DaoFactory;
import dao.DepartmentDao;
import dao.ListOfProceduresDao;
import dao.PatientDao;
import dao.StaffDao;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Authorization;
import model.Department;
import model.Patient;
import model.Staff;

public class LoginCommand implements Command {

    /*
    *   authorization
    *   if authorization successful create session
    *   if session attribute equals "admin" redirect to "adminUserInformation.jsp"
    *   if session attribute equals "doctor" redirect to "doctorStartPage.html"
    *   if session attribute equals "nurse" redirect to "nurseStart.jsp"
    */
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("name");
        String password = request.getParameter("password");
        Authorization au = new Authorization();
        au.setLogin(login);
        au.setPassword(password);
        DaoFactory factory = DaoFactory.getDaoFactory();
        AuthorizationDao udao = factory.getAuthorizationDao();
        StaffDao sdao = factory.getStaffDao();
        DepartmentDao ddao = factory.getDepartmentDao();
        PatientDao pdao = factory.getPatientDao();
        if (udao.isAuthorization(au)) {
            HttpSession session = request.getSession(true);
            Authorization forSession = udao.readByLogin(login);
            Staff forAccess = sdao.readById(forSession.getStaff());
            String fa = forAccess.getSpecialization();
            session.setAttribute("access", fa);
            if (session.getAttribute("access").equals("admin")) {
                List<Department> departments = ddao.getAllDepartments();
                request.setAttribute("departments", departments);
                return "/views/adminUserInformation.jsp";
            } else if (session.getAttribute("access").equals("doctor")) {
                return "/views/doctorStartPage.html";
            } else if (session.getAttribute("access").equals("nurse")) {
                List<Patient> patients = pdao.getAllPatients();
                request.setAttribute("patients", patients);
                return "/views/nurseStart.jsp";
            } else {
                return "/views/test.html";
            }
        } else {
            return "../index.html";
        }

    }
}
