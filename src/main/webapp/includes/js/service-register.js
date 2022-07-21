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
    if ($("#serviceRegisterForm").valid()) {
      let data = {
        customerId: $("#customerId").val(),
        serviceId: $("#serviceId").val(),
        dateUsage: $("#dateUsage").val(),
        timeUsage: $("#timeUsage").val(),
        amount: $("#amount").val(),
      };
      $.ajax({
        url: "service/add",
        type: "POST",
        data: data,
      }).done(function(msg) {
        if (msg) {
          $("#serviceRegisterForm").trigger("reset");
          toastr.success("Register Successfully")
        } else {
          toastr.error("Something Happened on Our End")
          // alert("Something Happened on Our End");
        }
      });
    }
  });
})