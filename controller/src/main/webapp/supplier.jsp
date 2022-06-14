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
    <title>Suppliers</title>
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
    <a class="active" href="supplier">Поставщики</a>
    <a href="branch">Филиалы</a>
    <a href="department_branch">Отделы</a>
    <a href="rPerson">МОЛ</a>
    <a href="about">Контакты</a>
</div>
<br><br>
<button class="btn success"><a href="<c:url value="/supplier-form.jsp?action=add"/>">Добавить поставщика</a></button>

<table id="customers">
    <caption><h1>Список Поставщиков</h1></caption>
    <thead>
    <tr>
        <th>№</th>
        <th>Название</th>
        <th>Адрес</th>
        <th>Контактное лицо</th>
        <th>Телефон</th>
        <th colspan="2"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="supplier" items="${supplierList}" varStatus="status">
        <tr>
            <td>${status.index+1}</td>
            <td><c:out value="${supplier.name}"/></td>
            <td><c:out value="${supplier.address}"/></td>
            <td><c:out value="${supplier.contact}"/></td>
            <td><c:out value="${supplier.phone}"/></td>
            <td>
                <form name="edit" method="post" action="supplier-form.jsp">
                    <input name="idSupplier" type="hidden" value="${supplier.id}">
                    <a href="<c:url value="/supplier-form.jsp?idSupplier=${supplier.id}&name=${supplier.name}&address=${supplier.address}&contact=${supplier.contact}&phone=${supplier.phone}&action=edit"/>"
                    >Редактировать</a>
                </form>
            </td>
            <td>
                <form name="delete" method="post" action="supplier-form.jsp">
                    <input name="idSupplier" type="hidden" value="${supplier.id}">
                    <a href="<c:url value="/supplier-form.jsp?idSupplier=${supplier.id}&name=${supplier.name}&address=${supplier.address}&contact=${supplier.contact}&phone=${supplier.phone}&action=delete"/>"
                    >Удалить</a>
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
