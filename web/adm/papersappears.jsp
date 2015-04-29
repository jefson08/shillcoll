<%-- 
    Document   : teib
    Created on : Mar 5, 2015, 10:37:35 PM
    Author     : B Mukhim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="shg.util.Utility" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<jsp:useBean id="dbutil" class="shg.util.DatabaseUtility"></jsp:useBean>
<jsp:useBean id="comb" class="shg.bean.courseCombination"></jsp:useBean>
<jsp:useBean id="combdao" class="shg.dao.CourseCombinationDAO"></jsp:useBean>
<jsp:useBean id="subpaper" class="shg.util.SubjectAndPaper"></jsp:useBean>
<jsp:setProperty name="comb" property="*"></jsp:setProperty>
<%@page import="shg.util.shgUtil"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="../scripts/jquery/jquery-1.6.2.min.js"></script>

        <script type="text/javascript" src="../scripts/jqueryform/ajaxupload.3.5.js"></script>
        <script type="text/javascript" src="../scripts/jqueryform/jquery.form.js"></script>
        <script type="text/javascript" src="../scripts/adm/course1.js"></script>
        <link rel="stylesheet" href="../scripts/jquerydatepicker/css/jquery.datepick.css" type="text/css" charset="utf-8" />
        <link href="../style/master-css/style.css" rel="stylesheet" />
        <link href="../style/master-css/master-layout.css" rel="stylesheet" />
        <link href="../style/master-css/menu-style.css" rel="stylesheet" />


        <title>Subject Combination</title>
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
                            <div class="frame-header" >Combination Details</div>
                            <div id="processing-area">



                                <form name="frmcourse" method="POST" action="" id="frmcourse">
                                    <input type="hidden" name="submitted" value="true" />
                                    <table border="0">
                                        <tbody>

                                            <tr>
                                                <td> Course Name </td>
                                                <td> : </td>
                                                <td>
                                                    <select name="coursecode" id="coursecode">
                                                        <option value="-1"></option>
                                                        <c:set var="ccode" value="${param.coursecode}"></c:set>
                                                        <c:out escapeXml="false" value="${dbutil.populatePopup(pageContext.request.servletContext,'course','coursecode','coursename',ccode)}"> </c:out>                               
                                                        </select>
                                                    </td>
                                                </tr>       
                                                
                                             <tr>
                                                <td> Exam ID</td>
                                                <td>:</td>
                                                <td>
                                                    <select name="examid" id="examid">
                                                        <option value="-1"></option>
                                                        <c:set var="examid" value="${param.coursecode1}"></c:set>
                                                        <c:out escapeXml="false" value="${dbutil.populatePopup(pageContext.request.servletContext,'examfeespmt','examid','examid',examid)}"> </c:out>                               
                                                        </select>
                                                    <c:if test="${param.submitted and course.streamValid}" var="v4">
                                                        Invalid Stream
                                                    </c:if>
                                                </td>
                                            </tr>

                                          
                                            <tr>
                                                <td> </td><td></td><td></td>
                                            </tr>
                                            
                                            <tr>
                                                <td></td><td></td>
                                                <td>       
                                                    <div id="divsub" style="background: " >

                                                        <c:if test="${param.submitted}">
                                                            <c:out escapeXml="false" value="${subpaper.getSubjectAndPaper(pageContext.request.servletContext, param.examid, paramValues.subjectcode , paramValues.papercode)}">

                                                            </c:out>
                                                        </c:if>

                                                    </div>                
                                                </td>
                                            </tr>
                                            <tr>

                                                <td> <input type="submit" value="SUBMIT"> </td>
                                                <td> <input type="button" value="NEXT" name="next" id="next"> </td>
                                            </tr>
                                        </tbody>
                                    </table>

                                </form>
                            </div>
                            <div id="msg" >

                                <c:if test="${param.submitted and !v3 and !v4}">
                                    <% int res = combdao.insertCourseCombination(getServletContext(), comb);
                                        if (res == 1) {
                                            out.println("Records added sucessfully");
                                        } else {
                                            out.println("Records Already Exist");
                                        }
                                    %>

                                </c:if>
                                <br><br>
                                <div id="msg1"> <label align="center"><jsp:getProperty property="msg1" name="comb"/></label>

                                    <b>  <label align="center"><jsp:getProperty property="msg" name="comb"/></label></div>

<!--<div id="footer"><%=application.getInitParameter("pageFooter")%></div>-->
                            </div>

                            </body>

                            </html>
