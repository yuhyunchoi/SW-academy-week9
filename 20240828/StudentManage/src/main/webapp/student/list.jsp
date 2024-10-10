<%--
  Created by IntelliJ IDEA.
  User: yuhyun
  Date: 2024-08-28
  Time: 오후 2:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>student - list</title>
    <link rel="stylesheet" href="/style.css" />
</head>

<body>
<h1>학생 리스트</h1>
<style>
    table {
        width: 800px;
        border-collapse: collapse;
        border:1px #ccc solid;
    }
    table tr>td,th{
        padding:5px;
        border:1px #ccc solid;
    }
</style>
<p><a href="/student/register.do" >학생(등록)</a></p>
<table>
    <thead>
    <tr>
        <th style="width: 25%">아이디</th>
        <th style="width: 25%">이름</th>
        <th style="width: 15%">성별</th>
        <th style="width: 15%">나이</th>
        <th style="width: 20%">cmd</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${studentList}">
        <tr>
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>${item.gender}</td>
            <td>${item.age}</td>
            <td>  <form action="<c:url value='/student/view.do' />" method="get">
                <input type="hidden" name="id" value="${item.id}" />
                <button type="submit">조회</button>
            </form>
            </td>
        </tr>
    </c:forEach>

    </tbody>
</table>
</body>
</html>