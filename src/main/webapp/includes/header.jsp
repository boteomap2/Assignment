<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <nav id="navbar" class="navbar navbar-expand-md navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/home">ADMIN</a>
            <button
                    class="navbar-toggler d-lg-none"
                    type="button"
                    data-toggle="collapse"
                    data-target="#collapsibleNavId"
                    aria-controls="collapsibleNavId"
                    aria-expanded="false"
                    aria-label="Toggle navigation"
            >
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="collapsibleNavId">
                <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/device/list">Máy</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/service/list">Dịch Vụ</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/customer/list">Khách Hàng</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a
                                class="nav-link dropdown-toggle"
                                href="#"
                                id="register"
                                data-toggle="dropdown"
                                aria-haspopup="true"
                                aria-expanded="false"
                        >Đăng Ký</a
                        >
                        <div class="dropdown-menu" aria-labelledby="register">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/register/device">Đăng ký sử dụng máy</a>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/register/service">Đăng ký sử dụng dịch vụ</a>
                        </div>
                    </li>
                </ul>
                <button type="button" class="btn btn-outline-success">
                    <a href="${pageContext.request.contextPath}/statistic">Thống kê</a>
                </button>
            </div>
        </div>
    </nav>
</header>
