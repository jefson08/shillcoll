<%-- 
    Document   : examinfo
    Created on : May 4, 2015, 11:23:20 AM
    Author     : TEIBOR
--%>
<%@page import="shg.util.shgUtil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:useBean id="examInfo" class="shg.bean.examinfo_Bean" scope ="session"></jsp:useBean>
<jsp:setProperty name="examInfo" property="*"></jsp:setProperty>

<%@page autoFlush="true" buffer="32kb" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../style/master-css/style.css" rel="stylesheet" />
        <link href="../style/master-css/master-layout.css" rel="stylesheet" />
        <link href="../style/master-css/menu-style.css" rel="stylesheet" /> 

        <link rel="stylesheet" href="../scripts/jquerydatepicker/css/jquery.datepick.css" type="text/css" charset="utf-8" />

        <script type="text/javascript" src="../scripts/jquery/jquery-1.6.2.min.js"></script>
        <script type="text/javascript" src="../scripts/jquerydatepicker/jquery.datepick.js"></script>
        <script type="text/javascript" src="../scripts/jquery/jquery.maskedinput-1.3.js"></script>
        <script type="text/javascript" src="../scripts/util/net.js"></script>

        <script type="text/javascript" src="../scripts/spinner/spin.js"></script>
        <script type="text/javascript" src="../scripts/spinner/spin.min.js"></script>

        <!--        <script type="text/javascript" src="../scripts/jqueryui/jquery-ui-1.8.16.custom.min.js"></script>-->
        <script type="text/javascript" src="../scripts/adm/examinfo_autocompleter.js"></script>

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
                                <form  name="exampayment" id="exampayment" method="POST">
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

                                                        <input type="text" id="rollno" name="rollno" size="20">                             <c:set var="rol" value="${param.rollno}"/>
                                                    </td>
                                                </tr>

                                                <tr>
                                                    <td width="16">&nbsp;</td>
                                                    <td width="200" height="29">Batch</td>
                                                    <td width="10">:</td>
                                                    <td width="400">

                                                        <input type="text"  size="20" name="txtBatch" id="txtBatch">
                                                    </td>
                                                </tr>

                                                <tr>
                                                    <td width="16">&nbsp;</td>
                                                    <td width="200" height="29"> University Roll Number</td>
                                                    <td width="10">:</td>
                                                    <td width="400">

                                                        <input type="text" name="txtUnirollno" size="20" id="txtUnirollno">

                                                    </td>
                                                </tr>

                                                <tr>
                                                    <td width="16">&nbsp;</td>
                                                    <td width="200" height="29">Registration Number</td>
                                                    <td width="10">:</td>
                                                    <td width="400">

                                                        <input type="text" name="txtRegno" size="20" id="txtRegno" >

                                                    </td>
                                                </tr>

                                                <tr>
                                                    <td width="16">&nbsp;</td>
                                                    <td width="200" height="29">Semester/Year</td>
                                                    <td width="10">:</td>
                                                    <td width="400">

                                                        <input type="text" list="semyr" id="seye" name="seye" size="20">
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td width="16">&nbsp;</td>
                                                    <td width="200" height="29">Category</td>
                                                    <td width="10">:</td>
                                                    <td width="400">
                                                        <input type="text" list="semyr" id="nri" name="nri" size="20">
                                                    </td>
                                                </tr>

                                                <tr>
                                                    <td width="16">&nbsp;</td>
                                                    <td width="200">Date of Payment</td>
                                                    <td width="10"> : </td>
                                                    <td width="400">
                                                        <input type="text" id="dat" name="dat" size="20">

                                                    </td>
                                                </tr>

                                                <tr>
                                                    <td width="16">&nbsp;</td>
                                                    <td width="200" height="29">Payment Status</td>
                                                    <td width="10">:</td>
                                                    <td width="400">
                                                        <input type="text" id="payment" name="payment" size="20">
                                                     </td>
                                                </tr>
                                                <tr>
                                                    <td width="16">&nbsp</td>
                                                    <td width="200" height="29"></td>
                                                    <td width="10">:</td>
                                                    <td width="800">
                                                      
                                                        <table border="2">
                                                            <tr>
                                                                <td width="300"> Subject</td>
                                                                <td width="300">Papers</td>
                                                                <td width="100"> Amount</td>
                                                            </tr>
                                                            <tr>
                                                                <td>Physics</td>
                                                                <td>Physical Physics</td>
                                                                <td>200 Rs</td>
                                                            </tr>
                                                            
                                                        </table>
                                                   
                                                    </td>
                                                </tr>
                                                
                                                <tr>
                                                    <td>&nbsp;</td>
                                                    <td height="29">&nbsp;</td>
                                                    <td>&nbsp;</td>

                                                    <td><input type="submit" id="cmdExaminfo"  name="cmdExaminfo" value="Submit"/></td>
                                                    <td>&nbsp;</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </form>

                                <c:if test="${param.submitted}">
                                    
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
