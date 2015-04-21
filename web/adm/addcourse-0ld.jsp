<%-- 
    Document   : stuenroll
    Created on : Feb 20, 2015, 9:52:10 PM
    Author     : B Mukhim
--%>
<%
    //response.setHeader("Pragma", "no-cache");
    //response.setHeader("Cache-Control", "no-store");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="course" class="shg.bean.ModifiedCourseBean" ></jsp:useBean>
<jsp:useBean id="ModifiedStudentCourse" class="shg.dao.ModifiedStudentCourse" ></jsp:useBean>
<jsp:setProperty name="course" property="*"></jsp:setProperty>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="DBConnection.ConnectionPool"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<jsp:useBean id="dbutil" class="shg.util.DatabaseUtility"></jsp:useBean>
<%@page import="java.sql.*"%>"
<%@page import="shg.util.shgUtil"%>
<!DOCTYPE html>

<%
    Connection con = null;
    PreparedStatement pst = null;
    String sql;
    ServletContext context = null;
    ConnectionPool connectionPool = null;
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../scripts/jquerydatepicker/css/jquery.datepick.css" type="text/css" charset="utf-8" />
        <link href="../style/master-css/style.css" rel="stylesheet" />
        <link href="../style/master-css/master-layout.css" rel="stylesheet" />
        <link href="../style/master-css/menu-style.css" rel="stylesheet" />

        <script type="text/javascript" src="../scripts/jquery/jquery-1.6.2.min.js"></script>
        <script type="text/javascript" src="../scripts/jquerydatepicker/jquery.datepick.js"></script>
        <script type="text/javascript" src="../scripts/jquery/jquery.maskedinput-1.3.js"></script>
        <script type="text/javascript" src="../scripts/util/populateComboBox.js"></script>
        <script type="text/javascript" src="../scripts/util/net.js"></script>
        <script type="text/javascript" src="../scripts/adm/course.js"></script>
        <title>Student Course</title>
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
                            <div class="frame-header" >Course Details</div>
                            <div id="processing-area">


                                <form name="course" method="POST">
                                    <input type="hidden" name="submitted" value="true" />
                                    <table border="0">
                                        <tbody>
                                            <tr>
                                                <td> Stream</td>
                                                <td>:</td>
                                                <td>
                                                    <select name="stream" id="stream">
                                                        <option value="-1"></option>
                                                        <c:set var="scode" value="${param.coursecode1}"></c:set>
                                                        <c:out escapeXml="false" value="${dbutil.populatePopup(pageContext.request.servletContext,'streams','streamcode','streamname',scode)}"> </c:out>                               
                                                        </select>
                                                    <c:if test="${param.submitted and course.streamValid}" var="v4">
                                                        Invalid Stream
                                                    </c:if>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Honours Subject *</td>
                                                <td> : </td>
                                                <td>
                                                    <select name="cmbhon" id="cmbhon">
                                                        <option value="-1">-</option>
                                                        <c:set var="scode" value="${param.stream}"></c:set>
                                                        <c:set var="sub" value="${param.subject}"></c:set>
                                                        <c:out escapeXml="false" value="${dbutil.populateDependentPopup(pageContext.request.servletContext,'subjects','subjectcode','subjectname','streamcode',scode,sub)}"> </c:out>                               
                                                        </select>
                                                    <c:if test="${param.submitted and course.cmbhonValid}" var="v5">
                                                        Invalid Stream
                                                    </c:if>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>Course Name *</td>
                                                <td> : </td>
                                                <td><input type="text" name="txtcoursename" id="txtcoursename" value="${param.txtcoursename}"  size="20" />
                                                    <c:if test="${param.submitted and course.txtcoursenameValid}" var="v3">
                                                        Invalid Course Name
                                                    </c:if>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>No Of Seats *</td>
                                                <td> : </td>
                                                <td><input type="text" name="txtnoofseat" id="txtnoofseat" value="${param.txtnoofseat}" size="5" />
                                                    <c:if test="${param.submitted and course.txtnoofseatValid}" var="v2">
                                                        Invalid No Of Seat Code
                                                    </c:if>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td colspan="3" style="text-align: center">
                                                    <input type="submit" value="Save" name="cmdSave"  /> 
                                                    <input type="button" value="Next" name="next" id ="next" ></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </form>

                               
                                <c:if test="${param.submitted and !v5 and !v2 and !v3 and !v4}">
                                    <%
                                        int res = ModifiedStudentCourse.insert(getServletContext(), course);
                                        if (res != 1) {
                                            //out.println("Success");

                                        } else {
                                            //  out.println("no");
                                        }

                                    %>
                                </c:if>
                                
                                <div id="msg"> <label align="center"><jsp:getProperty property="msg" name="course"/></label></div>
                                <div id="msg2"> <label align="center"><jsp:getProperty property="msg2" name="course"/></label>: 
                                
                                <label align="right"><B> <jsp:getProperty property="msg1" name="course"/></label></div>
                                
                            </div>
                        </div>

                    </td>

            </table>
        </div>

        <div id="footer"><%=application.getInitParameter("pageFooter")%></div>

    </body>
</html> 
