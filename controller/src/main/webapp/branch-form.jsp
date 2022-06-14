<%--
  Created by IntelliJ IDEA.
  User: lidia
  Date: 5/29/22
  Time: 8:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Branches</title>
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
    <a href="invoice">Накладные</a>
    <a href="receiver">Получатели</a>
    <a href="supplier">Поставщики</a>
    <a class="active" href="branch">Филиалы</a>
    <a href="department_branch">Отделы</a>
    <a href="rPerson">МОЛ</a>
    <a href="about">Контакты</a>
</div>

<c:choose>
    <c:when test="${param.action==\"add\"}">
        <h2>Добавить Филиал</h2>

        <div class="container">
            <form name="add" method="post" action="branch">
                <label>Филиал:
                    <input type="text" name="name" required placeholder="Название">
                </label>

                <label>Адрес:
                    <input type="text" name="address" required placeholder="Адрес">
                </label>

                <label>Контактное лицо:
                    <input type="text" name="contact" required placeholder="Контактное лицо">
                </label>

                <label>Телефон:
                    <input type="text" name="phone" required placeholder="Телефон">
                </label>

                <input name="action" type="hidden" value="add">
                <button>Сохранить</button>
            </form>
        </div>
    </c:when>

    <c:when test="${param.action==\"edit\"}">
        <h2>Редактирование данных о филиале</h2>

        <div class="container">
            <form name="edit" method="post" action="branch">
                <input name="idBranch" type="hidden" value="${param.idBranch}">

                <label>Название организации:
                    <input type="text" name="name" value="${param.name}">
                </label>

                <label>Адрес:
                    <input type="text" name="address" value="${param.address}">
                </label>

                <label>Контактное лицо:
                    <input type="text" name="contact" value="${param.contact}">
                </label>

                <label>Телефон:
                    <input type="text" name="phone" value="${param.phone}">
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

                <label>Название организации:
                    <input type="text" name="name" disabled value="${param.name}">
                </label>

                <label>Адрес:
                    <input type="text" name="address" disabled value="${param.address}">
                </label>

                <label>Контактное лицо:
                    <input type="text" name="contact" disabled value="${param.contact}">
                </label>

                <label>Телефон:
                    <input type="text" name="phone" disabled value="${param.phone}">
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

