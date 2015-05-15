/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {
//    $('#txtFees').datepick({showTrigger: '#calImg', dateFormat: 'dd-mm-yyyy', maxDate: new Date()});
//    $("#txtFees").mask("99-99-9999");
//   
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

       // alert($("#stream").val());
        if ($(this).val() != -1) {
            PopulateDependentComboId($("#stream").val(), document.fees.cmbhon, '../populateCourse1');
        }
    });

    $("select[name='cmbhon']").change(function() {

      //   alert($("#cmbhon").val());
        // 
        if ($(this).val() != -1) {
                 PopulateDependentComboId($("#cmbhon").val(), document.fees.cmbrollno, '../populateRollno');
        }   
    });

 $("select[name='cmbrollno']").change(function() {

       // alert($("#cmbrollno").val());
        if ($(this).val() != -1) {
            PopulateDependentComboId($("#cmbrollno").val(), document.fees.cmbexamid, '../populateExamId');
        }
    });

    $("input[name='generate1']").click(function() {
        //document.getElementById('subpaper').disabled = false;
        var v1 = document.getElementById('stream').value;
        var v2 = document.getElementById('cmbhon').value;
        var v3 = document.getElementById('cmbrollno').value;
        var v4 = document.getElementById('cmbexamid').value;
         alert("hello"+v1+v2+v3+v4);
        $.ajax({
            type: "POST",
            url: "../GetSubject2",
            data: ({v1: v1, v2: v2, v3: v3, v4: v4}),
            success: function(response) {
                $("#combination").html(response);
            },
            error: function(xhr) {
                alert(xhr.status);
                alert("error");
            }
        });
    });
});


 