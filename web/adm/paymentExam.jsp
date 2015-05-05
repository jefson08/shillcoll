<%-- 
    Document   : addcourse
    Created on : Apr 15, 2015, 11:16:02 PM
    Author     : B Mukhim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="fees" class="shg.bean.FeespaymentBean" ></jsp:useBean>
<jsp:useBean id="feespay" class="shg.dao.FeespaymentDAO" ></jsp:useBean>
<jsp:setProperty name="fees" property="*"></jsp:setProperty>
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
        <script type="text/javascript" src="../scripts/adm/feespayment.js"></script>
        <title>Fees Payment</title>
    </head>
    <body>
        <div id="header" ><%@include file="common-menu.jsp" %>
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
                                <form name="fees" method="POST">
                                    <input type="hidden" name="submitted" value="true" />
                                    <table border="0">
                                        <tbody>
                                            <tr>
                                                <td> Stream</td>
                                                <td>:</td>
                                                <td>
                                                    <select name="stream" id="stream">
                                                        <option value="-1">-</option>
                                                        <c:set var="scode1" value="${param.stream}"></c:set>
                                                        <c:out escapeXml="false" value="${dbutil.populatePopup(pageContext.request.servletContext,'streams','streamcode','streamname',scode1)}"> </c:out>                               
                                                        </select>
                                                    <c:if test="${param.submitted and !fees.streamValid}" var="v1">
                                                        <span style="color: red">Stream Not Selected</span>
                                                    </c:if> 
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Course </td>
                                                <td> : </td>
                                                <td>
                                                    <select name="cmbhon" id="cmbhon">
                                                        <option value="-1">-</option>
                                                        <c:set var="ccode" value="${fees.cmbhon}"></c:set>
                                                      
                                                        <c:out escapeXml="false" value="${dbutil.populateDependentPopup(pageContext.request.servletContext,'course','coursecode','coursename','streamcode',scode1,ccode)}"> </c:out>                               
                                                        </select>
                                                    <c:if test="${param.submitted and !fees.cmbhonValid}" var="v2">
                                                        <span style="color: red">Course Not Selected</span>
                                                    </c:if> 
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Rollno  </td>
                                                <td> : </td>
                                                <td>
                                                    <select name="cmbrollno" id="cmbrollno">
                                                        <option value="-1">-</option>
                                                        <c:set var="roll" value="${param.cmbrollno}"></c:set>
                                                      
                                                        <c:out escapeXml="false" value="${dbutil.populateDependentPopup(pageContext.request.servletContext,'studentdetails','rollno','rollno','cmbhon',ccode,roll)}"> </c:out>                               
                                                        </select>
                                                    <c:if test="${param.submitted and !fees.cmbrollnoValid}" var="v3">
                                                        <span style="color: red">Roll No Not Selected</span>
                                                    </c:if> 
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Date of Payment *</td>
                                                <td> : </td>
                                                <td><input type="text" name="txtFees" id="txtFees" value="${stuEnroll.txtDOB}" size="10" />
                                                    <div style="display: none;"> <img id="calImg" src="../scripts/jquerydatepicker/img/calendar.gif" alt="Popup" class="trigger"></div>
                                                    eg. dd-mm-yyyy
                                                    <c:if test="${param.submitted and !stuEnroll.txtDOBValid}" var="v4">
                                                        <span style="color: red"> Date of Birth is either be Blank OR invalid</span>
                                                    </c:if>
                                                </td>
                                            </tr>

                                                

                                            <tr>
                                                <td colspan="3" style="text-align: center">
                                                    <input type="submit" value="Save" name="cmdSave"  /> 

                                            </tr>
                                        </tbody>
                                    </table>
                                </form>


                                <c:if test="${param.submitted and !v1 and !v2 and !v3 and !v4}">
                                    <%
                                      feespay.insert(getServletContext(), fees);
                                       %>
                                </c:if>


                            </div>
                        </div>
                        </div>

                    </td>
            </table>
        </div>

        <div id="footer"><%=application.getInitParameter("pageFooter")%></div>
    </body>
</html>
