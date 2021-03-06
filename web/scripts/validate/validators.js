/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$.validator.addMethod("date", function (value, element) {
  if (value !== "") {
    var d, m, y;
    var tmp = new Array(3);

    tmp = value.split("-");

    d = tmp[0];
    m = tmp[1];
    y = tmp[2];

    var format = /^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[012])-(19[0-9][0-9]|20[0-9][0-9]|21[0-9][0-9])/;//date format dd-mm-yyyy 
   
    if (!(format.test(value))) {
      return false;
    }
    //check whether date selected is not later than today's data
    var sdate = new Date(y, m - 1, d);//month is 0 to 11
    var today = new Date();
    if (sdate.getTime() > today.getTime()) {
      return false;
    }
    return true;
  }
  else {
    return false;
  }
}, 'Please Enter Valid Date [dd-mm-yyyy]');

$.validator.addMethod("nameTextBoxOptional", function (value, element) {
  if (value !== "") {
    return this.optional(element) || /^[a-zA-Z ]+$/i.test(value);
  }
  return true;
}, 'Letter Only');

$.validator.addMethod("nameTextBox", function (value, element) {
  if (value !== "") {
    return this.optional(element) || /^[a-zA-Z ]+$/i.test(value);
  }
  else {
    return false;
  }
}, 'Letter Only');
$.validator.addMethod("combo", function (value, element) {
  if (value == "-1") {
    return true;
  }
  else {
    return false;
  }
}, 'List not selected');

$.validator.addMethod("alphanumeric", function (value, element) {
  if (value !== "") {
    return this.optional(element) || /^([a-zA-Z0-9 _-]+)$/i.test(value);
  }
  else {
    return false;
  }
}, 'Letters, numbers, and underscore only');

$.validator.addMethod("alphaNumSpaceNewLine", function (value, element) {
  if (value !== "") {
    return this.optional(element) || /^[0-9a-zA-Z\n ]+$/i.test(value);
  }
  else {
    return false;
  }
}, 'Letters, numbers, and underscore only');

$.validator.addMethod("numeric", function (value, element) {
  if (value !== "") {
    return this.optional(element) || /^\d+$/.test(value);
  }
  else {
    return false;
  }
}, 'Numeric Only');

$.validator.addMethod("float", function (value, element) {
  if (value !== "") {
    return this.optional(element) || /^-?(?:\d+|\d{1,3}(?:[\s\.,]\d{3})+)(?:[\.,]\d+)?$/.test(value);
  }
  return true;
}, 'Numeric Only');

$.validator.addMethod("year_pass", function (value, element) {
  if (/^\d+$/.test(value)) {
    //alert(value);
    var date = new Date();
    var current_year = date.getFullYear();
    var previous_year = current_year - 5;
    if (value >= previous_year && value <= current_year) {
      return true;
    }
    else {
      return false;
    }
  }
  else {
    return false;
  }
}, 'Numbers Should be equal to current year and greater than previous 5 year');

$.validator.addMethod("txtalphanumeric", function (value, element) {
  if (value !== "") {
    return this.optional(element) || /^([a-zA-Z0-9-/]+)$/i.test(value);
  }
  else {
    return false;
  }
}, 'Letters, numbers, - / only');

$.validator.addMethod("afnumeric", function (value, element) {
  if (value !== "") {
    return this.optional(element) || /^((af)||[0-9]+)$/i.test(value);
  }
  else {
    return false;
  }
}, 'AF or Numeric Only');

$.validator.addMethod("afnumericedit", function (value, element) {
  if (value !== "") {
    return this.optional(element) || /^((af)||[0-9]+)$/i.test(value);
  }
  else {
    return false;
  }
}, 'Numeric Only');