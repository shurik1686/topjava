<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<html lang="ru">
<head>
    <title>Meals</title>
    <link rel="stylesheet" type="text/css" href="css.css">
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table>
    <c:forEach var="num" items="${list}">
        <tr style="background-color:
            <c:if test="${num.excess}">red</c:if>
            <c:if test="${!num.excess}">green</c:if>
        ;">
            <td>${num.dateTime.format( DateTimeFormatter.ofPattern("dd.MM.yyyy HH:MM"))}</td>
            <td>${num.description}</td>
            <td>${num.calories}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>