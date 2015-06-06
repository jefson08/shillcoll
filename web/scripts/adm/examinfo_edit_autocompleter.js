///* 
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
$.validator.prototype.checkForm = function () {
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
};

$(document).ready(function () {

    $('#txtPmtDate').datepick({
        showTrigger: '#calImg',
        dateFormat: 'dd-mm-yyyy',
        maxDate: new Date()
    });
    $("#txtPmtDate").mask("99-99-9999");


    var validator = $("#examinformation_edit").bind("invalid-form.validate", function () {
        $("#summary").html("Your form contains " + validator.numberOfInvalids() + " errors, see details below.");
    }).validate({
        debug: true,
        errorElement: "em",
        errorContainer: $("#warning, #summary"),
        errorPlacement: function (error, element) {

            if (element.attr("type") === "radio") {
//                if (element.attr("name") === "nri")
//                    error.appendTo(".radCAT");
            }
            else
                error.appendTo(element.parent("td").next("td"));
        },
        success: function (label) {
            //label.text("ok!").addClass("success");
        },
        rules: {
            txtBatch: "numeric",
            txtNehurollno: "txtalphanumeric",
            txtRegno: "afnumericedit",
        },
        submitHandler: function (form) {
            form.submit();
        }
    });


    // populate college roll number 
    document.getElementById('rollno').addEventListener('input', function () {
        var roll = document.getElementById('rollno').value;

        $.ajax({
            url: "../examinfo_PopulateRollno_Edit",
            type: "POST",
            data: {
                term: roll
            },
            dataType: "json",
            success: function (response) {

                $("#crollno").empty();
                for (var i = 0, len = response.length; i < len; i++) {
                    $("#crollno").append("<option value='" + jQuery.trim(response[i]) + "'>");
                }
            },
            error: function (xhr) {
                //alert(xhr.status);
            }

        });
    });

    //this is for firing an event when dalatist option is selected from college roll number
    document.getElementById('rollno').addEventListener('input', function () {
        var Rollno = document.getElementById('rollno').value;

        $.ajax({
            type: "POST",
            url: "../examinfo_PopulateUnirollnoAuto",
            data: ({
                term: Rollno
            }),
            success: function (response) {

                if (!jQuery.trim(response))
                    response = "AF";

                //calling function to populate batch
                var rollnum = document.getElementById('rollno').value;
                $(this).getBatch(jQuery.trim(rollnum));

                //populate nehu roll number
                $('#txtNehurollno').val(jQuery.trim(response));

                //calling function to populate reg no based on nehu roll number
                $(this).getRegNo(jQuery.trim(response));

                //calling a function for populating sem/year
                //$(this).getSemYear(jQuery.trim(rollnum));

            },
            error: function (xhr) {
                //alert(xhr.status);
            }
        });
    });

    //function to populate batch
    (function ($) {
        $.fn.getBatch = function (param) {
            box1 = new ajaxLoader(".box-1");
            $.ajax({
                type: "POST",
                url: "../examinfo_PopulateBatch",
                data: ({
                    term: param
                }),
                success: function (response) {

                    if (!jQuery.trim(response))
                        response = "AF";

                    $("#txtBatch").val(jQuery.trim(response));
                },
                error: function (xhr) {
                    //            //alert(xhr.status);
                }
            });
        }
    })(jQuery);

    //function to populate regno if available
    (function ($) {
        $.fn.getRegNo = function (param) {
            $.ajax({
                type: "POST",
                url: "../examinfo_PopulateRegno",
                data: ({
                    term: param
                }),
                success: function (response) {

                    if (!jQuery.trim(response))
                        response = "AF";

                    $("#txtRegno").val(jQuery.trim(response));
                },
                error: function (xhr) {
                    //            //alert(xhr.status);
                }
            });
        }
    })(jQuery);


    //this is for firing an event when dalatist option is selected from sem/year
//    document.getElementById('seye').addEventListener('input', function () {
//        var SemYr = document.getElementById('seye').value;
//        var Rollno = document.getElementById('rollno').value;
//
//        $.ajax({
//            type: "POST",
//            url: "../examinfo_getCategory_Edit",
//            data: ({
//                term: SemYr
//            }),
//            success: function (response) {
//
//                $("input[name=nri][value=" + response + "]").prop('checked', true);
//
//                $(this).getDate(jQuery.trim(SemYr), jQuery.trim(Rollno));
//
//                // $(this).getPmtStatus(jQuery.trim(SemYr), jQuery.trim(Rollno));
//            },
//            error: function (xhr) {
//                //alert(xhr.status);
//            }
//        });
//    });


    //function to populate Sem/year
//    (function ($) {
//        $.fn.getSemYear = function (param) {
//            $.ajax({
//                type: "POST",
//                url: "../examinfo_PopulateSemYear_Edit",
//                data: ({
//                    term: param
//                }),
//                success: function (response) {
//
//                    $("#semyr").empty();
//                    for (var i = 0, len = response.length; i < len; i++) {
//                        $("#semyr").append("<option value='" + jQuery.trim(response[i]) + "'>");
//                    }
//                },
//                error: function (xhr) {
//                    //            //alert(xhr.status);
//                }
//            });
//        }
//    })(jQuery);

    //function to getPmtStatus
//    (function ($) {
//        $.fn.getPmtStatus = function (param1, param2) {
//            $.ajax({
//                type: "POST",
//                url: "../examinfo_getPmtStatus_Edit",
//                data: ({
//                    term: param1,
//                    term1: param2
//                }),
//                success: function (response) {
//
//                   // alert(response);
//                    $("input[name=pmtstatus][value=" + response + "]").prop('checked', true);
//
//                },
//                error: function (xhr) {
//                    //            //alert(xhr.status);
//                }
//            });
//        }
//    })(jQuery);

    //function to getDate
//    (function ($) {
//        $.fn.getDate = function (param1, param2) {
//            $.ajax({
//                type: "POST",
//                url: "../examinfo_getDate_Edit",
//                data: ({
//                    term: param1,
//                    term1: param2
//                }),
//                success: function (response) {
//                    $("#txtPmtDate").val(jQuery.trim(response));
//                },
//                error: function (xhr) {
//                    //            //alert(xhr.status);
//                }
//            });
//        }
//    })(jQuery);

});