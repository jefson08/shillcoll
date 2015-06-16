/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$.validator.prototype.checkForm = function () {
    //overriden in a specific page
    this.prepareForm();
    for (var i = 0, elements = (this.currentElements = this.elements()); elements[i]; i++) {
        if (this.findByName(elements[i].name).length !== undefined && this.findByName(elements[i].name).length > 1) {
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
    var validator = $("#boardnamesubject").bind("invalid-form.validate", function () {
        $("#summary").html("Your form contains " + validator.numberOfInvalids() + " errors, see details below.");
    }).validate({
        debug: true,
        errorElement: "em",
        errorContainer: $("#warning, #summary"),
        errorPlacement: function (error, element) {
            if (element.attr("type") === "radio") {
                error.insertAfter("#radErr");
            }
            error.appendTo(element.parent("td").next("td"));
        },
        success: function (label) {
            //label.text("ok!").addClass("success");
        },
        rules: {
            TxtBoaName: "nameTextBox",
            txtStream: "required",
            txtSubName: "nameTextBox"
        },
        submitHandler: function (form) {
            form.submit();
        }
    });

    $('#cmdSave').click(function () {
        $('[id^="txtSubName"]').each(function () {
            if ($(this).val().length > 0) {
                // alert($(this).val());
                $(this).rules('add', {
                    nameTextBox: true,
                    required: true,
                });

            }
        })
    })
    $('#cmbBoardID').change(function () {
        if ($(this).val() !== -1) {
            PopulateDependentCombo(document.boardnamesubject.cmbBoardID, document.boardnamesubject.txtStream, '../populateStream');
        }
        else {
            $('#txtStream').empty();
        }
    });

    $('#txtStream').change(function () {
        var cmbboardID = document.getElementById('cmbBoardID').value;
        var cmbstream = document.getElementById('txtStream').value;
        document.getElementById("Add").type = "button";
        document.getElementById("Delete").type = "button";
        document.getElementById("cmdSave").type = "submit";
        //$('#clear').clear();
        $('#clear').html('');
        box1 = new ajaxLoader(".box-1");
        $.ajax({
            type: "POST",
            url: "../EditBoardSubj",
            data: ({cmbBoardID: cmbboardID, cmbStream: cmbstream}),
            beforeSubmit: function () {
                // $('#processing').css({visibility: 'visible'});
                alert("before submit");
                return true;
            },
            success: function (response) {
                //$('#msg').html(response);
                //$('#subjectName').empty();
                if (box1)
                    box1.remove();
                $('#subjectName > tbody:last').append(response);
            },
            error: function (xhr) {
                alert(xhr.status);
            }
        });
    });
    $('input[name=Delete]').click(function () {
        var cmbboardID = document.getElementById('cmbBoardID').value;
        var stream = document.getElementById('txtStream').value;
        //$('#subjectName').empty();
        box1 = new ajaxLoader(".box-1");
        $.ajax({
            type: "POST",
            url: "../DeleteBoardSubj",
            data: ({cmbBoardID: cmbboardID, stream: stream}),
            success: function (response) {
                // $('#msg').html(response);              
                //$('#subjectName > tbody:last').append(response);
                if (box1)
                    box1.remove();
                window.location.replace("../adm/editBoardSubject.jsp");
            },
            error: function (xhr) {
                alert(xhr.status);
            }
        });
    });
    var count;
    $('#Add').live("click", function () {
        //var i=0;
        $("#subjectName").find("tr").each(function () {
            count = $(this).index();
        });
        //alert(count);
        var str = '<tr id=' + count + '><td>Subject*</td>';
        str += '<td><input type="text" name="txtSubName" id="txtSubName[' + count + ']" value="" size="40"/></td><td><img src="../images/remove.png" alt="Remove" imgno=' + count + ' id="DelIcon"/></td><td></td></tr>';
        $('#subjectName > tbody:last').append(str);
    });
    $("#DelIcon").live("click", function () {

        var del = $(this).attr("imgno");
//     alert(del);
        $('#' + del).remove();
    });
});