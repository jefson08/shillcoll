/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$.validator.prototype.checkForm = function () {
                //overriden in a specific page
                this.prepareForm();
                for (var i = 0, elements = (this.currentElements = this.elements()); elements[i]; i++) {
                    if (this.findByName(elements[i].name).length !== undefined && this.findByName(elements[i].name).length > 1) {
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
      var validator = $("#boardnamesubject").bind("invalid-form.validate", function () {
        $("#summary").html("Your form contains " + validator.numberOfInvalids() + " errors, see details below.");
    }).validate({
        debug: true,
        errorElement: "em",
        errorContainer: $("#warning, #summary"),
        errorPlacement: function (error, element) {
          if (element.attr("type") === "radio") {
                error.insertAfter("#radErr");
            } 
            error.appendTo(element.parent("td").next("td"));
        },
        success: function (label) {
            //label.text("ok!").addClass("success");
        },
        rules: {
            txtBoaName: "nameTextBox",
            txtStream: "required",
            txtSubName:"nameTextBox"
        },
        submitHandler: function (form) {
            form.submit();
        }
    });
    
    $('#cmdSave').click(function(){
                $('[id^="txtSubName"]').each(function(){
                    if ($(this).val().length>0){
                       // alert($(this).val());
                       $(this).rules('add', {
                            nameTextBox: true,
                            required:true,
                        });  
                        
                    }
                })
            })
    
    var count = 3;
    $("#ADDIcon").live("click", function () {
        // alert("hurrayy");
        count++;
        var str = '<tr id=' + count + '><td>Subject *</td>';
        str += '<td><input type="text" name="txtSubName" id=name="txtSubName['+count+']" value="" size="50"/></td>';
        str+='<td><img src="../images/remove.png" alt="Remove" imgno='+count+' id="DelIcon"/>&nbsp;';
        str+='<img src="../images/add.png" alt="Add" imgno='+count+' id="ADDIcon"/></td>';
        str += '<td></td></tr>';
        $('#subjectName > tbody:last').append(str);
    })


    $("#DelIcon").live("click", function () {

        var del = $(this).attr("imgno");
//     alert(del);
        $('#' + del).remove();
    });

});