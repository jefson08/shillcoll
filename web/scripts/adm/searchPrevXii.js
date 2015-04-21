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
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $("select[name='cmbBoardID']").change(function () {
        if ($(this).val() !== -1) {
            PopulateDependentCombo(document.clxiiinfo.cmbBoardID, document.clxiiinfo.txtStream, '../populateStream');
        }
        else {
            $('#txtStream').empty();
        }
    })

    $('#txtStream').change(function () {
        var cmbboardID = document.getElementById('cmbBoardID').value;
        var cmbstream = document.getElementById('txtStream').value;
        $('#clxiiinfo').hide();
        $("#waitbox").dialog("open");
        $.ajax({
            type: "POST",
            url: "../searchPrevXii",
            data: ({cmbboardID: cmbboardID, cmbstream: cmbstream}),
            beforeSubmit: function () {
                // $('#processing').css({visibility: 'visible'});
                //alert("before submit");
                return true;
            },
            success: function (response) {

                if (response === "Not Matching Record(s) Found") {
                    //alert("Not Matching Record(s) Found");
                    swal("Oops...", "Not Matching Record(s) Found!", "error");
                    window.location.replace("../adm/searchPrevXii.jsp");
                } else {
                    //$('#msg').html(response);
                    $("#waitbox").dialog("close");
                    document.getElementById("Next").type = "button";
                    $('#search_content > tbody:last').append(response);
                }
            },
            error: function (xhr) {
                alert(xhr.status);
            }
        });
    });
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

    });
});
