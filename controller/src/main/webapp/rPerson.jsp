<%--
  Created by IntelliJ IDEA.
  User: lidia
  Date: 5/30/22
  Time: 12:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>rPerson</title>
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
    <a href="department_branch">Отделы</a>
    <a class="active" href="rPerson">МОЛ</a>
    <a href="about">Контакты</a>
</div>
<br><br>
<button class="btn success"><a href="<c:url value="/rPerson-form.jsp?action=add"/>">Добавить МОЛ</a></button>
<br><br>

<table id="customers">
    <caption><h1>Список МОЛ</h1></caption>
    <thead>
    <tr>
        <th>№</th>
        <th>Фамилия</th>
        <th>Имя</th>
        <th>Отдел</th>
        <th>Филиал</th>
        <th colspan="3">Действия</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="rPersonDto" items="${listOfrPersonDto}" varStatus="status">
        <tr>
            <td>${status.index+1}</td>
            <td><c:out value="${rPersonDto.surname}"/></td>
            <td><c:out value="${rPersonDto.name}"/></td>
            <td><c:out value="${rPersonDto.departmentName}"/></td>
            <td><c:out value="${rPersonDto.branchName}"/></td>
            <td>
                <form name="edit" method="post" action="rPerson-form.jsp">
                    <c:url value="/rPerson-form.jsp" var="editRPerson">
                        <c:param name="idRPerson" value="${rPersonDto.id}"/>
                        <c:param name="surname" value="${rPersonDto.surname}"/>
                        <c:param name="name" value="${rPersonDto.name}"/>
                        <c:param name="branchName" value="${rPersonDto.branchName}"/>
                        <c:param name="departmentName" value="${rPersonDto.departmentName}"/>
                        <c:param name="action" value="edit"/>
                    </c:url>
                    <a href="${editRPerson}">Редактировать</a>
                </form>
            </td>
            <td>
                <form name="delete" method="post" action="rPerson-form.jsp">
                    <c:url value="/rPerson-form.jsp" var="deleteRPerson">
                        <c:param name="idRPerson" value="${rPersonDto.id}"/>
                        <c:param name="surname" value="${rPersonDto.surname}"/>
                        <c:param name="name" value="${rPersonDto.name}"/>
                        <%--                        <c:param name="branchName" value="${rPersonDto.branchName}"/>--%>
                        <%--                        <c:param name="departmentName" value="${rPersonDto.departmentName}"/>--%>
                        <c:param name="action" value="delete"/>
                    </c:url>
                    <a href="${deleteRPerson}">Удалить</a>
                </form>
            </td>
            <td>
                <form name="editDepartmentAndBranch" method="post" action="rPerson-form.jsp">
                    <c:url value="/rPerson-form.jsp" var="editDepartmentAndBranch">
                        <c:param name="idRPerson" value="${rPersonDto.id}"/>
                        <c:param name="surname" value="${rPersonDto.surname}"/>
                        <c:param name="name" value="${rPersonDto.name}"/>
                        <c:param name="idBranch" value="${rPersonDto.idBranch}"/>
                        <c:param name="branchName" value="${rPersonDto.branchName}"/>
                        <c:param name="idDepartment" value="${rPersonDto.idDepartment}"/>
                        <c:param name="departmentName" value="${rPersonDto.departmentName}"/>
                        <c:param name="action" value="editDepartmentAndBranch"/>
                    </c:url>
                    <a href="${editDepartmentAndBranch}">Изменить подразделение</a>
                </form>
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
