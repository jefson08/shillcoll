<%-- 
    Document   : clxii
    Created on : Feb 20, 2015, 9:52:10 PM
    Author     : B Mukhim
--%>
<%if ("GET".equalsIgnoreCase(request.getMethod())) {
        out.print("Invalid request -- Please try again");
        //return;
    }%>
<%@page  autoFlush="true" buffer="32kb" %>
<%@page import="shg.util.shgUtil"%>
<%
    //response.setHeader("Pragma", "no-cache");
    //response.setHeader("Cache-Control", "no-store");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="stuEnroll" class="shg.bean.StudentEnroll" scope="session" ></jsp:useBean>
<jsp:useBean id="stuEnrollDAO" class="shg.dao.StudentEnrollDAO" ></jsp:useBean>    
<jsp:useBean id="clxiiinfo" class="shg.bean.ClXiiInfo" ></jsp:useBean>    
<jsp:useBean id="clxiiinfoDAO" class="shg.dao.ClXiiInfoDAO" ></jsp:useBean>
<jsp:useBean id="dbutil" class="shg.util.DatabaseUtility"></jsp:useBean>
<jsp:setProperty name="clxiiinfo" property="*"></jsp:setProperty>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../style/master-css/style.css" rel="stylesheet" />
        <link href="../style/master-css/master-layout.css" rel="stylesheet" />
        <link href="../style/master-css/menu-style.css" rel="stylesheet" />
        <link href="../scripts/jqueryui/ui.css" rel="stylesheet" />
        <link rel="stylesheet" href="../style/master-css/sweet-alert.css">
        <script type="text/javascript" src="../scripts/jquery/jquery-1.6.2.min.js"></script>       
        <script type="text/javascript" src="../scripts/jqueryui/jquery-ui-1.8.16.custom.min.js"></script>
        <script type="text/javascript" src="../scripts/util/populateComboBox.js"></script> 
        <script type="text/javascript" src="../scripts/util/net.js"></script>
        <script src="../scripts/jquery/jquery.js"></script>
        <script src="../scripts/validate/jquery.validate.js"></script>
        <script src="../scripts/validate/additional-methods.js"></script>
        <script src="../scripts/validate/validators.js"></script>
        <script type="text/javascript" src="../scripts/adm/selectSubj.js"></script>
        <script type="text/javascript" src="../scripts/jquery/sweet-alert.min.js"></script>
        <title>Class XII Details</title>
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
                                <h2 id="summary"></h2> <!-- Validation error message display -->
                                <form name="clxiiinfo" method="Post" class="clxiiinfo" id="clxiiinfo" action="clxii.jsp" novalidate="novalidate">
                                    <input type="hidden" name="submitted1" value="true" />
                                    <table border="0" id="subjectName">

                                        <tbody>                
                                            <tr>
                                                <td>Board Roll*</td>
                                                <td><input type="text" name="txtBoardRoll" id="txtBoardRoll" value="${param.txtBoardRoll}" size="10" />
                                                    <c:if test="${param.submitted1 and !clxiiinfo.txtBoardRollValid}" var="v1">
                                                        <span style="color: red">Board Roll is either be Blank OR invalid </span>
                                                    </c:if>
                                                </td>
                                                <td></td>
                                            </tr>
                                            <tr>
                                                <td>Year Pass*</td>
                                                <td><input type="text" name="txtYrPass" id="txtYrPass" value="${param.txtYrPass}" size="4" />
                                                    <c:if test="${param.submitted1 and !clxiiinfo.txtYrPassValid}" var="v3">
                                                        <span style="color: red">Year Pass is either be Blank OR invalid OR Year Pass Cannot be greater than current Year</span>
                                                    </c:if>
                                                </td>
                                                <td></td>
                                            </tr>  
                                            <tr>
                                                <td>Board* &nbsp; &nbsp; &nbsp; &nbsp; </td>
                                                <td> <select name="cmbBoardID" id="cmbBoardID" title="Please select Board" required>
                                                        <option value="">-</option>
                                                        <c:set var="boaid" value="${param.cmbBoardID}"></c:set>
                                                        <c:out escapeXml="false" value="${dbutil.populatePopup(pageContext.request.servletContext,'boardname','boardid','boardname',boaid)}">                                
                                                        </c:out>
                                                    </select>
                                                    <c:if test="${param.submitted1 and !clxiiinfo.cmbBoardIDValid}" var="v4">
                                                        <span style="color: red">Board Name is either be Blank OR invalid</span>
                                                    </c:if>
                                                </td>
                                                <td></td>
                                            </tr>
                                            <tr>
                                                <td>Stream*</td>
                                                <td> <select name="cmbStream" id="cmbStream" title="Please select Stream" required>
                                                        <option value="">-</option>
                                                        <c:set var="comb" value="${'-'}"></c:set>
                                                        <c:out escapeXml="false" value="${dbutil.populateDependentPopup(pageContext.request.servletContext,'clxiisubj','stream','stream', 'boardid',boaid,comb)}">                                
                                                        </c:out>
                                                    </select>
                                                    <c:if test="${param.submitted1 and !clxiiinfo.cmbStreamValid}" var="v5">
                                                        <span style="color: red">Stream is either be Blank OR invalid</span>
                                                    </c:if>
                                                </td>
                                                <td></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                    <table id="add_subject">
                                        <tbody id="clear_subject">

                                        </tbody>
                                    </table>
                                    <table id="marks"><tbody>
                                            <tr>
                                                <td colspan="2" style="text-align: center">
                                                    <c:if test="${param.submitted1 and !clxiiinfo.txtSubjectValid}" var="v6">
                                                        <span style="color: red"> Subject Name is either be Blank OR invalid </span>
                                                    </c:if>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="2" style="text-align: center">
                                                    <c:if test="${param.submitted1 and !clxiiinfo.txtMarksValid}" var="v7">
                                                        <span style="color: red">Marks is either be Blank OR invalid AND Marks Can have maximum of 3 Digits</span>
                                                    </c:if>
                                                </td>
                                            </tr>                                               
                                        </tbody>
                                    </table>
                                    <table>
                                        <tr>
                                            <td>Total Mark*</td>
                                            <td><input type="text" name="txtTotalMarks" id="txtTotalMarks" value="${param.txtTotalMarks}" size="4" />
                                                <c:if test="${param.submitted1 and !clxiiinfo.txtTotalMarksValid}" var="v3">
                                                    <span style="color: red">Total Marks is either be Blank OR invalid OR Total Marks can have maximum of 4 Digits</span>
                                                </c:if>
                                            </td>
                                            <td></td>
                                        </tr>  
                                        <tr>                                          
                                            <td style="text-align: right">
                                                <input type="hidden" value="Add Subject" name="AddSub" id="AddSub" /> </td>
                                            <td style="text-align: left"><input type="hidden" value="Save" name="cmdSave" id="cmdSave"/> </td>
                                        </tr>               
                                    </table>
                                </form>
                                <h3 id="warning"></h3> <!-- Error Message Display -->
                                <c:if test="${param.submitted1 and !v1 and !v2 and !v3 and !v4 and !v5 and !v6 and !v7}">
                                    <%
                                        int i = stuEnrollDAO.insertStudent(getServletContext(), stuEnroll, clxiiinfo);

                                        if (i <= 0) {
                                            out.println("<script>swal(\"Oops...\", \"Server Has Encountered an Internal Error.!\", \"error\");</script>");
                                        } else if (i == 2) {
                                            out.println("<script>swal(\"Oops...\", \"Record Already exist!\", \"error\");</script>");
                                        } else if (i == 3) {
                                            out.println("<script>swal(\"Oops...\", \"Subject Repeat!\", \"error\");</script>");
                                        } else {
                                    %>
                                    <jsp:forward page="uploadstuphoto.jsp"></jsp:forward>
                                    <%
                                        }

                                    %>
                                </c:if>
                                <div id="waitbox" title="Loading Details">
                                    <div id="waitboxmsg" style="font-size: 20px;font-weight: bold;">
                                        Sending Request. Please Wait....<span id="processing" style="display: visible;"><img width="26" height="28" src="../misc/images/loading_1.gif"></span>
                                    </div><br>
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
