<%--
  Created by IntelliJ IDEA.
  User: Lidia
  Date: 03.06.2022
  Time: 22:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<html>

<head>
    <title>Equipment</title>
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
    <a class="active" href="equipment">Оборудование</a>
    <a href="invoice">Накладные</a>
    <a href="receiver">Получатели</a>
    <a href="supplier">Поставщики</a>
    <a href="branch">Филиалы</a>
    <a href="department_branch">Отделы</a>
    <a href="rPerson">МОЛ</a>
    <a href="about">Контакты</a>
</div>
<p>
    <c:url value="/equipmentDetail-form.jsp" var="eqDetailAddUrl">
        <c:param name="action" value="add"/>
    </c:url>
    <button class="btn success"><a href="${eqDetailAddUrl}">Добавить Оборудование</a></button>
</p>
<br><br>
<table id="customers">
    <caption><h1>Список Оборудования</h1></caption>
    <thead>
    <tr>
        <th>№</th>
        <th>Модель оборудования</th>
        <th>Описание</th>
        <th>Срок использования, лет</th>
        <th>Производитель</th>
        <th colspan="2">Действия</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="eqDetail" items="${listOfAllEqDetails}" varStatus="status">
        <tr>
            <td>${status.index+1}</td>
            <td><c:out value="${eqDetail.name}"/></td>
            <td><c:out value="${eqDetail.desc}"/></td>
            <td><c:out value="${eqDetail.termOfUse}"/></td>
            <td><c:out value="${eqDetail.producer}"/></td>
            <td>
                <c:url value="/equipmentDetail-form.jsp" var="eqDetailEditUrl">
                    <c:param name="idEqDetail" value="${eqDetail.id}"/>
                    <c:param name="name" value="${eqDetail.name}"/>
                    <c:param name="desc" value="${eqDetail.desc}"/>
                    <c:param name="termOfUse" value="${eqDetail.termOfUse}"/>
                    <c:param name="producer" value="${eqDetail.producer}"/>
                    <c:param name="action" value="edit"/>
                </c:url>
                <a href="${eqDetailEditUrl}">Редактировать</a>
            </td>
            <td>
                <c:url value="/equipmentDetail-form.jsp" var="eqDetailDeleteUrl">
                    <c:param name="idEqDetail" value="${eqDetail.id}"/>
                    <c:param name="name" value="${eqDetail.name}"/>
                    <c:param name="desc" value="${eqDetail.desc}"/>
                    <c:param name="termOfUse" value="${eqDetail.termOfUse}"/>
                    <c:param name="producer" value="${eqDetail.producer}"/>
                    <c:param name="action" value="delete"/>
                </c:url>
                <a href="${eqDetailDeleteUrl}">Удалить</a>
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
