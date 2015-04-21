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
<jsp:useBean id="dbutil" class="shg.util.DatabaseUtility"></jsp:useBean>
<%!//DatabaseUtility dbutil = new DatabaseUtility();%>
<jsp:useBean id="boardnamesubject" class="shg.bean.BoardNameSubject" ></jsp:useBean>
<jsp:useBean id="boardnamesubjectDAO" class="shg.dao.editBoardNameSubjectDAO" ></jsp:useBean>
<jsp:setProperty name="boardnamesubject" property="*"></jsp:setProperty>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../style/loader.css" rel="stylesheet" />
        <link href="../style/master-css/style.css" rel="stylesheet" />
        <link href="../style/master-css/master-layout.css" rel="stylesheet" />
        <link href="../style/master-css/menu-style.css" rel="stylesheet" />
        <link rel="stylesheet" href="../style/master-css/sweet-alert.css">
        <script type="text/javascript" src="../scripts/jquery/jquery-1.6.2.min.js"></script>
        <script type="text/javascript" src="../scripts/jquery/jquery.maskedinput-1.3.js"></script>
        <script type="text/javascript" src="../scripts/jquery/script.js"></script>
        <script type="text/javascript" src="../scripts/util/populateComboBox.js"></script> 
        <script type="text/javascript" src="../scripts/util/net.js"></script>             
        <script type="text/javascript" src="../scripts/adm/editSubj.js"></script>
        <script type="text/javascript" src="../scripts/jquery/sweet-alert.min.js"></script>
        <title>Edit Board Subject</title>
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
                                <div class="box-1">
                                    <form name="boardnamesubject" method="Post">
                                        <input type="hidden" name="submitted" value="true" />
                                        <table border="0" id="Clear">
                                            <tbody>
                                                <tr>
                                                    <td>Board Name *</td>
                                                    <td> <select name="cmbBoardID" id="cmbBoardID">
                                                            <option value="-1">-</option>
                                                            <c:set var="boaid" value="${param.cmbBoardID}"></c:set>
                                                            <c:out escapeXml="false" value="${dbutil.populatePopup(pageContext.request.servletContext,'boardname','boardId','boardname',boaid)}">                                
                                                            </c:out>
                                                        </select>
                                                        <c:if test="${param.submitted and !boardnamesubject.cmbBoardIDValid}" var="v1">
                                                            Board Name is either be Blank OR invalid
                                                        </c:if>
                                                    </td>
                                                </tr> 
                                                <tr>
                                                    <td>Stream *</td>
                                                    <td> <select name="txtStream" id="txtStream">
                                                            <option value="-1">-</option>
                                                            <c:set var="comb" value="${'-'}"></c:set>
                                                            <c:out escapeXml="false" value="${dbutil.populateDependentPopup(pageContext.request.servletContext,'clxiisubj','stream','stream', 'boardid',boaid,comb)}">                                
                                                            </c:out>
                                                        </select>
                                                        <c:if test="${param.submitted and !boardnamesubject.txtStreamValid}" var="v2">
                                                            Stream is either be Blank OR invalid
                                                        </c:if>
                                                    </td>
                                                </tr>
                                        </table>
                                        <table id="subjectName">
                                            <tbody>
                                                <tr>
                                                    <td colspan="2" style="text-align: center">
                                                        <c:if test="${param.submitted and !boardnamesubject.txtSubNameValid}" var="v3">
                                                            Subject Name is either be Blank OR invalid
                                                        </c:if>
                                                    </td>
                                                </tr>  
                                            </tbody>
                                        </table> 
                                        <table>
                                            <tbody>              
                                                <tr>
                                                    <td style="text-align: right">
                                                        <input type="hidden" value="Add" id="Add" name="Add"/>
                                                    </td>
                                                    <td style="text-align: right">
                                                        <input type="hidden" value="Delete" id="Delete" name="Delete"/>
                                                    </td>
                                                    <td colspan="3" style="text-align: center"><input type="hidden" value="Save" name="cmdSave" id="cmdSave"/> </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </form>

                                    <c:if test="${param.submitted and !v1 and !v2 and !v3}">
                                        <%
                                            int i;
                                            i = boardnamesubjectDAO.insertBoard(getServletContext(), boardnamesubject);
                                            //out.println("i =  "+i);
                                            if (i == 0) {
                                                out.println("<script>swal(\"Oops...\", \"Subject Name Should contain atleast 5 Letter!\", \"error\");</script>");
                                            } else if (i == 5) {
                                                out.println("<script>swal(\"Oops...\", \"Subject Repeat!\", \"error\");</script>");
                                            }
                                        %>
                                    </c:if>     
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
