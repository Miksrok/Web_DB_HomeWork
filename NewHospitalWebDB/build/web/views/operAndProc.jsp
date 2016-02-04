

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Operations and procedures</title>
    </head>
    <body>
        <form action="controller" method="get">
            <select size="1" name="patient_number">
                <option selected disabled>select patient</option>
                <c:forEach var="patient" items="${requestScope.patients}">
                    <option value="${patient.clinicalRecord}">${patient.name}</option>
                </c:forEach>
            </select>
            <input type="text" name="procedure" placeholder="procedure" value=""/>            
            <button type="submit" name="send" value="add_procedure">add procedure</button>
        </form>
        <form action="controller" method="get">
            <select size="1" name="patient_number">
                <option selected disabled>select patient</option>
                <c:forEach var="patient" items="${requestScope.patients}">
                    <option value="${patient.clinicalRecord}">${patient.name}</option>
                </c:forEach>
            </select>
            <input type="text" name="operation" placeholder="operation" value=""/>            
            <button type="submit" name="send" value="add_operation">add operation</button>
        </form>
        <form action="controller" method="get">   
            <input type="submit" name="send" value="redirect_to_doc_start_page"/>
        </form>
    </body>
</html>
