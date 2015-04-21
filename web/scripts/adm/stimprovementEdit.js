/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function (){
    
    $("select[name='cmbcourseName']").change(function(){
        
        if($(this).val() !== -1 && $(this).val()!=null){
            
            //        reset combo box  
            $('#cmbuniRollno').get(0).selectedIndex = 0;
            $('#cmbsemImproveSem').get(0).selectedIndex = 0;
            $('#cmbpaperName').get(0).selectedIndex = 0;
            $('#cmbpaperNameEdit').get(0).selectedIndex = 0;
            $('#txtSemEdit').val('');
            $('#txtpaperId').val('');
             
            var coursecodevalue=$("#cmbcourseName").val();
            //populate nehu roll number
            PopulateDependentComboId(coursecodevalue,document.stImprovementEdit.cmbuniRollno,'../populateUniRollNoEdit');
        }
    });
    
    $("select[name='cmbuniRollno']").change(function(){
        
        //        reset combo box  
           
        $('#cmbsemImproveSem').get(0).selectedIndex = 0;
        $('#cmbpaperName').get(0).selectedIndex = 0;
        $('#cmbpaperNameEdit').get(0).selectedIndex = 0;
        $('#txtSemEdit').val('');
        $('#txtpaperId').val('');
        //populate paper name based on previously applied papers
        var uniroll=$("#cmbuniRollno").val(); 
       
        PopulateDependentComboId(uniroll,document.stImprovementEdit.cmbpaperName,'../populatePaperNameEdit');
    });
    
    $("select[name='cmbpaperName']").change(function(){
      
        if($(this).val() !== -1 || $(this).val()!=null){
            
            //        reset combo box  
           
            $('#cmbsemImproveSem').get(0).selectedIndex = 0;
            $('#cmbpaperNameEdit').get(0).selectedIndex = 0;
            $('#txtSemEdit').val('');
            $('#txtpaperId').val('');
            
            //populating paper id / Semester in textbox 
            $("#txtpaperId").val($("#cmbpaperName").val());
          
            
            var uniroll=$("#cmbuniRollno").val(); 
            var pid=$("#txtpaperId").val(); 
            //populate SemesterEdit [hidden combo]
            //PopulateDependentComboFromTwoCondition(uniroll,pid,document.stImprovementEdit.cmbsemImproveSem,'../populateSemesterEdit'); 
            $.ajax({
            type: "POST",
            url: "../populateSemesterEdit",
            data: ({uniroll: uniroll, pid: pid}),
            beforeSubmit: function () {
                // $('#processing').css({visibility: 'visible'});
                //alert("before submit");
                return true;
            },
            success: function (response) {
                //$('#msg').html(response);
                //$('#subjectName').empty();
                alert(response);
                $("#txtSemEdit").val(response);
                var coursecodevalue=$("#cmbcourseName").val();
                var impvsem=response;
                PopulateDependentComboFromTwoCondition(coursecodevalue,impvsem,document.stImprovementEdit.cmbpaperNameEdit,'../populatePaperName');
            },
            error: function (xhr) {
                alert(xhr.status);
            }
        });
           
        }
           
        //this is to set an interval and populate paper
//        var $sem = $("#cmbsemImproveSem");
//        $sem.data("value", $sem.val());
//        setInterval(function() {
//            var data = $sem.data("value"),
//            val = $sem.val();
//
//            if (data !== val) {
//                $sem.data("value", val);
//                //populate semester for edit txtbox 
//                $("#txtSemEdit").val($("#cmbsemImproveSem").val());
//                //populating paper name for edit
                var coursecodevalue=$("#cmbcourseName").val();
                var impvsem=$("#txtSemEdit").val();
                PopulateDependentComboFromTwoCondition(coursecodevalue,impvsem,document.stImprovementEdit.cmbpaperNameEdit,'../populatePaperName');
//            }
//        }, 1);
       
    });
  
    $("select[name='cmbpaperNameEdit']").change(function(){
      
      
        if($(this).val() !== -1){
            //update paper id / Semester in textbox 
            $("#txtpaperId").val($("#cmbpaperNameEdit").val());
        }
    });
             
    //  delete duplicate item from cmbpapernameEdit
    setInterval(function() {
            
        var length = $('#cmbpaperNameEdit > option').length;
        var pvalue=$("#cmbpaperName").val();
        if(length>1 && pvalue!='-1'){
           
            $("#cmbpaperNameEdit option[value='"+pvalue+"']").remove(); 
        }
    }, 1);
});