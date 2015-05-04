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

$(document).ready(function () {
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