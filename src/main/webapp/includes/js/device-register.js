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

$(document).ready(function () {
  $("#submitBtn").on("click", function () {
    if ($("#deviceRegisterForm").valid()) {
      let data = {
        customerId: $("#customerId").val(),
        deviceId: $("#deviceId").val(),
        startDateUsage: $("#startDateUsage").val(),
        startTimeUsage: $("#startTimeUsage").val(),
        timeUsage: $("#timeUsage").val(),
      };
      $.ajax({
        url: "device/add",
        type: "POST",
        data: data,
      }).done(function(listDeviceJson) {
        if (listDeviceJson) {
          let listDevice = JSON.parse(listDeviceJson);
          let $deviceId = $("#deviceId");
          $deviceId.empty();
          for (let device of listDevice) {
            $deviceId.append(`<option value="${device.deviceId}">Device ${device.deviceId}</option>`)
          }
          $("#deviceRegisterForm").trigger("reset");
          toastr.success("Register Successfully")
        } else {
          alert("Something Happened on Our End");
        }
      });
    }
  });
})