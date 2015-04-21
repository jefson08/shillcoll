/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
    var rollno;
    $('input[name=cmdsrch]').click(function() {
        $("#offset").attr("value", "0");
        $('form[name=srchstu]').ajaxForm({
            url: '../srchimprovement',
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
    
    $(".editStu").live("click",function(){
       rollno = $(this).attr("rollno");
       
       $('#rollno').attr("value", rollno);
      
       $("#editstudent").submit();
      
   })
});