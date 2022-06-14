<%--
  Created by IntelliJ IDEA.
  User: lidia
  Date: 5/30/22
  Time: 12:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>rPerson</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/header.css" rel="stylesheet">
    <link href="css/footer.css" rel="stylesheet">
    <link href="css/table.css" rel="stylesheet">
    <link href="css/form.css" rel="stylesheet">
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
    <a href="supplier">Поставщики</a>
    <a href="branch">Филиалы</a>
    <a href="department_branch">Отделы</a>
    <a class="active" href="rPerson">МОЛ</a>
    <a href="about">Контакты</a>
</div>

<c:choose>
    <c:when test="${action==\"add\"}">
        <h2>Добавить МОЛ</h2>
        <div class="container">
            <form name="add" method="post" action="rPerson">
                <label>Фамилия:
                    <input type="text" name="surname" required placeholder="Фамилия">
                </label>

                <label>
                    Имя:
                    <input type="text" name="name" required placeholder="Имя">
                </label>

                <label>Филиал:
                    <select name="idBranch" required>
                        <option></option>
                        <c:forEach var="branch" items="${listOfAllBranchDto}">
                            <option value="${branch.id}">${branch.nameBranch}</option>
                        </c:forEach>
                    </select>
                </label>

                <label>Отдел:
                    <select name="idDepartment" required>
                        <option></option>
                        <c:forEach var="department" items="${listOfAllDepartments}">
                            <option value="${department.id}">${department.name}</option>
                        </c:forEach>
                    </select>
                </label>

                <input name="action" type="hidden" value="add">
                <button>Сохранить</button>
            </form>
        </div>
    </c:when>

    <c:when test="${param.action==\"edit\"}">
        <h2>Редактирование данных о МОЛ</h2>
        <div class="container">
            <form name="edit" method="post" action="rPerson">
                <input name="idRPerson" type="hidden" value="${param.idRPerson}">

                <label>Фамилия:
                    <input type="text" name="surname" value="${param.surname}">
                </label>

                <label>Имя:
                    <input type="text" name="name" value="${param.name}">
                </label>

                <label>Филиал:
                    <input type="text" name="branchName" disabled value="${param.branchName}">
                </label>

                <label>Отдел:
                    <input type="text" name="departmentName" disabled
                           value="${param.departmentName}">
                </label>

                <input name="action" type="hidden" value="edit">
                <button>Сохранить</button>
            </form>
        </div>
    </c:when>

    <c:when test="${param.action==\"delete\"}">
        <h2>Удаление данных о МОЛ</h2>

        <div class="container">
            <form name="delete" method="post" action="rPerson">
                <input name="idRPerson" type="hidden" value="${param.idRPerson}">

                <label>Фамилия:
                    <input type="text" name="surname" disabled value="${param.surname}">
                </label>

                <label>Имя:
                    <input type="text" name="name" disabled value="${param.name}">
                </label>

                <button name="action" type="submit" value="delete">Удалить</button>
                <button name="action" type="submit">Не удалять</button>
            </form>
        </div>
    </c:when>

    <c:when test="${action==\"editDepartmentAndBranch\"}">
        <h2>Перевод в другое подразделение</h2>
        <div class="container">
            <form name="add" method="post" action="rPerson">
                <input name="idRPerson" type="hidden" value="${rPersonDto.id}">

                <label>Фамилия:
                    <input type="text" name="surname" disabled value="${rPersonDto.surname}">
                </label>

                <label>Имя:
                    <input type="text" name="name" disabled value="${rPersonDto.name}">
                </label>

                <label>Филиал:
                    <select name="idBranch" required>
                        <option value="${rPersonDto.branch.id}">${rPersonDto.branch.name}</option>
                        <c:forEach var="branch" items="${listOfAllBranchDto}">
                            <option value="${branch.id}">${branch.nameBranch}</option>
                        </c:forEach>
                    </select>
                </label>

                <label>Отдел:
                    <select name="idDepartment" required>
                        <option value="${rPersonDto.department.id}">${rPersonDto.department.name}</option>
                        <c:forEach var="department" items="${listOfAllDepartments}">
                            <option value="${department.id}">${department.name}</option>
                        </c:forEach>
                    </select>
                </label>

                <input name="action" type="hidden" value="editDepartmentAndBranch">
                <button>Сохранить</button>
            </form>
        </div>
    </c:when>
</c:choose>

<%--<div class="footer">--%>
<%--    <h2>it.academy</h2>--%>
<%--</div>--%>
</body>
</html>