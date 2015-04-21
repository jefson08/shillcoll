<%-- 
    Document   : stimprovementEdit
    Created on : Mar 8, 2015, 4:56:12 PM
    Author     : hp
--%>

<%@page import="shg.util.shgUtil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="dbutil" class="shg.util.DatabaseUtility"></jsp:useBean>
<jsp:useBean id="stuimprove" class="shg.bean.StImprovement" ></jsp:useBean>
<jsp:useBean id="stimproveEditDAO" class="shg.dao.StImprovementEditDAO" ></jsp:useBean>
<jsp:setProperty name="stuimprove" property="*"></jsp:setProperty>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link href="../style/master-css/style.css" rel="stylesheet" />
        <link href="../style/master-css/master-layout.css" rel="stylesheet" />
        <link href="../style/master-css/menu-style.css" rel="stylesheet" /> 

        <link rel="stylesheet" href="../scripts/jquerydatepicker/css/jquery.datepick.css" type="text/css" charset="utf-8" />

        <!-- <link href="../style/master-css/style.css" rel="stylesheet" />
        <link href="../style/master-css/master-layout.css" rel="stylesheet" />
        <link href="../style/master-css/menu-style.css" rel="stylesheet" /> -->

        <script type="text/javascript" src="../scripts/jquery/jquery-1.6.2.min.js"></script>
        <script type="text/javascript" src="../scripts/jquerydatepicker/jquery.datepick.js"></script>
        <script type="text/javascript" src="../scripts/jquery/jquery.maskedinput-1.3.js"></script>
        <script type="text/javascript" src="../scripts/util/populateComboBox.js"></script>
        <script type="text/javascript" src="../scripts/util/net.js"></script>
        <script type="text/javascript" src="../scripts/adm/stimprovementEdit.js"></script>

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
                            <div class="frame-header" >Student Improvement Update</div>
                            <div id="processing-area">
                                <form  name="stImprovementEdit" id="stImprovementEdit" method="POST">
                                    <br><br>
                                    <input type="hidden" name="submitted" value="true" />
                                    <table width="100%" border="0" cellspacing="1" cellpadding="0" align="center">
                                        <tbody>
                                            <tr>
                                                <td width="16">&nbsp;</td>
                                                <td width="150" height="29"> Course Name *</td>
                                                <td width="10">:</td>
                                                <td width="50">
                                                    <select name="cmbcourseName" id="cmbcourseName">
                                                        <option value="-1">-</option>
                                                        <c:set var="cname" value="${param.cmbcourseName}"></c:set>
                                                        <c:out escapeXml="false" value="${dbutil.populatePopupInnerJoinThreeTable(pageContext.request.servletContext,'shgdb.course','shgdb.papers','shgdb.improvement','coursecode','coursecode','papercode','papercode','coursecode','coursename',cname)}">                                
                                                        </c:out>
                                                    </select> 
                                                    <c:if test="${param.submitted and !stuimprove.cmbCourseNameValid}" var="v1">
                                                        Course Name Not Selected
                                                    </c:if>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td width="16">&nbsp;</td>
                                                <td width="150" height="29">Student University Roll No *</td>
                                                <td width="10">:</td>
                                                <td width="50"><select  name="cmbuniRollno" id="cmbuniRollno">
                                                        <option value="-1">-</option>
                                                        <c:set var="ccode" value="${param.cmbcourseName}"></c:set>
                                                        <c:set var="uni" value="${param.cmbuniRollno}"></c:set>
                                                        <c:out escapeXml="false" value="${dbutil.populatePopupInnerJoinThreeTableOneCondition(pageContext.request.servletContext,'shgdb.improvement','shgdb.papers','shgdb.course','papercode','papercode','coursecode','coursecode','nehurollno','coursecode',ccode,uni)}">                                
                                                        </c:out>
                                                    </select>
                                                    <c:if test="${param.submitted and !stuimprove.cmbuniversityRollNoValid}" var="v2">
                                                        University Roll Number Not Selected
                                                    </c:if>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td width="16">&nbsp;</td>
                                                <td width="150" height="29"> Applied Paper Name *</td>
                                                <td width="10">:</td>
                                                <td width="50"><select name="cmbpaperName" id="cmbpaperName">
                                                        <option value="-1">-</option>
                                                        <c:set var="pname" value="${param.cmbpaperName}"></c:set>
                                                        <c:set var="uni" value="${param.cmbuniRollno}"></c:set>
                                                        <c:out escapeXml="false" value="${dbutil.populateDependentPopupFromTwoTableOneCondition(pageContext.request.servletContext,'shgdb.papers','shgdb.improvement','papercode', 'papercode','papercode','papername','nehurollno',uni,pname)}">                                
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
                                                <td width="150" height="29"><div id="pidEdit">Change Paper to *</div></td>
                                                <td width="10"><div id="pidcolonEdit">:</div></td>
                                                <td width="100">
                                                    <div id="cmbpaperEdit">
                                                        <select name="cmbpaperNameEdit" id="cmbpaperNameEdit">
                                                            <option value="-1">-</option>
                                                            <c:set var="papname" value="${param.cmbpaperNameEdit}"></c:set>
                                                            <c:set var="ccode" value="${param.cmbcourseName}"></c:set>
                                                            <c:set var="classcode" value="${param.txtSemEdit}"></c:set>
                                                            <c:out escapeXml="false" value="${dbutil.populateDependentPopupFromTwoCondition(pageContext.request.servletContext,'shgdb.papers','papercode','papername', 'coursecode','yearorsemno', ccode,classcode,papname)}">                               
                                                            </c:out>
                                                        </select>  
                                                        <c:if test="${param.submitted and !stuimprove.cmbpaperNameEditValid}" var="v5">
                                                            Paper Not Selected
                                                        </c:if>
                                                    </div>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td width="16">&nbsp;</td>
                                                <td width="150" height="29"><div id="pid">Applied Semester/Year </div></td>
                                                <td width="10"><div id="pidcolon">:</div></td>
                                                <td width="100">
                                                    <div id="semEd" style="display:none" >
                                                        <select name="cmbsemImproveSem" id="cmbsemImproveSem" type="hidden"></select>
                                                    </div>
                                                    <input type="text" name="txtSemEdit" id="txtSemEdit" 
                                                           <c:set var="sem" value="${param.txtSemEdit}"></c:set> 
                                                           value="<c:out escapeXml="false" value="${sem}"> </c:out>"  readonly>
                                                    <c:if test="${param.submitted and !stuimprove.txtSemEditValid}" var="v6">
                                                        Semester Not Valid
                                                    </c:if>

                                                </td>
                                            </tr>

                                            <tr>
                                                <td>&nbsp;</td>
                                                <td height="29">&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td><input type="submit" class="bigbutton" id="cmdImproveSave"  name="cmdImproveSave" value="Update" onclick=""/></td>
                                                <td>&nbsp;</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </form>

                                <c:if test="${param.submitted and !v1 and !v2 and !v3 and !v5 and !v6 }">
                                    <%
                                        stimproveEditDAO.updateStudent(getServletContext(), stuimprove);
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
