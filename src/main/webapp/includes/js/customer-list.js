let $table = $("#table");

$(function () {
  $table.bootstrapTable({
    locale: "en-US",
    data: data,
    columns: [
      {
        title: "Customer Id",
        field: "customerId",
        sortable: true,
        switchable: false,
      },
      {
        title: "Name",
        field: "name",
        sortable: true,
      },
      {
        title: "Address",
        field: "address",
        sortable: true,
      },
      {
        title: "Phone",
        field: "phone",
        sortable: true,
      },
      {
        title: "Email",
        field: "email",
        sortable: true,
      },
      {
        field: "operate",
        title: "Operation",
        align: "center",
        clickToSelect: false,
        events: window.operateEvents,
        formatter: operateFormatter,
      },
    ],
  });
});

function operateFormatter(value, row, index) {
  return [
    '<a class="update mr-2" href="javascript:void(0)" title="Like">',
    '<i class="far fa-edit fa-2x fa-fw"></i>',
    "</a>  ",
    '<a class="remove" href="javascript:void(0)" title="Remove">',
    '<i class="far fa-trash-alt fa-2x fa-fw"></i>',
    "</a>",
  ].join("");
}

let myIndex = 0;
let myRow = {};
window.operateEvents = {
  "click .update": function (e, value, row, index) {
    $("#updateCustomerId").val(row.customerId);
    $("#updateCustomerName").val(row.name);
    $("#updateCustomerAddress").val(row.address);
    $("#updateCustomerPhone").val(row.phone);
    $("#updateCustomerEmail").val(row.email);
    $("#updateModal").modal("show");
    myIndex = index;
  },
  "click .remove": function (e, value, row, index) {
    $("#deleteModal").modal("show");
    myIndex = index;
    myRow = row;
  },
};


// Update device
$("#updateModalBtn").on("click", function () {
  let updatedRow = {
    customerId: $("#updateCustomerId").val(),
    name: $("#updateCustomerName").val(),
    address: $("#updateCustomerAddress").val(),
    phone: $("#updateCustomerPhone").val(),
    email: $("#updateCustomerEmail").val(),
  };
  $.ajax({
    url: "list/update",
    type: "POST",
    data: updatedRow,
  }).done(function(result) {
    if (result) {
      $("#updateModal").modal("hide");
      $table.bootstrapTable("updateRow", {
        index: myIndex,
        row: updatedRow,
      });
    } else {
      alert("Something Happened on Our End");
    }
  });
});


// Delete device
$("#deleteModalBtn").on("click", function () {
  $.ajax({
    url: "list/delete",
    type: "POST",
    data: myRow,
  }).done(function(result) {
    if (result) {
      $table.bootstrapTable("remove", {
        field: "$index",
        values: [myIndex],
      });
      $("#deleteModal").modal("hide");
    } else {
      alert("Something Happened on Our End");
    }
  });
});


// Add new Device
$("#newBtn").on("click", function () {
  $("#addModal").modal("show");
});

$("#addModalBtn").on("click", function () {
  let newRow = {
    name: $("#addCustomerName").val(),
    address: $("#addCustomerAddress").val(),
    phone: $("#addCustomerPhone").val(),
    email: $("#addCustomerEmail").val(),
  };
  $.ajax({
    url: "list/add",
    type: "POST",
    data: newRow,
  }).done(function(idCallback) {
    if (idCallback) {
      newRow.customerId = idCallback;
      $table.bootstrapTable("append", newRow);
      $("#addModal").modal("hide");
      $("#addModalForm").trigger("reset");
    } else {
      alert("Something Happened on Our End");
    }
  });
});
