
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="controller" method="get">
            <select size="1" name="patient_number">
                <option selected disabled>select patient</option>
                <c:forEach var="patient" items="${requestScope.patients}">
                    <option value="${patient.clinicalRecord}">${patient.name}</option>
                </c:forEach>
            </select>         
            <button type="submit" name="send" value="choose_patient">choose patient</button>
        </form>
    </body>
</html>
