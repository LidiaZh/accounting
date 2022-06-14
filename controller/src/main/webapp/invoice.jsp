<%--
Created by IntelliJ IDEA.
User: Lidia
Date: 03.06.2022
Time: 13:38
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Invoice</title>
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
    <a class="active" href="invoice">Накладные</a>
    <a href="receiver">Получатели</a>
    <a href="supplier">Поставщики</a>
    <a href="branch">Филиалы</a>
    <a href="department_branch">Отделы</a>
    <a href="rPerson">МОЛ</a>
    <a href="about">Контакты</a>
</div>
<br>
<form name="add" method="post" action="invoice">
    <input name="action" type="hidden" value="getInvoiceForAction">
    <input name="action_2" type="hidden" value="add">
    <button class="btn success">Добавить Накладную</button>
</form>
<br><br>
<form name="new" method="post" action="invoice">
    <input name="action" type="hidden" value="getInvoiceForAction">
    <input name="action_2" type="hidden" value="new">
    <button class="btn success">Выписать Накладную</button>
</form>

<table id="customers">
    <caption><h1>Список Накладных</h1></caption>
    <thead>
    <tr>
        <th>№</th>
        <th>Номер</th>
        <th>Дата</th>
        <th>Основание</th>
        <th>ГрузоОтправитель</th>
        <th>ГрузоПолучатель</th>
        <th>Оборудование</th>
        <th colspan="2"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="invoice" items="${listOfAllInvoiceDto}" varStatus="status">
    <tr>
        <td>${status.index+1}</td>
        <td><c:out value="${invoice.number}"/></td>
        <td><c:out value="${invoice.date}"/></td>
        <td><c:out value="${invoice.cause}"/></td>
        <td><c:out value="${invoice.supplierName}"/></td>
        <td><c:out value="${invoice.receiverName}"/></td>
        <td>
            <form name="info" method="post" action="invoice">
                <input name="action" type="hidden" value="info">
                <input name="idInvoice" type="hidden" value="${invoice.id}">
                <button class="btn success">Отобразить</button>
            </form>
        </td>
        <td>
            <form name="edit" method="post" action="invoice">
                <input name="action" type="hidden" value="getInvoiceForAction">
                <input name="action_2" type="hidden" value="edit">
                <input name="idInvoice" type="hidden" value="${invoice.id}">
                <button class="btn success">Редактировать</button>
            </form>
<%--        </td>--%>
<%--        <td>--%>
            <form name="delete" method="post" action="invoice">
                <input name="action" type="hidden" value="getInvoiceForAction">
                <input name="action_2" type="hidden" value="delete">
                <input name="idInvoice" type="hidden" value="${invoice.id}">
                <button class="btn success">Удалить</button>
            </form>
        </td>
        </c:forEach>
    </tbody>
</table>
<%--<div class="footer">--%>
<%--    <h2>it.academy</h2>--%>
<%--</div>--%>
</body>
</html>
