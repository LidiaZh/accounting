<%--
  Created by IntelliJ IDEA.
  User: Lidia
  Date: 03.06.2022
  Time: 23:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
    <c:when test="${requestScope.action==\"add\"}">
        <h2>Добавить Оборудование</h2>

        <div class="container">
            <form name="add" method="post" action="equipment">

                <label>Инвентарный номер:
                    <input type="text" name="account_number" required placeholder="1203215">
                </label>

                <label>Серийный номер:
                    <input type="text" name="s_number" required placeholder="SA321S1AA">
                </label>

                <label>Накладная:
                    <select name="idInvoice" required>
                        <option></option>
                        <c:forEach var="invoice" items="${listOfAllInvoices}">
                            <option value="${invoice.id}">${invoice.number} ${invoice.date}</option>
                        </c:forEach>
                    </select>
                </label>

                <label>Модель оборудования:
                    <select name="idEqDetail" required>
                        <option></option>
                        <c:forEach var="eqDetail" items="${listOfEqDetails}">
                            <option value="${eqDetail.id}">${eqDetail.name}</option>
                        </c:forEach>
                    </select>
                </label>

                <label>Статус:
                    <select name="status" required>
                        <option value=""></option>
                        <option value="склад">склад</option>
                        <option value="оформление">оформление</option>
                        <option value="эксплуатация">эксплуатация</option>
                        <option value="ремонт">ремонт</option>
                        <option value="списание">списание</option>
                        <option value="списано">списано</option>
                    </select>
                </label>

                <label>Цена, бел.руб.:
                    <input type="text" name="price" required placeholder="1000.25">
                </label>

                <input name="action" type="hidden" value="add">
                <button>Сохранить</button>
            </form>
        </div>
    </c:when>

    <c:when test="${requestScope.action==\"edit\"}">
        <h2>Редактирование данных об оборудовании</h2>

        <div class="container">
            <form name="edit" method="post" action="equipment">
                <input name="idEquipment" type="hidden" value="${equipment.id}">

                <label>Инвентарный номер:
                    <input type="text" name="account_number" required value="${equipment.account_number}">
                </label>

                <label>Серийный номер:
                    <input type="text" name="s_number" required value="${equipment.s_number}">
                </label>

                <label>Накладная:
                    <input name="idInvoiceCurrent" type="hidden" required value="${invoiceCurrent.id}">
                    <select name="idInvoice" required>
                        <option value="${invoiceCurrent.id}">${invoiceCurrent.number} ${invoiceCurrent.date}</option>
                        <c:forEach var="invoice" items="${listOfAllInvoices}">
                            <option value="${invoice.id}">${invoice.number} ${invoice.date}</option>
                        </c:forEach>
                    </select>
                </label>

                <label>Модель оборудования:
                    <select name="idEqDetail" required>
                        <option value="${equipment.equipmentDetail.id}">${equipment.equipmentDetail.name}</option>
                        <c:forEach var="eqDetail" items="${listOfEqDetails}">
                            <option value="${eqDetail.id}">${eqDetail.name}</option>
                        </c:forEach>
                    </select>
                </label>

                <label>Статус (${equipment.status}):
                    <select name="status" required>
                        <option value="${equipment.status}">${equipment.status}</option>
                        <option value="склад">склад</option>
                        <option value="оформление">оформление</option>
                        <option value="эксплуатация">эксплуатация</option>
                        <option value="ремонт">ремонт</option>
                        <option value="списание">списание</option>
                        <option value="списано">списано</option>
                    </select>
                </label>

                <label>Начало эксплуатации:
                    <br><input type="date" name="start_date" value="${equipment.start_date}"><br><br>
                </label>

                <label>Цена, бел.руб.:
                    <input type="text" name="price" required value="${equipment.price}">
                </label>

                <label>МОЛ (${equipment.responsiblePerson.surname} ${equipment.responsiblePerson.name}):
                    <select name="idRPerson" required>
                        <option value="${equipment.responsiblePerson.id}">${equipment.responsiblePerson.surname} ${equipment.responsiblePerson.name}</option>
                        <c:forEach var="resPerson" items="${listOfResPersons}">
                            <option value="${resPerson.id}">${resPerson.surname} ${resPerson.name}</option>
                        </c:forEach>
                    </select>
                </label>

                <input name="action" type="hidden" value="edit">
                <button>Сохранить</button>
            </form>
        </div>
    </c:when>

    <c:when test="${param.action==\"delete\"}">
        <h2>Удаление данных об оборудовании</h2>

        <div class="container">
            <form name="delete" method="post" action="equipment">
                <input name="idEquipment" type="hidden" value="${param.idEquipment}">

                <label>Инвентарный номер:
                    <input type="text" name="account_number" disabled value="${param.account_number}">
                </label>

                <label>Серийный номер:
                    <input type="text" name="s_number" disabled value="${param.s_number}">
                </label>

                <label>Накладная:
                    <input type="text" name="invoiceNumber" disabled value="${param.invoiceNumber}">
                </label>

                <label>Модель оборудования:
                    <input type="text" name="equipmentName" disabled value="${param.equipmentName}">
                </label>

                <label>Статус:
                    <input type="text" name="status" disabled value="${param.status}">
                </label>

                <label>Начало эксплуатации:
                    <input type="text" name="start_date" disabled value="${param.start_date}">
                </label>

                <label>Цена, бел.руб.:
                    <input type="text" name="price" disabled value="${param.price}">
                </label>

                <label>МОЛ:
                    <input type="text" name="resPersonSurname" disabled value="${param.resPersonSurname}">
                    <input type="text" name="resPersonName" disabled value="${param.resPersonName}">
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
