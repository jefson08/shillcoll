/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$.validator.addMethod("alphanumeric", function (value, element) {
    if (value !== "") {
        return this.optional(element) || /^[0-9a-zA-Z]+$/i.test(value);
    }
    else {
        return false;
    }
}, 'Letters, numbers, and underscores only please');

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

$.validator.addMethod("numeric", function (value, element) {
    if (value !== "") {
        return this.optional(element) || /^\d+$/.test(value);
    }
    else {
        return false;
    }
}, 'Numeric Only');

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
    var validator = $("#clxiiinfo").bind("invalid-form.validate", function () {
        $("#summary").html("Your form contains " + validator.numberOfInvalids() + " errors, see details below.");
    }).validate({
        debug: true,
        errorElement: "em",
        errorContainer: $("#warning, #summary"),
        errorPlacement: function (error, element) {
            error.appendTo(element.parent("td").next("td"));
        },
        success: function (label) {
            label.text("ok!").addClass("success");
        },
        rules: {
            txtBoardRoll: "alphanumeric",
            txtYrPass: "year_pass",
            txtMarks: "numeric",
            txtTotalMarks: "numeric"
        },
        submitHandler: function (form) {
            form.submit();
        }
    });
   
    $('#cmdSave').click(function(){
                $('[id^="txtMarks"]').each(function(){
                    if ($(this).val().length>0){
                       // alert($(this).val());
                       $(this).rules('add', {
                            number: true,
                            required:true,
                        });  
                        
                    }
                })
            })
    
    $("select[name='cmbBoardID']").change(function () {
        if ($(this).val() !== -1) {
            PopulateDependentCombo(document.clxiiinfo.cmbBoardID, document.clxiiinfo.cmbStream, '../populateStream');
        }
        else {
            $('#cmbStream').empty();
        }
    })

    $('#cmbStream').change(function () {
        var cmbboardID = document.getElementById('cmbBoardID').value;
        var cmbstream = document.getElementById('cmbStream').value;
        $('#Clear').hide();
        //$('#marks').hide();   
        document.getElementById("AddSub").type = "button";
        document.getElementById("cmdSave").type = "submit";
        $("#waitbox").dialog("open");
        var count;
        $("#marks").find("tr").each(function () {
            count = $(this).index();
        });
        $.ajax({
            type: "POST",
            url: "../SelectAllSubjName",
            data: ({cmbBoardID: cmbboardID, cmbStream: cmbstream, Count: count}),
            beforeSubmit: function () {
                // $('#processing').css({visibility: 'visible'});
                //alert("before submit");
                return true;
            },
            success: function (response) {
                // $('#msg').html(response);
                $("#waitbox").dialog("close")
                $('#marks > tbody:last').append(response);
            },
            error: function (xhr) {
                alert(xhr.status);
            }
        });
    })
    $('input[name=AddSub]').click(function () {
        var cmbboardID = document.getElementById('cmbBoardID').value;
        var cmbstream = document.getElementById('cmbStream').value;
        //var subject = $('[name=txtSubject]').serialize(); //beter and shorter option
        var subject = "";
        $('[name=txtSubject]').each(function () {
            if (this.value != undefined) {
                subject += this.value + ',';
            }
        });
        var count;
        $("#marks").find("tr").each(function () {
            count = $(this).index();
        });
        $.ajax({
            type: "POST",
            url: "../SelectSubjName",
            data: ({cmbBoardID: cmbboardID, cmbStream: cmbstream, Count: count, Subject: subject}),
            success: function (response) {
                // $('#msg').html(response);
                if (response === "Error: Empty record tobe added") {
                    swal("Oops...", "Subject are already listed.....", "error");
                } else {
                    $('#marks > tbody:last').append(response);
                }
            },
            error: function (xhr) {
                alert(xhr.status);
            }
        });
    })
    $("#waitbox").dialog({
        autoOpen: false,
        open: function (event, ui) {
            $(".ui-dialog-titlebar-close").hide();
        }, //disable close button x located 
        closeOnEscape: false, //upper right conner
        height: 150,
        width: 450,
        modal: true,
        show: 'Explode'

    })
    $("#DelIcon").live("click", function () {

        var del = $(this).attr("imgno");
//     alert(del);
        $('#' + del).remove();
    });
    /*    $('#txtSubject').on('change', function () {
     alert("Hurray");
     $.ajax({
     type: "POST",
     url: "../UpdateFlag",
     data: "Subject=" + $("#txtSubject").val(),
     success: function (response) {
     $('#msg1').html(response);
     },
     error: function (xhr) {
     alert(xhr.status);
     }
     });
     })*/

});