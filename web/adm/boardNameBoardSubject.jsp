<%-- 
    Document   : boardNameBoardSubject
    Created on : Feb 20, 2015, 9:52:10 PM
    Author     : B Mukhim
--%>
<%@page import="shg.util.shgUtil"%>
<%
    //response.setHeader("Pragma", "no-cache");
    //response.setHeader("Cache-Control", "no-store");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="boardnamesubject" class="shg.bean.BoardNameSubject"></jsp:useBean>    
<jsp:useBean id="boardnamesubjectDAO" class="shg.dao.BoardNameSubjectDAO"></jsp:useBean>
<jsp:setProperty name="boardnamesubject" property="*"></jsp:setProperty>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../style/master-css/style.css" rel="stylesheet" />
        <link href="../style/master-css/master-layout.css" rel="stylesheet" />
        <link href="../style/master-css/menu-style.css" rel="stylesheet" />
        <link rel="stylesheet" href="../style/master-css/sweet-alert.css">
        <script type="text/javascript" src="../scripts/jquery/jquery-1.6.2.min.js"></script>         
        <script type="text/javascript" src="../scripts/adm/boardNmSb.js"></script>   
        <script type="text/javascript" src="../scripts/jquery/sweet-alert.min.js"></script>
        <title>Board Name and Subjects</title>
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
                                <form name="boardnamesubject" method="Post">
                                    <input type="hidden" name="submitted" value="true"/>                       
                                    <table border="0" id="subjectName">
                                        <tbody>                
                                            <tr>
                                                <td>Board Name *</td>
                                                <td><input type="text" name="txtBoaName" id="txtBoaName" value="${param.txtBoaName}" size="50" /></td>
                                                <td>
                                                    <c:if test="${param.submitted and !boardnamesubject.txtBoaNameValid}" var="v1">
                                                        Board Name is either be Blank OR invalid                                 
                                                    </c:if>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Stream *</td>
                                                <td><input type="radio" name="txtStream" id="txtStream" value="Science" >Science
                                                    <input type="radio" name="txtStream" id="txtStream" value="Commerce" >Commerce
                                                    <input type="radio" name="txtStream" id="txtStream" value="Arts" >Arts</td>
                                                <td>
                                                    <c:if test="${param.submitted and !boardnamesubject.txtStreamValid}" var="v2">
                                                        Stream Name is either be Blank OR invalid 
                                                    </c:if>   
                                                </td>
                                            </tr>
                                            <tr><td></td><td></td>
                                                <td>
                                                    <c:if test="${param.submitted and !boardnamesubject.txtSubNameValid}" var="v3">
                                                        Subject Name is either be Blank OR invalid
                                                    </c:if>
                                                </td>
                                            </tr>
                                            <tr id="1">
                                                <td>Subject *</td>
                                                <td><input type="text" name="txtSubName" id="txtSubName" value="${boardnamesubject.txtSubName[0]}" size="50" /></td>
                                                <td><img src="../images/remove.png" alt="Remove" imgno="1" id="DelIcon"/>
                                                    <img src="../images/add.png" alt="Add" imgno="1" id="ADDIcon"/></td>
                                            </tr>
                                            <tr id="2">
                                                <td>Subject *</td>
                                                <td><input type="text" name="txtSubName" id="txtSubName" value="${boardnamesubject.txtSubName[1]}" size="50" /></td>
                                                <td><img src="../images/remove.png" alt="Remove" imgno="2" id="DelIcon"/>
                                                    <img src="../images/add.png" alt="Add" imgno="2" id="ADDIcon"/></td>
                                            </tr>
                                            <tr id="3">
                                                <td>Subject *</td>
                                                <td><input type="text" name="txtSubName" id="txtSubName" value="${boardnamesubject.txtSubName[2]}" size="50" /></td>
                                                <td><img src="../images/remove.png" alt="Remove" imgno="3" id="DelIcon"/>
                                                    <img src="../images/add.png" alt="Add" imgno="3" id="ADDIcon"/></td>
                                            </tr>
                                            <tr id="4">
                                                <td>Subject *</td>
                                                <td><input type="text" name="txtSubName" id="txtSubName" value="${boardnamesubject.txtSubName[3]}" size="50" /></td>
                                                <td><img src="../images/remove.png" alt="Remove" imgno="4" id="DelIcon"/>
                                                    <img src="../images/add.png" alt="Add" imgno="4" id="ADDIcon"/></td>
                                            </tr>
                                            <c:forEach items="${boardnamesubject.txtSubName}" var="cur" begin="5" varStatus="status">
                                                <tr id="${cur}">
                                                    <td>Subject *</td>
                                                    <td><input type="text" name="txtSubName" value="${cur}" size="50" /></td>
                                                    <td><img src="../images/remove.png" alt="Remove" imgno="${cur}" id="DelIcon"/>
                                                        <img src="../images/add.png" alt="Add" imgno="${cur}" id="ADDIcon"/></td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                    <table>
                                        <tr>
                                            <td style="text-align: left"><input type="submit" value="Save" name="cmdSave" /> </td>
                                        </tr>               
                                    </table>
                                </form>
                                <c:if test="${param.submitted and !v1 and !v2 and !v3}">
                                    <%
                                        int i;
                                        i = boardnamesubjectDAO.insertBoard(getServletContext(), boardnamesubject);
                                        if (i == 0) {
                                         out.println("<script>swal(\"Oops...\", \"Board Name should contain atleast 4 Letter!\", \"error\");</script>");
                                        } else if (i == 3) {
                                            out.println("<script>swal(\"Oops...\", \"Subject Name Should contain atleast 5 Letter!\", \"error\");</script>");
                                        } else if (i == 2) {
                                            out.println("<script>swal(\"Oops...\", \"Board Name already exist!\", \"error\");</script>");
                                        } else if (i == 5) {
                                             out.println("<script>swal(\"Oops...\", \"Subject Repeat!\", \"error\");</script>");
                                        }
                                    %>
                                </c:if>
                            </div>
                        </div>
                    </td>
                </tr>
            </table>            
        </div>
        <div id="footer"><%=application.getInitParameter("pageFooter")%></div>
    </body>
</html>
