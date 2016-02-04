
package commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Залізний Мозок
 */
public class RedirectToDocStartPageCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "/views/doctorStartPage.html";
    }
    
}
