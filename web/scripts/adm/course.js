/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {
    //$('input[name=next]').attr('disabled', 'disabled');
//$('input[name=sub]').change(function(){
    $("#subjectcode").live("change", function() {
        var subval = $(this).attr("subval");
//        alert(subval);
        if ($(this).is(":checked") == true) {
            $("#" + subval).css({display: "inline"});
        }
        else if ($(this).is(":checked") == false) {
            $("#" + subval).css({'display': 'none'});
        }

    });

    $("select[name='coursecode']").change(function() {
// alert("hello=---"+$(this).attr("strm").val());
        $.ajax({
            type: "POST",
            url: "../GetSubject",
            data: "course=" + $("#coursecode").val() + "&subjectcode=" + $('#subjectcode').val() + "&papercode=" + $('#papercode').val(),
            success: function(response) {
                $("#divsub").html(response);
            },
            error: function(xhr) {
                alert(xhr.status);
            }
        });

    });

    $("select[name='stream']").change(function() {

        //  alert($("#stream").val());
        PopulateDependentCombo(document.course.stream, document.course.cmbhon, '../populatesubject');

    });

    $("input[name='reset']").click(function() {
//        document.getElementById('subpaper').disabled = false;
        document.getElementById('stream').value = "";
        document.getElementById('txtcoursename').value = "";
        document.getElementById('txtnoofseat').value = "";
        document.getElementById('cmbhon').value = "";
//        document.getElementById()

//        var v2 = document.getElementById('status').value;
//        var v3 = document.getElementById('yos').value;
        // alert("hello");
//        $.ajax({
//            type: "POST",
//            url: "../GetSubject1",
//            data: ({v1: v1, v2: v2, v3: v3}),
//            success: function(response) {
//                $("#subject1").html(response);
//            },
//            error: function(xhr) {
//                alert(xhr.status);
//                alert("error");
//            }
//        });
    });

    
});