<%-- 
    Document   : subjectmasterboard
    Created on : Feb 20, 2015, 9:52:10 PM
    Author     : B Mukhim
--%>
<%@page import="shg.util.shgUtil"%>
<%
    //response.setHeader("Pragma", "no-cache");
    //response.setHeader("Cache-Control", "no-store");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="clxiiinfo" class="shg.bean.EditClXiiInfo" ></jsp:useBean> 
<jsp:useBean id="clxiiinfoDAO" class="shg.dao.EditClXiiInfoDAO" ></jsp:useBean>
<jsp:useBean id="dbutil" class="shg.util.DatabaseUtility"></jsp:useBean>
<jsp:setProperty name="clxiiinfo" property="*"></jsp:setProperty>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../scripts/jqueryui/ui.css" rel="stylesheet" />
        <link href="../style/master-css/style.css" rel="stylesheet" />
        <link href="../style/master-css/master-layout.css" rel="stylesheet" />
        <link href="../style/master-css/menu-style.css" rel="stylesheet" />
        <link rel="stylesheet" href="../style/master-css/sweet-alert.css">
        <script type="text/javascript" src="../scripts/jquery/jquery-1.6.2.min.js"></script>
        <script type="text/javascript" src="../scripts/jqueryui/jquery-ui-1.8.16.custom.min.js"></script>
        <script type="text/javascript" src="../scripts/adm/Searchxii.js"></script>   
        <script type="text/javascript" src="../scripts/jquery/sweet-alert.min.js"></script>
        <title>Edit Class XII Details</title>
    </head>
    <body>
        <div id="header" ><%@include file="common-menu.jsp" %>
            <span id="header-span"  style="position: relative;top: 40px;"><%=application.getInitParameter("displayName")%></span>
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
                                <form name="clxiiinfo" method="Post">
                                    <input type="hidden" name="submitted" value="true" />
                                    <table border="0" id="clxiiinfo">
                                        <tbody>
                                            <tr id="clear_search">
                                                <td>Search by Board Or Degree Roll No *</td>
                                                <td><input type="text" name="txtSearchBy" id="txtSearchBy" value="${param.txtSearchBy}" size="50" />
                                                    <c:if test="${param.submitted and !clxiiinfo.txtSearchByValid}" var="v1">
                                                        Roll No OR Board Roll is either be Blank OR invalid 
                                                    </c:if>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                    <table id="clear_content">
                                        <tbody>
                                            <tr>
                                                <td colspan="2" style="text-align: center">
                                                    <c:if test="${param.submitted and !clxiiinfo.txtBoardRollValid}" var="v2">
                                                        Board Roll is either be Blank OR invalid
                                                    </c:if>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="2" style="text-align: center">
                                                    <c:if test="${param.submitted and !clxiiinfo.txtDegRollValid}" var="v3">
                                                        Degree Roll is either be Blank OR invalid
                                                    </c:if>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="2" style="text-align: center">
                                                    <c:if test="${param.submitted and !clxiiinfo.cmbBoardIDValid}" var="v4">
                                                        Board Name is either be Blank OR invalid
                                                    </c:if>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="2" style="text-align: center">
                                                    <c:if test="${param.submitted and !clxiiinfo.txtYrPassValid}" var="v5">
                                                        Year Pass is either be Blank OR invalid
                                                    </c:if>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="2" style="text-align: center">
                                                    <c:if test="${param.submitted and !clxiiinfo.cmbStreamValid}" var="v6">
                                                        Stream is either be Blank OR invalid
                                                    </c:if>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="2" style="text-align: center">
                                                    <c:if test="${param.submitted and !clxiiinfo.txtSubjectValid}" var="v7">
                                                        Subject Name is either be Blank OR invalid
                                                    </c:if>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="2" style="text-align: center">
                                                    <c:if test="${param.submitted and !clxiiinfo.txtMarksValid}" var="v8">
                                                        Marks is either be Blank OR invalid
                                                    </c:if>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="2" style="text-align: center">
                                                    <c:if test="${param.submitted and !clxiiinfo.txtTotalMarksValid}" var="v9">
                                                        Total Marks is either be Blank OR invalid OR Total Marks can have maximum of 4 Digits
                                                    </c:if>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table> 
                                    <table>
                                        <tbody>
                                            <tr>
                                                <td style="text-align: right">
                                                    <input type="button" value="Search" name="search" id="defaultActionButton" /> </td>
                                                <td style="text-align: right">
                                                    <input type="hidden" value="Delete" name="Delete" id="Delete" /> </td>
                                                <td style="text-align: right">
                                                    <input type="hidden" value="Add Subject" name="Add" id="Add" /> </td>
                                                <td colspan="3" style="text-align: center"><input type="hidden" value="Save" name="cmdSave" id="cmdSave"/> </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </form>

                                <c:if test="${param.submitted and !v1 and !v2 and !v3 and !v4 and !v5 and !v6 and !v7 and !v8 and !v9}">
                                    <%
                                        int i;
                                        i = clxiiinfoDAO.insertBoard(getServletContext(), clxiiinfo);
                                        if (i == 3) {
                                            out.println("<script>swal(\"Oops...\", \"Subject Name Repitation!\", \"error\");</script>");
                                        }
                                    %> 
                                </c:if>
                                <div id="waitbox" title="Loading Details">
                                    <div id="waitboxmsg" style="font-size: 20px;font-weight: bold;">
                                        Sending Request. Please Wait....<span id="processing" style="display: visible;"><img width="26" height="28" src="../misc/images/loading_1.gif"></span>
                                    </div><br>
                                </div>
                            </div>
                        </div>

                    </td>
                </tr>
            </table>
        </div>

        <div id="footer"><%=application.getInitParameter("pageFooter")%></div>

    </body>
</html>
