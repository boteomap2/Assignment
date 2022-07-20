<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins">
    <link rel="stylesheet" href="<c:url value="/includes/css/FontAwesome5.css" />">
    <link rel="stylesheet" href="<c:url value="/includes/css/bootstrap.min.css" />">
    <link rel="stylesheet" href="<c:url value="/includes/css/bootstrap-table/bootstrap-table.min.css" />">
    <link rel="stylesheet" href="<c:url value="/includes/css/style.css" />">
    <title>Admin dashboard</title>
  </head>
  <body class="d-flex flex-column">
    <script src="<c:url value="/includes/js/jquery.min.js" />"></script>
    <script src="<c:url value="/includes/js/bootstrap.bundle.min.js" />"></script>
    <script src="<c:url value="/includes/js/bootstrap-table/bootstrap-table.min.js" />"></script>
    <script src="<c:url value="/includes/js/bootstrap-table/locale/bootstrap-table-locale-all.min.js" />"></script>

    <%@ include file="../includes/header.jsp"%>

    <div class="container flex-grow-1">
      <div class="row flex-column h-75">
        <div class="col d-flex flex-grow-1 justify-content-center align-items-center">
          <h1>Admin Dashboard</h1>
        </div>
      </div>
    </div>

    <%@ include file="../includes/footer.jsp"%>
  </body>
</html>