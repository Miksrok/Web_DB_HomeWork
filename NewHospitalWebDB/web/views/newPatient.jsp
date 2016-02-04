

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>new patient</title>
    </head>
    <body>
        <%= new java.util.Date() %>
        <form action="controller" method="get">
            <input type="text" name="name" placeholder="name" value=""/>
            <input type="text" name="diagnosis" placeholder="diagnosis" value=""/>
            
            <select size="1" name="staff">
                <option selected disabled>select doctor</option>
                <c:forEach var="staff" items="${requestScope.doctors}">
                    <option value="${staff.id}">${staff.name}</option>
                </c:forEach>
            </select>
            <select size="1" name="department">
                <option selected disabled>select department</option>
                <c:forEach var="department" begin="1" items="${requestScope.departments}">
                    <option value="${department.number}">${department.name}</option>
                </c:forEach>
            </select>
            <input type="submit" name="send" value="add_patient"/>
        </form>
    </body>
</html>
