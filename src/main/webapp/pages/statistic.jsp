<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Statistic</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins">
        <link rel="stylesheet" href="<c:url value="/includes/css/FontAwesome5.css" />">
        <link rel="stylesheet" href="<c:url value="/includes/css/bootstrap.min.css" />">
        <link rel="stylesheet" href="<c:url value="/includes/css/bootstrap-table/bootstrap-table.min.css" />">
        <link
                rel="stylesheet"
                href="<c:url value="/includes/css/bootstrap-table/extensions/page-jump-to/bootstrap-table-page-jump-to.min.css" />"
        />
        <link rel="stylesheet" href="<c:url value="/includes/css/style.css" />">
    </head>
    <body class="d-flex flex-column">
        <script src="<c:url value="/includes/js/jquery.min.js" />"></script>
        <script src="<c:url value="/includes/js/bootstrap.bundle.min.js" />"></script>
        <script src="<c:url value="/includes/js/bootstrap-table/bootstrap-table.min.js" />"></script>
        <script src="<c:url value="/includes/js/bootstrap-table/locale/bootstrap-table-locale-all.min.js" />"></script>
        <script src="<c:url value="/includes/js/bootstrap-table/extensions/page-jump-to/bootstrap-table-page-jump-to.min.js" />"></script>

        <%@ include file="../includes/header.jsp"%>
        <div class="container flex-grow-1">
            <div class="jumbotron">
                <div class="row">
                    <div class="col">
                        <h2 class="text-center">Statistic</h2>
                    </div>
                    <div class="w-100"></div>

                    <div class="col" style="position: relative">
                        <table
                                id="table"
                                class="table table-bordered table-hover table-striped"
                                data-toggle="table"
                                data-locale="en-US"
                                data-show-columns="true"
                                data-show-columns-toggle-all="true"
                                data-search="true"
                                data-visible-search="true"
                                data-show-search-clear-button="true"
                                data-search-on-enter-key="true"
                                data-pagination="true"
                                data-page-list="[10, 25, 50, 100, all]"
                                data-show-jump-to="true"
                        >
                            <thead>
                                <tr>
                                    <th data-field="customerId" data-sortable="true" data-switchable="false">Customer Id</th>
                                    <th data-field="customerName" data-sortable="true" >Customer Name</th>
                                    <th data-field="deviceId" data-sortable="true" data-switchable="false">Device Id</th>
                                    <th data-field="position" data-sortable="true">Position</th>
                                    <th data-field="status" data-sortable="true">Status</th>
                                    <th data-field="startDateUsage" data-sortable="true">Start date usage</th>
                                    <th data-field="startTimeUsage" data-sortable="true">Start time usage</th>
                                    <th data-field="duration" data-sortable="true">Duration</th>
                                    <th data-field="serviceId" data-sortable="true" data-switchable="false">Service Id</th>
                                    <th data-field="dateUsage" data-sortable="true">Date usage</th>
                                    <th data-field="timeUsage" data-sortable="true">Time usage</th>
                                    <th data-field="amount" data-sortable="true">Amount</th>
                                    <th data-field="totalPrice" data-sortable="true">Total price</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${pageContext.request.getAttribute('items')}" var="item">
                                <tr>
                                    <td>${item[0]}</td>
                                    <td>${item[1]}</td>
                                    <td>${item[2]}</td>
                                    <td>${item[3]}</td>
                                    <td>${item[4]}</td>
                                    <td>${item[5]}</td>
                                    <td>${item[6]}</td>
                                    <td>${item[7]}</td>
                                    <td>${item[8]}</td>
                                    <td>${item[9]}</td>
                                    <td>${item[10]}</td>
                                    <td>${item[11]}</td>
                                    <td>${item[12]}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <%@ include file="../includes/footer.jsp"%>
    </body>
</html>