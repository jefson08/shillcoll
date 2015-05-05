/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {
    $('#txtFees').datepick({showTrigger: '#calImg', dateFormat: 'dd-mm-yyyy', maxDate: new Date()});
    $("#txtFees").mask("99-99-9999");
    $('input[name=next]').attr('disabled', 'disabled');
//$('input[name=sub]').change(function(){

    $("#stream").live("change", function() {
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

         alert($("#cmbhon").val());
        // 
        if ($(this).val() != -1) {
                 PopulateDependentComboId($("#cmbhon").val(), document.fees.cmbrollno, '../populateRollno');
        }   
    });

});


 