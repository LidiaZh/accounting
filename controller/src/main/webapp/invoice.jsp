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
<br><br>
<c:url value="/invoice-form.jsp" var="invoiceAddUrl">
    <c:param name="action" value="add"/>
</c:url>
<button class="btn success"><a href="${invoiceAddUrl}">Выписать Накладную</a></button>
<br><br>
<p>
    <c:url value="/invoice-form.jsp" var="invoiceNewAddUrl">
        <c:param name="action" value="add"/>
    </c:url>
    <button class="btn success"><a href="${invoiceAddUrl}">Ввести Накладную</a></button>
</p>
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
        <th colspan="2">Действия</th>
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
            <table>
                <tbody>
                <c:forEach var="equipment" items="${invoice.equipmentList}" varStatus="status1">
                    <tr>
                        <td>${status1.index+1}</td>
                        <td><c:out value="${equipment.equipmentDetail.name}"/></td>
                        <td><c:out value="${equipment.account_number}"/></td>
                        <td><c:out value="${equipment.price}"/></td>
                        <td>
                            <form name="info" method="post" action="rPerson.jsp">
                                <c:url value="/rPerson.jsp" var="equipmentInfoUrl">
                                    <c:param name="idBranch" value="${branch.id}"/>
                                    <c:param name="name" value="${branch.name}"/>
                                    <c:param name="actionB" value="info"/>
                                </c:url>
                                <a href="${equipmentInfoUrl}">Информация</a>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </td>
        <td>
            <form name="edit" method="post" action="invoice-form.jsp">
                <c:url value="/invoice-form.jsp" var="invoiceEditUrl">
                    <c:param name="idInvoice" value="${invoice.id}"/>
                    <c:param name="number" value="${invoice.number}"/>
                    <c:param name="date" value="${invoice.date}"/>
                    <c:param name="cause" value="${invoice.cause}"/>
                    <c:param name="idSupplier" value="${invoice.idSupplier}"/>
                    <c:param name="supplierName" value="${invoice.supplierName}"/>
                    <c:param name="idReceiver" value="${invoice.idReceiver}"/>
                    <c:param name="receiverName" value="${invoice.receiverName}"/>
                    <c:param name="equipmentList" value="${invoice.equipmentList}"/>
                    <c:param name="action" value="edit"/>
                </c:url>
                <a href="${invoiceEditUrl}">Редактировать</a>
            </form>
        </td>
        </c:forEach>
    </tbody>
</table>
<div class="footer">
    <h2>it.academy</h2>
</div>
</body>
</html>
