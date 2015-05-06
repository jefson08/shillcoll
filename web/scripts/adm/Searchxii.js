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
            //label.text("ok!").addClass("success");
        },
        rules: {
          txtBoardRoll: "alphanumeric",
            txtYrPass: "year_pass",
            //txtMarks[]: "numeric",
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
    
    
    $("input").bind("keydown", function (event) {
        // track enter key
        var keycode = (event.keyCode ? event.keyCode : (event.which ? event.which : event.charCode));
        if (keycode == 13) { // keycode for enter key
            // force the 'Enter Key' to implicitly click the Update button
            document.getElementById('defaultActionButton').click();
            return false;
        } else {
            return true;
        }
    }); // end of function  
//    $('input[name=search]').click(function () {
//        var txtsearchBy = document.getElementById('txtSearchBy').value;
//        $("#waitbox").dialog("open");
//        var count;
//        $("#clxiiinfo").find("tr").each(function () {
//            count = $(this).index();
//        });
//        $.ajax({
//            type: "POST",
//            url: "../Editclxii",
//            data: ({txtSearchBy: txtsearchBy, Count: count}),
//            beforeSubmit: function () {
//                // $('#processing').css({visibility: 'visible'});
//                //alert("before submit");
//                return true;
//            },
//            success: function (response) {
//                //$('#msg').html(response);
//                $("#waitbox").dialog("close");
//                if (response === "Not Matching Record(s) Found") {
//                    //window.location.replace("../adm/editxii.jsp");
//                    swal("Oops...", response, "error");
//                } else if (response === "Error: Enter search Value") {
//                    swal("Oops...", response, "error");
//                }
//                else {
//                    $('#clear_search').hide();
//                    document.getElementById("Delete").type = "button";
//                    document.getElementById("Add").type = "button";
//                    document.getElementById("cmdSave").type = "submit";
//                    $('#clxiiinfo > tbody:last').append(response);
//                    $('input[name="search"]').hide();
//                }
//            },
//            error: function (xhr) {
//                alert(xhr.status);
//            }
//        });
//    })
    
    $('#cmbBoardID').live('change',function () {
        $('#clear_subject').html("");
        var cmbboardID = document.getElementById('cmbBoardID').value;
        var cmbStream = document.getElementById('cmbStream').value;
        $("#waitbox").dialog("open");
        var count;
        $("#clxiiinfo").find("tr").each(function () {
            count = $(this).index();
        });
        $.ajax({
            type: "POST",
            url: "../EditclxiiOnBoardChange",
            data: ({cmbboardID:cmbboardID, Count: count, cmbStream:cmbStream}),
            beforeSubmit: function () {
                // $('#processing').css({visibility: 'visible'});
                //alert("before submit");
                return true;
            },
            success: function (response) {
                //$('#msg').html(response);
                $("#waitbox").dialog("close");
                if (response === "Not Matching Record(s) Found") {
                    //window.location.replace("../adm/editxii.jsp");
                    swal("Oops...", response, "error");
                } else {
                    //alert(response);
                    $('#add_subject > tbody:last').append(response);
                }
            },
            error: function (xhr) {
                alert(xhr.status);
            }
        });
    })
    
    
    
    $('input[name=Delete]').click(function () {
        var txtsearchBy = document.getElementById('txtSearchBy').value;
        $.ajax({
            type: "POST",
            url: "../Deleteclxii",
            data: ({txtSearchBy: txtsearchBy}),
            success: function (response) {
                //$('#msg').html(response);
                $('#clear_content').hide();
                // $('#clxiiinfo > tbody:last').append(response);
                //$('input[name="search"]').hide();
                swal("Oops...", response, "error");
                window.location.replace("../adm/editxii.jsp");
            },
            error: function (xhr) {
                alert(xhr.status);
            }
        });
    })

    $('input[name=Add]').click(function () {
        var cmbboardID = document.getElementById('cmbBoardID').value;
        var cmbstream = document.getElementById('cmbStream').value;
        var subject = "";
        $('[name=txtSubject]').each(function () {
            if (this.value != undefined) {
                subject += this.value + ',';
            }
        });
        var count;
        $("#clxiiinfo").find("tr").each(function () {
            count = $(this).index();
        });
        $.ajax({
            type: "POST",
            url: "../EditSelectSubjName",
            data: ({cmbBoardID: cmbboardID, cmbStream: cmbstream, Count: count, Subject: subject}),
            success: function (response) {
                if (response === "Error: Empty record tobe added") {
                    swal("Oops...", "Subject are already listed.....", "error");
                } else {
                    // $('#msg').html(response);
                    $('#add_subject > tbody:last').append(response);
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
});