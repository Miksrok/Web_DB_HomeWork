
package commands;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class CommandFactory {
    static CommandFactory instance=new CommandFactory();
    Map<String,Command> commandMap=new HashMap<>();
    private CommandFactory(){
        commandMap.put("registration",new RegistrationCommand());
        commandMap.put("login",new LoginCommand());
        commandMap.put("information", new InformationCommand());
        commandMap.put("add_patient", new PatientRegistrationCommand());
        commandMap.put("new_patient", new DoctorStartPageCommand());
        commandMap.put("add_operation", new OperationCommand());
        commandMap.put("add_procedure", new ProcedureCommand());
        commandMap.put("procedures", new OperAndProcCommand());
        commandMap.put("choose_patient", new NurseCommand());
        commandMap.put("finish_it", new NurseWorkCommand());
        commandMap.put("doctor_like_nurse", new DoctorLikeNurseCommand());
        commandMap.put("redirect_to_doc_start_page", new RedirectToDocStartPageCommand());
    }
    public static CommandFactory getCommandFactory(){
        return instance;
    }
    public Command getInstance(HttpServletRequest request){
        String value=request.getParameter("send");
        return commandMap.get(value);
    }
}
