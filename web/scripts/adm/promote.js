/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {
    $('input[name=radYearOrSem]').change(function() {
        $('select[name=cmbYearOrSemNo]').find("option").remove();//first clear the combo box then populate
        $('input[name=radYearOrSem]').each(function() {
            if ($(this).is(':checked'))
                YearOrSem = ($(this).attr('value'));
        })

        if (YearOrSem == 'y')
        {

            $('select[name=cmbYearOrSemNo]').each(function() {
                var that = this;
                var myOptions = {'-1': '-', y1: '1', y2: '2', y3: '3'};
                $.each(myOptions, function(val, text) {
                    $(that).append(new Option(text, val));
                })
            });
        }
        else if (YearOrSem == 's')
        {
            $('select[name=cmbYearOrSemNo]').each(function() {
                var that = this;
                var myOptions = {'-1': '-', s1: '1', s2: '2', s3: '3', s4: '4', s5: '5', s6: '6'};
                $.each(myOptions, function(val, text) {
                    $(that).append(new Option(text, val));
                })
            });
        }
    })
    
    $("select[name='cmbStream']").change(function() {
        PopulateDependentCombo(document.promote.cmbStream, document.promote.cmbCourse, '../populateCourse1');
    });
    
    $('#search').click(function (){
        
        $.ajax({
            type: "POST",
            url: "../getStuForPromote",
            data: ({cmbCourse: $('#cmbCourse').val(), cmbYearOrSemNo: $('#cmbYearOrSemNo').val()}),
            beforeSubmit: function () {
                // $('#processing').css({visibility: 'visible'});
                //alert("before submit");
                return true;
            },
            success: function (response) {
                //$('#msg').html(response);;
                $("#srchlist").html(response);
                
            },
            error: function (xhr) {
                alert(xhr.status);
            }
        });
    })
    $('#promote').live('click', function (){
        var rollno = $(this).attr('rollno');
        if($(this).is(':checked')){
            $('#'+rollno).attr("value", rollno);
        }
        else{
            $('#'+rollno).attr("value", '');
        }
        
    })
})