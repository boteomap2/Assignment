<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Customer List</title>
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
                        <h2 class="text-center">Customer List</h2>
                    </div>
                    <div class="w-100"></div>

                    <div class="col" style="position: relative">
                        <div id="toolbar">
                            <button id="newBtn" class="btn btn-secondary">
                                <i class="far fa-plus-circle fa-lg fa-fw"></i>
                                Add New
                            </button>
                        </div>
                        <table
                                id="table"
                                class="table table-bordered table-hover table-striped"
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
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <!-- Update Modal -->
        <div
                class="modal fade"
                id="updateModal"
                tabindex="-1"
                role="dialog"
                aria-labelledby="updateModalTitleId"
                aria-hidden="true"
        >
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Update row</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="updateModalForm" action="" method="post">
                            <div class="form-group">
                                <label for="updateCustomerId">Customer ID</label>
                                <input
                                        type="text"
                                        name="updateCustomerId"
                                        id="updateCustomerId"
                                        class="form-control"
                                        disabled
                                />
                            </div>
                            <div class="form-group">
                                <label for="updateCustomerName">Name</label>
                                <input
                                        type="text"
                                        name="updateCustomerName"
                                        id="updateCustomerName"
                                        class="form-control"
                                />
                            </div>
                            <div class="form-group">
                                <label for="updateCustomerAddress">Address</label>
                                <input
                                        type="text"
                                        name="updateCustomerAddress"
                                        id="updateCustomerAddress"
                                        class="form-control"
                                />
                            </div>
                            <div class="form-group">
                                <label for="updateCustomerPhone">Phone</label>
                                <input
                                        type="text"
                                        name="updateCustomerPhone"
                                        id="updateCustomerPhone"
                                        class="form-control"
                                />
                            </div>
                            <div class="form-group">
                                <label for="updateCustomerEmail">Email</label>
                                <input
                                        type="text"
                                        name="updateCustomerEmail"
                                        id="updateCustomerEmail"
                                        class="form-control"
                                />
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">
                            Close
                        </button>
                        <button id="updateModalBtn" type="button" class="btn btn-primary">
                            Save
                        </button>
                    </div>
                </div>
            </div>
        </div>

        <!-- delete Modal -->
        <div
                class="modal fade"
                id="deleteModal"
                tabindex="-1"
                role="dialog"
                aria-labelledby="deleteModalTitle"
                aria-hidden="true"
        >
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Delete Confirmation</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">Are you sure to delete this row?</div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">
                            Close
                        </button>
                        <button id="deleteModalBtn" type="button" class="btn btn-danger">
                            Delete
                        </button>
                    </div>
                </div>
            </div>
        </div>

        <!-- create new Modal -->
        <div
                class="modal fade"
                id="addModal"
                tabindex="-1"
                role="dialog"
                aria-labelledby="addModalTitle"
                aria-hidden="true"
        >
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Add</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="addModalForm">
                            <div class="form-group">
                                <label for="addCustomerName">Name</label>
                                <input
                                        type="text"
                                        name="addCustomerName"
                                        id="addCustomerName"
                                        class="form-control"
                                />
                            </div>
                            <div class="form-group">
                                <label for="addCustomerAddress">Address</label>
                                <input
                                        type="text"
                                        name="addCustomerAddress"
                                        id="addCustomerAddress"
                                        class="form-control"
                                />
                            </div>
                            <div class="form-group">
                                <label for="addCustomerPhone">Phone</label>
                                <input
                                        type="text"
                                        name="addCustomerPhone"
                                        id="addCustomerPhone"
                                        class="form-control"
                                />
                            </div>
                            <div class="form-group">
                                <label for="addCustomerEmail">Email</label>
                                <input
                                        type="text"
                                        name="addCustomerEmail"
                                        id="addCustomerEmail"
                                        class="form-control"
                                />
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">
                            Close
                        </button>
                        <button id="addModalBtn" type="button" class="btn btn-danger">Add</button>
                    </div>
                </div>
            </div>
        </div>

        <%@ include file="../includes/footer.jsp"%>
    </body>
    <script>
        let data = ${pageContext.request.getAttribute("listCustomerJson")};
    </script>
    <script src="<c:url value="/includes/js/customer-list.js" />"></script>
</html>