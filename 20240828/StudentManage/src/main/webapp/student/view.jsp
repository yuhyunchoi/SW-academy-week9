<%--
  Created by IntelliJ IDEA.
  User: yuhyun
  Date: 2024-08-28
  Time: 오후 5:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cfmt" uri="http://nhnacademy.com/cfmt" %>
<html>
<head>
  <title>학생 - 조회</title>
  <link rel="stylesheet" href="/style.css" />
  <style>
    table {
      width: 800px;
      border-collapse: collapse;
      border: 1px #ccc solid;
    }
    table th, td {
      padding: 5px;
      border: 1px #ccc solid;
    }
  </style>
</head>
<body>
<h1>학생 정보 조회</h1>

<table>
  <thead>
  <tr>
    <th>아이디</th>
    <th>이름</th>
    <th>성별</th>
    <th>나이</th>
    <th>생성일</th>
  </tr>
  </thead>
  <tbody>
  <tr>
    <td>${student.id}</td>
    <td>${student.name}</td>
    <td>${student.gender}</td>
    <td>${student.age}</td>
      <td>${cfmt:formatDate(student.createdAt, 'yyyy-MM-dd HH:mm:ss')}</td>
  </tr>
  </tbody>
</table>

<ul>
  <li><a href="${pageContext.request.contextPath}/student/list.do">리스트</a></li>
  <li>
    <!-- c:url을 사용하여 수정 링크 설정 -->
    <a href="<c:url value='/student/update.do?id=${student.id}' />">수정</a>
  </li>
  <li>
    <!-- 삭제 버튼 폼 -->
    <form action="<c:url value='/student/delete.do' />" method="post" onsubmit="return confirm('정말 삭제하시겠습니까?');">
      <input type="hidden" name="id" value="${student.id}" />
      <button type="submit">삭제</button>
    </form>
  </li>
</ul>
</body>
</html>
