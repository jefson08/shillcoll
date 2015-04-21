/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function (){
    $('#txtDOB').datepick({showTrigger: '#calImg', dateFormat:'dd-mm-yyyy',maxDate: new Date()});
    $("#txtDOB").mask("99-99-9999");
    
   // $("#txtPPhno").mask("9999999999");
   // $("#txtMobile").mask("9999999999");
    
    //PopulateCombo(document.stenroll.cmbCourseName,'../populateCourse');
    
    $("select[name='cmbCourseName']").change(function(){
        if($(this).val() !== -1){
            PopulateDependentCombo(document.stenroll.cmbCourseName,document.stenroll.cmbCombination,'../populateCombination');
        }
        else{
            $('#cmbCombination').empty();            
        }
    })
    
    $('input[name=copyaddress]').change(function(){
        if($(this).is(":checked")==true){
            $("#txtPAddress").attr("value", $("#txtMAddress").val());
            //$("#txtPAddress").attr("disabled", "disabled");
        }
        else if ($(this).is(":checked")==false){
            $("#txtPAddress").attr("value", "");
            //$("#txtPAddress").removeAttr("disabled"); 
        }
    })
});
    