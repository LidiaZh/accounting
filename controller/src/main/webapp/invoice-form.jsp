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
    <c:when test="${action==\"add\"}">
        <h2>Добавить Накладную</h2>

        <div class="container">
            <form name="add" method="post" action="invoice">
                <label>Номер №:
                    <input type="text" name="number" required placeholder="23545">
                </label>

                <label>Дата:
                    <br><input type="date" name="date" required><br><br>
                </label>

                <label>Основание:
                    <input type="text" name="cause" required placeholder="Договор поставки №135 от 20.05.2021">
                </label>

                <label>ГрузоОтправитель:
                    <select name="idSupplier" required>
                        <option></option>
                        <c:forEach var="supplier" items="${supplierList}">
                            <option value="${supplier.id}">${supplier.name}</option>
                        </c:forEach>
                    </select>
                </label>

                <label>ГрузоПолучатель:
                    <select name="idReceiver" required>
                        <option></option>
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

    <c:when test="${action==\"new\"}">
        <h2>Выписать Накладную</h2>
        <div class="container">
            <form name="new" method="post" action="invoice">
                <label>Номер №:
                    <input type="text" name="number" required placeholder="23545">
                </label>

                <label>Дата:
                    <input type="text" name="date" readonly value="<%= LocalDate.now()%>">
                </label>

                <label>Основание:
                    <input type="text" name="cause" required placeholder="Договор поставки №135 от 20.05.2021">
                </label>

                <label>ГрузоОтправитель:
                    <select name="idSupplier" required>
                        <option></option>
                        <c:forEach var="supplier" items="${supplierList}">
                            <option value="${supplier.id}">${supplier.name}</option>
                        </c:forEach>
                    </select>
                </label>

                <label>ГрузоПолучатель:
                    <select name="idReceiver" required>
                        <option></option>
                        <c:forEach var="receiver" items="${receiverList}">
                            <option value="${receiver.id}">${receiver.name}</option>
                        </c:forEach>
                    </select>
                </label>

                <input name="action" type="hidden" value="new">
                <button class="btn success">Далее</button>
            </form>
        </div>
    </c:when>

    <c:when test="${action==\"edit\"}">
        <h2>Редактирование Накладной</h2>

        <div class="container">
            <form name="edit" method="post" action="invoice">
                <input name="idInvoice" type="hidden" value="${invoice.id}">

                <label>Номер №:
                    <input type="text" name="number" readonly value="${invoice.number}">
                </label>

                <label>Дата:
                    <input type="text" name="date" readonly value="${invoice.date}">
                </label>

                <label>Основание:
                    <input type="text" name="cause" value="${invoice.cause}">
                </label>

                <label>ГрузоОтправитель:
                    <select name="idSupplier">
                        <option value="${invoice.supplier.id}">${invoice.supplier.name}</option>
                        <c:forEach var="supplier" items="${supplierList}">
                            <option value="${supplier.id}">${supplier.name}</option>
                        </c:forEach>
                    </select>
                </label>

                <label>ГрузоПолучатель:
                    <select name="idReceiver">
                        <option value="${invoice.receiver.id}">${invoice.receiver.name}</option>
                        <c:forEach var="receiver" items="${receiverList}">
                            <option value="${receiver.id}">${receiver.name}</option>
                        </c:forEach>
                    </select>
                </label>

                <input name="action" type="hidden" value="edit">
                <button>Сохранить</button>
            </form>
        </div>
    </c:when>

    <c:when test="${action==\"delete\"}">
        <h2>Удаление Накладной</h2>

        <div class="container">
            <form name="delete" method="post" action="invoice">
                <input name="idInvoice" type="hidden" value="${invoice.id}">

                <label>Номер №:
                    <input type="text" name="number" disabled value="${invoice.number}">
                </label>

                <label>Дата:
                    <input type="text" name="date" disabled value="${invoice.date}">
                </label>

                <label>Основание:
                    <input type="text" name="cause" disabled value="${invoice.cause}">
                </label>

                <button name="action" type="submit" value="delete">Удалить</button>
                <button name="action" type="submit">Не удалять</button>
            </form>
        </div>
    </c:when>
</c:choose>
<%--<div class="footer">--%>
<%--    <h2>it.academy</h2>--%>
<%--</div>--%>
</body>
</html>
