<%-- 
    Document   : stimprovement
    Created on : Feb 25, 2015, 9:39:34 AM
    Author     : hp
--%>
<%@page import="shg.util.shgUtil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="dbutil" class="shg.util.DatabaseUtility"></jsp:useBean>
<jsp:useBean id="stuimprove" class="shg.bean.StImprovement" ></jsp:useBean>
<jsp:useBean id="stimproveDAO" class="shg.dao.StImprovementDAO" ></jsp:useBean>
<jsp:setProperty name="stuimprove" property="*"></jsp:setProperty>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
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
        <script type="text/javascript" src="../scripts/adm/stimprovement.js"></script>

        <title>Student Improvement</title>
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
                            <div class="frame-header" >Student Improvement Entry</div>
                            <div id="processing-area">
                                <form  name="stImprovement" id="stImprovement" method="POST">
                                    <br><br>
                                    <input type="hidden" name="submitted" value="true" />
                                    <input type="hidden" name="coursecode"  value="true" />
                                    <div class="master-layout">
                                        <table width="100%" border="0" cellspacing="1" cellpadding="0" align="center">
                                            <tbody>
                                                <tr>
                                                    <td width="16">&nbsp;</td>
                                                    <td width="150" height="29"> Course Name *</td>
                                                    <td width="10">:</td>
                                                    <td width="100">
                                                        <select name="cmbcourseName" id="cmbcourseName">
                                                            <option value="-1">-</option>
                                                            <c:set var="ccode" value="${param.cmbcourseName}"></c:set>
                                                            <c:out escapeXml="false" value="${dbutil.populatePopup(pageContext.request.servletContext,'shgdb.course','coursecode','coursename',ccode)}">                                
                                                            </c:out>
                                                        </select> 
                                                        <c:if test="${param.submitted and !stuimprove.cmbCourseNameValid}" var="v1">
                                                            Course Name Not Selected
                                                        </c:if>
                                                    </td>

                                                </tr>

                                                <tr>
                                                    <td width="16">&nbsp;</td>
                                                    <td width="150" height="29">Semester/Year for Improvement *</td>
                                                    <td width="10">:</td>
                                                    <td width="100">
                                                        <select name="cmbsemImproveSem" id="cmbsemImproveSem">
                                                            <option value="-1">-</option>
                                                            <c:set var="impsem" value="${param.cmbsemImproveSem}"></c:set>
                                                            <c:out escapeXml="false" value="${dbutil.populateDependentPopup(pageContext.request.servletContext,'shgdb.papers','yearorsemno','yearorsemno', 'coursecode', ccode,impsem)}">                                
                                                            </c:out>
                                                        </select>
                                                        <c:if test="${param.submitted and !stuimprove.cmbsemImproveSemValid}" var="v2">
                                                            Semester Not Selected
                                                        </c:if>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td width="16">&nbsp;</td>
                                                    <td width="150" height="29"> Paper Name *</td>
                                                    <td width="10">:</td> 
                                                    <td width="100"><select name="cmbpaperName" id="cmbpaperName">
                                                            <option value="-1">-</option>
                                                            <c:set var="pname" value="${param.cmbpaperName}"></c:set>
                                                            <c:set var="ccode" value="${param.cmbcourseName}"></c:set>
                                                            <c:set var="classcode" value="${param.cmbsemImproveSem}"></c:set>
                                                            <c:out escapeXml="false" value="${dbutil.populateDependentPopupFromTwoCondition(pageContext.request.servletContext,'shgdb.papers','papercode','papername', 'coursecode','yearorsemno', ccode,classcode,pname)}">                                
                                                            </c:out>
                                                        </select>
                                                        <c:if test="${param.submitted and !stuimprove.cmbPaperNameValid}" var="v3">
                                                            Paper Name Not Selected
                                                        </c:if>
                                                    </td>

                                                    <td width="150" height="29"><div id="pid">Paper Code</div></td>
                                                    <td width="10"><div id="pidcolon">:</div></td>
                                                    <td width="100">
                                                        <div id="cmbpaper" >
                                                            <input type="text" name="txtpaperId" id="txtpaperId" 
                                                                   <c:set var="pid" value="${param.txtpaperId}"></c:set> 
                                                                   value="<c:out escapeXml="false" value="${pid}"> </c:out>"  readonly>
                                                            <c:if test="${param.submitted and !stuimprove.txtpaperIdValid}" var="v4">
                                                                Paper ID Not Valid
                                                            </c:if>
                                                        </div>
                                                    </td>
                                                </tr>

                                                <tr>
                                                    <td width="16">&nbsp;</td>
                                                    <td width="150" height="29">Year/Batch *</td>
                                                    <td width="10">:</td>
                                                    <td width="100"><select name="cmbyearBt" id="cmbyearBt">
                                                            <option value="-1">-</option>
                                                            <c:set var="yearb" value="${param.cmbyearBt}"></c:set>
                                                            <c:set var="ccode" value="${param.cmbcourseName}"></c:set>
                                                            <c:out escapeXml="false" value="${dbutil.populateDependentPopupInnerJoinTwoTable(pageContext.request.servletContext,'shgdb.batchwisestudent','shgdb.studentdetails','rollno','rollno','batch','coursecode',ccode,yearb)}">                                
                                                            </c:out>    
                                                        </select>
                                                        <c:if test="${param.submitted and !stuimprove.cmbYearBatchValid}" var="v5">
                                                            Year/Batch Not Selected
                                                        </c:if>
                                                    </td>
                                                </tr>

                                                <tr>
                                                    <td width="16">&nbsp;</td>
                                                    <td width="150" height="29">Student University Roll No *</td>
                                                    <td width="10">:</td>
                                                    <td width="100"><select  name="cmbuniRollno" id="cmbuniRollno">
                                                            <option value="-1">-</option>
                                                            <c:set var="uniroll" value="${param.cmbuniRollno}"></c:set>
                                                            <c:set var="bat" value="${param.cmbyearBt}"></c:set>
                                                            <c:set var="ccode" value="${param.cmbcourseName}"></c:set>
                                                            <c:out escapeXml="false" value="${dbutil.populateDependentPopupInnerJoinTwoTableTwoCondition(pageContext.request.servletContext,'shgdb.studentdetails','shgdb.batchwisestudent','rollno','rollno','nehurollno','coursecode','batch',ccode,bat,uniroll)}">                                
                                                            </c:out> 
                                                        </select>
                                                        <c:if test="${param.submitted and !stuimprove.cmbuniversityRollNoValid}" var="v6">
                                                            University Roll Number Not Selected
                                                        </c:if>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>&nbsp;</td>
                                                    <td height="29">&nbsp;</td>
                                                    <td>&nbsp;</td>

                                                    <td><input type="submit" class="bigbutton" id="cmdImproveSave"  name="cmdImproveSave" value="Save" onclick=""/></td>
                                                    <td>&nbsp;</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </form>

                                <c:if test="${param.submitted and !v1 and !v2 and !v3 and !v4 and !v5 and !v6}">
                                    <%
                                        stimproveDAO.insertStudentImprovement(getServletContext(), stuimprove);
                                    %>
                                </c:if>
                                <br></br>
                                <br></br>
                                <br></br> 
                                <div name="servermessage" align="center" >
                                    <label name="errorssuccmsg" id="errorssuccmsg" value=" "><jsp:getProperty property="errorssuccmsg" name="stuimprove"/></label><br>
                                </div>
                            </div>
                        </div>

                    </td>
            </table>
        </div>

        <div id="footer"><%=application.getInitParameter("pageFooter")%></div>
    </body>
</html>
