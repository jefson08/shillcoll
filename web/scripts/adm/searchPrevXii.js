/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    var validator = $("#clxiiinfo").bind("invalid-form.validate", function () {
        $("#summary").html("Your form contains " + validator.numberOfInvalids() + " errors, see details below.");
    }).validate({
        debug: true,
        errorElement: "em",
        errorContainer: $("#warning, #summary"),
        errorPlacement: function (error, element) {
           error.appendTo(element.parent("td").next("td"));
        },
        success: function (label) {
            //label.text("ok!").addClass("success");
        },
        rules: {
            rollno: "alphanumeric",
        },
        submitHandler: function (form) {
            form.submit();
        }
    });
    $('input[name=SeachAll]').click(function () {
        document.location.href = '../clxiireport';
    })

    $('input[name=print]').click(function () {
        //alert($('#roll').val());
        document.location.href = '../clxiireport_single_pdf?rollno=' + $('#roll').val() + '';
    })

});
