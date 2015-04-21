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
            $('#cmbyearBt').get(0).selectedIndex = 0;
            $('#txtpaperId').val('');
            
            PopulateDependentCombo(document.stImprovement.cmbcourseName,document.stImprovement.cmbsemImproveSem,'../populateSemester');  
        }
        else{}
    });
   
    $("select[name='cmbsemImproveSem']").change(function(){
      
        if($(this).val() !== -1 || $(this).val()!=null){
            
            var coursecodevalue=$("#cmbcourseName").val();
            var sem=$("#cmbsemImproveSem").val();
            // reset value
            $('#txtpaperId').val('');
            $('#cmbuniRollno').get(0).selectedIndex = 0;
            $('#cmbyearBt').get(0).selectedIndex = 0;
            //populate paper name
            PopulateDependentComboFromTwoCondition(coursecodevalue,sem,document.stImprovement.cmbpaperName,'../populatePaperName');
        }
    });

   
    $("select[name='cmbpaperName']").change(function(){
      
        if($(this).val() !== -1 || $(this).val()!=null){
            
          
            //populating paper id in textbox [hidden]
            $("#txtpaperId").val($("#cmbpaperName").val());
            
            var coursecodevalue=$("#cmbcourseName").val();
            //populate batch
            PopulateDependentComboId(coursecodevalue,document.stImprovement.cmbyearBt,'../populateYearBatch');
        }
        else{ }
        
    });
 
 
    $("select[name='cmbyearBt']").change(function(){
        
        var yearbatch=$("#cmbyearBt").find(":selected").text();
        var coursecodevalue=$("#cmbcourseName").val();
     
        //populating nehu roll number
        PopulateDependentComboFromTwoCondition(coursecodevalue,yearbatch,document.stImprovement.cmbuniRollno,'../populateUniRollNO')
    });   
    
});


