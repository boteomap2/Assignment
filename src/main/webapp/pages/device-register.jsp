<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <head>
        <meta charset="UTF-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title>Customer List</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins">
        <link rel="stylesheet" href="<c:url value="/includes/css/FontAwesome5.css" />">
        <link rel="stylesheet" href="<c:url value="/includes/css/bootstrap.min.css" />">
        <link rel="stylesheet"
              href="<c:url value="/includes/css/bootstrap-table/bootstrap-table.min.css" />">
        <link
                rel="stylesheet"
                href="<c:url value="/includes/css/bootstrap-table/extensions/page-jump-to/bootstrap-table-page-jump-to.min.css" />"
        />
        <link rel="stylesheet" href="<c:url value="/includes/css/toastr/toastr.min.css" />">
        <link rel="stylesheet" href="<c:url value="/includes/css/style.css" />">
    </head>
    <body class="d-flex flex-column">
    <script src="<c:url value="/includes/js/jquery.min.js" />"></script>
    <script src="<c:url value="/includes/js/bootstrap.bundle.min.js" />"></script>
    <script src="<c:url value="/includes/js/bootstrap-table/bootstrap-table.min.js" />"></script>
    <script src="<c:url value="/includes/js/bootstrap-table/locale/bootstrap-table-locale-all.min.js" />"></script>
    <script src="<c:url value="/includes/js/bootstrap-table/extensions/page-jump-to/bootstrap-table-page-jump-to.min.js" />"></script>
    <script src="<c:url value="/includes/js/toastr/toastr.min.js" />"></script>
    <script src="<c:url value="/includes/js/jquery-validate.js" />"></script>

    <%@ include file="../includes/header.jsp" %>

    <div class="container flex-grow-1">
        <div class="row justify-content-center">
            <div class="jumbotron w-75">
                <div class="col-12">
                    <h2 class="text-center">Device Register</h2>
                </div>
                <form id="deviceRegisterForm">
                    <div class="w-100"></div>
                    <div class="col">
                        <div class="form-group">
                            <label for="customerId">Customer</label>
                            <select class="form-control" name="customerId" id="customerId">
                                <c:forEach items="${pageContext.request.getAttribute('listCustomer')}" var="customer" >
                                    <option value="${customer.customerId}">${customer.customerId} - ${customer.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="w-100"></div>
                    <div class="col">
                        <div class="form-group">
                            <label for="deviceId">Device (Only in-active)</label>
                            <select class="form-control" name="deviceId" id="deviceId">
                                <c:forEach items="${pageContext.request.getAttribute('listDeviceInactive')}" var="device" >
                                    <option value="${device.deviceId}">Device ${device.deviceId}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="w-100"></div>
                    <div class="col">
                        <div class="form-group">
                            <label for="startDateUsage">Start Date</label>
                            <input
                                    type="date"
                                    name="startDateUsage"
                                    id="startDateUsage"
                                    class="form-control"
                            />
                        </div>
                    </div>
                    <div class="w-100"></div>
                    <div class="col">
                        <div class="form-group">
                            <label for="startTimeUsage">Start Time</label>
                            <input
                                    type="time"
                                    name="startTimeUsage"
                                    id="startTimeUsage"
                                    class="form-control"
                            />
                        </div>
                    </div>
                    <div class="w-100"></div>
                    <div class="col">
                        <div class="form-group">
                            <label for="timeUsage">Duration</label>
                            <input
                                    type="number"
                                    name="timeUsage"
                                    id="timeUsage"
                                    class="form-control"
                            />
                        </div>
                    </div>
                    <div class="w-100"></div>
                    <div class="col d-flex justify-content-end">
                        <div class="form-group">
                            <button id="submitBtn" type="button" class="btn btn-primary btn-md">Register</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <%@ include file="../includes/footer.jsp" %>
    </body>
    <script>
      $("#deviceRegisterForm").validate({
        rules: {
          startDateUsage: {
            required: true,
          },
          startTimeUsage: {
            required: true,
          },
          timeUsage: {
            required: true,
            isValidInteger: true,
          },
        },
        messages: {
          startDateUsage: {
            required: "Start date usage is required",
          },
          startTimeUsage: {
            required: "Start time usage is required",
          },
          timeUsage: {
            required: "Duration is required",
          },
        },
        errorPlacement: function (error, element) {
          // for check box and radio
          if (element.attr("name") == "checkme") {
            error.appendTo("#error-mess");
          } else {
            error.insertAfter(element);
          }
        },
        onfocusout: function (element) {
          // "eager" validation
          this.element(element);
        },
        success: "valid",
      });
    </script>
    <script src="<c:url value="/includes/js/validate-method.js" />"></script>
    <script src="<c:url value="/includes/js/device-register.js" />"></script>
</html>