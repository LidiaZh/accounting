<%--
  Created by IntelliJ IDEA.
  User: Lidia
  Date: 08.06.2022
  Time: 23:10
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
<div style="text-align: center;">
    <h1>Накладная № ${invoiceInfo.number} от ${invoiceInfo.date}</h1>
</div>
<p>
    ГрузоОтправитель: ${invoiceInfo.supplier.name}
</p>
<p>
    Грузополучатель: ${invoiceInfo.receiver.name}
</p>
<p>
    Основание: ${invoiceInfo.cause}
</p>
<table id="customers">
    <caption><h2>Оборудование</h2></caption>
    <thead>
    <tr>
        <th>№</th>
        <th>Инв. номер</th>
        <th>Модель оборудования</th>
        <th>Цена, бел.руб.</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="equipment" items="${invoiceInfo.equipment}" varStatus="status">
        <tr>
            <td>${status.index+1}</td>
            <td><c:out value="${equipment.account_number}"/></td>
            <td><c:out value="${equipment.equipmentDetail.name}"/></td>
            <td><c:out value="${equipment.price}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<%--<div class="footer">--%>
<%--    <h2>it.academy</h2>--%>
<%--</div>--%>
</body>
</html>
