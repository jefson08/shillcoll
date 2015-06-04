

$(document).ready(function() {
 $('#papercode').live('click',function(){
     var pcode=$(this).val();
     if($(this).is(':checked')){
         $('#'+pcode).css({display: "inline"});
     }
     else{
         $('#'+pcode).css({display: "none"});
     }
     })
 $('#genamount').live('click',function(){
    alert("anythin");
    $('#amount').val("2570");
     
 })
 
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
            data: ({rollno: $('#rollno').val()}),
            success: function(response) {
                $("#subject1").html(response);
            },
            error: function(xhr) {
                alert(xhr.status);
                alert("error");
            }
        });


    });


    $("input[name='generate']").click(function() {
        document.getElementById('subpaper').disabled = false;
        var v1 = document.getElementById('roll1').value;
        var v2 = document.getElementById('status').value;
        var v3 = document.getElementById('yos').value;
        //alert(v3);
        $.ajax({
            type: "POST",
            url: "../GetSubject1",
            data: ({v1: v1, v2: v2, v3: v3}),
            success: function(response) {
                $("#subject1").html(response);
            },
            error: function(xhr) {
                alert(xhr.status);
                alert("error");
            }
        });
    });



    $("input[name='generate1']").click(function() {
        document.getElementById('subpaper').disabled = false;
        var v1 = document.getElementById('roll1').value;
        var v2 = document.getElementById('status').value;
        var v3 = document.getElementById('yos').value;
      //   alert("hello");
        $.ajax({
            type: "POST",
            url: "../GetSubject1",
            data: ({v1: v1, v2: v2, v3: v3}),
            success: function(response) {
                $("#subject1").html(response);
            },
            error: function(xhr) {
                alert(xhr.status);
                alert("error");
            }
        });
    });
         
    $("input[name='genamount']").click(function() {
        alert("generate amount");
        
    });
    //$("input[name='amount1']").click(function(){
    
    
    
    $('.sub').click(function (){ 
     //$('#amount1').live('click',function(){
     
        var sum=0,i,val=0;
       //v=$(this).parent().parent().html();
       //var c = $(this).prev().find('input').is(':checked');
              var c = $('#tab').find('input[type="checkbox"]:checked').each(function (){
               //if(c){
             //  if ($(this).prev().find('input').is(":checked")) {
                    //v+=$(this).val();
                    //val=parseInt($(this).text());
                    sum=sum+parseInt($(this).text());
                    //sum += $(this).val() + ',';
                   // sum=100;
                });
           // alert(v);}
      
       
        //alert(val);
        alert(sum);
    });    
  
  //});
});
 