
package commands;

import dao.DaoFactory;
import dao.PatientDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Patient;

/**
 *
 * @author Залізний Мозок
 */
public class PatientRegistrationCommand implements Command {
    /*
    create and add new patient
    */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String diagnosis = request.getParameter("diagnosis");
        int department = Integer.valueOf(request.getParameter("department"));
        int primaryDoctor = Integer.valueOf(request.getParameter("staff"));
        Patient pa = new Patient();
        pa.setName(name);
        pa.setDiagnosis(diagnosis);
        pa.setDepartment(department);
        pa.setPrimaryDoctor(primaryDoctor);
        DaoFactory factory = DaoFactory.getDaoFactory();
        PatientDao pdao = factory.getPatientDao();
        if (pdao.insert(pa)){
            return "/views/successful.html";
        }else{
            return "/views/test.html";
        }
    }
}
