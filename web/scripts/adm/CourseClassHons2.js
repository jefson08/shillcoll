/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
// $('#txtDOB').datepick({showTrigger: '#calImg', dateFormat:'dd-mm-yyyy',maxDate: new Date()});
// $("#txtDOB").mask("99-99-9999");
// });
// $("#txtPPhno").mask("9999999999");
// $("#txtMobile").mask("9999999999");

//PopulateCombo(document.CourseClassHons.cmbCourseName,'../populateCourse');
    var i = 3;
    
        
    $("#chkCategorydummy").live("click", function() {
//alert($(this).attr("chkStat") +"--"+$(this).is(':checked'));
        var chkval = $(this).is(':checked');
        var cval = $(this).attr("chkStat");
        $('input[name=chkCategory]').each(function(i, obj) {
            var v = $(this).attr('chkVal');
            if (cval == v) {
                $(this).val(chkval);
            }

        })
    })
    $('input[name=cmdNext]').click(function(){
        $('#CCHDiv').hide();
        $('input[name=cmdAddMore]').removeAttr('disabled');
        $('input[name=cmdNext]').attr('disabled', 'disabled');
        $('select[name=cmbCourseName]').val(-1);
        $('select[name=cmbStream]').val(-1);
        $('select[name=cmbSubjectName]').val(-1);
        $('select[name=cmbYearOrSemNo]').each(function() {
            $(this).val(-1);
        })
        $('input[name=txtPaperId]').each(function() {
            $(this).val('');
        })
        $('input[name=txtPaperName]').each(function() {
            $(this).val('');
        })
        $('input[name=chkCategorydummy]').each(function() {
            $(this).removeAttr('checked');
        })
        $('input[name=chkCategory]').each(function(i, obj) {
            var v = $(this).attr('false');
        })
    })

    
    $("select[name='cmbStream']").change(function() {

        if ($(this).val() !== -1) {
//alert("onchange entered"+$(this).val());
            PopulateDependentCombo(document.CourseClassHons.cmbStream, document.CourseClassHons.cmbSubjectName, '../populateSubjects');
        }
        else {
            $('#cmbSubjectName').empty();
        }
    })

    $('input[name=cmdAddMore]').click(function() {
//alert("hurrayy");
        i++;
        var str = '<tr><td>Paper Id</td>';
        str += '<td><input type="text" name="txtPaperId" value="" /></td>';
        str += '<td>Paper Name</td>';
        str += '<td><input type="text" name="txtPaperName" value="" /></td>';
        str += '<td>Year/Semester Number</td><td><select name="cmbYearOrSemNo" id="cmbYearOrSemNo">';
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
        str += '" ${param.submitted and (paramValues.chkCategory[' + i + '])?"checked":""} / > Honours';
        str += '</label>';
        str += '<input type = "hidden" name = "chkCategory" id = "chkCategory" chkVal = "' + i;
        str += '" value = "${param.submitted?paramValues.chkCategory[' + i + ']:false}" />';
        str += '</td>';
        $('#papers > tbody:last').append(str);
    })


  
});

  