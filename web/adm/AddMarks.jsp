<%-- 
    Document   : teib
    Created on : Mar 5, 2015, 10:37:35 PM
    Author     : A Mitri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="shg.util.Utility" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<jsp:useBean id="dbutil" class="shg.util.DatabaseUtility"></jsp:useBean>
<jsp:useBean id="AM" class="shg.bean.AddMarks"></jsp:useBean>
<jsp:useBean id="AMDAO" class="shg.dao.AddMarksDAO"></jsp:useBean>

<jsp:setProperty name="AM" property="*"></jsp:setProperty>
<%@page import="shg.util.shgUtil"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="../scripts/jquery/jquery-1.6.2.min.js"></script>
        <link rel="stylesheet" href="../style/TableCSSCode.css" type="text/css" charset="utf-8" />
        <script type="text/javascript" src="../scripts/jqueryform/ajaxupload.3.5.js"></script>
        <script type="text/javascript" src="../scripts/jqueryform/jquery.form.js"></script>
        <script type="text/javascript" src="../scripts/adm/AddMarks.js"></script>

        <link href="../style/master-css/style.css" rel="stylesheet" />
        <link href="../style/master-css/master-layout.css" rel="stylesheet" />
        <link href="../style/master-css/menu-style.css" rel="stylesheet" />


        <title>Marks Entry</title>
    </head>
    <body>
        <h1></h1>
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
                            <div class="frame-header" >Insert Marks</div>
                            <div id="processing-area">
                                <h2 id="summary"></h2>


                                <form name="frmAddMarks" method="POST" action="" id="frmAddMarks">
                                    <input type="hidden" name="submitted" value="true" />
                                    <table border="1" rules="none" frame="box" align="center">
                                        <tr><td width="150"><b>Select System :</b> </td>

                                            <td width="150">    <input type="radio" id="radYear" name="radYearOrSem" value="y" />Annual</td>
                                            <td width="150">   <input type="radio" id="radSem" name="radYearOrSem" value="s" ${!(param.submitted)?'checked':''} />Semester
                                                <c:if test="${param.submitted and !empty(param.radYearOrSem)}">

                                                    <c:set var="yearorsem" value="${param.radYearOrSem}"></c:set>
                                                </c:if>
                                                <c:if test="${!(param.submitted)}" >
                                                    <c:set var="yearorsem" value="s" ></c:set>
                                                </c:if>
                                            </td></tr>
                                    </table>
                                    <br>
                                    <table border="0" align="center">
                                        <tbody>
                                            <tr align="left">
                                                <th width="120">
                                                    NEHU Roll Number 
                                                </th> 
                                                <th width="120"> Category</th>
                                                <th width="120">Year/Semester</th>
                                                <th width="120">Exam Month</th>
                                                <th width="120">Exam Year</th>
                                                <th rowspan="2"><input type="button" value="Show Papers" id="cmdSearch" name="cmdSearch" style=" width: 100px; height: 45px" /></th>
                                            </tr>
                                            <tr>
                                                <!--<td> <input type="text" name="txtNehuRollNo" id="txtNehuRollNo" value="${param.submitted?param.txtNehuRollNo:''}" size="20" /></td>-->
                                                <td>
                                                     <input type="text" list="nehurollno" id="txtNehuRollNo" name="txtNehuRollNo" size="20" autocomplete="off" placeholder=" Roll Number"
                                                               <c:set var="rol" value="${param.txtNehuRollNo}"/>
                                                               value="<c:out escapeXml="false" value="${rol}"/>">
                                                        <datalist id="nehurollno" name="nehurollno"> 
                                                        </datalist>

                                                        
                                                </td>
                                                <td>

                                                    <select name="cmbNR" id="cmbNR" style="width: 120px">
                                                        <option value="-1">-</option>
                                                        <option value="R" ${param.cmbNR=='R'?'selected':''}>Regular</option>
                                                        <option value="N" ${param.cmbNR=='N'?'selected':''}>Non Regular</option>
                                                        <option value="I" ${param.cmbNR=='I'?'selected':''}>Improvement</option>
                                                        <c:set var="nr" value="${param.cmbNR}"></c:set>

                                                        </select>

                                                    </td>


                                                    <td>

                                                        <select name="cmbYearOrSemNo" id="cmbYearOrSemNo" style="width: 120px">
                                                        <c:choose>
                                                            <c:when test="${param.submitted and yearorsem=='y'}">
                                                                <option value="-1">-</option> 

                                                                <option value="y1" ${param.cmbYearOrSemNo=='y1'?'selected':''}>1</option>
                                                                <option value="y2" ${param.cmbYearOrSemNo=='y2'?'selected':''}>2</option>
                                                                <option value="y3" ${param.cmbYearOrSemNo=='y3'?'selected':''}>3</option>

                                                            </c:when>
                                                            <c:otherwise>
                                                                <option value="-1">-</option> 

                                                                <option value="s1" ${param.cmbYearOrSemNo=='s1'?'selected':''}>1</option>
                                                                <option value="s2" ${param.cmbYearOrSemNo=='s2'?'selected':''}>2</option>
                                                                <option value="s3" ${param.cmbYearOrSemNo=='s3'?'selected':''}>3</option>
                                                                <option value="s4" ${param.cmbYearOrSemNo=='s4'?'selected':''}>4</option>
                                                                <option value="s5" ${param.cmbYearOrSemNo=='s5'?'selected':''}>5</option>
                                                                <option value="s6" ${param.cmbYearOrSemNo=='s6'?'selected':''}>6</option>

                                                            </c:otherwise> 
                                                        </c:choose>   
                                                        <c:set var="yearorsemno" value="${param.cmbYearOrSemNo}"></c:set>
                                                        </select> 
                                                    </td>
                                                    <td>
                                                               <input type="text" list="emonth" id="txtExamMonth" name="txtExamMonth" size="10" autocomplete="off" placeholder="Month"
                                                               <c:set var="em" value="${param.txtExamMonth}"/>
                                                               value="<c:out escapeXml="false" value="${em}"/>">
                                                        <datalist id="emonth" name="emonth">
                                                            <option value="January"></option>
                                                            <option value="February"></option>
                                                            <option value="March"></option>
                                                            <option value="April"></option>
                                                            <option value="May"></option>
                                                            <option value="June"></option>
                                                            <option value="July"></option>
                                                            <option value="August"></option>
                                                            <option value="September"></option>
                                                            <option value="October"></option>
                                                            <option value="November"></option>
                                                            <option value="December"></option>
                                                        </datalist>
                                                       
                                                </td>
                                                <td><input type="text" list="eyear" id="txtExamYear" name="txtExamYear" size="10" autocomplete="off" placeholder="Year"
                                                               <c:set var="ey" value="${param.txtExamYear}"/>
                                                               value="<c:out escapeXml="false" value="${ey}"/>">
                                                        <datalist id="eyear" name="eyear"> 
                                                        </datalist></td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <c:if test="${param.submitted and !AM.txtNehuRollNoValid}" var="v1">
                                                        <span style="color:red" >Enter Roll No.</span>
                                                    </c:if>
                                                </td><td>
                                                    <c:if test="${param.submitted and !AM.cmbNRValid}" var="v2">
                                                        <span style="color:red" >Invalid Category</span>
                                                    </c:if>
                                                </td>
                                                <td>
                                                    <c:if test="${param.submitted and !AM.cmbYearOrSemNoValid}" var="v3">
                                                        <span style="color:red" >Invalid Semester or Year</span>
                                                    </c:if>
                                                </td>
                                                <td>
                                                    <c:if test="${param.submitted and !AM.isTxtMarksThValid()}" var="v4">
                                                        <span style="color:red" >Invalid Marks</span>
                                                    </c:if>
                                                </td>
                                                <td>
                                                    <c:if test="${param.submitted and !AM.isTxtMarksPrValid()}" var="v5">
                                                        <span style="color:red" >Invalid Marks</span>
                                                    </c:if>
                                                </td>
                                                <td>
                                                    <c:if test="${param.submitted and !AM.isCmbDivValid()}" var="v5">
                                                        <span style="color:red" >Division not selected</span>
                                                    </c:if>
                                                </td>
                                                <td>
                                                    <c:if test="${param.submitted and !AM.txtExamMonthValid}" var="v3">
                                                        <span style="color:red" >Invalid Exam Month</span>
                                                    </c:if>
                                                </td>
                                                <td>
                                                    <c:if test="${param.submitted and !AM.txtExamYearValid}" var="v3">
                                                        <span style="color:red" >Invalid Exam Year</span>
                                                    </c:if>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                    <br> <div id="srchlist">
                                        <c:if test="${param.submitted}">

                                            <div class="CSSTableGenerator"><table>
                                                    <tr><td>Subject</td>
                                                        <td>Paper Id</td>
                                                        <td>Paper Name</td>
                                                        <td>Theory Marks</td> 
                                                        <td>Practical Marks</td> 
                                                    <input type="hidden" name="txtExamID" id="txtExamID" value="${AM.txtExamID}" />
                                                    <input type="hidden" name="txtRollNo" id="txtRollNo" value="${AM.txtRollNo}" />
                                                    <input type="hidden" name="txtNehuRollNo" id="txtNehuRollNo" value="${AM.txtNehuRollNo}" /></tr>
                                                    <c:forEach items="${AM.txtSubjectName}" var="cur" begin="0" varStatus="status">
                                                        <tr><td><label><c:out value="${AM.txtSubjectName[status.index]}">   </c:out></label>
                                                                <input type="hidden" name="txtSubjectCode" id="txtSubjectCode" value="${AM.txtSubjectCode[status.index]}" /></td>
                                                            <td><label><c:out value="${AM.txtPaperCode[status.index]}"></c:out></label>
                                                                <input type=hidden name=txtPaperCode id=txtPaperCode value="${AM.txtPaperCode[status.index]}" /></td>
                                                            <td><label><c:out value="${AM.txtPaperName[status.index]}" > </c:out></label></td>
                                                            <td><input type=text name=txtMarksTh id=txtMarksTh value="${AM.txtMarksTh[status.index]}" size=\"10\" /></td>
                                                        <input type="Hidden" name="txtSubjectName" id="txtSubjectName" value="${AM.txtSubjectName[status.index]}"/>
                                                        <c:choose>
                                                            <c:when test="${AM.txtMarksPr[status.index]=='-1'}">
                                                                <td><input type="Hidden" name="txtMarksPr" id="txtMarksPr" value="-1"  /></td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                <td><input type="text" name="txtMarksPr" id="txtMarksPr" value="${AM.txtMarksPr[status.index]}" size=\"10\" /></td>

                                                            </c:otherwise>
                                                        </c:choose>
                                                        </tr>
                                                    </c:forEach>
                                                    <tr>
                                                        <td><b>Division &nbsp;&nbsp;</b>
                                                            <select name="cmbDiv" id="cmbDiv"> 
                                                                <c:choose>
                                                                    <c:when test="${yearorsemno=='s6' or yearorsemno=='y3'}">
                                                                        <option value="-1">-</option>
                                                                        <option value="I"  ${AM.cmbDiv == 'I' ? 'selected' : ''}>I</option>
                                                                        <option value="II" ${AM.cmbDiv == 'II' ? 'selected' : ''}>II</option>
                                                                        <option value="III" ${AM.cmbDiv == 'III' ? 'selected' : ''}>III</option>
                                                                        <option value="fail" ${AM.cmbDiv == 'fail' ? 'selected' : ''}>Failed</option>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <option value="-1">-</option>
                                                                        <option value="PASS" ${AM.cmbDiv == 'PASS' ? 'selected' : ''}>Passed</option>
                                                                        <option value="FAIL" ${AM.cmbDiv == 'FAIL' ? 'selected' : ''}>Failed</option>
                                                                    </c:otherwise>
                                                                </c:choose>

                                                            </select>
                                                        </td>
                                                        <td>
                                                            <c:choose>
                                                                <c:when test="${yearorsemno=='s6' or yearorsemno=='y3'}"><b>Position &nbsp;&nbsp;</b>
                                                                    <select name="cmbPos" id="cmbPos"> 
                                                                        <option value="-1">-</option>
                                                                        <option value="1" ${AM.cmbPos == '1' ? 'selected' : ''}>1st</option>
                                                                        <option value="2" ${AM.cmbPos == '2' ? 'selected' : ''}>2nd</option>
                                                                        <option value="3" ${AM.cmbPos == '3' ? 'selected' : ''}>3rd</option>
                                                                        <option value="4" ${AM.cmbPos == '4' ? 'selected' : ''}>4th</option>
                                                                        <option value="5" ${AM.cmbPos == '5' ? 'selected' : ''}>5th</option>
                                                                        <option value="6" ${AM.cmbPos == '6' ? 'selected' : ''}>6th</option>
                                                                        <option value="7" ${AM.cmbPos == '7' ? 'selected' : ''}>7th</option>
                                                                        <option value="8" ${AM.cmbPos == '8' ? 'selected' : ''}>8th</option>
                                                                        <option value="9" ${AM.cmbPos == '9' ? 'selected' : ''}>9th</option>
                                                                        <option value="10" ${AM.cmbPos == '10' ? 'selected' : ''}>10th</option>
                                                                    </select>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <select name="cmbPos" id="cmbPos" disabled> 
                                                                        <option value="-1">-</option>
                                                                        <option value="1" ${AM.cmbPos == '1' ? 'selected' : ''}>1st</option>
                                                                        <option value="2" ${AM.cmbPos == '2' ? 'selected' : ''}>2nd</option>
                                                                        <option value="3" ${AM.cmbPos == '3' ? 'selected' : ''}>3rd</option>
                                                                        <option value="4" ${AM.cmbPos == '4' ? 'selected' : ''}>4th</option>
                                                                        <option value="5" ${AM.cmbPos == '5' ? 'selected' : ''}>5th</option>
                                                                        <option value="6" ${AM.cmbPos == '6' ? 'selected' : ''}>6th</option>
                                                                        <option value="7" ${AM.cmbPos == '7' ? 'selected' : ''}>7th</option>
                                                                        <option value="8" ${AM.cmbPos == '8' ? 'selected' : ''}>8th</option>
                                                                        <option value="9" ${AM.cmbPos == '9' ? 'selected' : ''}>9th</option>
                                                                        <option value="10" ${AM.cmbPos == '10' ? 'selected' : ''}>10th</option>
                                                                    </select>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>

                                                        <td>     <input type="submit" name="cmdSave" id="cmdSave" value="Save" /></td></tr>
                                                </table></div>
                                            </c:if>
                                    </div>

                                    <h3 id="warning"></h3> 

                                    <c:if test="${param.submitted}">
                                        <%

                                            int res = AMDAO.UpdateMarks(getServletContext(), AM);
                                            if (res == 1) {
                                                System.out.println("operation Successful");
                                            } else {
                                                System.out.println("operation Unuccessful");
                                            }
                                        %>
                                    </c:if>


                                </form>
                            </div>
                            <div id="msg" >






<!--<div id="footer"><%=application.getInitParameter("pageFooter")%></div>-->
                            </div>

                            </body>

                            </html>
