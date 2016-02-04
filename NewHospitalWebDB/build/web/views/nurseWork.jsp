


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nurses and doctors work page</title>
    </head>
    <body>
        <c:set var="doc" value="${access}"/>
        <form action="controller" method="get">
            <p items="${requestScope.number}">clinical record: ${number}</p>
            <input type="hidden" name="patient" items="${requestScope.number}" value="${number}"/>
            <table border="1" align="centr">
                <caption>in progress</caption>
                <tr>
                    <td>id</td>
                    <td>procedures</td>
                    <td>Status</td>
                </tr>
                <c:forEach var="listOfProcedures" items="${requestScope.trueProc}">
                    <tr>
                        <td>${listOfProcedures.id}</td>
                        <td>${listOfProcedures.procedure}</td>
                        <td><font color="green">${listOfProcedures.procedureStatus}</font></td>
                    </tr>               
                </c:forEach>
            </table>
            <table border="1" align="centr">
                <caption>finished</caption>
                <tr>
                    <td>id</td>
                    <td>procedures</td>
                    <td>Status</td>
                </tr>
                <c:forEach var="listOfProcedures" items="${requestScope.falseProc}">
                    <tr>
                        <td>${listOfProcedures.id}</td>
                        <td>${listOfProcedures.procedure}</td>
                        <td><font color="red">${listOfProcedures.procedureStatus}</font></td>
                    </tr>               
                </c:forEach>
            </table> 
            <!--<textarea rows="1" cols="5" name="patient" readonly items="${requestScope.number}">${number}</textarea>-->
            <select size="1" name="true_false">
                <option selected disabled>select procedure</option>
                <c:forEach var="listOfProcedures" items="${requestScope.trueProc}">
                    <option value="${listOfProcedures.id}">${listOfProcedures.procedure}</option>
                </c:forEach>
            </select>         
            <button type="submit" name="send" value="finish_it">finish it!</button>
        </form>
        <form action="controller" method="get">     
            <button type="submit" name="send" value="doctor_like_nurse">other patient</button>
        </form> 


        <c:if test="${fn:contains(doc, 'doctor')}">
            <form action="controller" method="get">
                <input type="hidden" name="patient" items="${requestScope.number}" value="${number}"/>
                <table border="1" align="centr">
                    <caption>operations in progress</caption>
                    <tr>
                        <td>id</td>
                        <td>operations</td>
                        <td>Status</td>
                    </tr>
                    <c:forEach var="operation" items="${requestScope.trueOperations}">
                        <tr>
                            <td>${operation.id}</td>
                            <td>${operation.operation}</td>
                            <td><font color="green">${operation.status}</font></td>
                        </tr>               
                    </c:forEach>
                </table>

                <table border="1" align="centr">
                    <caption> finished operations</caption>
                    <tr>
                        <td>id</td>
                        <td>operations</td>
                        <td>Status</td>
                    </tr>
                    <c:forEach var="operation" items="${requestScope.falseOperations}">
                        <tr>
                            <td>${operation.id}</td>
                            <td>${operation.operation}</td>
                            <td><font color="green">${operation.status}</font></td>
                        </tr>               
                    </c:forEach>
                </table>

                <select size="1" name="true_false_operation">
                    <option selected disabled>select operation</option>
                    <c:forEach var="operation" items="${requestScope.trueOperations}">
                        <option value="${operation.id}">${operation.operation}</option>
                    </c:forEach>
                </select>
                <button type="submit" name="send" value="finish_it">finish it!</button>
            </form>
        </c:if>


    </body>
</html>
