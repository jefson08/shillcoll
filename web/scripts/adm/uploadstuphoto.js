/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
    $("#photo").change(function() {
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
            beforeSubmit: function() {
                $('#processing').css({visibility: 'visible'});
                return true;
            },
            success: function(data) {
                var event = data.substr(0, 1);
                var output = data.substr(1);
                if (event == 0) {
                    $('#processing').css({visibility: 'hidden'});
                    $("#photo").attr("value", "");
                    //updateTips(output);
 }
                else if (event == 1) {
                    $('form[name=uploadphoto]').prop('target', '');
                    var img = "<img src='" + output + "' width='190' height='200'></img>";
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