/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$.validator.prototype.checkForm = function () {
<<<<<<< HEAD
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
=======
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
// $('#txtDOB').datepick({showTrigger: '#calImg', dateFormat:'dd-mm-yyyy',maxDate: new Date()});
// $("#txtDOB").mask("99-99-9999");
// });
// $("#txtPPhno").mask("9999999999");
// $("#txtMobile").mask("9999999999");
>>>>>>> origin/master

<<<<<<< HEAD
$(document).ready(function() {
var validator = $("#CourseClassHons").bind("invalid-form.validate", function () {
        $("#summary").html("Your form contains " + validator.numberOfInvalids() + " errors, see details below.");
    }).validate({
        debug: true,
        errorElement: "em",
        errorContainer: $("#warning, #summary"),
        errorPlacement: function (error, element) {

            if (element.attr("type") === "radio") {
                if (element.attr("name") === "radYearOrSem")
                    error.appendTo(".radSYR");
                if (element.attr("name") === "radGender")
                    error.appendTo(".radGEN");
                if (element.attr("name") === "radCategory")
                    error.appendTo(".radCAT");
            }
            else
                error.appendTo(element.parent("td").next("td"));
        },
        success: function (label) {
            //label.text("ok!").addClass("success");
        },
        rules: {
            cmbStream: "combo",
            radYearOrSem: "required",
            txtDOB: "date",
            txtFName: "nameTextBox",
            txtMName: "nameTextBox",
            txtPOccup: "nameTextBoxOptional",
            txtMAddress: "alphaNumSpaceNewLine",
            txtPAddress: "alphaNumSpaceNewLine",
            txtEmail: {
                email: true,
            },
            txtIncome: "float",            
        },
        submitHandler: function (form) {
            form.submit();
        }
    });

    var i = 3;
     var YearOrSem='s';
        
    $("#chkCategorydummy").live("click", function() {
//alert($(this).attr("chkStat") +"--"+$(this).is(':checked'));
        var chkval = $(this).is(':checked');
        var cval = $(this).attr("chkStat");
        $('input[name=chkCategory]').each(function(i, obj) {
            var v = $(this).attr('chkVal');
            if (cval == v) {
                $(this).val(chkval);
            }
=======
  var validator = $("#CourseClassHons").bind("invalid-form.validate", function () {
    $("#summary").html("Your form contains " + validator.numberOfInvalids() + " errors, see details below.");
  }).validate({
    debug: true,
    errorElement: "em",
    errorContainer: $("#warning, #summary"),
    errorPlacement: function (error, element) {
      if (element.attr("type") === "radio") {
        if (element.attr("name") === "radYearOrSem") {
          error.appendTo(".radSYR");
        }
      }
      if (element.attr("name") === "txtPaperId")
      {
              $('#error_Subid').append(error);
      }
      else
        error.appendTo(element.parent("td").next("td"));
    },
    success: function (label) {
      //label.text("ok!").addClass("success");
    },
    rules: {
      //txtStuName: "nameTextBox",
      radYearOrSem: "required",
      //txtDOB: "date",
      //txtFName: "nameTextBox",
      txtPaperId: "alphanumeric",
//            txtMName: "nameTextBox",
//            txtPOccup: "nameTextBoxOptional",
//            txtMAddress: "alphaNumSpaceNewLine",
//            txtPAddress: "alphaNumSpaceNewLine",
//            txtEmail: {
//                email: true,
//            },
//            txtIncome: "float",            
    },
    submitHandler: function (form) {
      form.submit();
    }
  });

//PopulateCombo(document.CourseClassHons.cmbCourseName,'../populateCourse');
  var i = 3;
  var YearOrSem = 's';
>>>>>>> origin/master

  $("#chkCategorydummy").live("click", function () {
//alert($(this).attr("chkStat") +"--"+$(this).is(':checked'));
    var chkval = $(this).is(':checked');
    var cval = $(this).attr("chkStat");
    $('input[name=chkCategory]').each(function (i, obj) {
      var v = $(this).attr('chkVal');
      if (cval == v) {
        $(this).val(chkval);
      }

    })
  })
  $("#chkPractdummy").live("click", function () {
//alert($(this).attr("chkStat") +"--"+$(this).is(':checked'));
    var chkval = $(this).is(':checked');
    var cval = $(this).attr("chkPstat");
    $('input[name=chkPract]').each(function (i, obj) {
      var v = $(this).attr('chkPval');
      if (cval == v) {
        $(this).val(chkval);
      }

    })
  })
  $('input[name=cmdNext]').click(function () {
    $('#CCHDiv').hide();
    $('input[name=cmdAddMore]').removeAttr('disabled');
    $('input[name=cmdNext]').attr('disabled', 'disabled');
    $('select[name=cmbCourseName]').val(-1);
    $('select[name=cmbStream]').val(-1);
    $('select[name=cmbSubjectName]').val(-1);
    $('select[name=cmbYearOrSemNo]').each(function () {
      $(this).val(-1);
    })
    $('input[name=txtPaperId]').each(function () {
      $(this).val('');
    })
    $('input[name=txtPaperName]').each(function () {
      $(this).val('');
    })
    $('input[name=chkCategorydummy]').each(function () {
      $(this).removeAttr('checked');
    })
    $('input[name=chkCategory]').each(function (i, obj) {
      var v = $(this).attr('false');
    })
    $('input[name=chkPractdummy]').each(function () {
      $(this).removeAttr('checked');
    })
    $('input[name=chkPract]').each(function (i, obj) {
      var v = $(this).attr('false');
    })
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

    if ($(this).val() !== -1) {
//alert("onchange entered"+$(this).val());
      PopulateDependentCombo(document.CourseClassHons.cmbStream, document.CourseClassHons.cmbSubjectName, '../populateSubjects');
    }
    else {
      $('#cmbSubjectName').empty();
    }
  })

  $('input[name=cmdAddMore]').click(function () {

//alert(YearOrSem);
    i++;

    var str = '<tr>';
    str += '<td><input type="text" name="txtPaperId" value="" /></td>';
    //str += '<td>Paper Name</td>';
    str += '<td><input type="text" name="txtPaperName" value="" /></td>';

    str += '<td><select name="cmbYearOrSemNo" id="cmbYearOrSemNo">';
    if (YearOrSem == 's')
    {
      str += '<option value="-1">-</option>';
      str += '<option value="s1">1</option>';
      str += '<option value="s2">2</option>';
      str += '<option value="s3">3</option>';
      str += '<option value="s4">4</option>';
      str += '<option value="s5">5</option>';
      str += '<option value="s6">6</option>';
      str += '<c:set var="yearOrSemNo" value="${param.cmbYearOrSem}"></c:set>';
    }
    else
    {
      str += '<option value="-1">-</option>';
      str += '<option value="y1">1</option>';
      str += '<option value="y2">2</option>';
      str += '<option value="y3">3</option>';
    }
    str += '</select></td>';
    str += '<td>';

    str += '<input name ="chkCategorydummy" type ="checkbox" id ="chkCategorydummy" chkStat ="' + i;
    str += '" ${param.submitted and (paramValues.chkCategory[' + i + '])?"checked":""} / > ';

    str += '<input type = "hidden" name = "chkCategory" id = "chkCategory" chkVal = "' + i;
    str += '" value = "${param.submitted?paramValues.chkCategory[' + i + ']:false}" />';
    str += '</td>';
    str += '<td><input name ="chkPractdummy" type ="checkbox" id ="chkPractdummy" chkStat ="' + i;
    str += '" ${param.submitted and (paramValues.chkPract[' + i + '])?"checked":""} / > ';

    str += '<input type = "hidden" name = "chkPract" id = "chkPract" chkVal = "' + i;
    str += '" value = "${param.submitted?paramValues.chkPract[' + i + ']:false}" />';
    str += '</td>';
    $('#papers > tbody:last').append(str);
  })



});



