
/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {

    var i  = $("#nextrow").val();
   
    
    
    $("#chkCategorydummy").live("click", function() {

        var chkval = $(this).is(':checked');
        var cval = $(this).attr("chkStat");
        $('input[name=chkCategory]').each(function(i, obj) {
            var v = $(this).attr('chkVal');
            if (cval == v) {
                $(this).val(chkval);
            }

        })
    })
$("#delIcon").live("click",function(){
   
    var imgno=$(this).attr('val');
    $('#' +  imgno).remove();
})
    $("select[name='cmbStream']").change(function() {

        if ($(this).val() !== -1) {

            PopulateDependentCombo(document.CourseClassHons.cmbStream, document.CourseClassHons.cmbSubjectName, '../populateSubjects');
        }
        else {
            $('#cmbSubjectName').empty();
        }
    })

    $('input[name=cmdAddMore]').click(function() {
//alert("hurrayy");
        //i = $("#nextrow").val()
        i++;
        var str = '<tr id="'+i+'"><td>Paper Id</td>';
        str += '<td><input type="text" name="txtPaperId" id="txtPaperId" value="" /></td>';
        str += '<td>Paper Name</td>';
        str += '<td><input type="text" name="txtPaperName" id="txtPaperName" value="" /></td>';
        str += '<td>Year or Semester No</td><td><select name="cmbYearOrSemNo" id="cmbYearOrSemNo">';
        str += '<option value="-1">-</option>';
        str += '<option value="1">1</option>';
        str += '<option value="2">2</option>';
        str += '<option value="3">3</option>';
        str += '<option value="4">4</option>';
        str += '<option value="5">5</option>';
        str += '<option value="6">6</option>';
        str += '<c:set var="yearOrSemNo" value="${param.cmbYearOrSem}"></c:set>';
        str += '</select></td>';
        str += '<td>';
        str += '<label>';
        str += '<input name ="chkCategorydummy" type ="checkbox" id ="chkCategorydummy" chkStat ="' + i;
        str += '" ${param.submitted and !empty(paramValues.chkCategorydummy['+i+'])?"checked":""} / > Honours';
        str += '</label>';
        str += '<input type = "hidden" name = "chkCategory" id = "chkCategory" chkVal = "' + i;
        str += '" value = "${param.submitted?paramValues.chkCategory['+i+']:false}" />';
        str += '</td> ';
        str += '<td><img src="../images/remove.png" id="delIcon" val="';
        str += i + '" /></td>';
        $('#papers > tbody:last').append(str);
    })
 
   $('select[name=cmbSubjectName]').change(function () {
    
        $('input[type=submit]').removeAttr('disabled');
        $('input[name=cmdAddMore]').removeAttr('disabled');
        var cmbSubjectName = $(this).val();
        
        $.ajax({
            type: "POST",
            url: "../CourseClassHonsRetrieve2",
            data: ({cmbSubjectName: cmbSubjectName}),
            success: function (response) {
                // $('#msg').html(response);
                $('#papers > tbody:last').html(response);
            }
            })
       
    })
    
});

  $('#frmCCHRetrieve').load(function(){
     $('input[name=cmdAddMore]').attr('disabled','disabled');
     $('input[type=submit]').attr('disabled','disabled');
 });

/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {

    var i  = $("#nextrow").val();
   
    
    
    $("#chkCategorydummy").live("click", function() {

        var chkval = $(this).is(':checked');
        var cval = $(this).attr("chkStat");
        $('input[name=chkCategory]').each(function(i, obj) {
            var v = $(this).attr('chkVal');
            if (cval == v) {
                $(this).val(chkval);
            }

        })
    })
$("#delIcon").live("click",function(){
   
    var imgno=$(this).attr('val');
    $('#' +  imgno).remove();
})
    $("select[name='cmbStream']").change(function() {

        if ($(this).val() !== -1) {

            PopulateDependentCombo(document.CourseClassHons.cmbStream, document.CourseClassHons.cmbSubjectName, '../populateSubjects');
        }
        else {
            $('#cmbSubjectName').empty();
        }
    })

    $('input[name=cmdAddMore]').click(function() {
//alert("hurrayy");
        //i = $("#nextrow").val()
        i++;
        var str = '<tr id="'+i+'"><td>Paper Id</td>';
        str += '<td><input type="text" name="txtPaperId" id="txtPaperId" value="" /></td>';
        str += '<td>Paper Name</td>';
        str += '<td><input type="text" name="txtPaperName" id="txtPaperName" value="" /></td>';
        str += '<td>Year or Semester No</td><td><select name="cmbYearOrSemNo" id="cmbYearOrSemNo">';
        str += '<option value="-1">-</option>';
        str += '<option value="1">1</option>';
        str += '<option value="2">2</option>';
        str += '<option value="3">3</option>';
        str += '<option value="4">4</option>';
        str += '<option value="5">5</option>';
        str += '<option value="6">6</option>';
        str += '<c:set var="yearOrSemNo" value="${param.cmbYearOrSem}"></c:set>';
        str += '</select></td>';
        str += '<td>';
        str += '<label>';
        str += '<input name ="chkCategorydummy" type ="checkbox" id ="chkCategorydummy" chkStat ="' + i;
        str += '" ${param.submitted and !empty(paramValues.chkCategorydummy['+i+'])?"checked":""} / > Honours';
        str += '</label>';
        str += '<input type = "hidden" name = "chkCategory" id = "chkCategory" chkVal = "' + i;
        str += '" value = "${param.submitted?paramValues.chkCategory['+i+']:false}" />';
        str += '</td> ';
        str += '<td><img src="../images/remove.png" id="delIcon" val="';
        str += i + '" /></td>';
        $('#papers > tbody:last').append(str);
    })
 
   $('select[name=cmbSubjectName]').change(function () {
    
        $('input[type=submit]').removeAttr('disabled');
        $('input[name=cmdAddMore]').removeAttr('disabled');
        var cmbSubjectName = $(this).val();
        
        $.ajax({
            type: "POST",
            url: "../CourseClassHonsRetrieve2",
            data: ({cmbSubjectName: cmbSubjectName}),
            success: function (response) {
                // $('#msg').html(response);
                $('#papers > tbody:last').html(response);
            }
            })
       
    })
    
});

  $('#frmCCHRetrieve').load(function(){
     $('input[name=cmdAddMore]').attr('disabled','disabled');
     $('input[type=submit]').attr('disabled','disabled');
 });
