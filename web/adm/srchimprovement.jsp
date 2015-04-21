<%-- 
    Document   : srchimprovement
    Created on : Apr 1, 2015, 11:58:21 PM
    Author     : hp
--%>

<%@page import="shg.util.shgUtil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../style/TableCSSCode.css" type="text/css" charset="utf-8" />

        <link href="../style/master-css/style.css" rel="stylesheet" />
        <link href="../style/master-css/master-layout.css" rel="stylesheet" />
        <link href="../style/master-css/menu-style.css" rel="stylesheet" /> 

        <!-- <link href="../style/master-css/style.css" rel="stylesheet" />
        <link href="../style/master-css/master-layout.css" rel="stylesheet" />
        <link href="../style/master-css/menu-style.css" rel="stylesheet" /> -->

        <script type="text/javascript" src="../scripts/jquery/jquery-1.6.2.min.js"></script>
        <script type="text/javascript" src="../scripts/adm/srchimprovement.js"></script>
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
                            <div class="frame-header" >Search Student Improvement</div>
                            <div id="processing-area">
                                <form name="srchstu" id="srchstu" method="POST">
                                    <table border="0" align="center">
                                        <tbody>
                                            <tr>
                                                <td colspan="2">
                                                    <label>
                                                        Enter University Roll Number
                                                    </label><br />
                                                </td>
                                                <td><input type="text" name="txtsrchval" id="txtsrchval" value="" size="50" /></td>
                                            </tr>
                                            <tr>
                                                <td colspan="2"></td>
                                                <td><input type="submit" value="Search" name="cmdsrch" /></td>

                                            </tr>

                                        </tbody>
                                    </table>
                                    <input name="limit" id="limit" type="hidden" value="10"><input name="offset" id="offset" type="hidden" value="0" />
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

