<%-- 
    Document   : examinfo_edit
    Created on : Jun 1, 2015, 7:00:36 PM
    Author     : Ransly
--%>

<%@page import="shg.util.shgUtil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:useBean id="examInfo" class="shg.bean.examinfo_Bean" ></jsp:useBean>
<jsp:useBean id="examinfoEDAO" class="shg.dao.examinfo_Edit_DAO" ></jsp:useBean>
<jsp:setProperty name="examInfo" property="*"></jsp:setProperty>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../style/master-css/style.css" rel="stylesheet" />
        <link href="../style/master-css/master-layout.css" rel="stylesheet" />
        <link href="../style/master-css/menu-style.css" rel="stylesheet" /> 

        <link href="../style/loader.css" rel="stylesheet" />
        <script type="text/javascript" src="../scripts/jquery/script.js"></script>

        <link rel="stylesheet" href="../scripts/jquerydatepicker/css/jquery.datepick.css" type="text/css" charset="utf-8" />

        <script type="text/javascript" src="../scripts/jquery/jquery-1.6.2.min.js"></script>
        <script type="text/javascript" src="../scripts/jquerydatepicker/jquery.datepick.js"></script>
        <script type="text/javascript" src="../scripts/jquery/jquery.maskedinput-1.3.js"></script>
        <script type="text/javascript" src="../scripts/util/net.js"></script>

        <script src="../scripts/validate/jquery.validate.js"></script>
        <script src="../scripts/validate/additional-methods.js"></script>
        <script src="../scripts/validate/validators.js"></script>

        <script type="text/javascript" src="../scripts/adm/examinfo_edit_autocompleter.js"></script>

        <title>University Examination Information</title>
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
                            <div class="frame-header" >University Examination Information</div>
                            <div id="processing-area">
                                <form  name="examinformation_edit" id="examinformation_edit" method="POST">
                                    <br><br>
                                    <input type="hidden" name="submitted" value="true" />
                                    <div class="master-layout">
                                        <table width="100%" border="0" cellspacing="1" cellpadding="0" align="center">
                                            <tbody>

                                                <tr>

                                                    <td width="16">&nbsp;</td>
                                                    <td width="200" height="29"> College Roll Number*</td>
                                                    <td width="10">:</td>
                                                    <td width="400">

                                                        <input type="text" list="crollno" id="rollno" name="rollno" size="20" autocomplete="off" placeholder=" Roll Number"
                                                               <c:set var="rol" value="${param.rollno}"/>
                                                               value="<c:out escapeXml="false" value="${rol}"/>">
                                                        <datalist id="crollno" name="crollno"> 
                                                        </datalist>

                                                        <c:if test="${param.submitted and !examInfo.rollnoValid}" var="v1">
                                                            <span style="color: red"> Roll Number Invalid </span>
                                                        </c:if>

                                                    </td>
                                                </tr>

                                                <tr>
                                                    <td width="16">&nbsp;</td>
                                                    <td width="200" height="29">Batch</td>
                                                    <td width="10">:</td>
                                                    <td width="400">

                                                        <input type="text"  size="20" name="txtBatch" id="txtBatch" 
                                                               <c:set var="bno" value="${param.txtBatch}"></c:set> 
                                                               value="<c:out escapeXml="false" value="${bno}"> </c:out>" readonly/>

                                                        <c:if test="${param.submitted and !examInfo.txtBatchValid}" var="v2">
                                                            <span style="color: red"> Batch  Invalid </span>
                                                        </c:if>

                                                    </td>
                                                </tr>

                                                <tr>
                                                    <td width="16">&nbsp;</td>
                                                    <td width="200" height="29"> University Roll Number*</td>
                                                    <td width="10">:</td>
                                                    <td width="400">

                                                        <input type="text" name="txtNehurollno" size="20" id="txtNehurollno" 
                                                               <c:set var="unino" value="${param.txtNehurollno}"></c:set> 
                                                               value="<c:out escapeXml="false" value="${unino}"> </c:out>">

                                                    <c:if test="${param.submitted and !examInfo.txtNehurollnoEditValid}" var="v3">
                                                            <span style="color: red"> Roll Number Invalid </span>
                                                        </c:if>
                                                    </td>
                                                    <td></td>
                                                </tr>

                                                <tr>
                                                    <td width="16">&nbsp;</td>
                                                    <td width="200" height="29">Registration Number*</td>
                                                    <td width="10">:</td>
                                                    <td width="400">
                                                        <input type="text" name="txtRegno" size="20" id="txtRegno" 
                                                               <c:set var="no" value="${param.txtRegno}"></c:set> 
                                                               value="<c:out escapeXml="false" value="${no}"> </c:out>">

                                                    <c:if test="${param.submitted and !examInfo.txtRegnoEditValid}" var="v4">
                                                            <span style="color: red">   Registration Number Invalid </span>
                                                        </c:if>
                                                    </td>
                                                    <td></td>
                                                </tr>

                                                <tr>
                                                    <td>&nbsp;</td>
                                                    <td height="29">&nbsp;</td>
                                                    <td>&nbsp;</td>

                                                    <td><input type="submit" id="cmdExaminfo"  name="cmdExaminfo" value="Update"/></td>
                                                    <td>&nbsp;</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </form>

                                <c:if test="${param.submitted and !v1 and !v2 and !v3 and !v4}">

                                    <%
                                        examinfoEDAO.examinfoUpdate(getServletContext(), examInfo);
                                    %>
                                    <%--<jsp:forward page="stimprovement.jsp"></jsp:forward>--%>
                                </c:if>
                                <br></br>
                                <br></br>
                                <br></br> 
                                <div name="servermessage" align="center" >
                                    <label name="errorssuccmsg" id="errorssuccmsg" value=" "><jsp:getProperty property="errorssuccmsg" name="examInfo"/></label><br>
                                </div>
                            </div>
                        </div>

                    </td>
            </table>
        </div>

        <div id="footer"><%=application.getInitParameter("pageFooter")%></div>
    </body>
</html>



