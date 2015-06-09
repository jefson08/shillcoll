<%-- 
    Document   : teib
    Created on : Mar 5, 2015, 10:37:35 PM
    Author     : B Mukhim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="shg.util.Utility" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<jsp:useBean id="dbutil" class="shg.util.DatabaseUtility"></jsp:useBean>
<jsp:useBean id="comb" class="shg.bean.courseCombination"></jsp:useBean>
<jsp:useBean id="combdao" class="shg.dao.CourseCombinationDAO"></jsp:useBean>
<jsp:useBean id="subpaper" class="shg.util.SubjectAndPaper"></jsp:useBean>
<jsp:setProperty name="comb" property="*"></jsp:setProperty>
<%@page import="shg.util.shgUtil"%>
<%@page import="java.sql.SQLException"%>
<%@page import="DBConnection.ConnectionPool"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="../scripts/jquery/jquery-1.6.2.min.js"></script>

        <script type="text/javascript" src="../scripts/jqueryform/ajaxupload.3.5.js"></script>
        <script type="text/javascript" src="../scripts/jqueryform/jquery.form.js"></script>
        <script type="text/javascript" src="../scripts/adm/course.js"></script>
        <link rel="stylesheet" href="../scripts/jquerydatepicker/css/jquery.datepick.css" type="text/css" charset="utf-8" />
        <link href="../style/master-css/style.css" rel="stylesheet" />
        <link href="../style/master-css/master-layout.css" rel="stylesheet" />
        <link href="../style/master-css/menu-style.css" rel="stylesheet" />


        <title>Subject Combination</title>
    </head>
    <body>

        <%
            Connection con = null;
            PreparedStatement pst = null;
            ResultSet rs = null;
            String sql;
            ServletContext context = null;
            ConnectionPool connectionPool = null;
            // Statement s=con.createStatement();
%>

        <h1></h1>
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
                            <div class="frame-header" >Combination Details</div>
                            <div id="processing-area">



                                <form name="frmcourse" method="POST" action="" id="frmcourse">
                                    <input type="hidden" name="submitted" value="true" />
                                    <table border="3" style="width: 600" id="tab" name="tab">
                                        <tr>
                                            <th colspan="2">Course Combination Summary</th>
                                        </tr>
                                        <tr bgcolor="yellow">
                                            <th width="200">Course Name</th>
                                            <th width="400">Combination Name</th>

                                        </tr>
                                        <%
                                            try {
                                                //            context = getServletContext();
                                                context = getServletContext();
                                                connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
                                                con = connectionPool.getConnection();
                                            } catch (SQLException e) {
                                                //            response.sendRedirect("output.jsp?message=Connection not Established ");
                                                System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
                                                //return null;
                                            }

                                            String sql11 = "SELECT course.coursename,combination.combinationname \n"
                                                    + " FROM shgdb.course,  shgdb.combination,  shgdb.coursedetails\n"
                                                    + "where  course.coursecode = coursedetails.coursecode AND  coursedetails.combinationcode = combination.combinationcode order by coursename asc ";
                                            pst = con.prepareStatement(sql11);
                                            System.out.println("Statements " + pst);
                                            rs = pst.executeQuery();
                                            while (rs.next()) {
                                        %>

                                        <tr>
                                            <td><%=rs.getString(1)%></td>
                                            <td><%=rs.getString(2)%></td>
                                            <!--                                                            <td><input type="checkbox" name="amount1" id="amount1"></td>-->
                                        </tr>

                                        <%}%>
                                        <tr>
                                            <!--<td><input type="button" name="sub" class="sub" value="add"></td>-->
                                        </tr>    

                                    </table> 
                                    <br>
                                    <table border="0">
                                        <tbody>

                                            <tr>
                                                <td> Course Name </td>
                                                <td> : </td>
                                                <td>
                                                    <select name="coursecode" id="coursecode">
                                                        <option value="-1"></option>
                                                        <c:set var="ccode" value="${param.coursecode}"></c:set>
                                                        <c:out escapeXml="false" value="${dbutil.populatePopup(pageContext.request.servletContext,'course','coursecode','coursename',ccode)}"> </c:out>                               
                                                        </select>
                                                    <c:if test="${param.submitted and !comb.coursecodeValid}" var="v2">
                                                        <span style="color: red">Course Not Selected </span>
                                                    </c:if> 
                                                </td>
                                            </tr>       
                                            <tr>
                                                <td>Effective Year *</td>
                                                <td> : </td>
                                                <c:choose>
                                                    <c:when test="${param.submitted}">
                                                        <c:set var="dt" value="${param.txteffectiveyear}"></c:set>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:set var="dt" value="<%=Utility.currentYear()%>"></c:set>
                                                    </c:otherwise>
                                                </c:choose>
                                                <td><input type="text" name="txteffectiveyear" id="txteffectiveyear" value="${dt}"  size="10" />
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    Status
                                                </td>
                                                <td>:</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${param.submitted}">
                                                            <c:set var="status" value=""></c:set>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:set var="status" value="checked"></c:set>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <input type="radio" value="true" name="txtstatus" id="txtstatus" ${status} ${param.submitted and param.txtstatus?'checked':''}>Yes
                                                    <input type="radio" value="false"name="txtstatus" id="txtstatus" ${param.submitted and !param.txtstatus?'checked':''}>No
                                                </td>
                                            </tr>
                                            <tr>
                                                <td> </td><td></td><td></td>
                                            </tr>
                                            <tr><td> </td><td> </td>
                                                <td>
                                                    <c:if test="${param.submitted and !comb.isSubjectcodeValid(pageContext.request.servletContext)}" var="v3">
                                                        <span style="color: red">    [No Papers Checked from Selected Subjects]</span>
                                                    </c:if>

                                                </td>
                                            </tr>


                                        </tbody>
                                    </table>
                                    <table>
                                        <tr>
                                            <td></td><td></td>
                                            <td>       
                                                <div id="divsub" style="background: " >

                                                    <c:if test="${param.submitted}">
                                                        <c:out escapeXml="false" value="${subpaper.getSubjectAndPaper(pageContext.request.servletContext, param.coursecode, paramValues.subjectcode , paramValues.papercode)}">

                                                        </c:out>
                                                    </c:if>

                                                </div>                
                                            </td>
                                        </tr>
                                        <tr>

                                            <td> <input type="submit" value="SUBMIT"> </td>

                                        </tr>
                                    </table>
                                </form>
                            </div>
                            <div id="comb">

                            </div>
                            <div id="msg" >

                                <c:if test="${param.submitted and !v3 and !v4}">
                                    <% int res = combdao.insertCourseCombination(getServletContext(), comb);
                                        if (res == 1) {
                                            out.println("Records Added Sucessfully");
                                        } else {
                                            out.println("Records Added Failed: DUPLICATE");
                                        }
                                    %>

                                </c:if>


                                <br><br>
                                <div id="msg1"> <label align="center"><jsp:getProperty property="msg1" name="comb"/></label>

                                    <b>  <label align="center"><jsp:getProperty property="msg" name="comb"/></label></div>

<!--<div id="footer"><%=application.getInitParameter("pageFooter")%></div>-->
                            </div>

                            </body>

                            </html>
