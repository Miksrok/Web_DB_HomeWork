
package controllers;

import commands.Command;
import commands.CommandFactory;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Controller extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CommandFactory factory=CommandFactory.getCommandFactory();
        Command handler=factory.getInstance(request);
        String path=handler.execute(request,response);
        RequestDispatcher rd=request.getRequestDispatcher(path);
        rd.forward(request, response);
        
    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
}
