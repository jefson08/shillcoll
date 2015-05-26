<%-- 
            Document   : Edit/delete subject Papers
            Created on : Feb 20, 2015, 9:52:10 PM
            Author     : A. Mitri
--%>
<%@page import="javax.rmi.CORBA.Util"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="shg.util.shgUtil"%>
<jsp:useBean id="dbutil" class="shg.util.DatabaseUtility"></jsp:useBean>
<%!//DatabaseUtility dbutil = new DatabaseUtility();%>
<jsp:useBean id="ASP" class="shg.bean.AddSubjectPapers" ></jsp:useBean>
<jsp:useBean id="ESPDAO" class="shg.dao.EditSubjectPapersDAO" ></jsp:useBean>
<jsp:setProperty name="ASP" property="*"></jsp:setProperty>
<%--<jsp:useBean id="CCHEdit" class="shg.dao.CourseClassHonsRetrieve2" />--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!DOCTYPE html>
<html>
<<<<<<< HEAD
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../style/master-css/style.css" rel="stylesheet" />
        <link href="../style/master-css/master-layout.css" rel="stylesheet" />
        <link href="../style/master-css/menu-style.css" rel="stylesheet" />
        <link href="../style/master-css/html-elements.css" rel="stylesheet" />
        <script type="text/javascript" src="../scripts/jquery/jquery-1.6.2.min.js"></script>
        <script type="text/javascript" src="../scripts/adm/EditSubjectPapers.js"></script>
        <script type="text/javascript" src="../scripts/jquery/jquery.maskedinput-1.3.js"></script>
        <script type="text/javascript" src="../scripts/util/net.js"></script>
        <script type="text/javascript" src="../scripts/util/populateComboBox.js"></script>
        <title>Edit Or Delete Papers of a Subject</title>
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
                            <div class="frame-header" >Edit Papers</div>
                            <div id="processing-area">
                                <form name="frmCCHRetrieve" method="POST" action="">
                                    <input type="hidden" name="submitted" value="true" />
                                    <table border="0" id="papers1" >
                                        <tr>
                                            <td colspan="2">Subject * :</td>
                                            <td colspan="2"> 
                                                <select name="cmbSubjectName" id="cmbSubjectName" class="bigSelect">
                                                    <option value="-1">-</option>
                                                    <c:set var="subjectid" value="${param.cmbSubjectName}"></c:set>
                                                        <!--public String populateDependentPopup(ServletContext context, String tablename, String valueField, String optionField, String dependsOnField, String dependsOnVal, String defValue)      -->                         
                                                    <c:out escapeXml="false" value="${dbutil.populatePopup(pageContext.request.servletContext,'subjects','subjectcode','subjectname',subjectid)}">       
                                                    </c:out>
                                                </select> 
                                                <c:if test="${param.submitted and !ASP.cmbSubjectNameValid}" var="v2">
                                                    <span style="color:red">Subject Not Selected</span>
                                                </c:if>
                                            </td>
                                        </tr>
                                    </table>
                                    <table border="0" id="papers">
                                        <tbody>
                                            <tr>
                                                <td colspan="4">
                                                    <c:if test="${param.submitted and !ASP.txtPaperIdValid}" var="v3"> 
                                                        <span style="color:red"> Paper ID is either blank or invalid!!</span>
                                                    </c:if>
                                                </td>
                                            </tr><tr>
                                                <td colspan="4">
                                                    <c:if test="${param.submitted and !ASP.txtPaperNameValid}" var="v4"> 
                                                        <span style="color:red"> Paper Name is either blank or invalid!!</span>
                                                    </c:if>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="4">
                                                    <c:if test="${param.submitted and !ASP.cmbYearOrSemNoValid}" var="v5">
                                                        <span style="color:red">Semester No. or Year No. Not Selected!! </span>
                                                    </c:if>
                                                </td>
                                            </tr>
                                            <c:forEach items="${ASP.txtPaperId}" var="cur" begin="0" varStatus="status">
                                                <tr id="${status.index}">
                                                    <td>Paper Id</td>
                                                    <td><input type="text" name="txtPaperId" value="${cur}" /></td>
                                                    <td>Paper Name</td>
                                                    <td><input type="text" name="txtPaperName" value="${ASP.txtPaperName[status.index]}" /></td>
                                                    <td>Semester Number</td>
                                                    <td>
                                                        <select name="cmbYearOrSemNo" id="cmbYearOrSemNo">
                                                            <c:set var="yors" value="${ASP.cmbYearOrSemNo[status.index]}"></c:set>
                                                            <c:choose>
                                                                <c:when test="${fn:startsWith(yors,'s')}">
                                                                    <option value="-1">-</option>
                                                                    <option value="1" ${ASP.cmbYearOrSemNo[status.index] == 's1' ? 'selected' : ''}>1</option>
                                                                    <option value="2" ${ASP.cmbYearOrSemNo[status.index] == 's2' ? 'selected' : ''}>2</option>
                                                                    <option value="3" ${ASP.cmbYearOrSemNo[status.index] == 's3' ? 'selected' : ''}>3</option>
                                                                    <option value="4" ${ASP.cmbYearOrSemNo[status.index] == 's4' ? 'selected' : ''}>4</option>
                                                                    <option value="5" ${ASP.cmbYearOrSemNo[status.index] == 's5' ? 'selected' : ''}>5</option>
                                                                    <option value="6" ${ASP.cmbYearOrSemNo[status.index] == 's6' ? 'selected' : ''}>6</option>
                                                                    <c:set var="yearOrSemNo" value="${param.cmbYearOrSemNo}"></c:set>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <option value="-1">-</option>
                                                                    <option value="1" ${ASP.cmbYearOrSemNo[status.index] == 'y1' ? 'selected' : ''}>1</option>
                                                                    <option value="2" ${ASP.cmbYearOrSemNo[status.index] == 'y2' ? 'selected' : ''}>2</option>
                                                                    <option value="3" ${ASP.cmbYearOrSemNo[status.index] == 'y3' ? 'selected' : ''}>3</option>
                                                                    <c:set var="yearOrSemNo" value="${param.cmbYearOrSemNo}"></c:set>  
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </select> 
                                                    </td>
                                                    <td>
                                                        <label><input name="chkCategorydummy" type="checkbox" id="chkCategorydummy" chkStat="${status.index+1}" 
                                                                      ${(( (paramValues.chkCategory[status.index])) and (ASP.chkCategory[status.index]=='true'))?'checked':''} />
                                                            Honours</label>
                                                        <input type ="hidden"  name="chkCategory" id="chkCategory" chkVal="${status.index+1}" value= "${ASP.chkCategory[status.index]}"/>
                                                    </td>
                                                    <td>
                                                        <label>  <input name ="chkPractdummy" type = "checkbox" id ="chkPractdummy" chkPstat = "${status.index+1}"
                                                                        ${((param.submitted and (paramValues.chkPract[status.index])) and (ASP.chkPract[status.index]=='true'))?'checked':''} />
                                                            Practical</label>   </td>
                                                        <input type = "hidden" name = "chkPract" id = "chkPract" chkPval = "${status.index+1}"  value ="${param.submitted?paramValues.chkPract[status.index]:'false'}"/>
                                                   

                                                    <td>   <img src="../images/remove.png" name="delIcon"  id="delIcon" val="${status.index}" /> </td>
                                                </tr>
                                            </c:forEach> 
                                        <input type ="hidden" name="nextrow" id="nextrow" value="${status.index+1}" />
                                        </tbody> 
                                    </table>
                                    <table>
                                        <tr>
                                            <td colspan="2">
                                                <input type="button" value="Add more papers" name="cmdAddMore" id="cmdAddMore" disabled="false" />
                                            </td>
                                            <td  style="text-align: center">
                                                <input type="submit" name="cmdSave" value="Save" disabled="false"/>
                                            </td>
                                        </tr>   
                                    </table>  
                                </form>
                                </body>
                                <div id="CCHDiv">
                                <c:if test="${param.submitted and !v1 and !v2 and !v3 and !v4 and !v5}">
                                    <% 
                                        int res = ESPDAO.updateToAllPapers(getServletContext(), ASP);
                                        if (res == 1) {

                                            out.println("Update operation successful");
                                        } else if (res == -1) {
                                            out.println(" Update operation unsucessful");
                                        } else if (res == 2) {
                                            out.println("Duplicate values for Paper code");
                                        } else if (res == 3) {
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
=======
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="../style/master-css/style.css" rel="stylesheet" />
    <link href="../style/master-css/master-layout.css" rel="stylesheet" />
    <link href="../style/master-css/menu-style.css" rel="stylesheet" />
    <link href="../style/master-css/html-elements.css" rel="stylesheet" />
    <link rel="stylesheet" href="../style/TableCSSCode.css" type="text/css" charset="utf-8" />
    <script type="text/javascript" src="../scripts/jquery/jquery-1.6.2.min.js"></script>
    <script type="text/javascript" src="../scripts/adm/EditSubjectPapers.js"></script>
    <script type="text/javascript" src="../scripts/jquery/jquery.maskedinput-1.3.js"></script>
    <script type="text/javascript" src="../scripts/util/net.js"></script>
    <script type="text/javascript" src="../scripts/util/populateComboBox.js"></script>
    <title>Edit Or Delete Papers of a Subject</title>
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
              <div class="frame-header" >Edit Papers</div>
              <div id="processing-area">
                <form name="frmCCHRetrieve" method="POST" action="">
                  <input type="hidden" name="submitted" value="true" />
                  <table border="0" id="papers1" >
                    <tr>
                      <td colspan="2">Subject * :</td>
                      <td colspan="2"> 
                        <select name="cmbSubjectName" id="cmbSubjectName" class="bigSelect">
                          <option value="-1">-</option>
                          <c:set var="subjectid" value="${param.cmbSubjectName}"></c:set>
                            <!--public String populateDependentPopup(ServletContext context, String tablename, String valueField, String optionField, String dependsOnField, String dependsOnVal, String defValue)      -->                         
                          <c:out escapeXml="false" value="${dbutil.populatePopup(pageContext.request.servletContext,'subjects','subjectcode','subjectname',subjectid)}">       
                          </c:out>
                        </select> 
                        <c:if test="${param.submitted and !ASP.cmbSubjectNameValid}" var="v2">
                          <span style="color:red">Subject Not Selected</span>
                        </c:if>
                      </td>
                    </tr>
                  </table>
                  <table border="0">
                    <tbody>
                      <tr>
                        <td colspan="4">
                          <c:if test="${param.submitted and !ASP.txtPaperIdValid}" var="v3"> 
                            <span style="color:red"> Paper ID is either blank or invalid!!</span>
                          </c:if>
                        </td>
                      </tr><tr>
                        <td colspan="4">
                          <c:if test="${param.submitted and !ASP.txtPaperNameValid}" var="v4"> 
                            <span style="color:red"> Paper Name is either blank or invalid!!</span>
                          </c:if>
                        </td>
                      </tr>
                      <tr>
                        <td colspan="4">
                          <c:if test="${param.submitted and !ASP.cmbYearOrSemNoValid}" var="v5">
                            <span style="color:red">Semester No. or Year No. Not Selected!! </span>
                          </c:if>
                        </td>
                      </tr></tbody> </table>
                    <br> <div id="srchlist">
                      <c:if test="${param.submitted}">
                        <div class="CSSTableGenerator">
                            <table id="papers"><tbody>
                            <tr id="0">
                              <td>Paper Code</td>
                              <td>Paper Name</td>
                              <td>Semester/Year Number</td> 
                              <td>Honours</td> 
                              <td>Practical</td> 
                              <td>Delete</td> 
                            </tr>
                            <c:forEach items="${ASP.txtPaperId}" var="cur" begin="0" varStatus="status">
                              <tr id="${status.index+1}">

                                <td><input type="text" name="txtPaperId" value="${cur}" /></td>

                                <td><input type="text" name="txtPaperName" value="${ASP.txtPaperName[status.index]}" /></td>

                                <td>
                                  <select name="cmbYearOrSemNo" id="cmbYearOrSemNo">
                                    <c:set var="yors" value="${ASP.cmbYearOrSemNo[status.index]}"></c:set>
                                    <c:choose>
                                      <c:when test="${fn:startsWith(yors,'s')}">
                                        <option value="-1">-</option>
                                        <option value="s1" ${ASP.cmbYearOrSemNo[status.index] == 's1' ? 'selected' : ''}>1</option>
                                        <option value="s2" ${ASP.cmbYearOrSemNo[status.index] == 's2' ? 'selected' : ''}>2</option>
                                        <option value="s3" ${ASP.cmbYearOrSemNo[status.index] == 's3' ? 'selected' : ''}>3</option>
                                        <option value="s4" ${ASP.cmbYearOrSemNo[status.index] == 's4' ? 'selected' : ''}>4</option>
                                        <option value="s5" ${ASP.cmbYearOrSemNo[status.index] == 's5' ? 'selected' : ''}>5</option>
                                        <option value="s6" ${ASP.cmbYearOrSemNo[status.index] == 's6' ? 'selected' : ''}>6</option>
                                        <c:set var="yearOrSemNo" value="${param.cmbYearOrSemNo}"></c:set>
                                      </c:when>
                                      <c:otherwise>
                                        <option value="-1">-</option>
                                        <option value="y1" ${ASP.cmbYearOrSemNo[status.index] == 'y1' ? 'selected' : ''}>1</option>
                                        <option value="y2" ${ASP.cmbYearOrSemNo[status.index] == 'y2' ? 'selected' : ''}>2</option>
                                        <option value="y3" ${ASP.cmbYearOrSemNo[status.index] == 'y3' ? 'selected' : ''}>3</option>
                                        <c:set var="yearOrSemNo" value="${param.cmbYearOrSemNo}"></c:set>  
                                      </c:otherwise>
                                    </c:choose>
                                  </select> 
                                </td>
                                <td>
                                  <input name="chkCategorydummy" type="checkbox" id="chkCategorydummy" chkStat="${status.index+1}" 
                                         ${(( (paramValues.chkCategory[status.index])) and (ASP.chkCategory[status.index]=='true'))?'checked':''} />

                                  <input type ="hidden"  name="chkCategory" id="chkCategory" chkVal="${status.index+1}" value= "${ASP.chkCategory[status.index]}"/>
                                </td>
                                <td>
                                  <input name ="chkPractdummy" type = "checkbox" id ="chkPractdummy" chkPstat = "${status.index+1}"
                                         ${((paramValues.chkPract[status.index]) and (ASP.chkPract[status.index]=='true'))?'checked':''} />

                                  <input type = "hidden" name = "chkPract" id = "chkPract" chkPval = "${status.index+1}"  value ="${ASP.chkPract[status.index]}"/>
                                </td>

                                <td>   <img src="../images/remove.png" name="delIcon"  id="delIcon" val="${status.index}" /> </td>
                              </tr>
                            </c:forEach> 
                              <!--<tr><td colspan="6"><input type ="hidden" name="nextrow" id="nextrow" value="${status.index+1}" /></td></tr>-->
                            </tbody> 
                          </table>
                        </div>
                      </c:if>
                    </div>
                    <table>
                      <tr>
                        <td colspan="2">
                          <input type="button" value="Add more papers" name="cmdAddMore" id="cmdAddMore" disabled="false" />
                        </td>
                        <td  style="text-align: center">
                          <input type="submit" name="cmdSave" value="Save" ${(param.submitted and !v1 and !v2 and !v3 and !v4 and !v5)?'disabled':''}/>
                        </td>
                      </tr>   
                    </table>  
                </form>

                <div id="CCHDiv">
                  <c:if test="${param.submitted and !v1 and !v2 and !v3 and !v4 and !v5}">
                    <%
                      int res = ESPDAO.updateToAllPapers(getServletContext(), ASP);
                      if (res == 1) {

                        out.println("Update operation successful");
                      } else if (res == -1) {
                        out.println(" Update operation unsucessful");
                      } else if (res == 2) {
                        out.println("Duplicate values for Paper code");
                      } else if (res == 3) {
                        out.println("Duplicate values for Paper Names");
                      }
                    %>
                  </c:if>
                </div>



                </td>
                </table>
              </div>
            </div>           
            <div id="footer"><%=application.getInitParameter("pageFooter")%></div>
            </body>
            </html>
>>>>>>> origin/master
