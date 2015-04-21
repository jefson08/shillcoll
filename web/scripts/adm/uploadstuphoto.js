/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
    $("#photo").change(function() {
         $('form[name=uploadphoto]').prop('target','upload_target');
        //alert("hurray");
        $('form[name=uploadphoto]').ajaxForm({
            url: '../showUploadImage',
            beforeSubmit: function() {
                
                $('#processing').css({visibility: 'visible'});
                return true;
            },
            success: function(data) {
                
                var event = data.substr(0, 1);
                var output = data.substr(1);
                //$(".validateTips").text("All form fields marked with * are required.");
                //$(".validateTips").removeClass("ui-state-highlight", 0);
                if (event == 0) {
                    $('#processing').css({visibility: 'hidden'});
                    $("#photo").attr("value", "");
                    //updateTips(output);
                    alert(output);
                }
                else if (event == 1) {
                    
                    var img = "<img src='" + output + "' width='190' height='200'></img>";
                    $("#upload_target").contents().find("html").html(img);
                    $('#processing').css({visibility: 'hidden'});
                }
            },
            error: function(err) {
                // end progress bar

            }
        }
               
        );
        $('form[name=uploadphoto]').submit();
        $('form[name=uploadphoto]').prop('target','');
            })
}); 