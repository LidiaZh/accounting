<%--
  Created by IntelliJ IDEA.
  User: Lidia
  Date: 03.06.2022
  Time: 13:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.time.LocalDate" %>
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
    <link href="css/form.css" rel="stylesheet">

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

<c:choose>
    <c:when test="${param.action==\"add\"}">
        <h2>Добавить Накладную</h2>

        <div class="container">
            <form name="add" method="post" action="invoice">
                <label>Номер:
                    <input type="text" name="number" required placeholder="Номер">
                </label>

                <label>Дата:
                    <input type="text" name="date" readonly value="<%= LocalDate.now()%>">
                </label>

                <label>Основание:
                    <input type="text" name="cause" required placeholder="Контактное лицо">
                </label>

                <label>ГрузоОтправитель:
                    <select id="idSupplier" name="idSupplier" required>
                        <c:forEach var="supplier" items="${supplierList}">
                            <option value="${supplier.id}">${supplier.name}</option>
                        </c:forEach>
                    </select>
                </label>

                <label>ГрузоОтправитель:
                    <select id="idReceiver" name="idReceiver" required>
                        <c:forEach var="receiver" items="${receiverList}">
                            <option value="${receiver.id}">${receiver.name}</option>
                        </c:forEach>
                    </select>
                </label>

                <input name="action" type="hidden" value="add">
                <button>Сохранить</button>
            </form>
        </div>
    </c:when>

    <c:when test="${param.action==\"edit\"}">
        <h2>Редактирование в накладной</h2>

        <div class="container">
            <form name="edit" method="post" action="invoice">
                <input name="idInvoice" type="hidden" value="${param.idInvoice}">
                <input name="idSupplier" type="hidden" value="${param.idSupplier}">
                <input name="idReceiver" type="hidden" value="${param.idReceiver}">

                <label>Номер:
                    <input type="text" name="number" readonly value="${param.number}">
                </label>

                <label>Дата:
                    <input type="text" name="date" readonly value="${param.date}">
                </label>

                <label>Основание:
                    <input type="text" name="cause" value="${param.cause}">
                </label>

                <label>ГрузоОтправитель:
                    <input type="text" name="supplierName" value="${param.supplierName}">
                </label>

                <label>ГрузоОтправитель:
                    <input type="text" name="receiverName" value="${param.receiverName}">
                </label>

                <input name="action" type="hidden" value="edit">
                <button>Сохранить</button>
            </form>
        </div>
    </c:when>

    <c:when test="${param.action==\"delete\"}">
        <h2>Удаление данных о получателе товара</h2>

        <div class="container">
            <form name="delete" method="post" action="branch">
                <input name="idBranch" type="hidden" value="${param.idBranch}">

                <label for="name2">Название организации:
                    <input type="text" id="name2" name="name" disabled value="${param.name}">
                </label>

                <label for="address2">Адрес:
                    <input type="text" id="address2" name="address" disabled value="${param.address}">
                </label>

                <label for="contact2">Контактное лицо:
                    <input type="text" id="contact2" name="contact" disabled value="${param.contact}">
                </label>

                <label for="phone2">Телефон:
                    <input type="text" id="phone2" name="phone" disabled value="${param.phone}">
                </label>

                <button name="action" type="submit" value="delete">Удалить</button>
                <button name="action" type="submit">Не удалять</button>
            </form>
        </div>
    </c:when>
</c:choose>
<div class="footer">
    <h2>it.academy</h2>
</div>
</body>
</html>
