<%-- 
    Document   : stuenrolledit
    Created on : Feb 20, 2015, 9:52:10 PM
    Author     : B Mukhim
--%>
<%@page  autoFlush="true" buffer="32kb" %>
<%@page import="shg.util.shgUtil"%>
<%
    //response.setHeader("Pragma", "no-cache");
    //response.setHeader("Cache-Control", "no-store");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="dbutil" class="shg.util.DatabaseUtility"></jsp:useBean>
<jsp:useBean id="stuEnroll" class="shg.bean.StudentEnroll" scope="session" ></jsp:useBean>
<jsp:useBean id="stuEnrollEdit" class="shg.dao.StudentEnrollRetrieve" />
<jsp:useBean id="stuEnrollEditDAO" class="shg.dao.StudentEnrollEditDAO" />

<jsp:setProperty name="stuEnroll" property="*"></jsp:setProperty>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${!param.submitted}">
    <c:set var="rollno" value="${param.rollno}" />
    ${stuEnrollEdit.retrieveStudent(pageContext.request.servletContext, stuEnroll, rollno)}
</c:if>

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
        <script type="text/javascript" src="../scripts/util/populateComboBox.js"></script>
        <script type="text/javascript" src="../scripts/util/net.js"></script>
        <script type="text/javascript" src="../scripts/adm/stuenroll.js"></script>

        <title>Edit Student Details</title>
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
                            <div class="frame-header" >Student Details</div>
                            <div id="processing-area">
                                <form name="stenroll" method="POST">
                                    <input type="hidden" name="submitted" value="true" />
                                    <input type="hidden" name="rollno" value="${stuEnroll.rollno}" />
                                    <table border="0">
                                        <tbody>
                                            <c:if test="${!(stuEnroll.radYearOrSem.equals('s') or stuEnroll.radYearOrSem.equals('y'))}">
                                                <c:set var="disabled" value="style=\"pointer-events:none\"" scope="page"></c:set>
                                            </c:if>
                                            <c:if test="${(stuEnroll.radYearOrSem.equals('s') or stuEnroll.radYearOrSem.equals('y'))}">
                                                <tr>
                                                    <td colspan='3' style="text-align: center">
                                                        <label><input type="radio" id="radYear" name="radYearOrSem" value="y" ${stuEnroll.radYearOrSem=='y'?'checked':''} ${disabled}/>Annual</label>
                                                        <label><input type="radio" id="radSem" name="radYearOrSem" value="s" ${stuEnroll.radYearOrSem=='s'?'checked':''} ${disabled}/>Semester</label>
                                                            <c:if test="${param.submitted and !stuEnroll.radYearOrSemValid}" var="v16">
                                                            <span style="color: red">Option Year or Semester not Selected</span>
                                                        </c:if>
                                                    </td>
                                                </tr>
                                            </c:if>
                                            <tr>
                                                <td>Roll No </td>
                                                <td> : </td>
                                                <td><b>${stuEnroll.rollno}</b></td>
                                            </tr>
                                            <tr>
                                                <td>Section *</td>
                                                <td> : </td>
                                                <td> 
                                                    <select name="cmbSection" id="cmbSection">
                                                        <option value="-1">-</option>
                                                        <c:set var="seccode" value="${stuEnroll.cmbSection}"></c:set>
                                                        <c:out escapeXml="false" value="${dbutil.populatePopup(pageContext.request.servletContext,'section','sectioncode','sectionname',seccode)}">                                
                                                        </c:out>
                                                    </select> 
                                                    <c:if test="${param.submitted and !stuEnroll.chkCmbSectionValid(pageContext.request.servletContext)}" var="v17">
                                                        <span style="color: red">Section Not Selected</span>
                                                    </c:if>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Course Name *</td>
                                                <td> : </td>
                                                <td> 
                                                    <select name="cmbCourseName" id="cmbCourseName" ${disabled}>
                                                        <option value="-1">-</option>
                                                        <c:set var="ccode" value="${stuEnroll.cmbCourseName}"></c:set>
                                                        <c:out escapeXml="false" value="${dbutil.populatePopup(pageContext.request.servletContext,'course','coursecode','coursename',ccode)}">                                
                                                        </c:out>
                                                    </select> 
                                                    <c:if test="${param.submitted and !stuEnroll.cmbCourseNameValid}" var="v1">
                                                        Course Name Not Selected
                                                    </c:if>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Combination *</td>
                                                <td> : </td>
                                                <td>
                                                    <select name="cmbCombination" id="cmbCombination" ${disabled}>
                                                        <option value="-1">-</option>
                                                        <c:set var="comb" value="${stuEnroll.cmbCombination}"></c:set>
                                                        <c:out escapeXml="false" value="${dbutil.populateDependentPopup(pageContext.request.servletContext,'coursedetails','combinationcode','combinationcode', 'coursecode', ccode,comb)}">                                
                                                        </c:out>
                                                    </select>
                                                    <c:if test="${param.submitted and !stuEnroll.cmbCombinationValid}" var="v2">
                                                        Combination Not Selected
                                                    </c:if>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Student's Name *</td>
                                                <td> : </td>
                                                <td><input type="text" name="txtStuName" id="txtStuName" value="${stuEnroll.txtStuName}" size="30" />
                                                    <c:if test="${param.submitted and !stuEnroll.txtStuNameValid}" var="v3">
                                                        Student's Name is either be Blank OR invalid 
                                                    </c:if>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Date of Birth *</td>
                                                <td> : </td>
                                                <td><input type="text" name="txtDOB" id="txtDOB" value="${stuEnroll.txtDOB}" size="10" />
                                                    <div style="display: none;"> <img id="calImg" src="../scripts/jquerydatepicker/img/calendar.gif" alt="Popup" class="trigger"></div>
                                                    eg. dd-mm-yyyy
                                                    <c:if test="${param.submitted and !stuEnroll.txtDOBValid}" var="v4">
                                                        Date of Birth is either be Blank OR invalid
                                                    </c:if>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Gender *</td>
                                                <td> : </td>
                                                <td>
                                                    <label><input type="radio" name="radGender" id="radGender" value="m" ${stuEnroll.radGender=='m'?'checked':''} /> Male</label>
                                                    <label><input type="radio" name="radGender" id="radGender" value="f" ${stuEnroll.radGender=='f'?'checked':''}/> Female</label>
                                                        <c:if test="${param.submitted and !stuEnroll.radGenderValid}" var="v5">
                                                        Gender not selected
                                                    </c:if>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>Category *</td>
                                                <td> : </td>
                                                <td>
                                                    ${dbutil.getCategory(pageContext.request.servletContext, stuEnroll.radCategory)}
                                                    <c:if test="${param.submitted and !stuEnroll.chkRadCategoryValid(pageContext.request.servletContext)}" var="v7">
                                                        <span style="color: red">Category not selected</span>
                                                    </c:if>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Father's Name *</td>
                                                <td> : </td>
                                                <td><input type="text" name="txtFName" id="txtFName" value="${stuEnroll.txtFName}" size="30" />
                                                    <c:if test="${param.submitted and !stuEnroll.txtFNameValid}" var="v8">
                                                        Father's name is either blank or invalid
                                                    </c:if>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Mother's Name *</td>
                                                <td> : </td>
                                                <td><input type="text" name="txtMName" id="txtMName" value="${stuEnroll.txtMName}" size="30" />
                                                    <c:if test="${param.submitted and !stuEnroll.txtMNameValid}" var="v9">
                                                        Mother's name is either blank or invalid
                                                    </c:if>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Parent's Phone No.</td>
                                                <td> : </td>
                                                <td><input type="text" name="txtPPhno" id="txtPPhno" value="${stuEnroll.txtPPhno}" size="10" />
                                                    <c:if test="${param.submitted and !stuEnroll.txtPPhnoValid}" var="v10">
                                                        Invalid Phone Number
                                                    </c:if>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Parent's Occupation</td>
                                                <td> : </td>
                                                <td><input type="text" name="txtPOccup" id="txtPOccup" value="${stuEnroll.txtPOccup}" size="40" />
                                                    <c:if test="${param.submitted and !stuEnroll.txtPOccupValid}" var="v11">
                                                        Invalid Parent's Occupation
                                                    </c:if>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Country *</td>
                                                <td> : </td>
                                                <td><select name="cmbCountry" id="cmbCountry">
                                                        <option value="-1">-</option>
                                                        <c:set var="country" value="${stuEnroll.cmbCountry}"></c:set>
                                                        <c:out escapeXml="false" value="${dbutil.populatePopup(pageContext.request.servletContext,'country','countrycode','countryname',country)}">                                
                                                        </c:out>
                                                    </select>
                                                    <c:if test="${param.submitted and !stuEnroll.cmbCountryValid}" var="v18">
                                                        <span style="color: red">Country Not Selected</span>
                                                    </c:if>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>State *</td>
                                                <td> : </td>
                                                <td><select name="cmbState" id="cmbState">
                                                        <option value="-1">-</option>
                                                        <c:set var="state" value="${stuEnroll.cmbState}"></c:set>
                                                        <c:out escapeXml="false" value="${dbutil.populateDependentPopup(pageContext.request.servletContext,'state','statecode','statename', 'countrycode', country, state)}">                                
                                                        </c:out>
                                                    </select>
                                                    <c:if test="${param.submitted and !stuEnroll.cmbStateValid}" var="v19">
                                                        <span style="color: red">State Not Selected</span>
                                                    </c:if>
                                                </td>

                                            </tr>
                                            <tr>
                                                <td>District *</td>
                                                <td> : </td>
                                                <td><select name="cmbDistrict" id="cmbDistrict">
                                                        <option value="-1">-</option>
                                                        <c:set var="dist" value="${stuEnroll.cmbDistrict}"></c:set>
                                                        <c:out escapeXml="false" value="${dbutil.populateDependentPopup(pageContext.request.servletContext,'district','districtcode','districtname', 'statecode', state, dist)}">                                
                                                        </c:out>
                                                    </select>
                                                    <c:if test="${param.submitted and !stuEnroll.cmbDistrictValid}" var="v20">
                                                        <span style="color: red">District Not Selected</span>
                                                    </c:if>
                                                </td>

                                            </tr>
                                            <tr>
                                                <td style="vertical-align: text-top">Mailing Address *</td>
                                                <td> : </td>
                                                <td><textarea name="txtMAddress" id="txtMAddress" rows="5" cols="30">
                                                        ${stuEnroll.txtMAddress}
                                                    </textarea>
                                                    <c:if test="${param.submitted and !stuEnroll.txtMAddressValid}" var="v12">
                                                        Mailing Address cannot be blank
                                                    </c:if>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td style="vertical-align: text-top">Permanent Address *</td>
                                                <td> : </td>
                                                <td><label>
                                                        <input name="copyaddress" type="checkbox" id="copyaddress" value="copy" ${param.copyaddress=='copy'?'checked':''} />
                                                        Same as Mailing Address</label><br />
                                                    <textarea name="txtPAddress" id="txtPAddress" rows="5" cols="30">
                                                        ${stuEnroll.txtPAddress}
                                                    </textarea>
                                                    <c:if test="${param.submitted and !stuEnroll.txtPAddressValid}" var="allValid">
                                                        Permanent Address cannot be blank
                                                    </c:if>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Mobile</td>
                                                <td> : </td>
                                                <td><input type="text" name="txtMobile" id="txtMobile" value="${stuEnroll.txtMobile}" size="10" />
                                                    <c:if test="${param.submitted and !stuEnroll.txtMobileValid}" var="v13">
                                                        Invalid Mobile Number
                                                    </c:if>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Email</td>
                                                <td> : </td>
                                                <td><input type="text" name="txtEmail" id="txtEmail" value="${stuEnroll.txtEmail}" size="30" />
                                                    <c:if test="${param.submitted and !stuEnroll.txtEmailValid}" var="v14">
                                                        Invalid Email Address
                                                    </c:if>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Family Income</td>
                                                <td> : </td>
                                                <td><input type="text" name="txtIncome" id="txtIncome" value="${stuEnroll.txtIncome}" size="30" />
                                                    <c:if test="${param.submitted and !stuEnroll.txtIncomeValid}" var="v15">
                                                        Invalid Income Value
                                                    </c:if>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td colspan="3" style="text-align: center"><input type="submit" value="Save" name="cmdSave" /> </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </form>
                            </div>
                        </div>

                    </td>
            </table>
        </div>
        <c:if test="${param.submitted and !v1 and !v2 and !v3 and !v4 and !v5 and !v6 and !v7 and !v8 and !v9
                      and !v10 and !v11 and !v12 and !v13 and !v14 and !v15}">
            <%
                stuEnrollEditDAO.updateStudent(getServletContext(), stuEnroll);
            %>
        </c:if>
    </body>
</html>
