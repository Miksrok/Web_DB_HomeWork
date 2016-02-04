
package commands;

import dao.AuthorizationDao;
import dao.DaoFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Authorization;

/**
 *
 * @author Залізний Мозок
 */
public class RegistrationCommand implements Command{
    /*
    add login and password of current user to db and finish registration
    */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login=request.getParameter("login");
        String password=request.getParameter("password");
        Authorization au = new Authorization();
        au.setLogin(login);
        au.setPassword(password);  
        DaoFactory factory=DaoFactory.getDaoFactory();
        AuthorizationDao adao = factory.getAuthorizationDao();
        if (adao.insert(au)){
            return "/views/successful.html";
        }else{
            return "../index.html";
        }
        
    }
    
}
