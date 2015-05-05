

$(document).ready(function() {
    $('input[name=next]').attr('disabled', 'disabled');
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
    $("select[name='rollno']").change(function() {
       
      //alert($("#rollno").val());
     
         $.ajax({
             
           type: "POST",
           url: "../GetSubject1",
           data:  ({rollno: $('#rollno').val()}),
            success: function(response) {
                $("#subject1").html(response);
            },
           error: function(xhr) {
                alert(xhr.status);
                alert("error");
           }
        });
       
   
});


 $("input[name='generate']").click(function () {
     document.getElementById('subpaper').disabled=false;
     var v1=document.getElementById('roll1').value;
     var v2=document.getElementById('status').value;
     var v3=document.getElementById('yos').value;
    // alert(v3);
         $.ajax({
             
           type: "POST",
           url: "../GetSubject1",
           data:  ({v1:v1, v2:v2 ,v3:v3}),
            success: function(response) {
                $("#subject1").html(response);
            },
           error: function(xhr) {
                alert(xhr.status);
                alert("error");
           }
        });
        }); 
});
 