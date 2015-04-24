/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

    $("select[name='cmbBoardID']").change(function () {
        if ($(this).val() !== -1) {
            PopulateDependentCombo(document.boardnamesubject.cmbBoardID, document.boardnamesubject.txtStream, '../populateStream');
        }
        else {
            $('#txtStream').empty();
        }
    })

    $('#txtStream').change(function () {
        var cmbboardID = document.getElementById('cmbBoardID').value;
        var cmbstream = document.getElementById('txtStream').value;
        document.getElementById("Add").type = "button";
        document.getElementById("Delete").type = "button";
        document.getElementById("cmdSave").type = "submit";
        $('#Clear').hide();
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
    })
    $('input[name=Delete]').click(function () {
        var cmbboardID = document.getElementById('cmbBoardID').value;
        //$('#subjectName').empty();
        box1 = new ajaxLoader(".box-1");
        $.ajax({
            type: "POST",
            url: "../DeleteBoardSubj",
            data: ({cmbBoardID: cmbboardID}),
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
    })
    $('input[name=Add]').click(function () {
        var count;
        $("#subjectName").find("tr").each(function () {
            count = $(this).index();
        });
        //alert(count);
        var str = '<tr id=' + count + '><td>Subject  *</td>';
        str += '<td><input type="text" name="txtSubName" value="" size="40"/></td><td><img src="../images/remove.png" alt="Remove" imgno=' + count + ' id="DelIcon"/></td></tr>';
        $('#subjectName > tbody:last').append(str);
    })
    $("#DelIcon").live("click", function () {

        var del = $(this).attr("imgno");
//     alert(del);
        $('#' + del).remove();
    });
});