<%-- 
    Document   : uploadstuphoto
    Created on : Apr 20, 2015, 11:41:13 AM
    Author     : B Mukhim
--%>
<%if ("GET".equalsIgnoreCase(request.getMethod())) {
        out.print("Invalid request -- Please try again");
        return;
    }%>
<%@page  autoFlush="true" buffer="32kb" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="stuEnroll" class="shg.bean.StudentEnroll" scope="session" ></jsp:useBean>
<jsp:useBean id="imgDb" class="shg.dao.saveImageToDB"></jsp:useBean>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../style/loader.css" rel="stylesheet" />
        
        <script type="text/javascript" src="../scripts/jquery/jquery-1.6.2.min.js"></script>
        <script type="text/javascript" src="../scripts/jqueryform/ajaxupload.3.5.js"></script>
        <script type="text/javascript" src="../scripts/jquery/script.js"></script>
        <script type="text/javascript" src="../scripts/jqueryform/jquery.form.js"></script>
        <script type="text/javascript" src="../scripts/adm/uploadstuphoto.js"></script>
        
        <title>Upload Student's Photo</title>
    </head>
    <body>
        <div class="box-1">
        <form name='uploadphoto' enctype="multipart/form-data" method="post" action="uploadstuphoto.jsp">
            <input type="hidden" name="psubmitted" value="true" />
            <table>
                <tr>
                    <td colspan="3" style="text-align: center"><h3>Student's Record Successfully Added</h3></td>
                    <td rowspan="5" style="vertical-align: top">
                        <iframe id="upload_target" name="upload_target" src="" style="width:160px;height:170px;" frameborder="0" marginheight="0" marginwidth="0"></iframe>
                    </td>
               </tr>
                <tr>
                    <td>Student's Name</td>
                    <td> : </td>
                    <td>
                        <b>${stuEnroll.txtStuName}</b>

                    </td>
                </tr>
                <tr>
                    <td colspan="3">His/Her College Rollno is : <b>${stuEnroll.rollno}</b>
                        <input type="hidden" name="rollno" value="${stuEnroll.rollno}" />
                    </td>
                </tr>
                <tr>
                    <td>Student' Passport Photo *</td>
                    <td> : </td>
                    <td>
                        <input name="photo" type="file" id="photo">
                        <font size="2"><br>file types: .gif, .png, .jpg, .tiff - max size 1Mb </font><br />
                    </td>
                </tr>
                
                <tr>
                    <td colspan="3">
                        <input type="submit" name="upload" id="upload" value="Upload Photo" />
                        <input type="button" name="cancel" id="cancel" value="Later" />
                    </td>

                </tr>
            </table>
        </form>
        <c:if test="${!param.submitted1}">
            <%if ("POST".equalsIgnoreCase(request.getMethod())) {
                    String result = imgDb.saveImage(request, getServletContext());
                    out.print("<br /><br /><center><h4>"+result+"</h4></center>");
// Form was submitted.
                }%>

        </c:if>
                    </div>
    </body>
</html>
