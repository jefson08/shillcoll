<%-- 
    Document   : Add Papers
    Created on : Feb 20, 2015, 9:52:10 PM
    Author     : A Mitri
--%>

<%
  //response.setHeader("Pragma", "no-cache");
  //response.setHeader("Cache-Control", "no-store");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="shg.util.shgUtil"%>
<jsp:useBean id="dbutil" class="shg.util.DatabaseUtility"></jsp:useBean>
<%!//DatabaseUtility dbutil = new DatabaseUtility();%>
<jsp:useBean id="ASP" class="shg.bean.AddSubjectPapers" ></jsp:useBean>
<jsp:useBean id="ASPDAO" class="shg.dao.AddSubjectPapersDAO" ></jsp:useBean>
<jsp:setProperty name="ASP" property="*"></jsp:setProperty>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link href="../style/master-css/style.css" rel="stylesheet" />
    <link href="../style/master-css/master-layout.css" rel="stylesheet" />
    <link href="../style/master-css/menu-style.css" rel="stylesheet" />
    <link rel="stylesheet" href="../style/TableCSSCode.css" type="text/css" charset="utf-8" />
    <!--    <script type="text/javascript" src="../scripts/jquery/jquery-1.6.2.min.js"></script>
        <script type="text/javascript" src="../scripts/jquerydatepicker/jquery.datepick.js"></script>                
        <script type="text/javascript" src="../scripts/jquery/jquery.maskedinput-1.3.js"></script>
        <script type="text/javascript" src="../scripts/util/net.js"></script>
        <script type="text/javascript" src="../scripts/util/populateComboBox.js"></script>
        <script src="../scripts/validate/jquery.validate.js"></script>
        <script src="../scripts/validate/additional-methods.js"></script>
        <script src="../scripts/validate/validators.js"></script>
        <script src="../scripts/adm/stuenroll.js"></script>-->


    <script type="text/javascript" src="../scripts/jquery/jquery-1.6.2.min.js"></script>
    <script type="text/javascript" src="../scripts/jquerydatepicker/jquery.datepick.js"></script>
    <script type="text/javascript" src="../scripts/jquery/jquery.maskedinput-1.3.js"></script>
    <script type="text/javascript" src="../scripts/util/populateComboBox.js"></script>
    <script type="text/javascript" src="../scripts/util/net.js"></script>
    <script src="../scripts/validate/jquery.validate.js"></script>
    <script src="../scripts/validate/additional-methods.js"></script>
    <script src="../scripts/validate/validators.js"></script>
    <script type="text/javascript" src="../scripts/adm/AddSubjectPapers.js"></script>
    <title>Add Papers to a Subject</title>
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
              <div class="frame-header" >Subject Papers</div>
              <div id="processing-area">
                <h2 id="summary"></h2>
                <form name="CourseClassHons" id="CourseClassHons" method="POST">
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
                      </td>
                      <td><div class="radSYR"></div></td>
                    </tr>
                  </table><br>
                  <table border="0" align="center">
                    <tbody>

                      <tr>
                        <td><b>Stream * :</b>
                          <select name="cmbStream" id="cmbStream" required="" title="Stream not Selected">
                            <option value="-1">-</option>
                            <c:set var="streamcode" value="${param.cmbStream}"></c:set>

                            <c:out escapeXml="false" value="${dbutil.populatePopup(pageContext.request.servletContext,'streams','streamcode','streamname',streamcode)}">                                
                            </c:out>
                          </select> 
                          <c:if test="${param.submitted and !ASP.cmbSubjectNameValid}" var="v2">
                            <span style="color:red" > Stream Not Selected</span>
                          </c:if>
                        <td><div class="selSYR"></div></td>
                        </td>
                      </tr>
                      <tr>
                        <td><b>Subject * :</b>
                          <select name="cmbSubjectName" id="cmbSubjectName">
                            <option value="-1">-</option>
                            <c:set var="subjectid" value="${param.cmbSubjectName}"></c:set>
                              <!--public String populateDependentPopup(ServletContext context, String tablename, String valueField, String optionField, String dependsOnField, String dependsOnVal, String defValue)      -->                         
                            <c:out escapeXml="false" value="${dbutil.populateDependentPopup(pageContext.request.servletContext,'subjects','subjectcode','subjectname', 'streamcode', streamcode,subjectid)}">
                            </c:out>
                          </select> 
                          <c:if test="${param.submitted and !ASP.cmbSubjectNameValid}" var="v3">
                            <span style="color:red" >Subject Not Selected</span>    
                          </c:if>
                        </td>


                      </tr>

                      <tr>
                        <td colspan="4">
                          <c:if test="${param.submitted and !ASP.txtPaperIdValid}" var="v4"> 
                            <span style="color:red" >Paper ID is either blank or invalid!!!!</span>
                          </c:if>
                        </td>
                      </tr><tr>
                        <td colspan="4">
                          <c:if test="${param.submitted and !ASP.txtPaperNameValid}" var="v5"> 
                            <span style="color:red" >Paper Name is either blank or invalid!!!!</span>
                          </c:if>
                        </td>
                      </tr>
                      <tr>
                        <td colspan="4">
                          <c:if test="${param.submitted and !ASP.cmbYearOrSemNoValid}" var="v6">
                            <span style="color:red" > Semester No. or Year No. Not Selected!!!</span>
                          </c:if>
                        </td>
                      </tr>

                  </table>
                  <font face="verdana"> * Paper name and Paper ID as per the University's Syllabus</font>
                  <div class="CSSTableGenerator">
                    <table  id="papers">
                      <tbody>
                        <tr>
                          <td>Paper Id</td>
                          <td>Paper Name</td>
                          <td>Year/Semester Number</td>
                          <td>Honours</td>
                          <td>Practical</td>
                        </tr>
                        <tr>
                          <td>
                            <input name="txtPaperId" type="text" id="txtPaperId[0]"  value="${param.submitted?paramValues.txtPaperId[0]:''}"/>
                          </td>

                          <td>
                            <input name="txtPaperName" type="text" id="txtPaperName"  value="${param.submitted?paramValues.txtPaperName[0]:''}" />
                          </td>
                          <td>

                            <select name="cmbYearOrSemNo" id="cmbYearOrSemNo">
                              <c:choose>
                                <c:when test="${param.submitted and yearorsem=='y'}">
                                  <option value="-1">-</option> 

                                  <option value="y1" ${paramValues.cmbYearOrSemNo[0]=='y1'?'selected':''}>1</option>
                                  <option value="y2" ${paramValues.cmbYearOrSemNo[0]=='y2'?'selected':''}>2</option>
                                  <option value="y3" ${paramValues.cmbYearOrSemNo[0]=='y3'?'selected':''}>3</option>

                                </c:when>
                                <c:otherwise>
                                  <option value="-1">-</option> 

                                  <option value="s1" ${paramValues.cmbYearOrSemNo[0]=='s1'?'selected':''}>1</option>
                                  <option value="s2" ${paramValues.cmbYearOrSemNo[0]=='s2'?'selected':''}>2</option>
                                  <option value="s3" ${paramValues.cmbYearOrSemNo[0]=='s3'?'selected':''}>3</option>
                                  <option value="s4" ${paramValues.cmbYearOrSemNo[0]=='s4'?'selected':''}>4</option>
                                  <option value="s5" ${paramValues.cmbYearOrSemNo[0]=='s5'?'selected':''}>5</option>
                                  <option value="s6" ${paramValues.cmbYearOrSemNo[0]=='s6'?'selected':''}>6</option>

                                </c:otherwise> 
                              </c:choose>   
                            </select> 
                          </td>

                          <td>

                            <input name="chkCategorydummy" type="checkbox" id="chkCategorydummy" chkStat="1" ${param.submitted and (paramValues.chkCategory[0])?"checked":""}/>

                            <input type ="hidden" name="chkCategory" id="chkCategory" chkVal="1" value= "${param.submitted?paramValues.chkCategory[0]:false}"/>

                          </td>
                          <td>

                            <input name="chkPractdummy" type="checkbox" id="chkPractdummy" chkPstat="1" ${param.submitted and (paramValues.chkPract[0])?"checked":""}/>

                            <input type ="hidden" name="chkPract" id="chkPract" chkPval="1" value= "${param.submitted?paramValues.chkPract[0]:false}"/>

                          </td>
                        </tr>
                        <tr>

                          <td><input name="txtPaperId" type="text" id="txtPaperId[1]"  value="${param.submitted?paramValues.txtPaperId[1]:''}" /></td>

                          <td><input name="txtPaperName" type="text" id="txtPaperName"  value="${param.submitted?paramValues.txtPaperName[1]:''}" /></td>


                          <td>
                            <select name="cmbYearOrSemNo" id="cmbYearOrSemNo">
                              <c:choose>
                                <c:when test="${param.submitted and yearorsem=='y'}">
                                  <option value="-1">-</option> 

                                  <option value="y1" ${paramValues.cmbYearOrSemNo[1]=='y1'?'selected':''}>1</option>
                                  <option value="y2" ${paramValues.cmbYearOrSemNo[1]=='y2'?'selected':''}>2</option>
                                  <option value="y3" ${paramValues.cmbYearOrSemNo[1]=='y3'?'selected':''}>3</option>

                                </c:when>
                                <c:otherwise>
                                  <option value="-1">-</option> 

                                  <option value="s1" ${paramValues.cmbYearOrSemNo[1]=='s1'?'selected':''}>1</option>
                                  <option value="s2" ${paramValues.cmbYearOrSemNo[1]=='s2'?'selected':''}>2</option>
                                  <option value="s3" ${paramValues.cmbYearOrSemNo[1]=='s3'?'selected':''}>3</option>
                                  <option value="s4" ${paramValues.cmbYearOrSemNo[1]=='s4'?'selected':''}>4</option>
                                  <option value="s5" ${paramValues.cmbYearOrSemNo[1]=='s5'?'selected':''}>5</option>
                                  <option value="s6" ${paramValues.cmbYearOrSemNo[1]=='s6'?'selected':''}>6</option>

                                </c:otherwise> 
                              </c:choose>   
                            </select> 
                          </td>
                          <td>
                            <input name="chkCategorydummy" type="checkbox" id="chkCategorydummy" chkStat="2" ${param.submitted and (paramValues.chkCategory[1])?"checked":""}/>

                            <input type ="hidden" name="chkCategory" id="chkCategory" chkVal="2" value="${param.submitted?paramValues.chkCategory[1]:false}"/>   
                          </td>
                          <td>

                            <input name="chkPractdummy" type="checkbox" id="chkPractdummy" chkPstat="2" ${param.submitted and (paramValues.chkPract[1])?"checked":""}/>

                            <input type ="hidden" name="chkPract" id="chkPract" chkPval="2" value= "${param.submitted?paramValues.chkPract[1]:false}"/>

                          </td>
                          <td></td>
                        </tr>
                        <tr>

                          <td><input name="txtPaperId" type="text" id="txtPaperId[2]"  value="${param.submitted?paramValues.txtPaperId[2]:''}" /></td>

                          <td><input name="txtPaperName" type="text" id="txtPaperName"  value="${param.submitted?paramValues.txtPaperName[2]:''}" /></td>


                          <td>
                            <select name="cmbYearOrSemNo" id="cmbYearOrSemNo">
                              <c:choose>
                                <c:when test="${param.submitted and yearorsem=='y'}">
                                  <option value="-1">-</option> 

                                  <option value="y1" ${paramValues.cmbYearOrSemNo[2]=='y1'?'selected':''}>1</option>
                                  <option value="y2" ${paramValues.cmbYearOrSemNo[2]=='y2'?'selected':''}>2</option>
                                  <option value="y3" ${paramValues.cmbYearOrSemNo[2]=='y3'?'selected':''}>3</option>

                                </c:when>
                                <c:otherwise>
                                  <option value="-1">-</option> 

                                  <option value="s1" ${paramValues.cmbYearOrSemNo[2]=='s1'?'selected':''}>1</option>
                                  <option value="s2" ${paramValues.cmbYearOrSemNo[2]=='s2'?'selected':''}>2</option>
                                  <option value="s3" ${paramValues.cmbYearOrSemNo[2]=='s3'?'selected':''}>3</option>
                                  <option value="s4" ${paramValues.cmbYearOrSemNo[2]=='s4'?'selected':''}>4</option>
                                  <option value="s5" ${paramValues.cmbYearOrSemNo[2]=='s5'?'selected':''}>5</option>
                                  <option value="s6" ${paramValues.cmbYearOrSemNo[2]=='s6'?'selected':''}>6</option>

                                </c:otherwise> 
                              </c:choose>   
                            </select> 
                          </td>
                          <td>
                            <input name="chkCategorydummy" type="checkbox" id="chkCategorydummy" chkStat="3"  ${param.submitted and (paramValues.chkCategory[2])?"checked":""}/>

                            <input type ="hidden" name="chkCategory" id="chkCategory" chkVal="3"  value="${param.submitted?paramValues.chkCategory[2]:false}" />                                
                          </td>
                          <td>

                            <input name="chkPractdummy" type="checkbox" id="chkPractdummy" chkPstat="3" ${param.submitted and (paramValues.chkPract[2])?"checked":""}/>

                            <input type ="hidden" name="chkPract" id="chkPract" chkPval="3" value= "${param.submitted?paramValues.chkPract[2]:false}"/>

                          </td>
                        <tr>
                          <td id="error_Subid"></td>
                          
                        </tr>
                          </tr>
                        <c:if test="${param.submitted}" >
                          <c:forEach items="${ASP.txtPaperId}" var="cur" begin="3" varStatus="status">
                            <tr >

                              <td><input type="text" name="txtPaperId" value="${cur}" /></td>

                              <td><input type="text" name="txtPaperName" value="${ASP.txtPaperName[status.index]}" /></td>

                              <td>
                                <select name="cmbYearOrSemNo" id="cmbYearOrSemNo">
                                  <option value="-1">-</option>
                                  <option value="1" ${ASP.cmbYearOrSemNo[status.index] == 1 ? 'selected' : ''}>1</option>
                                  <option value="2" ${ASP.cmbYearOrSemNo[status.index] == 2 ? 'selected' : ''}>2</option>
                                  <option value="3" ${ASP.cmbYearOrSemNo[status.index] == 3 ? 'selected' : ''}>3</option>
                                  <option value="4" ${ASP.cmbYearOrSemNo[status.index] == 4 ? 'selected' : ''}>4</option>
                                  <option value="5" ${ASP.cmbYearOrSemNo[status.index] == 5 ? 'selected' : ''}>5</option>
                                  <option value="6" ${ASP.cmbYearOrSemNo[status.index] == 6 ? 'selected' : ''}>6</option>
                                  <c:set var="yearOrSemNo" value="${param.cmbYearOrSemNo}"></c:set>
                                  </select> 
                                </td>
                                <td>
                                  <input name="chkCategorydummy" type="checkbox" id="chkCategorydummy" chkStat="${status.index+1}" 
                                       ${((param.submitted and ASP.chkCategory[status.index])=='true')?'checked':''} />

                                <input type ="hidden" name="chkCategory" id="chkCategory" chkVal="${status.index+1}" value= "${paramValues.chkCategory[status.index]}"/>
                              </td>
                              <td>
                                <input name="chkPractdummy" type="checkbox" id="chkPractdummy" chkPstat="${status.index+1}" 
                                       ${((param.submitted and !empty(paramValues.chkPractdummy[status.index])) or (!param.submitted2 and (ASP.chkPract[status.index]=='true')))?'checked':''} />

                                <input type ="hidden" name="chkPract" id="chkPract" chkVal="${status.index+1}" value= "${param.submitted?paramValues.chkPract[status.index]:'false'}"/>
                              </td>
                            </tr>
                          </c:forEach> 
                        </c:if>
                      </tbody> 
                    </table>
                  </div>
                  <table>

                    <tr>
                      <td>
                        <input type="button" value="Add more papers" name="cmdAddMore" ${(param.submitted and !v1 and !v2 and !v3 and !v4 and !v5 and !v6 and !v7)?'disabled':''}/>
                      </td>
                      <td  style="text-align: center">
                        <input type="submit" value="Save" />
                      </td>
                      <td>
                        <input type="button" value="Next" name="cmdNext" ${(param.submitted and !v1 and !v2 and !v3 and !v4 and !v5 and !v6 and !v7)?'':'disabled'} />
                      </td>

                    </tr>

                  </table>                
                </form>
                <h3 id="warning"></h3> <!-- Error Message Display -->
                </body>

                <div id="CCHDiv">
                  <c:if test="${param.submitted and !v1 and !v2 and !v3 and !v4 and !v5 and !v6 and !v7}">
                    <%       StringBuilder ErrMsg = new StringBuilder();
                      int res = ASPDAO.insertToAllPapers(getServletContext(), ASP, ErrMsg);
                      if (res == 1) {
                        out.println(" Papers Added sucessfully");
                      } else if (res == -1) {
                        out.println(" Add operation unsucessfully");
                      } else if (res == 2) {
                        out.println(ErrMsg);
                      } else if (res == 3) {
                        out.println(ErrMsg);
                      } else if (res == 4) {
                        out.println("Duplicate values for Paper code");
                      } else if (res == 5) {
                        out.println("Duplicate values for Paper Names");
                      }

                    %>


                  </c:if>
                </div>
              </div>
            </div>

          </td>
      </table>
    </div>

    <div id="footer"><%=application.getInitParameter("pageFooter")%></div>

</html>
