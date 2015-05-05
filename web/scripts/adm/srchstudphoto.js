/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {
    var rollno, name;
    $('input[name=cmdsrch]').click(function() {
        $("#offset").attr("value", "0");
        $('form[name=srchstu]').ajaxForm({
            url: '../SearchStudPhoto',
            beforeSubmit: function() {
                //alert("huuryyy--before");
                return true;
            },
            success: function(data) {
                $("#srchresult").html(data);
            },
            error: function(err) {
                // end progress bar
            }
        })
    })

    $(".editStu").live("click", function() {
        rollno = $(this).attr("rollno");
        name = $(this).attr("name");
        $('#rollno').attr("value", rollno);
        $('#name').attr("value", name);
        $("#editstudphoto").submit();

    })

    $("#previous").live("click", function() {
        var n = parseInt($("#offset").val()) - parseInt($("#limit").val());
        $("#offset").attr("value", n);

        var param = "radSrchBy=" + $("#radSrchBy:checked").val() + "&txtSrchVal=" + $("#txtSrchVal").val() + "&limit=" + $("#limit").val() + "&offset=" + $("#offset").val();
        $.ajax({
            type: "POST",
            url: "../SearchStudPhoto",
            data: param,
            success: function(response) {
                $("#srchresult").html(response);
            },
            error: function(xhr) {
                alert(xhr.status);
            }
        });
    });

    $("#next").live("click", function() {
        var n = parseInt($("#offset").val()) + parseInt($("#limit").val());
        $("#offset").attr("value", n);

        var param = "radSrchBy=" + $("#radSrchBy:checked").val() + "&txtSrchVal=" + $("#txtSrchVal").val() + "&limit=" + $("#limit").val() + "&offset=" + $("#offset").val();
        $.ajax({
            type: "POST",
            url: "../SearchStudPhoto",
            data: param,
            success: function(response) {
                $("#srchresult").html(response);
            },
            error: function(xhr) {
                alert(xhr.status);
            }
        });
    })
});