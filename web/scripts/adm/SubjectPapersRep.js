/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
$("select[name='cmbStream']").change(function() {

        if ($(this).val() !== -1) {
//alert("onchange entered"+$(this).val());
            PopulateDependentCombo(document.SubjectPapers.cmbStream, document.SubjectPapers.cmbSubjectName, '../populateSubjects');
        }
        else {
            $('#cmbSubjectName').empty();
        }
    })
    });