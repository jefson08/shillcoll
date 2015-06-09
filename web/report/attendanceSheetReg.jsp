<%-- 
    Document   : addcourse
    Created on : Apr 15, 2015, 11:16:02 PM
    Author     : B Mukhim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="course" class="shg.bean.ModifiedCourseBean" ></jsp:useBean>
<jsp:useBean id="ModifiedStudentCourse" class="shg.dao.ModifiedStudentCourse" ></jsp:useBean>
<jsp:setProperty name="course" property="*"></jsp:setProperty>
<jsp:useBean id="dbutil" class="shg.util.DatabaseUtility"></jsp:useBean>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import="shg.util.shgUtil"%>
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
        <script type="text/javascript" src="../scripts/adm/course.js"></script>
        <title>Attendance Sheet</title>
    </head>
    <body>
        <div id="header" >
            <%--<%@include file="common-menu.jsp" %>--%>
            <span id="header-span" ><%=application.getInitParameter("displayName")%></span>
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
                                <form name="course" method="POST" action="../attendanceSheetReg">
                                    <input type="hidden" name="submitted" value="true" />
                                    <table border="0">
                                        <tbody>
                                            <tr>
                                                <td> Stream *</td>
                                                <td>:</td>
                                                <td>
                                                    <select name="stream" id="stream">
                                                        <option value="-1"></option>
                                                        <c:set var="scode" value="${param.stream}"></c:set>
                                                        <c:out escapeXml="false" value="${dbutil.populatePopup(pageContext.request.servletContext,'streams','streamcode','streamname',scode)}"> </c:out>                               
                                                        </select>
                                                    <c:if test="${param.submitted and !course.streamValid}" var="v1">
                                                        <span style="color: red">Stream Not Selected</span>
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
                                                        <c:set var="sub" value="${param.cmbhon}"></c:set>
                                                        <c:out escapeXml="false" value="${dbutil.populateDependentPopup(pageContext.request.servletContext,'subjects','subjectname','subjectcode','streamcode',scode,sub)}"> </c:out>                               
                                                        </select>
                                                    <c:if test="${param.submitted and !course.cmbhonValid}" var="v2">
                                                        <span style="color: red">Honours Subject Not Selected / Honours Subject is Blank</span>
                                                    </c:if> 
                                                </td>
                                            </tr>
                                            <tr>
                                                <td >Semester/Year</td>
                                                   
                                                    <td>:</td>
                                                    <td>

                                                        <input type="text" list="semyr" id="seye" name="seye" size="5" autocomplete="off" placeholder=" Semester/Year" 
                                                               <c:set var="sy" value="${param.seye}"/>
                                                               value="<c:out escapeXml="false" value="${sy}"/>">      
                                                        <datalist id="semyr" name="semyr">
                                                            <option value="s1"/>
                                                            <option value="s2"/>
                                                            <option value="s3"/>
                                                            <option value="s4"/>
                                                            <option value="s5"/>
                                                            <option value="s6"/>
                                                            <option value="1y"/>
                                                            <option value="2y"/>
                                                            <option value="3y"/>
                                                        </datalist>

                                                       
                                                    </td>
                                                </tr>
                                            <tr>
                                                
                                                <td >Category*</td>
                                                <td> : </td>
                                                <td>
                                                    <label><input type="radio" name="nri" id="nri" value="r" /> Regular</label>
                                                    <label><input type="radio" name="nri" id="nri" value="n" /> Non-Regular</label>
                                                    <label><input type="radio" name="nri" id="nri" value="i" /> Improvement</label>
                                                        <c:if test="${param.submitted and !examInfo.nriValid}" var="v6">
                                                        <br><span style="color: red">Category not selected</span>
                                                    </c:if>
                                                </td>
                                            </tr>


                                            <tr>
                                                <td></td><td></td>
                                                <td>
                                                    <input type="submit" value="Search" name="cmdSave"  /> 

                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </form>



                                <span style="color: red"><div id="msg3"> <label align="center"><jsp:getProperty property="msg3" name="course"/></label></div></span>                        
                                <div id="msg"> <label align="center"><jsp:getProperty property="msg" name="course"/></label></div>
                                <div id="msg2"> <label align="center"><jsp:getProperty property="msg2" name="course"/></label>

                                    <label align="right"><B> <jsp:getProperty property="msg1" name="course"/></label></div>
                            </div>
                        </div>

                    </td>
            </table>
        </div>

        <div id="footer"><%=application.getInitParameter("pageFooter")%></div>
    </body>
</html>
