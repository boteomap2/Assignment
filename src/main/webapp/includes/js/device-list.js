let $table = $("#table");

$(function () {
  $table.bootstrapTable({
    locale: "en-US",
    data: data,
    columns: [
      {
        title: "Device Id",
        field: "deviceId",
        sortable: true,
        switchable: false,
      },
      {
        title: "Position",
        field: "position",
        sortable: true,
      },
      {
        title: "Status",
        field: "status",
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
    $("#updateDeviceId").val(row.deviceId);
    $("#updateDevicePosition").val(row.position);
    $("#updateDeviceStatus").val(row.status);
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
    deviceId: $("#updateDeviceId").val(),
    position: $("#updateDevicePosition").val(),
    status: $("#updateDeviceStatus").val(),
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
    position: $("#addDevicePosition").val(),
    status: $("#addDeviceStatus").val(),
  };
  $.ajax({
    url: "list/add",
    type: "POST",
    data: newRow,
  }).done(function(idCallback) {
    if (idCallback) {
      newRow.deviceId = idCallback;
      $table.bootstrapTable("append", newRow);
      $("#addModal").modal("hide");
      $("#addModalForm").trigger("reset");
    } else {
      alert("Something Happened on Our End");
    }
  });
});
