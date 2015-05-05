<%-- 
    Document   : editstuphoto
    Created on : Apr 20, 2015, 11:41:13 AM
    Author     : B Mukhim
--%>
<%@page import="shg.util.shgUtil"%>
<%if ("GET".equalsIgnoreCase(request.getMethod())) {
        out.print("Invalid request -- Please try again");
        return;
    }%>
<%@page  autoFlush="true" buffer="32kb" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="stuEnroll" class="shg.bean.StudentEnroll"></jsp:useBean>
<jsp:useBean id="imgDb" class="shg.dao.saveImageToDB"></jsp:useBean>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../style/loader.css" rel="stylesheet" />
        <link href="../style/master-css/style.css" rel="stylesheet" />
        <link href="../style/master-css/master-layout.css" rel="stylesheet" />
        <link href="../style/master-css/menu-style.css" rel="stylesheet" />
        <link rel="stylesheet" href="../style/master-css/sweet-alert.css">

        <script type="text/javascript" src="../scripts/jquery/jquery-1.6.2.min.js"></script>
        <script type="text/javascript" src="../scripts/jqueryform/ajaxupload.3.5.js"></script>
        <script type="text/javascript" src="../scripts/jquery/script.js"></script>
        <script type="text/javascript" src="../scripts/jqueryform/jquery.form.js"></script>
        <script type="text/javascript" src="../scripts/adm/uploadstuphoto.js"></script>
        <script type="text/javascript" src="../scripts/jquery/sweet-alert.min.js"></script>
        <title>Upload Student's Photo</title>
    </head>
    <body>
        <div id="header" ><%@include file="common-menu.jsp" %>
            <span id="header-span"><%=application.getInitParameter("displayName")%></span>
        </div>
        <div class="master-layout">
            <table border="0" width="100%" cellpadding="0" cellspacing="0">
                <tr ><td width="20%" valign="top" class="leftcontainer">
                        <div class="frame-header" >Menu</div><br>
                        <!--<table width="100%" border="0" id="menu-container">-->
                        <!--<tr> <td style="vertical-align: text-top" ><div id="menu">-->
                        <span id="menu">           
                            <%= new shgUtil().getUserProcess()%>
                        </span>
                        <!--</div></td></tr>-->
                        <!--</table>-->
                        <br />
                        <div class="frame-header">Login Details</div>
                        <div id="logindetails">

                        </div>
                    </td>
                    <td width="80%" valign="top" class="rightcontainer">
                        <div id="right-frame">
                            <div class="frame-header" ></div>
                            <div id="processing-area">
                                <div class="box-1">
                                    <form name='uploadphoto' enctype="multipart/form-data" method="post" action="uploadstuphoto.jsp">
                                        <input type="hidden" name="psubmitted" value="true" />
                                        <table>
                                            <tr>
                                                <td colspan="3" style="text-align: center"></td>
                                                <td rowspan="5" style="vertical-align: top">
                                                  <iframe id="upload_target" name="upload_target" src="../showImageFromDB?rollno=${param.rollno}" style="width:160px;height:170px;" frameborder="0" marginheight="0" marginwidth="0">
                                                    
                                                  </iframe>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Student's Name</td>
                                                <td> : </td>
                                                <td>
                                                    <b>${param.name}</b>

                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="3">His/Her College Rollno is : <b>${param.rollno}</b>
                                                    <input type="hidden" name="rollno" value="${param.rollno}" />
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
                                    <c:if test="${param.psubmitted}">
                                        <%if ("POST".equalsIgnoreCase(request.getMethod())) {
                                                String result = imgDb.saveImage(request, getServletContext());
                                                if (result.equals("1")) {
                                        %>
                                        <jsp:forward page="result.jsp">
                                            <jsp:param name="msg" value="Student's Photo Successfully updated"></jsp:param>
                                        </jsp:forward>
                                        <%
                                                }
                                                out.print("<br /><br /><center><h4>" + result + "</h4></center>");
                                            // Form was submitted.
                                        }%>

                                    </c:if>
                                </div>
                            </div>
                        </div>

                    </td>
            </table>
        </div>

        <div id="footer"><%=application.getInitParameter("pageFooter")%></div>
    </body>
</html>
