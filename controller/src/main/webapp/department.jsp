<%--
  Created by IntelliJ IDEA.
  User: lidia
  Date: 5/29/22
  Time: 9:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<html>

<head>
    <title>Departments</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/header.css" rel="stylesheet">
    <link href="css/footer.css" rel="stylesheet">
    <link href="css/table.css" rel="stylesheet">
    <link href="css/button.css" rel="stylesheet">
</head>
<body>

<div class="header">
    <h1>Учет оборудования</h1>
</div>

<div class="topnav">
    <a href="index.jsp">Главная</a>
    <a href="equipment">Оборудование</a>
    <a href="invoice">Накладные</a>
    <a href="receiver">Получатели</a>
    <a href="supplier">Поставщики</a>
    <a href="branch">Филиалы</a>
    <a class="active" href="department_branch">Отделы</a>
    <a href="rPerson">МОЛ</a>
    <a href="about">Контакты</a>
</div>
<p>
    <c:url value="/department-form.jsp" var="departmentAddUrl">
        <c:param name="action" value="add"/>
    </c:url>
    <button class="btn success"><a href="${departmentAddUrl}">Добавить Отдел</a></button>
</p>
<br><br>
<p>
    <c:url value="department_branch" var="departmentAndBranchListUrl">
    </c:url>
    <button class="btn success"><a href="${departmentAndBranchListUrl}">Отделы и Филиалы</a></button>
</p>
<br>
<table id="customers">
    <caption><h1>Список Отделов</h1></caption>
    <thead>
    <tr>
        <th>№</th>
        <th>Название</th>
        <th colspan="2">Действия</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="department" items="${listOfAllDepartments}" varStatus="status">
        <tr>
            <td>${status.index+1}</td>
            <td><c:out value="${department.name}"/></td>
            <td>
                <c:url value="/department-form.jsp" var="departmentEditUrl">
                    <c:param name="idDepartment" value="${department.id}"/>
                    <c:param name="name" value="${department.name}"/>
                    <c:param name="action" value="edit"/>
                </c:url>
                <a href="${departmentEditUrl}">Редактировать</a>
            </td>
            <td>
                <c:url value="/department-form.jsp" var="departmentDeleteUrl">
                    <c:param name="idDepartment" value="${department.id}"/>
                    <c:param name="name" value="${department.name}"/>
                    <c:param name="action" value="delete"/>
                </c:url>
                <a href="${departmentDeleteUrl}">Удалить</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="footer">
    <h2>it.academy</h2>
</div>
</body>
</html>

