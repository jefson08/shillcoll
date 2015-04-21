/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function (){
    $('input[name=Add]').click(function(){
        //alert("hurrayy");
        var str='<tr><td>Paper Id</td>';
            str +='<td><input type="text" name="paperid" value="" /></td>';
            str +='<td>Paper Name</td>';
            str += '<td><input type="text" name="papername" value="" /></td></tr>';
                        
        $('#papers > tbody:last').append(str);
    })
});