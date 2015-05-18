///* 
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
$(document).ready(function() {
         
         
//    var opts = {
//        lines: 13, // The number of lines to draw
//        length: 7, // The length of each line
//        width: 2, // The line thickness
//        radius: 0, // The radius of the inner circle
//        corners: 1, // Corner roundness (0..1)
//        rotate: 0, // The rotation offset
//        direction: 1, // 1: clockwise, -1: counterclockwise
//        color: '#000', // #rgb or #rrggbb or array of colors
//        speed: 1.6, // Rounds per second
//        trail: 38, // Afterglow percentage
//        shadow: true, // Whether to render a shadow
//        hwaccel: false, // Whether to use hardware acceleration
//        className: 'spinner', // The CSS class to assign to the spinner
//        zIndex: 2e9, // The z-index (defaults to 2000000000)
//        top: '50%', // Top position relative to parent
//        left: '50%' // Left position relative to parent
//    };
//    var target = document.getElementById('txtBatch');
//    var spinner = new Spinner(opts).spin(target);
    //    $('#radYear').click(function() {
    //        
    //        alert(jQuery( 'input[name=radYearOrSem]:checked' ).val());
    //        
    //    });
    //    $('#radSem').click(function() {
    //        
    //        alert(jQuery( 'input[name=radYearOrSem]:checked' ).val());
    //        
    //    });
  
    
    $('#txtPmtDate').datepick({
        showTrigger: '#calImg', 
        dateFormat: 'dd-mm-yyyy', 
        maxDate: new Date()
    });
    $("#txtPmtDate").mask("99-99-9999");
     
    //binding datalist to event //and populate college roll number 
    $("#rollno").bind('input', function (){  
        var roll = document.getElementById('rollno').value;
      
        $.ajax({
            url : "../examinfo_PopulateRollno",
            type : "POST",
            async:false,
            data : {
                term : roll
            },
            dataType : "json",
            success : function(response) {
                
                $("#crollno").empty();
                for(var i=0, len=response.length; i<len; i++) {
                    $("#crollno").append("<option value='" +jQuery.trim(response[i]) + "'>");
                }
                
            },
            error: function (xhr) {
            //alert(xhr.status);
            }
            
        });
    });
     
    //this is for firing an event when dalatist option is selected from college roll number
    //and populate university roll number
    document.getElementById('rollno').addEventListener('input', function () {
        var Rollno = document.getElementById('rollno').value;
        
        $.ajax({
            type: "POST",
            url: "../examinfo_PopulateUnirollnoAuto",
            data: ({          
                term: Rollno
            }),
            success: function (response) {
                
                if(!jQuery.trim(response))
                    response="AF";
                
                //calling function to populate batch
                var rollnum = document.getElementById('rollno').value;
                $(this).getBatch(jQuery.trim(rollnum));
                
                //populate nehu roll number
                $('#txtUnirollno').val(jQuery.trim(response));
                
                //calling function to populate reg no based on nehu roll number
                $(this).getRegNo(jQuery.trim(response));
               
                //calling a function for generating examid
                $(this).getExamid(jQuery.trim(rollnum));
                
            },
            error: function (xhr) {
            //alert(xhr.status);
            }
        });
    });
  
    //function to generate exam id
    (function( $ ){
       
        $.fn.getExamid = function(param){
            $.ajax({
                type: "POST",
                url: "../examinfo_generate_examid",
                data: ({          
                    term: param
                }),
                success: function (response) {
                    
                // if(!jQuery.trim(response))
                //   response="AF";
                
                // $("#txtBatch").val(jQuery.trim(response));
                },
                error: function (xhr) {
                //            //alert(xhr.status);
                }
            });
        }
    })( jQuery );
  
  
    //function to populate batch
    (function( $ ){
        $.fn.getBatch = function(param){
            $.ajax({
                type: "POST",
                url: "../examinfo_PopulateBatch",
                data: ({          
                    term: param
                }),
                success: function (response) {
                    
                    if(!jQuery.trim(response))
                        response="AF";
                
                    $("#txtBatch").val(jQuery.trim(response));
                },
                error: function (xhr) {
                //            //alert(xhr.status);
                }
            });
        }
    })( jQuery );
    
    //function to populate regno if available
    (function( $ ){
        $.fn.getRegNo = function(param){
            $.ajax({
                type: "POST",
                url: "../examinfo_PopulateRegno",
                data: ({          
                    term: param
                }),
                success: function (response) {
                    
                    if(!jQuery.trim(response))
                        response="AF";
                
                    $("#txtRegno").val(jQuery.trim(response));
                },
                error: function (xhr) {
                //            //alert(xhr.status);
                }
            });
        }
    })( jQuery );


//    //binding datalist to event //and populate batch
//    $("#datalstbatch").bind('input', function (){  
//        var bat = document.getElementById('datalstbatch').value;
//        // alert(batch);
//        $.ajax({
//            url : "../examinfo_PopulateBatch",
//            type : "POST",
//            async:false,
//            data : {
//                term : bat
//            },
//            dataType : "json",
//            success : function(response) {
//                
//                $("#batch").empty();
//                for(var i=0, len=response.length; i<len; i++) {
//                    $("#batch").append("<option value='" +jQuery.trim(response[i]) + "'>");
//                }
//            },
//            error: function (xhr) {
//            //alert(xhr.status);
//            }
//            
//        });
//    });
//    
    
//this is for firing an event when dalatist option is selected from university roll number
//and populate registration number
//    document.getElementById('datalstunirollno').addEventListener('input', function () {
//        var uniroll = document.getElementById('datalstunirollno').value;
//     
//        $.ajax({
//            type: "POST",
//            url: "../examinfo_PopulateRegno",
//            data: ({          
//                term: uniroll
//            }),
//            success: function (response) {
//                
//                $("#regno").empty();
//                $("#regno").append("<option value='" +jQuery.trim(response) + "'>");
//            },
//            error: function (xhr) {
//            //alert(xhr.status);
//            }
//        });
// });
    
//    $("#datalstunirollno").bind('input', function (){     
//        var roll = document.getElementById('datalstunirollno').value;
//        $.ajax({
//            url : "../examinfo_PopulateUnirollno",
//            type : "POST",
//            async:false,
//            data : {
//                term : roll
//            },
//            dataType : "json",
//            success : function(response) {
//                var dataList = $("#unirollno");
//                dataList.empty();
//                for(var i=0, len=response.length; i<len; i++) {
//                    dataList.append("<option value='" +jQuery.trim(response[i]) + "'>");
//                }
//            },
//            error: function (xhr) {
//                alert(xhr.status);
//            }
//        });
//    });
  
});


