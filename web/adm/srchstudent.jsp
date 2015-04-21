<%-- 
    Document   : srchstudent
    Created on : Mar 2, 2015, 10:26:46 PM
    Author     : B Mukhim
--%>

<%@page import="shg.util.shgUtil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../style/master-css/style.css" rel="stylesheet" />
        <link href="../style/master-css/master-layout.css" rel="stylesheet" />
        <link href="../style/master-css/menu-style.css" rel="stylesheet" />
        
        <link rel="stylesheet" href="../style/TableCSSCode.css" type="text/css" charset="utf-8" />
        <link href="../style/master-css/style.css" rel="stylesheet" />
        <link href="../style/master-css/master-layout.css" rel="stylesheet" />
        <link href="../style/master-css/menu-style.css" rel="stylesheet" />
        <script type="text/javascript" src="../scripts/jquery/jquery-1.6.2.min.js"></script>
        <script type="text/javascript" src="../scripts/adm/srchstudent.js"></script>
        <script type="text/javascript" src="../scripts/jqueryform/jquery.form.js"></script>
        <script type="text/javascript" src="../scripts/jqueryform/ajaxupload.3.5.js"></script>

        <title>Search Student</title>
    </head>
    <body>
        <div id="header" ><%@include file="common-menu.jsp" %>
            <span id="header-span"><%=application.getInitParameter("displayName")%></span>
        </div>
        <div class="master-layout">
            <table border="0" width="100%" cellpadding="0" cellspacing="0">
                <tr ><td width="18%" valign="top" class="leftcontainer">
                        <div class="frame-header" >Menu</div><br>
                        <table width="100%" border="0" id="menu-container">
                            <tr> <td style="vertical-align: text-top" ><div id="menu">
                                        <%= new shgUtil().getUserProcess()%>
                                    </div></td></tr>
                        </table>
                        <br>
                        <div class="frame-header">Login Details</div>
                        <div id="logindetails">

                        </div>
                    </td>
                    <td width="80%" valign="top" class="rightcontainer">
                        <div id="right-frame">
                            <div class="frame-header" >Student Details</div>
                            <div id="processing-area">
                                <form name="srchstu" id="srchstu" method="POST">
                                    <table border="0">
                                        <tbody>
                                            <tr>
                                                <td colspan="2">
                                                    <label>
                                                        <input type="radio" name="radSrchBy" id="radSrchBy" value="rollno" checked="checked" />Search By Rollno
                                                    </label><br />
                                                    <label>
                                                        <input type="radio" name="radSrchBy" id="radSrchBy" value="name" />Search By Name
                                                    </label><br />
                                                    <label>
                                                        <input type="radio" name="radSrchBy" id="radSrchBy" value="course" />Search By Course
                                                    </label><br />
                                                </td>
                                            </tr>
                                            <tr>
                                                <td><input type="text" name="txtSrchVal" id="txtSrchVal" value="" size="50" /></td>
                                                <td><input type="submit" value="Search" name="cmdsrch" /></td>
                                            </tr>

                                        </tbody>
                                    </table>
                                    <input name="limit" id="limit" type="hidden" value="3"><input name="offset" id="offset" type="hidden" value="0" />
                                </form>
                                <div id="srchresult"></div>
                            </div>
                        </div>

                    </td>
            </table>
        </div>
        <div id="footer"><%=application.getInitParameter("pageFooter")%></div>
    </body>
</html>
