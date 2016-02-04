
package commands;

import dao.DaoFactory;
import dao.ListOfProceduresDao;
import dao.OperationDao;
import dao.PatientDao;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ListOfProcedures;
import model.Operation;

/**
 *
 * @author Залізний Мозок
 */
public class NurseCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Integer number = Integer.valueOf(request.getParameter("patient_number"));
        String access = (String) session.getAttribute("access");
        DaoFactory factory = DaoFactory.getDaoFactory();
        ListOfProceduresDao ldao = factory.getListOfProceduresDao();
        List<ListOfProcedures> trueProc = ldao.trueProc(number);
        List<ListOfProcedures> falseProc = ldao.falseProc(number);
        if (access.equals("doctor")){
            OperationDao odao = factory.getOperationDao();
            List <Operation> trueOperations = odao.getTrueOperations(number);
            List<Operation> falseOperations = odao.getFalseOperations(number);
            request.setAttribute("trueOperations", trueOperations);
            request.setAttribute("falseOperations", falseOperations);
        }
        request.setAttribute("access", access);
        request.setAttribute("number", number);
        request.setAttribute("trueProc", trueProc);
        request.setAttribute("falseProc", falseProc);
        return "/views/nurseWork.jsp";
        
    }
    
}
