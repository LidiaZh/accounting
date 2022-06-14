<%--
  Created by IntelliJ IDEA.
  User: Lidia
  Date: 01.06.2022
  Time: 14:05
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
        <link href="css/button.css" rel="stylesheet">
    </head>

    <body>
<div class="main">

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
    <div class="content">
        <p>
            <c:url value="/department-form.jsp" var="departmentAddUrl">
                <c:param name="action" value="add"/>
            </c:url>
            <button class="btn success"><a href="${departmentAddUrl}">Добавить Отдел</a></button>
        </p>
        <br><br>
        <p>
            <c:url value="department" var="departmentListUrl">
                <c:param name="action" value="showDepartment"/>
            </c:url>
            <button class="btn success"><a href="${departmentListUrl}">Список Отделов</a></button>
        </p>
        <br>
        <c:forEach var="branch" items="${listOfAllBranchDto}" varStatus="status">
            <table id="customers">
                <caption><h1><c:out value="${branch.nameBranch}"/></h1></caption>
                <thead>
                <tr>
                    <th>№</th>
                    <th>Отделы</th>
                    <th>Действия</th>
                </tr>
                </thead>
                <tbody>
                <% int i = 1;%>
                <c:forEach var="dep" items="${branch.department}">
                    <tr>
                        <td><%= i++%>
                        </td>
                        <td><c:out value="${dep.name}"/></td>
                        <td>
                            <form name="deleteFromBranch" method="post" action="department_branch">
                                <input name="idDepartment" type="hidden" value="${dep.id}">
                                <input name="idBranch" type="hidden" value="${branch.id}">
                                <button class="btn success" type="submit" name="action" value="deleteFromBranch">Удалить</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <br>
            <form name="add" method="post" action="department_branch">
                <input name="action" type="hidden" value="getDepartmentForAction">
                <input name="idBranch" type="hidden" value="${branch.id}">
                <button class="btn success">Добавить для филиала</button>
            </form>

        </c:forEach>
    </div>

<%--<div class="footer">--%>
<%--    <h2>it.academy</h2>--%>
<%--</div>--%>
</div>
</body>
</html>

