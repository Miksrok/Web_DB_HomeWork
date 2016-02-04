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
public class DoctorLikeNurseCommand implements Command {

    /*
    redirect to nurseStart.jsp when press  <button>do proc</button> on doctorStartPage.html
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        DaoFactory factory = DaoFactory.getDaoFactory();
        PatientDao pdao = factory.getPatientDao();
        List<Patient> patients = pdao.getAllPatients();
        request.setAttribute("patients", patients);
        return "/views/nurseStart.jsp";
    }

}
