/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import dao.DaoFactory;
import dao.PatientDao;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Patient;

/**
 *
 * @author Залізний Мозок
 */
public class OperAndProcCommand implements Command{

    /*
    redirect to operAndProc.jsp when press  <button>add procedures/operation</button> on doctorStartPage.html
    */
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        DaoFactory factory = DaoFactory.getDaoFactory();
        PatientDao pdao = factory.getPatientDao();
        List <Patient> patients = pdao.getAllPatients();
        request.setAttribute("patients", patients);
        return "/views/operAndProc.jsp";
    }
    
}
