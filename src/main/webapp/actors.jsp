<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
    <table>
        <tr>
            <th>编号</th>
            <th>姓</th>
            <th>名</th>
            <th>修改时间</th>
        </tr>
        <c:forEach var="actor" items="${list}">
            <tr>
                <td>${actor.actorId}</td>
                <td>${actor.firstName}</td>
                <td>${actor.lastName}</td>
                <td>${actor.lastUpdate}</td>

            </tr>
        </c:forEach>
    </table>

</body>
</html>
