<%--
  Created by IntelliJ IDEA.
  User: Lidia
  Date: 03.06.2022
  Time: 22:18
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
    <link href="css/form.css" rel="stylesheet">

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

<c:choose>
    <c:when test="${param.action==\"add\"}">
        <h2>Добавить Оборудование</h2>

        <div class="container">
            <form name="add" method="post" action="equipmentDetail">
                <label>Модель оборудования:
                    <input type="text" name="name" required placeholder="Монитор Samsung S24R350FZI">
                </label>

                <label>Описание:
                    <input type="text" name="desc" required placeholder="23.8'', 16:9, 1920x1080, IPS, 75 Гц, AMD FreeSync, интерфейсы HDMI+D-Sub (VGA)">
                </label>

                <label>Срок использования, лет:
                    <input type="text" name="termOfUse" required placeholder="5">
                </label>

                <label>Производитель:
                    <input type="text" name="producer" required placeholder="Samsung">
                </label>

                <input name="action" type="hidden" value="add">
                <button>Сохранить</button>
            </form>
        </div>
    </c:when>

    <c:when test="${param.action==\"edit\"}">
        <h2>Редактирование данных об оборудовании</h2>

        <div class="container">
            <form name="edit" method="post" action="equipmentDetail">
                <input name="idEqDetail" type="hidden" value="${param.idEqDetail}">

                <label>Модель оборудования:
                    <input type="text" name="name" value="${param.name}">
                </label>

                <label>Описание:
                    <input type="text" name="desc" value="${param.desc}">
                </label>

                <label>Срок использования, лет:
                    <input type="text" name="termOfUse" value="${param.termOfUse}">
                </label>

                <label>Производитель:
                    <input type="text" name="producer" value="${param.producer}">
                </label>

                <input name="action" type="hidden" value="edit">
                <button>Сохранить</button>
            </form>
        </div>
    </c:when>

    <c:when test="${param.action==\"delete\"}">
        <h2>Удаление данных об оборудовании</h2>

        <div class="container">
            <form name="delete" method="post" action="equipmentDetail">
                <input name="idEqDetail" type="hidden" value="${param.idEqDetail}">

                <label>Модель оборудования:
                    <input type="text" name="name" disabled value="${param.name}">
                </label>

                <label>Описание:
                    <input type="text" name="desc" disabled value="${param.desc}">
                </label>

                <label>Срок использования, лет:
                    <input type="text" name="termOfUse" disabled value="${param.termOfUse}">
                </label>

                <label>Производитель:
                    <input type="text" name="producer" disabled value="${param.producer}">
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
