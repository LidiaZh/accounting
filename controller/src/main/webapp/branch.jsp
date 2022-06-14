<%--
  Created by IntelliJ IDEA.
  User: lidia
  Date: 5/29/22
  Time: 8:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Branches</title>
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
    <a class="active" href="branch">Филиалы</a>
    <a href="department_branch">Отделы</a>
    <a href="rPerson">МОЛ</a>
    <a href="about">Контакты</a>
</div>
<br><br>
<c:url value="/branch-form.jsp" var="branchAddUrl">
    <c:param name="action" value="add"/>
</c:url>
<button class="btn success"><a href="${branchAddUrl}">Добавить филиал</a></button>

<table id="customers">
    <caption><h1>Список Филиалов</h1></caption>
    <thead>
    <tr>
        <th>№</th>
        <th>Название</th>
        <th>Адрес</th>
        <th>Контактное лицо</th>
        <th>Телефон</th>
        <th>Информация</th>
        <th colspan="2"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="branch" items="${listOfAllBranches}" varStatus="status">
        <tr>
            <td>${status.index+1}</td>
            <td><c:out value="${branch.name}"/></td>
            <td><c:out value="${branch.address}"/></td>
            <td><c:out value="${branch.contact}"/></td>
            <td><c:out value="${branch.phone}"/></td>
            <td>
                <form name="edit" method="post" action="branch-form.jsp">
                    <input name="idBranch" type="hidden" value="${branch.id}">
                    <c:url value="/branch-form.jsp" var="branchEditUrl">
                        <c:param name="idBranch" value="${branch.id}"/>
                        <c:param name="name" value="${branch.name}"/>
                        <c:param name="address" value="${branch.address}"/>
                        <c:param name="contact" value="${branch.contact}"/>
                        <c:param name="phone" value="${branch.phone}"/>
                        <c:param name="action" value="edit"/>
                    </c:url>
                    <a href="${branchEditUrl}">Редактировать</a>
                </form>
            </td>
            <td>
                <form name="delete" method="post" action="branch-form.jsp">
                    <input name="idDepartment" type="hidden" value="${department.id}">

                    <c:url value="/branch-form.jsp" var="branchDeleteUrl">
                        <c:param name="idBranch" value="${branch.id}"/>
                        <c:param name="name" value="${branch.name}"/>
                        <c:param name="address" value="${branch.address}"/>
                        <c:param name="contact" value="${branch.contact}"/>
                        <c:param name="phone" value="${branch.phone}"/>
                        <c:param name="action" value="delete"/>
                    </c:url>
                    <a href="${branchDeleteUrl}">Удалить</a>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<%--<div class="footer">--%>
<%--    <h2>it.academy</h2>--%>
<%--</div>--%>
</body>
</html>

