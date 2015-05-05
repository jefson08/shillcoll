/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
 $("select[name='cmbHead']").change(function () {
     //$('input[type=submit]').removeAttr('disabled');
       // $('input[name=cmdAddMore]').removeAttr('disabled');
       $('#ASHADiv').hide();
        var accheadcode = $(this).val();
        
        $.ajax({
            type: "POST",
            url: "../GetLeafSubHeads",
            data: ({accheadcode: accheadcode}),
            success: function (response) {
                // $('#msg').html(response);
                $('#SHAmt > tbody:last').html(response);
            }
            })
  })
  });