
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
<jsp:useBean id="CCH" class="shg.bean.CourseClassHons" ></jsp:useBean>
<jsp:useBean id="CCHDAO" class="shg.dao.CourseClassHonsDAO" ></jsp:useBean>
<jsp:setProperty name="CCH" property="*"></jsp:setProperty>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="../style/master-css/style.css" rel="stylesheet" />
        <link href="../style/master-css/master-layout.css" rel="stylesheet" />
        <link href="../style/master-css/menu-style.css" rel="stylesheet" />

        <script type="text/javascript" src="../scripts/jquery/jquery-1.6.2.min.js"></script>
        <script type="text/javascript" src="../scripts/jquerydatepicker/jquery.datepick.js"></script>                
        <script type="text/javascript" src="../scripts/adm/CourseClassHons3.js"></script>
        <script type="text/javascript" src="../scripts/jquery/jquery.maskedinput-1.3.js"></script>
        <script type="text/javascript" src="../scripts/util/net.js"></script>
        <script type="text/javascript" src="../scripts/util/populateComboBox.js"></script>

        <title>Add Papers to a Course</title>
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
                            <div class="frame-header" >Course Papers</div>
                            <div id="processing-area">
                                <form name="CourseClassHons" method="POST">
                                    <input type="hidden" name="submitted" value="true" />

                                    <table border="0" id="papers">
                                        <tbody>

                                            <tr>
                                                <td>Stream *</td>
                                                <td> : </td>
                                                <td> 
                                                    <select name="cmbStream" id="cmbStream">
                                                        <option value="-1">-</option>
                                                        <c:set var="streamcode" value="${param.cmbStream}"></c:set>

                                                        <c:out escapeXml="false" value="${dbutil.populatePopup(pageContext.request.servletContext,'streams','streamcode','streamname',streamcode)}">                                
                                                        </c:out>
                                                    </select> 
                                                    <c:if test="${param.submitted and !CCH.cmbSubjectNameValid}" var="v2">
                                                        <span style="color:red" > Subject Not Selected</span>
                                                    </c:if>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Subject *</td>
                                                <td> : </td>
                                                <td> 
                                                    <select name="cmbSubjectName" id="cmbSubjectName">
                                                        <option value="-1">-</option>
                                                        <c:set var="subjectid" value="${param.cmbSubjectName}"></c:set>
                                                            <!--public String populateDependentPopup(ServletContext context, String tablename, String valueField, String optionField, String dependsOnField, String dependsOnVal, String defValue)      -->                         
                                                        <c:out escapeXml="false" value="${dbutil.populateDependentPopup(pageContext.request.servletContext,'subjects','subjectcode','subjectname', 'streamcode', streamcode,subjectid)}">
                                                        </c:out>
                                                    </select> 
                                                    <c:if test="${param.submitted and !CCH.cmbSubjectNameValid}" var="v3">
                                                        <span style="color:red" >Subject Not Selected</span>    
                                                    </c:if>
                                                </td>


                                            </tr>
                                        <input type="radio" id="radYear" name="radYearOrSem" value="y" />Annual
                                        <input type="radio" id="radSem" name="radYearOrSem" value="s" ${!(param.submitted)?'checked':''} />Semester
                                        <c:if test="${param.submitted and !empty(param.radYearOrSem)}">
                                             
                                             <c:set var="yearorsem" value="${param.radYearOrSem}"></c:set>
                                        </c:if>
                                       <c:if test="${!(param.submitted)}" >
                                           <c:set var="yearorsem" value="s" ></c:set>
                                       </c:if>
                                        <tr>
                                            <td colspan="4">
                                                <c:if test="${param.submitted and !CCH.txtPaperIdValid}" var="v4"> 
                                                    <span style="color:red" >Paper ID is either blank or invalid!!!!</span>
                                                </c:if>
                                            </td>
                                        </tr><tr>
                                            <td colspan="4">
                                                <c:if test="${param.submitted and !CCH.txtPaperNameValid}" var="v5"> 
                                                    <span style="color:red" >Paper Name is either blank or invalid!!!!</span>
                                                </c:if>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="4">
                                                <c:if test="${param.submitted and !CCH.cmbYearOrSemNoValid}" var="v6">
                                                    <span style="color:red" > Semester No. or Year No. Not Selected!!!</span>
                                                </c:if>
                                            </td>
                                        </tr>
                                        <tr><td colspan='2'>Enter the paper name and Paper ID as per the University's Syllabus</td></tr>
                                        <tr>
                                            <td>Paper Id</td>
                                            <td>
                                                <input name="txtPaperId" type="text" id="txtPaperId"  value="${param.submitted?paramValues.txtPaperId[0]:''}"/></td>
                                            <td>Paper Name</td>
                                            <td><input name="txtPaperName" type="text" id="txtPaperName"  value="${param.submitted?paramValues.txtPaperName[0]:''}" /></td>

                                          
                                            <td>Year/Semester Number</td>
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
                                                    <label>
                                                        <input name="chkCategorydummy" type="checkbox" id="chkCategorydummy" chkStat="1" ${param.submitted and (paramValues.chkCategory[0])?"checked":""}/>Honours
                                                    </label>
                                                    <input type ="hidden" name="chkCategory" id="chkCategory" chkVal="1" value= "${param.submitted?paramValues.chkCategory[0]:false}"/>

                                                </td>
                                                <td>
                                                    <label>
                                                        <input name="chkPractdummy" type="checkbox" id="chkPractdummy" chkPstat="1" ${param.submitted and (paramValues.chkPract[0])?"checked":""}/>Practical
                                                    </label>
                                                    <input type ="hidden" name="chkPract" id="chkPract" chkVal="1" value= "${param.submitted?paramValues.chkCategory[0]:false}"/>

                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Paper Id</td>
                                                <td><input name="txtPaperId" type="text" id="txtPaperId"  value="${param.submitted?paramValues.txtPaperId[1]:''}" /></td>
                                                <td>Paper Name</td>
                                                <td><input name="txtPaperName" type="text" id="txtPaperName"  value="${param.submitted?paramValues.txtPaperName[1]:''}" /></td>

                                                <td>Year/Semester Number</td>
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
                                                    <label><input name="chkCategorydummy" type="checkbox" id="chkCategorydummy" chkStat="2" ${param.submitted and (paramValues.chkCategory[1])?"checked":""}/>
                                                        Honours</label>
                                                    <input type ="hidden" name="chkCategory" id="chkCategory" chkVal="2" value="${param.submitted?paramValues.chkCategory[1]:false}"/>   
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Paper Id</td>
                                                <td><input name="txtPaperId" type="text" id="txtPaperId"  value="${param.submitted?paramValues.txtPaperId[2]:''}" /></td>
                                                <td>Paper Name</td>
                                                <td><input name="txtPaperName" type="text" id="txtPaperName"  value="${param.submitted?paramValues.txtPaperName[2]:''}" /></td>

                                                <td>Year/Semester Number</td>
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
                                                    <label><input name="chkCategorydummy" type="checkbox" id="chkCategorydummy" chkStat="3"  ${param.submitted and (paramValues.chkCategory[2])?"checked":""}/>
                                                        Honours</label>
                                                    <input type ="hidden" name="chkCategory" id="chkCategory" chkVal="3"  value="${param.submitted?paramValues.chkCategory[2]:false}" />                                
                                                </td>

                                            </tr>
                                            <c:if test="${param.submitted}" >
                                                <c:forEach items="${CCH.txtPaperId}" var="cur" begin="3" varStatus="status">
                                                    <tr >
                                                        <td>Paper Id</td>
                                                        <td><input type="text" name="txtPaperId" value="${cur}" /></td>
                                                        <td>Paper Name</td>
                                                        <td><input type="text" name="txtPaperName" value="${CCH.txtPaperName[status.index]}" /></td>
                                                        <td>Year/Semester Number</td>
                                                        <td>
                                                            <select name="cmbYearOrSemNo" id="cmbYearOrSemNo">
                                                                <option value="-1">-</option>
                                                                <option value="1" ${CCH.cmbYearOrSemNo[status.index] == 1 ? 'selected' : ''}>1</option>
                                                                <option value="2" ${CCH.cmbYearOrSemNo[status.index] == 2 ? 'selected' : ''}>2</option>
                                                                <option value="3" ${CCH.cmbYearOrSemNo[status.index] == 3 ? 'selected' : ''}>3</option>
                                                                <option value="4" ${CCH.cmbYearOrSemNo[status.index] == 4 ? 'selected' : ''}>4</option>
                                                                <option value="5" ${CCH.cmbYearOrSemNo[status.index] == 5 ? 'selected' : ''}>5</option>
                                                                <option value="6" ${CCH.cmbYearOrSemNo[status.index] == 6 ? 'selected' : ''}>6</option>
                                                                <c:set var="yearOrSemNo" value="${param.cmbYearOrSemNo}"></c:set>
                                                                </select> 
                                                            </td>
                                                            <td>
                                                                <label><input name="chkCategorydummy" type="checkbox" id="chkCategorydummy" chkstat="${status.index+1}" 
                                                                          ${((param.submitted and !empty(paramValues.chkCategorydummy[status.index])) or (!param.submitted2 and (CCH.chkCategory[status.index]=='true')))?'checked':''} />
                                                                Honours</label>
                                                            <input type ="hidden" name="chkCategory" id="chkCategory" chkVal="${status.index+1}" value= "${param.submitted?paramValues.chkCategory[status.index]:'false'}"/>
                                                        </td>

                                                    </tr>
                                                </c:forEach> 
                                            </c:if>
                                            </tbody> 

                                        </table>
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

                                    </body>

                                    <div id="CCHDiv">
                                        <c:if test="${param.submitted and !v1 and !v2 and !v3 and !v4 and !v5 and !v6 and !v7}">
                                            <%       StringBuilder ErrMsg = new StringBuilder();
                                                int res = CCHDAO.insertToAllPapers(getServletContext(), CCH, ErrMsg);
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
