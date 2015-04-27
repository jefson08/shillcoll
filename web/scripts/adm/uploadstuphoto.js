/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
    $('#upload').click(function(){
        
        if($.trim($('#photo').val()) == ''){
            alert('photo no selected');
            return false;
        }
        $('form[name=uploadphoto]').submit();
    })
    
    $('#cancel').click(function(){
        window.location = "home.jsp";
    })
    
    $("#photo").change(function() {
        var box1;
        $('form[name=uploadphoto]').prop('target', 'upload_target');
        var fd = new FormData();
        fd.append('photo', $('#photo')[0].files[0]);

        $.ajax({
            type: "POST",
            url: "../showUploadImage",
            data: fd,
            processData: false,
            contentType: false,
            mimeType: "multipart/form-data",
            beforeSend: function() {
                //$('#processing').css({visibility: 'visible'});
                box1 = new ajaxLoader(".box-1");
                return true;
            },
            success: function(data) {
                var event = data.substr(0, 1);
                var output = data.substr(1);
                if (box1)
                    box1.remove();
                if (event == 0) {
                    $('#processing').css({visibility: 'hidden'});
                    $("#photo").attr("value", "");
                    //updateTips(output);
 }
                else if (event == 1) {
                    $('form[name=uploadphoto]').prop('target', '');
                    var img = "<img src='" + output + "' width='160' height='170'></img>";
                    $("#upload_target").contents().find("html").html(img);
                    $('#processing').css({visibility: 'hidden'});                    
                }
            },
            error: function(xhr) {
                alert(xhr.status);
            }
        });
    })
}); 