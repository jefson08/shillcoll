/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$.validator.prototype.checkForm = function () {
    //overriden in a specific page
    this.prepareForm();
    for (var i = 0, elements = (this.currentElements = this.elements()); elements[i]; i++) {
        if (this.findByName(elements[i].name).length != undefined && this.findByName(elements[i].name).length > 1) {
            for (var cnt = 0; cnt < this.findByName(elements[i].name).length; cnt++) {
                this.check(this.findByName(elements[i].name)[cnt]);
            }
        } else {
            this.check(elements[i]);
        }
    }
    return this.valid();
}

$(document).ready(function () {
    $('#txtDOB').datepick({showTrigger: '#calImg', dateFormat: 'dd-mm-yyyy', maxDate: new Date()});
    $("#txtDOB").mask("99-99-9999");
    $("#txtPPhno").mask("9999999999");
    $("#txtMobile").mask("9999999999");

    var validator = $("#stenroll").bind("invalid-form.validate", function () {
        $("#summary").html("Your form contains " + validator.numberOfInvalids() + " errors, see details below.");
    }).validate({
        debug: true,
        errorElement: "em",
        errorContainer: $("#warning, #summary"),
        errorPlacement: function (error, element) {
<<<<<<< HEAD

=======
>>>>>>> origin/master
            if (element.attr("type") === "radio") {
                if (element.attr("name") === "radYearOrSem")
                    error.appendTo(".radSYR");
                if (element.attr("name") === "radGender")
                    error.appendTo(".radGEN");
                if (element.attr("name") === "radCategory")
                    error.appendTo(".radCAT");
            }
            else
                error.appendTo(element.parent("td").next("td"));
        },
        success: function (label) {
            //label.text("ok!").addClass("success");
        },
        rules: {
            txtStuName: "nameTextBox",
            radYearOrSem: "required",
            txtDOB: "date",
            txtFName: "nameTextBox",
            txtMName: "nameTextBox",
            txtPOccup: "nameTextBoxOptional",
            txtMAddress: "alphaNumSpaceNewLine",
            txtPAddress: "alphaNumSpaceNewLine",
            txtEmail: {
                email: true,
            },
            txtIncome: "float",            
        },
        submitHandler: function (form) {
            form.submit();
        }
    });



    //PopulateCombo(document.stenroll.cmbCourseName,'../populateCourse');

    $("select[name='cmbCourseName']").change(function () {
        if ($(this).val() != -1) {
            PopulateDependentCombo(document.stenroll.cmbCourseName, document.stenroll.cmbCombination, '../populateCombination');
        }
        else {
            $('#cmbCombination').empty();
        }
    })

    $("select[name='cmbCountry']").change(function () {

        if ($(this).val() != -1) {
            PopulateDependentCombo(document.stenroll.cmbCountry, document.stenroll.cmbState, '../populateState');
        }
        else {
            $('#cmbDistrict').empty();
            $('#cmbState').empty();

        }
    })

    $("select[name='cmbState']").change(function () {
        if ($(this).val() != -1) {
            PopulateDependentCombo(document.stenroll.cmbState, document.stenroll.cmbDistrict, '../populateDistrict');
        }
        else {
            $('#cmbDistrict').empty();
        }
    })

    $('input[name=copyaddress]').change(function () {
        if ($(this).is(":checked") == true) {
            $("#txtPAddress").attr("value", $("#txtMAddress").val());
            //$("#txtPAddress").attr("disabled", "disabled");
        }
        else if ($(this).is(":checked") == false) {
            $("#txtPAddress").attr("value", "");
            //$("#txtPAddress").removeAttr("disabled"); 
        }
    })
});
    
