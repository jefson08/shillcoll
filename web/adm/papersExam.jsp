<%-- 
    Document   : teib
    Created on : Mar 5, 2015, 10:37:35 PM
    Author     : B Mukhim
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="DBConnection.ConnectionPool"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="shg.util.Utility" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<jsp:useBean id="dbutil" class="shg.util.DatabaseUtility"></jsp:useBean>
<jsp:useBean id="exam" class="shg.bean.ExamPaperBean"></jsp:useBean>
<jsp:useBean id="exampaper" class="shg.dao.ExamPapers"></jsp:useBean>
<jsp:useBean id="subpaper" class="shg.util.SubjectAndPaper1"></jsp:useBean>
<jsp:setProperty name="exam" property="*"></jsp:setProperty>
<%@page import="shg.util.shgUtil"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="../scripts/jquery/jquery-1.6.2.min.js"></script>

        <script type="text/javascript" src="../scripts/jqueryform/ajaxupload.3.5.js"></script>
        <script type="text/javascript" src="../scripts/jqueryform/jquery.form.js"></script>
        <script type="text/javascript" src="../scripts/adm/papersappears.js"></script>
        <link rel="stylesheet" href="../scripts/jquerydatepicker/css/jquery.datepick.css" type="text/css" charset="utf-8" />
        <link href="../style/master-css/style.css" rel="stylesheet" />
        <link href="../style/master-css/master-layout.css" rel="stylesheet" />
        <link href="../style/master-css/menu-style.css" rel="stylesheet" />


        <title>Papers Appears</title>
    </head>
    <body>




        <h1></h1>
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
                            <div class="frame-header" >Exam Appears</div>
                            <div id="processing-area">



                                <form name="frmcourse" method="POST" action="" id="frmcourse">
                                    <%
                                        String rollno = request.getParameter("roll1");
                                        String status = request.getParameter("status");
                                        String yos = request.getParameter("yos");
                                        String nehuroll = request.getParameter("nehuroll");
                                        String examid = "sampleexamid";
                                        System.out.println("Name" + rollno);
                                    %>  
                                    <input type="hidden" name="submitted" value="true" />
                                    <table border="0">
                                        <tbody>
                                            <tr>
                                                <td>Rollno </td>
                                                <td> : </td>
                                                <td><input type="text" name="roll1" id="roll1" value="<%=rollno%>" size="20 "  />

                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Nehu Rollno </td>
                                                <td> : </td>
                                                <td><input type="text" name="nehuroll" id="nehuroll" value="<%=nehuroll%>" size="20 "  />

                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Status </td>
                                                <td> : </td>
                                                <td><input type="text" name="status" id="status" value="<%=status%>" size="10 "  />

                                                </td>
                                            </tr>


                                            <tr>
                                                <td>Year / Semester </td>
                                                <td> : </td>
                                                <td><input type="text" name="yos" id="yos" value="<%=yos%>" size="5 "  />

                                                </td>
                                            </tr>

                                            <!--                                            <tr>
                                                                                            <td> Roll No </td>
                                                                                            <td> : </td>
                                                                                            <td>
                                                                                                <select name="rollno" id="rollno">
                                                                                                    <option value="-1"></option>
                                            --><!--
                                            
                                                                                                    </select>
                                                                                                </td>
                                                                                            </tr>  -->
                                            <tr>
                                                <td>
                                                    <input type="button" value="Show Combination" name="generate" id="generate">

                                                </td>
                                            </tr>

                                            <tr>
                                                <td colspan="3">
                                                    <c:if test="${param.submitted and !exam.isSubjectcodeValid(pageContext.request.servletContext)}" var="v3">
                                                        <span style="color: red">[No Papers Checked from Selected Subjects]</span>
                                                    </c:if>

                                                </td>
                                            </tr>
                                            <tr>
                                                <td> </td><td></td><td></td>
                                            </tr>
                                            <tr>
                                                <td colspan="3">


                                                </td>
                                            </tr>
                                            <tr>
                                                <td></td><td></td>
                                                <td>       
                                                    <div id="subject1" style="background: " >



                                                    </div>                
                                                </td>
                                            </tr>
                                            <tr>

                                                <td> <input type="submit" value="SUBMIT" disabled="" name="subpaper" id="subpaper"> </td>

                                            </tr>
                                        </tbody>
                                    </table>

                                </form>
                            </div>

                            <div id="msg" >

                                <c:if test="${param.submitted and !v3 }">
                                    <%
                                        exampaper.insertPapers(getServletContext(), exam);
                                    %>

                                </c:if>
                            </div>
                            <br><br>
                            <div id="msg1">
                                <label align="center"><jsp:getProperty property="msg1" name="exam"/></label>

                            </div>
                            <div id="msg3">
                                <label align="center"><jsp:getProperty property="msg3" name="exam"/></label>
                            </div>
                            <span style="color: red">
                                <div id="msg2">
                                    <label align="center"><jsp:getProperty property="msg2" name="exam"/></label>
                                </div>
                            </span>

                            </body>

                            </html>
