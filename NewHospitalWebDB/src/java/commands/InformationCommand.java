/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import dao.DaoFactory;
import dao.StaffDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Staff;

/**
 *
 * @author Залізний Мозок
 */
public class InformationCommand implements Command {

    /*
    add information about doctor/nurse to DB and redirect to adminUserLogin.html
    */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String specialization = request.getParameter("specialization");
        String dep = request.getParameter("department");
        int department=Integer.valueOf(dep);
        Staff staff = new Staff();
        staff.setName(name);
        staff.setSpecialization(specialization);
        staff.setDepartment(department);
        DaoFactory factory=DaoFactory.getDaoFactory();
        StaffDao sdao=factory.getStaffDao();
        if (sdao.insert(staff)){
            return "/views/adminUserLogin.html";
        }else{
            return "../index.html";
        }
    }

}
