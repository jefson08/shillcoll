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
    $('input[name=search]').click(function () {
        var txtsearchBy = document.getElementById('txtSearchBy').value;
        $("#waitbox").dialog("open");
        var count;
        $("#clxiiinfo").find("tr").each(function () {
            count = $(this).index();
        });
        $.ajax({
            type: "POST",
            url: "../Editclxii",
            data: ({txtSearchBy: txtsearchBy, Count: count}),
            beforeSubmit: function () {
                // $('#processing').css({visibility: 'visible'});
                //alert("before submit");
                return true;
            },
            success: function (response) {
                //$('#msg').html(response);
                $("#waitbox").dialog("close"); 
                $('#clear_search').hide();
                document.getElementById("Delete").type = "button";
                document.getElementById("Add").type = "button";
                document.getElementById("cmdSave").type = "submit";
                if (response==="Not Matching Record(s) Found") {
                    //alert("Not Matching Record(s) Found");
                    swal("Oops...", "Not Matching Record(s) Found!", "error");
                    window.location.replace("../adm/editxii.jsp");                                     
                } else {
                    $('#clxiiinfo > tbody:last').append(response);
                    $('input[name="search"]').hide();
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
        var count;
        $("#clxiiinfo").find("tr").each(function () {
            count = $(this).index();
        });
        $.ajax({
            type: "POST",
            url: "../EditSelectSubjName",
            data: ({cmbBoardID: cmbboardID, cmbStream: cmbstream, Count: count}),
            success: function (response) {
                // $('#msg').html(response);
                $('#clxiiinfo > tbody:last').append(response);
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