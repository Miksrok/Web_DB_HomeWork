
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>users personal information</title>
    </head>
    <body>
        <form action="controller" method="get">
            <input type="text" name="name" value=""/>
            <input type="text" name="specialization" value=""/>
            <select size="1" name="department">
                <option selected disabled>select department</option>
                <c:forEach var="department" items="${requestScope.departments}">
                    <option value="${department.number}">${department.name}</option>
                </c:forEach>
            </select>
            <input type="submit" name="send" value="new_patient"/>
        </form>
    </body>
</html>
