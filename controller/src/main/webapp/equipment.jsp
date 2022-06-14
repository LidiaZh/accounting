<%--
  Created by IntelliJ IDEA.
  User: Lidia
  Date: 03.06.2022
  Time: 23:10
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

<c:if test="${action!=\"chooseEq\"}">
    <br>
    <form name="add" method="post" action="equipment">
        <input name="action" type="hidden" value="getEquipmentForAction">
        <input name="action_2" type="hidden" value="add">
        <button class="btn success">Добавить</button>
    </form>
    <br><br>
        <c:url value="equipmentDetail" var="equipmentDetailListUrl">
        </c:url>
        <button class="btn success"><a href=${equipmentDetailListUrl}>Тип Оборудования</a></button>
    <br>
</c:if>
<c:if test="${action==\"chooseEq\"}">
<form name="new" method="post" action="invoice">
    </c:if>
    <table id="customers">

        <c:if test="${action!=\"chooseEq\"}">
            <caption><h1>Список Оборудования</h1></caption>
        </c:if>

        <c:if test="${action==\"chooseEq\"}">
            <caption><h1>Выберите Оборудование</h1></caption>
        </c:if>

        <thead>
        <tr>
            <th>№</th>
            <th>Инв. номер</th>
            <th>Серийный номер</th>
            <th>Номер накладной</th>
            <th>Модель оборудования</th>
            <th>Статус</th>
            <th>Начало использования</th>
            <th>Цена, бел.руб.</th>
            <th>МОЛ</th>
            <th>Отдел</th>
            <th>Филиал</th>
            <th colspan="3"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="equipment" items="${listOfAllEquipmentDto}" varStatus="status">
            <tr>
                <td>${status.index+1}</td>
                <td><c:out value="${equipment.account_number}"/></td>
                <td><c:out value="${equipment.s_number}"/></td>
                <td><c:out value="${equipment.invoiceNumberCurrent}"/></td>
                <td><c:out value="${equipment.equipmentName}"/></td>
                <td><c:out value="${equipment.status}"/></td>
                <td><c:out value="${equipment.start_date}"/></td>
                <td><c:out value="${equipment.price}"/></td>
                <td><c:out value="${equipment.resPerson.surname}"/>
                    <c:out value="${equipment.resPerson.name}"/></td>
                <td><c:out value="${equipment.resPerson.department.name}"/></td>
                <td><c:out value="${equipment.resPerson.branch.name}"/></td>
                <c:if test="${action!=\"chooseEq\"}">
                    <td>
                        <form name="edit" method="post" action="equipment">
                            <input name="action" type="hidden" value="getEquipmentForAction">
                            <input name="action_2" type="hidden" value="edit">
                            <input name="idEquipment" type="hidden" value="${equipment.id}">
                            <input name="idInvoiceCurrent" type="hidden" value="${equipment.idInvoiceCurrent}">
                            <button class="btn success">Редактировать</button>
                        </form>
                    </td>
                    <td>
                        <form name="info" method="post" action="equipment">
                            <input name="action" type="hidden" value="info">
                            <input name="idEquipment" type="hidden" value="${equipment.id}">
                            <button class="btn success">Информация</button>
                        </form>
                    </td>
                    <td>
                        <c:url value="/equipment-form.jsp" var="equipmentDeleteUrl">
                            <c:param name="idEquipment" value="${equipment.id}"/>
                            <c:param name="account_number" value="${equipment.account_number}"/>
                            <c:param name="s_number" value="${equipment.s_number}"/>
                            <c:param name="equipmentName" value="${equipment.equipmentName}"/>
                            <c:param name="start_date" value="${equipment.start_date}"/>
                            <c:param name="status" value="${equipment.status}"/>
                            <c:param name="price" value="${equipment.price}"/>
                            <c:param name="resPersonSurname" value="${equipment.resPerson.surname}"/>
                            <c:param name="resPersonName" value="${equipment.resPerson.name}"/>
                            <c:param name="action" value="delete"/>
                        </c:url>
                        <a href="${equipmentDeleteUrl}">Удалить</a>
                    </td>
                </c:if>
                <c:if test="${action==\"chooseEq\"}">
                <td>
                    <input type="checkbox" name="boxNameOfEq" value="${equipment.id}">
                    <input name="action" type="hidden" value="getEquipmentForInvoice">
                    <input name="idInvoice" type="hidden" value="${idInvoice}">
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>
    <c:if test="${action==\"chooseEq\"}">
    <button class="btn success">Выбрать</button>
</form>
</c:if>

<%--<div class="footer">--%>
<%--    <h2>it.academy</h2>--%>
<%--</div>--%>
</body>
</html>
