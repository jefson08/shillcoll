<%-- 
    Document   : examinfo
    Created on : May 4, 2015, 11:23:20 AM
    Author     : Rans
--%>
<%@page import="shg.util.shgUtil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:useBean id="examInfo" class="shg.bean.examinfo_Bean" ></jsp:useBean>
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
                                                    <td colspan='4' style="text-align: center">
                                                        <label><input type="radio" title="Select Semester or Year"  id="radYear" name="radYearOrSem" value="y" ${param.radYearOrSem=='y'?'checked':''} />Annual</label>
                                                        <label><input type="radio" title="Select Semester or Year"   id="radSem" name="radYearOrSem" value="s" ${param.radYearOrSem=='s'?'checked':''} />Semester</label>
                                                            <c:if test="${param.submitted and !examInfo.radYearOrSemValid}" var="v9">
                                                            <span style="color: red">Option Year or Semester not Selected</span>
                                                        </c:if>
                                                    </td>
                                                    <td><div class="radSYR"></div></td>
                                                </tr>

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

                                                        <input type="text" name="txtUnirollno" size="20" id="txtUnirollno" 
                                                               <c:set var="unino" value="${param.txtUnirollno}"></c:set> 
                                                               value="<c:out escapeXml="false" value="${unino}"> </c:out>">

                                                        <c:if test="${param.submitted and !examInfo.txtUnirollnoValid}" var="v3">
                                                            <span style="color: red"> Roll Number Invalid </span>
                                                        </c:if>

                                                    </td>
                                                </tr>

                                                <tr>
                                                    <td width="16">&nbsp;</td>
                                                    <td width="200" height="29">Registration Number*</td>
                                                    <td width="10">:</td>
                                                    <td width="400">

                                                        <input type="text" name="txtRegno" size="20" id="txtRegno" 
                                                               <c:set var="no" value="${param.txtRegno}"></c:set> 
                                                               value="<c:out escapeXml="false" value="${no}"> </c:out>">

                                                        <c:if test="${param.submitted and !examInfo.txtRegnoValid}" var="v4">
                                                            <span style="color: red">   Registration Number Invalid </span>
                                                        </c:if>

                                                    </td>
                                                </tr>

                                                <tr>
                                                    <td width="16">&nbsp;</td>
                                                    <td width="200" height="29">Semester/Year*</td>
                                                    <td width="10">:</td>
                                                    <td width="400">

                                                        <input type="text" list="semyr" id="seye" name="seye" size="20" autocomplete="off" placeholder=" Semester/Year" 
                                                               <c:set var="sy" value="${param.seye}"/>
                                                               value="<c:out escapeXml="false" value="${sy}"/>">      
                                                        <datalist id="semyr" name="semyr">
                                                            <option value="1s"/>
                                                            <option value="2s"/>
                                                            <option value="3s"/>
                                                            <option value="4s"/>
                                                            <option value="5s"/>
                                                            <option value="6s"/>
                                                            <option value="1y"/>
                                                            <option value="2y"/>
                                                            <option value="3y"/>
                                                        </datalist>

                                                        <c:if test="${param.submitted and !examInfo.seyeValid}" var="v5">
                                                            <span style="color: red"> Semester Not Selected </span>
                                                        </c:if>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td width="16">&nbsp;</td>
                                                    <td width="200" height="29">Category*</td>
                                                    <td width="10">:</td>
                                                    <td width="400">
                                                        <label><input type="radio" name="nri" id="nri" value="r" ${param.nri=='r'?'checked':''} /> Regular</label>
                                                        <label><input type="radio" name="nri" id="nri" value="n" ${param.nri=='n'?'checked':''}/> Non-Regular</label>
                                                        <label><input type="radio" name="nri" id="nri" value="i" ${param.nri=='i'?'checked':''}/> Improvement</label>
                                                            <c:if test="${param.submitted and !examInfo.nriValid}" var="v6">
                                                            <br><span style="color: red">Category not selected</span>
                                                        </c:if>
                                                    </td>
                                                </tr>

                                                <tr>
                                                    <td width="16">&nbsp;</td>
                                                    <td width="200">Date of Payment*</td>
                                                    <td width="10"> : </td>
                                                    <td width="400"><input class="datepick" type="text" name="txtPmtDate" id="txtPmtDate" value="${param.txtPmtDate}" size="10" />
                                                        <div style="display: none;"> <img id="calImg" src="../scripts/jquerydatepicker/img/calendar.gif" alt="Popup" class="trigger"></div>
                                                        eg. dd-mm-yyyy
                                                        <c:if test="${param.submitted and !examInfo.txtPmtDateValid}" var="v7">
                                                            <span style="color: red"> Date of Payment is either Blank OR invalid</span>
                                                        </c:if>
                                                    </td>
                                                </tr>

                                                <tr>
                                                    <td width="16">&nbsp;</td>
                                                    <td width="200" height="29">Payment Status*</td>
                                                    <td width="10">:</td>
                                                    <td width="400">
                                                        <label><input type="radio" name="pmtstatus" id="pmtstatus" value="y" ${param.pmtstatus=='y'?'checked':''} /> Paid</label>
                                                        <label><input type="radio" name="pmtstatus" id="pmtstatus" value="n" ${param.pmtstatus=='n'?'checked':''}/> Unpaid</label>
                                                            <c:if test="${param.submitted and !examInfo.pmtStatusValid}" var="v8">
                                                            <br><span style="color: red">Category not selected</span>
                                                        </c:if>
                                                    </td>
                                                </tr>

                                                <tr>
                                                    <td>&nbsp;</td>
                                                    <td height="29">&nbsp;</td>
                                                    <td>&nbsp;</td>

                                                    <td><input type="submit" id="cmdExaminfo"  name="cmdExaminfo" value="Next"/></td>
                                                    <td>&nbsp;</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </form>

                                <c:if test="${param.submitted and !v1 and !v2 and !v3 and !v4 and !v5 and !v6}">
                                    <jsp:forward page="papersExam.jsp"></jsp:forward>
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
