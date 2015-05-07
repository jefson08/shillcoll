<%-- 
    Document   : stuenroll
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
<%!//DatabaseUtility dbutil = new DatabaseUtility();%>
<jsp:useBean id="stuEnroll" class="shg.bean.StudentEnroll" scope="session" ></jsp:useBean>
<jsp:useBean id="stuEnrollDAO" class="shg.dao.StudentEnrollDAO" ></jsp:useBean>
<jsp:setProperty name="stuEnroll" property="*"></jsp:setProperty>

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
    <script type="text/javascript" src="../scripts/util/populateComboBox.js"></script>
    <script type="text/javascript" src="../scripts/util/net.js"></script>
    <script src="../scripts/jquery/jquery.validate.js"></script>
    <script src="../scripts/jquery/additional-methods.js"></script>
    <script src="../scripts/validate/validators.js"></script>
    <script type="text/javascript" src="../scripts/adm/stuenroll.js"></script>
   
    <title>Student Enrollment</title>
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
                <h2 id="summary"></h2>
                <form name="stenroll" id="stenroll" method="POST">
                  <input type="hidden" name="submitted" value="true" />

                  <table border="0">
                    <tbody>
                      <tr>
                        <td colspan='3' style="text-align: center">
                          <label><input type="radio" title="Select Semester or Year"  id="radYearOrSem" name="radYearOrSem" value="y" ${param.radYearOrSem=='y'?'checked':''} />Annual</label>
                          <label><input type="radio" title="Select Semester or Year"   id="radYearOrSem" name="radYearOrSem" value="s" ${param.radYearOrSem=='s'?'checked':''} />Semester</label>
                            <c:if test="${param.submitted and !stuEnroll.radYearOrSemValid}" var="v16">
                            <span style="color: red">Option Year or Semester not Selected</span>
                          </c:if>
                        </td>
                        <td><div class="radSYR"></div></td>
                      </tr>
                      <tr>
                        <td colspan="3">

                        </td>
                        <td></td>
                      </tr>
                      <tr>
                        <td>Section *</td>
                        <td> : </td>
                        <td> 
                          <select name="cmbSection" id="cmbSection" required title=" Section Not Selected ">
                            <option value="-1">-</option>
                            <c:set var="seccode" value="${param.cmbSection}"></c:set>
                            <c:out escapeXml="false" value="${dbutil.populatePopup(pageContext.request.servletContext,'section','sectioncode','sectionname',seccode)}">                                
                            </c:out>
                          </select> 
                          <c:if test="${param.submitted and !stuEnroll.chkCmbSectionValid(pageContext.request.servletContext)}" var="v17">
                            <span style="color: red">Section Not Selected</span>
                          </c:if>
                        </td>
                        <td></td>
                      </tr>
                      <tr>
                        <td>Course Name *</td>
                        <td> : </td>
                        <td> 
                          <select name="cmbCourseName" id="cmbCourseName" required="" title="Course Name not Selected">
                            <option value="-1">-</option>
                            <c:set var="ccode" value="${param.cmbCourseName}"></c:set>
                            <c:out escapeXml="false" value="${dbutil.populatePopup(pageContext.request.servletContext,'course','coursecode','coursename',ccode)}">                                
                            </c:out>
                          </select> 
                          <c:if test="${param.submitted and !stuEnroll.cmbCourseNameValid}" var="v1">
                            <span style="color: red">Course Name Not Selected</span>
                          </c:if>
                        </td>
                        <td></td>
                      </tr>
                      <tr>
                        <td>Combination *</td>
                        <td> : </td>
                        <td>
                          <select name="cmbCombination" id="cmbCombination" required="" title="Combination not Selected">
                            <option value="-1">-</option>
                            <c:set var="comb" value="${param.cmbCombination}"></c:set>
                            <c:out escapeXml="false" value="${dbutil.populateDependentPopupFromTwoTableOneCondition(pageContext.request.servletContext,'combination' , 'coursedetails','combinationcode', 'combinationcode', 'combinationcode', 'combinationname',
                                                              'coursecode', ccode ,comb)}">                                
                            </c:out>
                          </select>
                          <c:if test="${param.submitted and !stuEnroll.cmbCombinationValid}" var="v2">
                            <span style="color: red">Combination Not Selected</span>
                          </c:if>
                        </td>
                        <td></td>
                      </tr>
                      <tr>
                        <td>Student's Name *</td>
                        <td> : </td>
                        <td><input type="text" name="txtStuName" id="txtStuName" value="${param.txtStuName}" size="30" />
                          <c:if test="${param.submitted and !stuEnroll.txtStuNameValid}" var="v3">
                            <span style="color: red">Student's Name is either be Blank OR invalid</span> 
                          </c:if>
                        </td>
                        <td></td>
                      </tr>
                      <tr>
                        <td>Date of Birth *</td>
                        <td> : </td>
                        <td><input class="datepick" type="text" name="txtDOB" id="txtDOB" value="${param.txtDOB}" size="10" />
                          <div style="display: none;"> <img id="calImg" src="../scripts/jquerydatepicker/img/calendar.gif" alt="Popup" class="trigger"></div>
                          eg. dd-mm-yyyy
                          <c:if test="${param.submitted and !stuEnroll.txtDOBValid}" var="v4">
                            <span style="color: red"> Date of Birth is either be Blank OR invalid</span>
                          </c:if>
                        </td>
                        <td></td>
                      </tr>
                      <tr>
                        <td>Gender *</td>
                        <td> : </td>
                        <td>
                            <label><input type="radio" required="" title="Gender not Selected" name="radGender" id="radGender" value="m" ${param.radGender=='m'?'checked':''} /> Male</label>
                          <label><input type="radio" required="" title="Gender not Selected" name="radGender" id="radGender" value="f" ${param.radGender=='f'?'checked':''}/> Female</label>
                            <c:if test="${param.submitted and !stuEnroll.radGenderValid}" var="v5">
                            <span style="color: red">Gender not selected</span>
                          </c:if>
                        </td>
                        <td><div class="radGEN"></div></td>
                      </tr>
                      <tr>
                        <td>Category *</td>
                        <td> : </td>
                        <td>
                          ${dbutil.getCategory(pageContext.request.servletContext, param.radCategory)}
                          <c:if test="${param.submitted and !stuEnroll.chkRadCategoryValid(pageContext.request.servletContext)}" var="v7">
                            <span style="color: red">Category not selected</span>
                          </c:if>
                        </td>
                        <td><div class="radCAT"></div></td>
                      </tr>
                      <!--//newly added-->

                      <!--//newly added-->
                      <tr>
                        <td>Father's Name *</td>
                        <td> : </td>
                        <td><input type="text" name="txtFName" id="txtFName" value="${param.txtFName}" size="30" />
                          <c:if test="${param.submitted and !stuEnroll.txtFNameValid}" var="v8">
                            <span style="color: red">Father's name is either blank or invalid</span>
                          </c:if>
                        </td>
                        <td></td>
                      </tr>
                      <tr>
                        <td>Mother's Name *</td>
                        <td> : </td>
                        <td><input type="text" name="txtMName" id="txtMName" value="${param.txtMName}" size="30" />
                          <c:if test="${param.submitted and !stuEnroll.txtMNameValid}" var="v9">
                            <span style="color: red">Mother's name is either blank or invalid</span>
                          </c:if>
                        </td>
                        <td></td>
                      </tr>
                      <tr>
                        <td>Parent's Phone No.</td>
                        <td> : </td>
                        <td><input type="text" name="txtPPhno" id="txtPPhno" value="${param.txtPPhno}" size="10" />
                          <c:if test="${param.submitted and !stuEnroll.txtPPhnoValid}" var="v10">
                            <span style="color: red">Invalid Phone Number</span>
                          </c:if>
                        </td>
                        <td></td>
                      </tr>
                      <tr>
                        <td>Parent's Occupation</td>
                        <td> : </td>
                        <td><input type="text" name="txtPOccup" id="txtPOccup" value="${param.txtPOccup}" size="40" />
                          <c:if test="${param.submitted and !stuEnroll.txtPOccupValid}" var="v11">
                            <span style="color: red">Invalid Parent's Occupation</span>
                          </c:if>
                        </td>
                        <td></td>
                      </tr>
                      <!--///Newly added-->
                      <tr>
                        <td>Country *</td>
                        <td> : </td>
                        <td><select name="cmbCountry" id="cmbCountry" required title=" Country Not Selected ">
                            <option value="-1">-</option>
                            <c:set var="country" value="${param.cmbCountry}"></c:set>
                            <c:out escapeXml="false" value="${dbutil.populatePopup(pageContext.request.servletContext,'country','countrycode','countryname',country)}">                                
                            </c:out>
                          </select>
                          <c:if test="${param.submitted and !stuEnroll.cmbCountryValid}" var="v18">
                            <span style="color: red">Country Not Selected</span>
                          </c:if>
                        </td>
                        <td></td>
                      </tr>
                      <tr>
                        <td>State *</td>
                        <td> : </td>
                        <td><select name="cmbState" id="cmbState" required title=" State Not Selected ">
                            <option value="-1">-</option>
                            <c:set var="state" value="${param.cmbState}"></c:set>
                            <c:out escapeXml="false" value="${dbutil.populateDependentPopup(pageContext.request.servletContext,'state','statecode','statename', 'countrycode', country, state)}">                                
                            </c:out>
                          </select>
                          <c:if test="${param.submitted and !stuEnroll.cmbStateValid}" var="v19">
                            <span style="color: red">State Not Selected</span>
                          </c:if>
                        </td>
                        <td></td>
                      </tr>
                      <tr>
                        <td>District *</td>
                        <td> : </td>
                        <td><select name="cmbDistrict" id="cmbDistrict" required title=" District Not Selected ">
                            <option value="-1">-</option>
                            <c:set var="dist" value="${param.cmbDistrict}"></c:set>
                            <c:out escapeXml="false" value="${dbutil.populateDependentPopup(pageContext.request.servletContext,'district','districtcode','districtname', 'statecode', state, dist)}">                                
                            </c:out>
                          </select>
                          <c:if test="${param.submitted and !stuEnroll.cmbDistrictValid}" var="v20">
                            <span style="color: red">District Not Selected</span>
                          </c:if>
                        </td>
                        <td></td>
                      </tr>
                      <!--//newly added-->
                      <tr>
                        <td style="vertical-align: text-top">Mailing Address *</td>
                        <td> : </td>
                        <td><textarea title="Enter Address "required="" class="textarea" name="txtMAddress" id="txtMAddress" rows="5" cols="30">${param.txtMAddress}</textarea>
                          <c:if test="${param.submitted and !stuEnroll.txtMAddressValid}" var="v12">
                            <span style="color: red">Mailing Address cannot be blank</span>
                          </c:if>
                        </td>
                        <td></td>
                      </tr>
                      <tr>
                        <td style="vertical-align: text-top">Permanent Address *</td>
                        <td> : </td>
                        <td><label>
                            <input name="copyaddress" type="checkbox" id="copyaddress" value="copy" ${param.copyaddress=='copy'?'checked':''} />
                            Same as Mailing Address</label><br />
                          <textarea title="Enter Address "required="" class="textarea" name="txtPAddress" id="txtPAddress" rows="5" cols="30">${param.txtPAddress}</textarea>
                          <c:if test="${param.submitted and !stuEnroll.txtPAddressValid}" var="allValid">
                            <span style="color: red">Permanent Address cannot be blank</span>
                          </c:if>
                        </td>
                        <td></td>
                      </tr>
                      <tr>
                        <td>Mobile</td>
                        <td> : </td>
                        <td><input type="text" name="txtMobile" id="txtMobile" value="${param.txtMobile}" size="10" />
                          <c:if test="${param.submitted and !stuEnroll.txtMobileValid}" var="v13">
                            <span style="color: red">Invalid Mobile Number</span>
                          </c:if>
                        </td>
                        <td></td>
                      </tr>
                      <tr>
                        <td>Email</td>
                        <td> : </td>
                        <td><input type="text" name="txtEmail" id="txtEmail" value="${param.txtEmail}" size="30" />
                          <c:if test="${param.submitted and !stuEnroll.txtEmailValid}" var="v14">
                            <span style="color: red">Invalid Email Address</span>
                          </c:if>
                        </td>
                        <td></td>
                      </tr>
                      <tr>
                        <td>Family Income</td>
                        <td> : </td>
                        <td><input type="text" name="txtIncome" id="txtIncome" value="${param.txtIncome}" size="30" />
                          <c:if test="${param.submitted and !stuEnroll.txtIncomeValid}" var="v15">
                            <span style="color: red">Invalid Income Value</span>
                          </c:if>
                        </td>
                        <td></td>
                      </tr>
                      <tr>
                        <td colspan="3" style="text-align: center"><input type="submit" value="Next" name="cmdNext" /> </td>
                        <td></td>
                      </tr>
                    </tbody>
                  </table>
                </form>
                <h3 id="warning"></h3> <!-- Error Message Display -->
              </div>
            </div>
          </td>
      </table>   
    </div>
    <c:if test="${param.submitted and !v1 and !v2 and !v3 and !v4 and !v5 and !v6 and !v7 and !v8 and !v9
                  and !v10 and !v11 and !v12 and !v13 and !v14 and !v15 and !v16 and !v17  and !v18 and !v19 and !v20}">

          <jsp:forward page="clxii.jsp" ></jsp:forward>

            <!--stuEnrollDAO.insertStudent(getServletContext(), stuEnroll);%>-->

    </c:if>
    <div id="footer"><%=application.getInitParameter("pageFooter")%></div>
  </body>
</html>
