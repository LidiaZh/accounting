<%--
  Created by IntelliJ IDEA.
  User: Lidia
  Date: 13.06.2022
  Time: 22:07
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

<div style="text-align: center;">
    <h1>${equipment.equipmentDetail.name}</h1>
</div>
<p>
    <strong>Инв.Номер:</strong> ${equipment.account_number}
</p>
<p>
    <strong>Серийный номер:</strong> ${equipment.s_number}
</p>
<p>
    <strong>МОЛ: </strong>${equipment.responsiblePerson.surname}
    ${equipment.responsiblePerson.name}
    ${equipment.responsiblePerson.department.name}
    ${equipment.responsiblePerson.branch.name}
</p>

<c:set value="${equipment.price*(1/equipment.equipmentDetail.termOfUse)}" scope="session" var="yearDepreciation"/>
<c:set value="${yearDepreciation/12}" scope="session" var="monthDepreciation"/>
<c:set value="${Math.abs(LocalDate.now.getMonthValue()-equipment.start_date.getMonthValue())}" scope="session" var="usageInMonth"/>
<c:set value="${Math.abs(equipment.start_date.getYear()+equipment.equipmentDetail.termOfUse)}" scope="session" var="yearOfEndUsage"/>

<table id="customers">
    <caption><h2>Амортизация</h2></caption>
    <thead>
    <tr>
        <th>Начало экспл.</th>
        <th>Цена, руб.</th>
        <th>Сумма аморт. год., руб.</th>
        <th>Сумма аморт. мес., руб.</th>
        <th>Остаточная стоимость, руб.</th>
        <th>Оконч.экспл.</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td><c:out value="${equipment.start_date}"/></td>
        <td><c:out value="${equipment.price}"/></td>
        <td><fmt:formatNumber type = "number" maxIntegerDigits = "3" value="${yearDepreciation}"/></td>
        <td><fmt:formatNumber type = "number" maxIntegerDigits = "3" value="${monthDepreciation}"/></td>
        <td><fmt:formatNumber type = "number" maxIntegerDigits = "3" value="${usageInMonth*monthDepreciation}"/></td>
        <td><c:out value="${yearOfEndUsage}"/></td>
    </tr>
    </tbody>
</table>


<br><br>
<table id="customers">
    <caption><h2>Движение Оборудования</h2></caption>
    <thead>
    <tr>
        <th>№</th>
        <th>№ Накладной</th>
        <th>Дата</th>
        <th>Основание</th>
        <th>ГрузоОтправитель</th>
        <th>ГрузоПолучатель</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="invoice" items="${equipment.invoice}" varStatus="status">
        <tr>
            <td>${status.index+1}</td>
            <td><c:out value="${invoice.number}"/></td>
            <td><c:out value="${invoice.date}"/></td>
            <td><c:out value="${invoice.cause}"/></td>
            <td><c:out value="${invoice.supplier.name}"/></td>
            <td><c:out value="${invoice.receiver.name}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<%--<div class="footer">--%>
<%--    <h2>it.academy</h2>--%>
<%--</div>--%>
</body>
</html>
