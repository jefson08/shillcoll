/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$.validator.prototype.checkForm = function () {
  //overriden in a specific page
  this.prepareForm();
  for (var i = 0, elements = (this.currentElements = this.elements()); elements[i]; i++) {
    if (this.findByName(elements[i].name).length != undefined && this.findByName(elements[i].name).length > 1) {
      for (var cnt = 0; cnt < this.findByName(elements[i].name).length; cnt++) {
        this.check(this.findByName(elements[i].name)[cnt]);
      }
    } else {
      this.check(elements[i]);
    }
  }
  return this.valid();
}

$(document).ready(function () {


  var validator = $("#promote").bind("invalid-form.validate", function () {
    $("#summary").html("Your form contains " + validator.numberOfInvalids() + " errors, see details below.");
  }).validate({
    debug: true,
    errorElement: "em",
    errorContainer: $("#warning, #summary"),
    errorPlacement: function (error, element) {
      if (element.attr("type") === "checkbox") {
        //alert('ssss');
        error.appendTo("#err");
      }
      //error.appendTo(element.parent("td").next("td"));
    },
    success: function (label) {
      //label.text("ok!").addClass("success");
    },
    rules: {
      "promote[]": {
        required: true,
        minlength: 1
      }
    },
    messages: {
      "promote[]": "You must select at least one!"
    },
    submitHandler: function (form) {
      form.submit();
    }
  })
  $('input[name=radYearOrSem]').change(function () {
    $('select[name=cmbYearOrSemNo]').find("option").remove();//first clear the combo box then populate
    $('input[name=radYearOrSem]').each(function () {
      if ($(this).is(':checked'))
        YearOrSem = ($(this).attr('value'));
    })

    if (YearOrSem == 'y')
    {

      $('select[name=cmbYearOrSemNo]').each(function () {
        var that = this;
        var myOptions = {'-1': '-', y1: '1', y2: '2', y3: '3'};
        $.each(myOptions, function (val, text) {
          $(that).append(new Option(text, val));
        })
      });
    }
    else if (YearOrSem == 's')
    {
      $('select[name=cmbYearOrSemNo]').each(function () {
        var that = this;
        var myOptions = {'-1': '-', s1: '1', s2: '2', s3: '3', s4: '4', s5: '5', s6: '6'};
        $.each(myOptions, function (val, text) {
          $(that).append(new Option(text, val));
        })
      });
    }
  })

  $("select[name='cmbStream']").change(function () {
    PopulateDependentCombo(document.promote.cmbStream, document.promote.cmbCourse, '../populateCourse1');
  });

  $('#search').click(function () {
    var box1;
    $.ajax({
      type: "POST",
      url: "../getStuForPromote",
      data: ({cmbCourse: $('#cmbCourse').val(), cmbYearOrSemNo: $('#cmbYearOrSemNo').val()}),
      beforeSend: function () {
        box1 = new ajaxLoader(".box-1");
        return true;
      },
      success: function (response) {
        //$('#msg').html(response);;
        if (box1)
          box1.remove();
        $("#srchlist").html(response);

      },
      error: function (xhr) {
        alert(xhr.status);
      }
    });
  })
  $('#promote').live('click', function () {
    var rollno = $(this).attr('rollno');
    var course = $(this).attr('course');
    var smoryr = $(this).attr('smoryr');
    if ($(this).is(':checked')) {
      $('#rollno' + rollno).attr("value", rollno);
      $('#course' + rollno).attr("value", course);
      $('#smoryr' + rollno).attr("value", smoryr);
    }
    else {
      $('#rollno' + rollno).attr("value", '');
      $('#course' + rollno).attr("value", '');
      $('#smoryr' + rollno).attr("value", '');
    }

  })
})