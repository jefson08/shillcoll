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



<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="dbutil" class="shg.util.DatabaseUtility"></jsp:useBean>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="../style/master-css/style.css" rel="stylesheet" />
        <link href="../style/master-css/master-layout.css" rel="stylesheet" />
        <link href="../style/master-css/menu-style.css" rel="stylesheet" />
        <link rel="stylesheet" href="../style/TableCSSCode.css" type="text/css" charset="utf-8" />
        <script type="text/javascript" src="../scripts/jquery/jquery-1.6.2.min.js"></script>
        
        <script type="text/javascript" src="../scripts/util/net.js"></script>
        <script type="text/javascript" src="../scripts/util/populateComboBox.js"></script>
        <script src="../scripts/validate/jquery.validate.js"></script>
        <script src="../scripts/validate/additional-methods.js"></script>
        <script src="../scripts/validate/validators.js"></script>
        <script type="text/javascript" src="../scripts/adm/SubjectPapersRep.js"></script>
        <title>Print list of papers for a subject</title>
    </head>
    <body>
       
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
                                <form name="SubjectPapers" id="SubjectPapers" method="POST" action="../SubjectPapers" >
                                    <input type="hidden" name="submitted" value="true" />
                                    <table border="1" rules="none" frame="box" align="center">
                                        <tr><td width="150"><b>Select System :</b> </td>

                                            <td width="150">    <input type="radio" id="radYear" name="radYearOrSem" value="y" ${(param.submitted and param.txtYearOrSem=='y')?'checked':''}/>Annual</td>
                                            <td width="150">   <input type="radio" id="radSem" name="radYearOrSem" value="s" ${!param.submitted or((param.submitted and param.txtYearOrSem=='s'))?'checked':''} />Semester
                                                <c:if test="${param.submitted and !empty(param.radYearOrSem)}">

                                                    <c:set var="yearorsem" value="${param.radYearOrSem}"></c:set>
                                                </c:if>
                                                <c:if test="${!(param.submitted)}" >
                                                    <c:set var="yearorsem" value="s" ></c:set>
                                                </c:if>
                                            </td>
                                            <td> <input type ="hidden" name="txtYearOrSem" value="${param.submitted?param.radYearOrSem:''}"/><td>
                                        </tr>
                                    </table><br>
                                    <table border="0" align="center">
                                        <tbody>

                                            <tr>
                                                <td><b>Stream * :</b>
                                                    <select name="cmbStream" id="cmbStream" >
                                                        <option value="-1">-</option>
                                                        <c:set var="streamcode" value="${param.cmbStream}"></c:set>

                                                        <c:out escapeXml="false" value="${dbutil.populatePopup(pageContext.request.servletContext,'streams','streamcode','streamname',streamcode)}">                                
                                                        </c:out>
                                                    </select> 
                                                    <c:if test="${param.submitted and !ASP.cmbSubjectNameValid}" var="v2">
                                                        <span style="color:red" > Stream Not Selected</span>
                                                    </c:if>
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
                                            >
                                            <td  style="text-align: center">
                                                <input type="submit" value="Show Papers" />
                                            </td>
                                            

                                        </tr>

                                    </table>                
                                </form>
                                <h3 id="warning"></h3>
                                </body>

                              
                            </div>
                        </div>

                    </td>
            </table>
        </div>

        <div id="footer"><%=application.getInitParameter("pageFooter")%></div>

</html>
