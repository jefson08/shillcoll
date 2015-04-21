/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    var count = 4;
    $("#ADDIcon").live("click", function () {
        // alert("hurrayy");
        count++;
        var str = '<tr id=' + count + '><td>Subject *</td>';
        str += '<td><input type="text" name="txtSubName" value="" size="50"/></td>';
        str+='<td><img src="../images/remove.png" alt="Remove" imgno='+count+' id="DelIcon"/>&nbsp;';
        str+='<img src="../images/add.png" alt="Add" imgno='+count+' id="ADDIcon"/></td>';
        str += '</tr>';
        $('#subjectName > tbody:last').append(str);
    })


    $("#DelIcon").live("click", function () {

        var del = $(this).attr("imgno");
//     alert(del);
        $('#' + del).remove();
    });

});