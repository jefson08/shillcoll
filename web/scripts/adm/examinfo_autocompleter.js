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

    //to populate datalist base on option selected SEM/YEAR
    $('#seye').live('click', function () {
        if ($('#radYear').is(':checked')) {
            $("#semyr").empty();
            for (var i = 1; i <= 3; i++) {
                $('#semyr').append("<option value='" + "y" + i + "'>");
            }
        }
        else if ($('#radSem').is(':checked')) {
            $("#semyr").empty();
            for (var i = 1; i <= 6; i++) {
                $('#semyr').append("<option value='" + "s" + i + "'>");
            }
        }
        else {
            alert("Please Select System Year or Semester!");
        }

    });
    
    //populate Year from the current year
    var currentYear = (new Date).getFullYear();
    $("#eyear").empty();
    for (var i = 1; i <= 2; i++) {
        $("#eyear").append("<option value='" + currentYear + "'>");
        currentYear++;
    }

//populate RegYear from the current year
    var currentYear = (new Date).getFullYear();
    $("#Regnoyear").empty();
    currentYear = currentYear - 1;
    for (var i = 1; i <= 2; i++) {
        $("#Regnoyear").append("<option value='" + currentYear + "'>");
        currentYear++;
    }

    $('#txtPmtDate').datepick({
        showTrigger: '#calImg',
        dateFormat: 'dd-mm-yyyy',
        maxDate: new Date()
    });
    $("#txtPmtDate").mask("99-99-9999");


    var validator = $("#examinformation").bind("invalid-form.validate", function () {
        $("#summary").html("Your form contains " + validator.numberOfInvalids() + " errors, see details below.");
    }).validate({
        debug: true,
        errorElement: "em",
        errorContainer: $("#warning, #summary"),
        errorPlacement: function (error, element) {

            if (element.attr("type") === "radio") {
                if (element.attr("name") === "radYearOrSem")
                    error.appendTo(".radSYR");
                if (element.attr("name") === "nri")
                    error.appendTo(".radCAT");
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
            txtRegno: "afnumeric",
        },
        submitHandler: function (form) {
            form.submit();
        }
    });

    //binding datalist to event //and populate college roll number 
    document.getElementById('rollno').addEventListener('input', function () {
        var roll = document.getElementById('rollno').value;
        $.ajax({
            url: "../examinfo_PopulateRollno",
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

                // populate default regyear 
                $(this).getRegNoYear(jQuery.trim(response));
            },
            error: function (xhr) {
                //alert(xhr.status);
            }
        });
    });

    //function to populate regnoyear if available
    (function ($) {
        $.fn.getRegNoYear = function (param) {
            $.ajax({
                type: "POST",
                url: "../examinfo_PopulateRegnoYear",
                data: ({
                    term: param
                }),
                success: function (response) {

                    if (!jQuery.trim(response))
                        response = "AF";

                    $("#regyear").val(jQuery.trim(response));
                },
                error: function (xhr) {
                    //            //alert(xhr.status);
                }
            });
        }
    })(jQuery);

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


//    //binding datalist to event //and populate batch
//    $("#datalstbatch").bind('input', function (){  
//        var bat = document.getElementById('datalstbatch').value;
//        // alert(batch);
//        $.ajax({
//            url : "../examinfo_PopulateBatch",
//            type : "POST",
//            async:false,
//            data : {
//                term : bat
//            },
//            dataType : "json",
//            success : function(response) {
//                
//                $("#batch").empty();
//                for(var i=0, len=response.length; i<len; i++) {
//                    $("#batch").append("<option value='" +jQuery.trim(response[i]) + "'>");
//                }
//            },
//            error: function (xhr) {
//            //alert(xhr.status);
//            }
//            
//        });
//    });
//    

//this is for firing an event when dalatist option is selected from university roll number
//and populate registration number
//    document.getElementById('datalstunirollno').addEventListener('input', function () {
//        var uniroll = document.getElementById('datalstunirollno').value;
//     
//        $.ajax({
//            type: "POST",
//            url: "../examinfo_PopulateRegno",
//            data: ({          
//                term: uniroll
//            }),
//            success: function (response) {
//                
//                $("#regno").empty();
//                $("#regno").append("<option value='" +jQuery.trim(response) + "'>");
//            },
//            error: function (xhr) {
//            //alert(xhr.status);
//            }
//        });
// });

//    $("#datalstunirollno").bind('input', function (){     
//        var roll = document.getElementById('datalstunirollno').value;
//        $.ajax({
//            url : "../examinfo_PopulateUnirollno",
//            type : "POST",
//            async:false,
//            data : {
//                term : roll
//            },
//            dataType : "json",
//            success : function(response) {
//                var dataList = $("#unirollno");
//                dataList.empty();
//                for(var i=0, len=response.length; i<len; i++) {
//                    dataList.append("<option value='" +jQuery.trim(response[i]) + "'>");
//                }
//            },
//            error: function (xhr) {
//                alert(xhr.status);
//            }
//        });
//    });

});


