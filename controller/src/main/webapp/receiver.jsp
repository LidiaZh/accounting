<%--
Created by IntelliJ IDEA.
User: lidia
Date: 5/27/22
Time: 8:03 PM
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Receivers</title>
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
    <a class="active" href="receiver">Получатели</a>
    <a href="supplier">Поставщики</a>
    <a href="branch">Филиалы</a>
    <a href="department_branch">Отделы</a>
    <a href="rPerson">МОЛ</a>
    <a href="about">Контакты</a>
</div>
<br><br>

<c:url value="/receiver-form.jsp" var="receiverAddUrl">
    <c:param name="action" value="add"/>
</c:url>
<button class="btn success"><a href="${receiverAddUrl}">Добавить получателя</a></button>
<br><br>
<table id="customers">
    <caption><h1>Список Получателей</h1></caption>
    <thead>
    <tr>
        <th>№</th>
        <th>Название</th>
        <th>Адрес</th>
        <th>Контактное лицо</th>
        <th>Телефон</th>
        <th colspan="2">Действия</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="receiver" items="${receiverList}" varStatus="status">
        <tr>
            <td>${status.index+1}</td>
            <td><c:out value="${receiver.name}"/></td>
            <td><c:out value="${receiver.address}"/></td>
            <td><c:out value="${receiver.contact}"/></td>
            <td><c:out value="${receiver.phone}"/></td>
            <td>
                <form name="edit" method="post" action="receiver-form.jsp">
                    <c:url value="/receiver-form.jsp" var="receiverEditUrl">
                        <c:param name="idReceiver" value="${receiver.id}"/>
                        <c:param name="name" value="${receiver.name}"/>
                        <c:param name="address" value="${receiver.address}"/>
                        <c:param name="contact" value="${receiver.contact}"/>
                        <c:param name="phone" value="${receiver.phone}"/>
                        <c:param name="action" value="edit"/>
                    </c:url>
                    <a href="${receiverEditUrl}">Редактировать</a>
                </form>
            </td>
            <td>
                <form name="delete" method="post" action="receiver-form.jsp">
                    <c:url value="/receiver-form.jsp" var="receiverDeleteUrl">
                        <c:param name="idReceiver" value="${receiver.id}"/>
                        <c:param name="name" value="${receiver.name}"/>
                        <c:param name="address" value="${receiver.address}"/>
                        <c:param name="contact" value="${receiver.contact}"/>
                        <c:param name="phone" value="${receiver.phone}"/>
                        <c:param name="action" value="delete"/>
                    </c:url>
                    <a href="${receiverDeleteUrl}">Удалить</a>
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
