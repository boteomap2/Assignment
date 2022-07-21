$.validator.addMethod("isValidCustomerCode", function (value, element) {
  return /^(KH)[0-9]{4}$/.test(value);
}, "Mã khách hàng phải có định dạng 'KHxxxx' trong đó x là chữ số!");

$.validator.addMethod("isValidEmail", function (value, element) {
  return /^[^@\s]+@([^@\s]+\.)+[^@\s]+$/.test(value);
}, "Email chưa đúng định dạng!");

$.validator.addMethod("isValidPhone", function (value, element) {
  return /^((090)|(091)|(\(84\)\+90)|(\(84\)\+91))[0-9]{7}$/.test(value);
}, "Số điện thoại không đúng định dạng!");

$.validator.addMethod("isValidServiceCode", function (value, element) {
  return /^(DV)[0-9]{3}$/.test(value);
}, "Mã dịch vụ phải có định dạng 'DVxxx' trong đó x là chữ số!");

$.validator.addMethod("isValidInteger", function (value, element) {
  return /^[1-9][0-9]*$/.test(value);
}, "Only accept positive number");