<%-- 
    Document   : addcourse
    Created on : Apr 15, 2015, 11:16:02 PM
    Author     : B Mukhim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="edit" class="shg.bean.EditPapersBean" ></jsp:useBean>
<jsp:useBean id="editpaper" class="shg.dao.UpdatePapers" ></jsp:useBean>
<jsp:setProperty name="edit" property="*"></jsp:setProperty>
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
        <title>Edit Papers Exam</title>
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
                                                        <option value="-1"></option>
                                                        <c:set var="scode" value="${param.stream}"></c:set>
                                                        <c:out escapeXml="false" value="${dbutil.populatePopup(pageContext.request.servletContext,'streams','streamcode','streamname',scode)}"> </c:out>                               
                                                        </select>
                                                  
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Course </td>
                                                <td> : </td>
                                                <td>
                                                    <select name="cmbhon" id="cmbhon">
                                                        <option value="-1">-</option>
                                                        <c:set var="ccode" value="${param.cmbhon}"></c:set>
                                                        <c:out escapeXml="false" value="${dbutil.populateDependentPopup(pageContext.request.servletContext,'course','coursecode','coursename','streamcode',scode1,ccode)}"> </c:out>                               
                                                   
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Rollno  </td>
                                                <td> : </td>
                                                <td>
                                                    <select name="cmbrollno" id="cmbrollno">
                                                        <option value="-1">-</option>
                                                        <c:set var="roll" value="${param.cmbrollno}"></c:set>

                                                        <c:out escapeXml="false" value="${dbutil.populateDependentPopup(pageContext.request.servletContext,'studentdetails','rollno','rollno','cmbrollno',roll,roll)}"> </c:out>                               
                                                        </select>
                                                   
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Exam ID  </td>
                                                <td> : </td>
                                                <td>
                                                    <select name="cmbexamid" id="cmbexamid">
                                                        <option value="-1">-</option>


                                                        <c:out escapeXml="false" value="${dbutil.populateDependentPopup(pageContext.request.servletContext,'papersappear','examid','examid','cmbhon',ccode,roll)}"> </c:out>                               
                                                        </select>

                                                    </td>
                                                </tr>

                                                <tr>
                                                    <td>
                                                        <input type="button" value="generate Combination" name="generate1" id="generate1">

                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td></td><td></td>
                                                    <td>       
                                                        <div id="combination" style="background: " >



                                                        </div>                
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td colspan="3" style="text-align: center">
                                                        <input type="submit" value="Update" name="cmdSave"  /> 

                                                </tr>
                                            </tbody>
                                        </table>
                                    </form>


                                <c:if test="${param.submitted and !v1 and !v2 and !v3 and !v4}">
                                    <%
                                        editpaper.insert(getServletContext(), edit);
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
