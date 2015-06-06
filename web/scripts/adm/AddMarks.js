/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, cho/* 
 
 */

$(document).ready(function() {
     var YearOrSem='s';
 $('input[name=cmdSearch]').click(function () {
   //$('#srchlist').hide();
        $('input[type=submit]').removeAttr('disabled');
        $('input[name=cmdAddMore]').removeAttr('disabled');
        var txtNehuRollNo = $('#txtNehuRollNo').val();
        var cmbNR=$('#cmbNR').val();
        var cmbYearOrSemNo=$('#cmbYearOrSemNo').val(); 
       
        $.ajax({
            type: "POST",
            url: "../GetExamPapers",
            data: ({txtNehuRollNo: txtNehuRollNo, cmbNR: cmbNR, cmbYearOrSemNo:cmbYearOrSemNo}),
            success: function (response) {
                // $('#msg').html(response);
                 $('#srchlist').html(response);
            }
            })
       
    })
    $('input[name=radYearOrSem]').change(function(){
    
      $('select[name=cmbYearOrSemNo]').find("option").remove();//first clear the combo box then populate
    $('input[name=radYearOrSem]').each(function(){
        if($(this).is(':checked'))
            YearOrSem=($(this).attr('value'));
    })
    
    if (YearOrSem=='y')
    {
            
        $('select[name=cmbYearOrSemNo]').each(function(){
            var that=this;
            var myOptions = {'-1':'-', y1 : '1', y2: '2', y3: '3' };
            $.each(myOptions, function(val, text) { 
                                      $(that).append(new Option(text, val));})
    });
    }
    else if(YearOrSem=='s')
        {
             $('select[name=cmbYearOrSemNo]').each(function(){
            var that=this;
            var myOptions = { '-1':'-', s1 : '1', s2: '2', s3: '3',s4: '4', s5: '5', s6: '6'};
            $.each(myOptions, function(val, text) { 
                                      $(that).append(new Option(text, val));})
    });
    }
})
 $('select[name=cmbYearOrSemNo]').change(function()
 {
     if (($(this).attr('value')=='s6') || ($(this).attr('value')=='y3'))
     { 
         $('select[name=cmbDiv]').removeAttr('disabled');
          $('select[name=cmbPos]').removeAttr('disabled');
     }
     else
     {
        $('select[name=cmbDiv]').attr('disabled','disabled'); 
        $('select[name=cmbPos]').attr('disabled','disabled'); 
     }
     })
      var currentYear = (new Date).getFullYear();
    $("#eyear").empty();
    for (var i = 1; i <= 2; i++) {
        $("#eyear").append("<option value='" + currentYear + "'>");
        currentYear++;
    }
      $("#txtNehuRollNo").bind('input', function (){  
        var roll = document.getElementById('txtNehuRollNo').value;
      
        $.ajax({
            url : "../Marks_PopulateRollNo",
            type : "POST",
            async:false,
            data : {
                term : roll
            },
            dataType : "json",
            success : function(response) {
                
                $("#nehurollno").empty();
                for(var i=0, len=response.length; i<len; i++) {
                    $("#nehurollno").append("<option value='" +jQuery.trim(response[i]) + "'>");
                }
                
            },
            error: function (xhr) {
            //alert(xhr.status);
            }
            
        });
    });
   });

     
     


