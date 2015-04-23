<%-- 
    Document   : uploadstuphoto
    Created on : Apr 20, 2015, 11:41:13 AM
    Author     : B Mukhim
--%>
<%@page  autoFlush="true" buffer="32kb" %>
<jsp:useBean id="stuEnroll" class="shg.bean.StudentEnroll" scope="session" ></jsp:useBean>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="../scripts/jquery/jquery-1.6.2.min.js"></script>
        <script type="text/javascript" src="../scripts/jqueryform/ajaxupload.3.5.js"></script>
        <script type="text/javascript" src="../scripts/jqueryform/jquery.form.js"></script>
        <script type="text/javascript" src="../scripts/adm/uploadstuphoto.js"></script>
        <title>Upload Student's Photo</title>
    </head>
    <body>
        <form name='uploadphoto' enctype="multipart/form-data" method="post" action="uploadstuphoto.jsp">
            <table>
                <tr>
                    <td colspan="3"><h3>Record Successfully Added</h3></td>
                </tr>
                <tr>
                    <td>Student's Name</td>
                    <td> : </td>
                    <td>
                        <b>${stuEnroll.txtStuName}</b>
                    </td>
                </tr>
                <tr>
                    <td colspan="3">His/Her College Rollno is : <b>${stuEnroll.rollno}</b></td>
                </tr>
                <tr>
                    <td>Student' Passport Photo *</td>
                    <td> : </td>
                    <td>
                        <input name="photo" type="file" id="photo">
                        <span id="processing" style="visibility: hidden;"><img width="26" height="28" src="../images/loading_1.gif"></span>
                        <font size="2"><br>file types: .gif, .png, .jpg, .tiff - max size 1Mb </font>
                    </td>
                </tr>
                <tr>
                    <td colspan="3" style="text-align: right">
                        <iframe id="upload_target" name="upload_target" src="" style="width:190px;height:200px;" frameborder="0" marginheight="0" marginwidth="0"></iframe>
                    </td>
                </tr>
                <tr>
                    <td colspan="3">
                        <input type="submit" name="upload" value="Upload Photo" />
                        <input type="button" name="cancel" value="Skip" />
                    </td>
                    
                </tr>
            </table>
        </form>
    </body>
</html>
