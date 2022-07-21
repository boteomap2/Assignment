toastr.options = {
  "closeButton": false,
  "debug": false,
  "newestOnTop": true,
  "progressBar": false,
  "positionClass": "toast-top-right",
  "preventDuplicates": false,
  "onclick": null,
  "showDuration": "300",
  "hideDuration": "1000",
  "timeOut": "4000",
  "extendedTimeOut": "0",
  "showEasing": "swing",
  "hideEasing": "linear",
  "showMethod": "fadeIn",
  "hideMethod": "fadeOut"
}

let $table = $("#table");

$(function () {
  $table.bootstrapTable({
    locale: "en-US",
    data: data,
    columns: [
      {
        title: "Service Id",
        field: "serviceId",
        sortable: true,
        switchable: false,
      },
      {
        title: "Name",
        field: "name",
        sortable: true,
      },
      {
        title: "Unit",
        field: "unit",
        sortable: true,
      },
      {
        title: "Price",
        field: "price",
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
    $("#updateServiceId").val(row.serviceId);
    $("#updateServiceName").val(row.name);
    $("#updateServiceUnit").val(row.unit);
    $("#updateServicePrice").val(row.price);
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
  if ($("#updateModalForm").valid()) {
    let updatedRow = {
      serviceId: $("#updateServiceId").val(),
      name: $("#updateServiceName").val(),
      unit: $("#updateServiceUnit").val(),
      price: $("#updateServicePrice").val(),
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
        toastr.success("Update Service Successfully")
      } else {
        toastr.error("Something Happened on Our End");
      }
    });
  }
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
      toastr.success("Delete Service Successfully")
    } else {
      toastr.error("Something Happened on Our End");
    }
  });
});


// Add new Device
$("#newBtn").on("click", function () {
  $("#addModal").modal("show");
});

$("#addModalBtn").on("click", function () {
  if ($("#addModalForm").valid()) {
    let newRow = {
      name: $("#addServiceName").val(),
      unit: $("#addServiceUnit").val(),
      price: $("#addServicePrice").val(),
    };
    $.ajax({
      url: "list/add",
      type: "POST",
      data: newRow,
    }).done(function(idCallback) {
      if (idCallback) {
        newRow.serviceId = idCallback;
        $table.bootstrapTable("append", newRow);
        $("#addModal").modal("hide");
        $("#addModalForm").trigger("reset");
        toastr.success("Create New Service Successfully")
      } else {
        toastr.error("Something Happened on Our End");
      }
    });
  }
});
