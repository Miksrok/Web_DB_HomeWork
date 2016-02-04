/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class NurseWorkCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Integer number = Integer.valueOf(request.getParameter("patient"));
        DaoFactory factory = DaoFactory.getDaoFactory();
        ListOfProceduresDao ldao = factory.getListOfProceduresDao();

        if (request.getParameter("true_false") != null) {
            int procNumber = Integer.valueOf(request.getParameter("true_false"));
            ldao.finishIt(procNumber);
        }

        String access = (String) session.getAttribute("access");

        List<ListOfProcedures> trueProc = ldao.trueProc(number);
        List<ListOfProcedures> falseProc = ldao.falseProc(number);

        if (access.equals("doctor")) {
            OperationDao odao = factory.getOperationDao();

            if (request.getParameter("true_false_operation") != null) {
                int operNumber = Integer.valueOf(request.getParameter("true_false_operation"));
                odao.finishThisOperation(operNumber);
            }

            List<Operation> trueOperations = odao.getTrueOperations(number);
            List<Operation> falseOperations = odao.getFalseOperations(number);
            request.setAttribute("trueOperations", trueOperations);
            request.setAttribute("falseOperations", falseOperations);
        }

        request.setAttribute("number", number);
        request.setAttribute("trueProc", trueProc);
        request.setAttribute("falseProc", falseProc);
        return "/views/nurseWork.jsp";
    }

}
