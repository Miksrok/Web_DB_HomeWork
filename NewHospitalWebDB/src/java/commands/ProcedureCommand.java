
package commands;

import dao.DaoFactory;
import dao.ListOfProceduresDao;
import dao.PatientDao;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ListOfProcedures;
import model.Patient;

/**
 *
 * @author Залізний Мозок
 */
public class ProcedureCommand implements Command {

    /*
    add procedure, refresh operAndProc.jsp
    */
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int patientNumber = Integer.valueOf(request.getParameter("patient_number"));
        String proc = request.getParameter("procedure");
        ListOfProcedures lop = new ListOfProcedures();
        lop.setPatientNumber(patientNumber);
        lop.setProcedure(proc);
        DaoFactory factory = DaoFactory.getDaoFactory();
        ListOfProceduresDao ldao = factory.getListOfProceduresDao();
        PatientDao pdao = factory.getPatientDao();
        if (ldao.insert(lop)) {
            List<Patient> patients = pdao.getAllPatients();
            request.setAttribute("patients", patients);
            return "/views/operAndProc.jsp";
        } else {
            return "/views/test.html";
        }
    }
}
