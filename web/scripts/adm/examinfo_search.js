/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    var rollno;

    // populate college roll number 
    document.getElementById('txtexaminfosearch').addEventListener('input', function () {
        var roll = document.getElementById('txtexaminfosearch').value;

        $.ajax({
            url: "../examinfo_PopulateRollno_Edit",
            type: "POST",
            data: {
                term: roll
            },
            dataType: "json",
            success: function (response) {

                $("#examinfosearch").empty();
                for (var i = 0, len = response.length; i < len; i++) {
                    $("#examinfosearch").append("<option value='" + jQuery.trim(response[i]) + "'>");
                }
            },
            error: function (xhr) {
                //alert(xhr.status);
            }

        });
    });

     $('input[name=cmdsrch]').click(function () {
        $("#offset").attr("value", "0");
        $('form[name=srchstuexaminfo]').ajaxForm({
            url: '../SearchExamInfo',
            beforeSubmit: function () {
                return true;
            },
            success: function (data) {
                $("#srchresult").html(data);
            },
            error: function (err) {
                // end progress bar
            }
        });
    });

    $(".editStu").live("click", function () {
        rollno = $(this).attr("rollno");
        $('#rollno').attr("value", rollno);
        $("#editstudent").submit();

    });

    $("#next").live("click", function () {
        var n = parseInt($("#offset").val()) + parseInt($("#limit").val());
        $("#offset").attr("value", n);

        var param = "&txtexaminfosearch=" + $("#txtexaminfosearch").val() + "&limit=" + $("#limit").val() + "&offset=" + $("#offset").val();
        $.ajax({
            type: "POST",
            url: "../SearchExamInfo",
            data: param,
            success: function (response) {
                $("#srchresult").html(response);
            },
            error: function (xhr) {
                alert(xhr.status);
            }
        });
    });

    $("#previous").live("click", function () {
        var n = parseInt($("#offset").val()) - parseInt($("#limit").val());
        $("#offset").attr("value", n);

        var param = "&txtexaminfosearch=" + $("#txtexaminfosearch").val() + "&limit=" + $("#limit").val() + "&offset=" + $("#offset").val();
        $.ajax({
            type: "POST",
            url: "../SearchExamInfo",
            data: param,
            success: function (response) {
                $("#srchresult").html(response);
            },
            error: function (xhr) {
                alert(xhr.status);
            }
        });
    });
});

