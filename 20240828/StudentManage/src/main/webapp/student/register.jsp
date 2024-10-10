<%--
  Created by IntelliJ IDEA.
  User: yuhyun
  Date: 2024-08-28
  Time: 오후 4:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>학생 등록/수정</title>
  <link rel="stylesheet" href="/style.css" />
  <meta charset="UTF-8" />
  <style>
    table {
      width: 800px;
      border-collapse: collapse;
      border: 1px solid #ccc;
    }
    table th, td {
      padding: 5px;
      border: 1px solid #ccc;
    }
    input[type="text"],
    select,
    input[type="number"] {
      width: 100%;
      padding: 8px;
      margin-bottom: 15px;
      border: 1px solid #ccc;
      border-radius: 4px;
      box-sizing: border-box;
    }
    button {
      width: 100%;
      padding: 10px;
      background-color: #4CAF50;
      color: white;
      border: none;
      border-radius: 4px;
      cursor: pointer;
    }
    button:hover {
      background-color: #45a049;
    }
  </style>
</head>

<body>
<h1>학생 등록/수정</h1>
<p><a href="/student/register.do" >학생(등록)</a></p>
<c:choose>
  <c:when test="${empty student}">
    <c:set var="action" value="/student/register.do" />
  </c:when>
  <c:otherwise>
    <c:set var="action" value="/student/update.do" />
  </c:otherwise>
</c:choose>

<form method="post" action="${action}">
  <table>
    <tbody>
    <tr>
      <th>ID</th>
      <td><input type="text" name="id" value="${student.id}"
              <c:if test="${not empty student}">
                readonly
              </c:if>
                 required /></td>
    </tr>
    <tr>
      <th>이름</th>
      <td><input type="text" name="name" value="${student.name}" required /></td>
    </tr>
    <tr>
      <th>성별</th>
      <td>
        <select name="gender" required>
          <option value="M" <c:if test="${student.gender == 'M'}">selected</c:if>>남자</option>
          <option value="F" <c:if test="${student.gender == 'F'}">selected</c:if>>여자</option>
        </select>
      </td>
    </tr>
    <tr>
      <th>나이</th>
      <td><input type="number" name="age" value="${student.age}" min="1" max="100" required /></td>
    </tr>
    </tbody>
  </table>
  <p>
    <button type="submit">
      <c:choose>
        <c:when test="${empty student}">
          등록
        </c:when>
        <c:otherwise>
          수정
        </c:otherwise>
      </c:choose>
    </button>
  </p>
</form>

</body>
</html>

