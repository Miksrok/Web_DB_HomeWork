/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import dao.DaoFactory;
import dao.OperationDao;
import dao.PatientDao;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Operation;
import model.Patient;

/**
 *
 * @author Залізний Мозок
 */
public class OperationCommand implements Command{
    /*
    * add operation, refresh operAndProc.jsp
    */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int patientNumber = Integer.valueOf(request.getParameter("patient_number"));
        String operation = request.getParameter("operation");
        Operation op = new Operation();
        op.setPatientNumber(patientNumber);
        op.setOperation(operation);
        DaoFactory factory=DaoFactory.getDaoFactory();
        OperationDao odao=factory.getOperationDao();
        PatientDao pdao = factory.getPatientDao();
        if (odao.insert(op)){
            List <Patient> patients = pdao.getAllPatients();
            request.setAttribute("patients", patients);
            return "/views/operAndProc.jsp";
        }else{
            return "/views/test.html";
        }
    }
    
}
