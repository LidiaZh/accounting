<%--
  Created by IntelliJ IDEA.
  User: lidia
  Date: 5/29/22
  Time: 9:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Departments</title>
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
    <a href="branch">Филиалы</a>
    <a class="active" href="department_branch">Отделы</a>
    <a href="rPerson">МОЛ</a>
    <a href="about">Контакты</a>
</div>

<c:choose>
    <c:when test="${param.action==\"add\"}">
        <h2>Добавить Отдел</h2>

        <div class="container">
            <form name="add" method="post" action="department">
                <input name="idBranch" type="hidden" value="${param.id}">

                <label>Название отдела:
                    <input type="text" name="name" required placeholder="Название">
                </label>

                <input name="action" type="hidden" value="add">
                <button>Сохранить</button>
            </form>
        </div>
    </c:when>

    <c:when test="${param.action==\"edit\"}">
        <h2>Редактирование данных об отделе</h2>

        <div class="container">
            <form name="edit" method="post" action="department">
                <input name="idDepartment" type="hidden" value="${param.idDepartment}">

                <label>Название отдела:
                    <input type="text" name="name" value="${param.name}">
                </label>

                <input name="action" type="hidden" value="edit">
                <button>Сохранить</button>
            </form>
        </div>
    </c:when>

    <c:when test="${param.action==\"delete\"}">
        <h2>Удаление отдела</h2>

        <div class="container">
            <form name="delete" method="post" action="department">
                <input name="idDepartment" type="hidden" value="${param.idDepartment}">

                <label>Название отдела:
                    <input type="text" name="name" disabled value="${param.name}">
                </label>

                <button name="action" type="submit" value="delete">Удалить</button>
                <button name="action" type="submit">Не удалять</button>
            </form>
        </div>
    </c:when>

    <c:when test="${action==\"addInBranch\"}">
        <h2>Добавить отдел в состав филиала</h2>
        <div class="container">
            <form name="addInBranch" method="post" action="department_branch">
                <input name="idBranch" type="hidden" value="${idBranch}">

                <label>Название отдела:
                    <select name="idDepartment" required>
                        <option></option>
                        <c:forEach var="department" items="${listOfAllDepartments}">
                            <option value="${department.id}">${department.name}</option>
                        </c:forEach>
                    </select>
                </label>

                <input name="action" type="hidden" value="addInBranch">
                <button>Сохранить</button>
            </form>
        </div>
    </c:when>

    <c:when test="${param.action==\"deleteFromBranch\"}">
        <h2>Удаление отдела из состава филиала</h2>

        <div class="container">
            <form name="deleteFromBranch" method="post" action="department_branch">
                <input name="idBranch" type="hidden" value="${param.idBranch}">
                <input name="idDepartment" type="hidden" value="${param.idDepartment}">
                <button name="action" type="submit" value="deleteFromBranch">Удалить</button>
            </form>
        </div>
    </c:when>
</c:choose>
<%--<div class="footer">--%>
<%--    <h2>it.academy</h2>--%>
<%--</div>--%>
</body>
</html>
