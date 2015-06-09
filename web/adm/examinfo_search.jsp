<%-- 
    Document   : examinfo_search
    Created on : Jun 4, 2015, 8:19:49 PM
    Author     : Ransly
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
        <script type="text/javascript" src="../scripts/adm/examinfo_search.js"></script>
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
                                <form name="srchstuexaminfo" id="srchstuexaminfo" method="POST">
                                    <table border="0">
                                        <tbody>
                                            <tr>
                                                <td>
                                                    <input type="text" list="examinfosearch" id="txtexaminfosearch" name="txtexaminfosearch" size="30" autocomplete="off" placeholder=" Enter College Roll Number">
                                                    <datalist id="examinfosearch" name="examinfosearch"> 
                                                    </datalist>
                                                </td>
                                                <td><input type="submit" value="Search" name="cmdsrch" /></td>
                                            </tr>

                                        </tbody>
                                    </table>
                                    <input name="limit" id="limit" type="hidden" value="5"><input name="offset" id="offset" type="hidden" value="0" />
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

