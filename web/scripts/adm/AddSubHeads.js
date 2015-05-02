/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
   var rows= $("#row").val();
    $('input[name=cmdNext]').click(function(){
        $('#ASHDiv').hide();
        $('input[name=cmdAddMore]').removeAttr('disabled');
        $('input[name=cmdNext]').attr('disabled', 'disabled');
        $('select[name=cmbHead]').val(-1);
        alert(rows);
        $('input[name=txtSubHead]').each(function() {
               $(this).val('');
            })
           while(rows>0) 
           {
              $('#' +  rows).remove(); 
              --rows;
           }
        })
        
               
    

    $('input[name=cmdAddMore]').click(function() {
       //  alert(rows);
         var str = '<tr><td>Sub Head</td>';
        str += '<td><input type="text" name="txtSubHead" value="" /></td>';
        $('#SubHeads > tbody:last').append(str);
    })

  
  
});

  