<%-- 
    Document   : teib
    Created on : Mar 5, 2015, 10:37:35 PM
    Author     : Teibor
--%>

<%@page import="shg.bean.examinfo_Bean"%>
<%@page autoFlush="true" buffer="32kb" %>

<%@page import="java.sql.SQLException"%>
<%@page import="DBConnection.ConnectionPool"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="shg.util.Utility" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<jsp:useBean id="dbutil" class="shg.util.DatabaseUtility"></jsp:useBean>
<jsp:useBean id="exam" class="shg.bean.ExamPaperBean"></jsp:useBean>
<jsp:useBean id="exampaper" class="shg.dao.ExamPapers"></jsp:useBean>
<jsp:useBean id="subpaper" class="shg.util.SubjectAndPaper1"></jsp:useBean>
<jsp:setProperty name="exam" property="*"></jsp:setProperty>
<jsp:useBean id="examInfo" class="shg.bean.examinfo_Bean" scope ="session"></jsp:useBean>
<%@page import="shg.util.shgUtil"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="../scripts/jquery/jquery-1.6.2.min.js"></script>

        <script type="text/javascript" src="../scripts/jqueryform/ajaxupload.3.5.js"></script>
        <script type="text/javascript" src="../scripts/jqueryform/jquery.form.js"></script>
        <script type="text/javascript" src="../scripts/adm/papersappears.js"></script>
        <link rel="stylesheet" href="../scripts/jquerydatepicker/css/jquery.datepick.css" type="text/css" charset="utf-8" />
        <link href="../style/master-css/style.css" rel="stylesheet" />
        <link href="../style/master-css/master-layout.css" rel="stylesheet" />
        <link href="../style/master-css/menu-style.css" rel="stylesheet" />


        <title>Papers Appears</title>
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
                            <div class="frame-header" >Exam Appears</div>
                            <div id="processing-area">
                                <form name="frmcourse" method="POST" action="papersExam.jsp" id="frmcourse">
                                    <%
                                        String rollno = examInfo.getRollno();
                                        String status = examInfo.getNri();
                                        String yos = examInfo.getSeye();
                                        String nehuroll = examInfo.getTxtUnirollno();
                                        String examid = examInfo.getExamId();

                                        System.out.print("examid  ::" + examid);
                                        //String examid=examInfo.getExamId();
                                    %>  
                                    <input type="hidden" name="submitted1" value="true" />
                                    <input type="text" name ="examid" id="examid" value="<%=examid%>" hidden>


                                    <table border="0" >
                                        <tbody>
                                            <tr>
                                                <td>Rollno </td>
                                                <td> : </td>
                                                <td><input type="text" name="roll1" id="roll1" value="<%=rollno%>" size="12 "  style="pointer-events:none"/>
                                                </td>
                                                <td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                                                <td rowspan="10">

                                                    <table border="3" style="width: 600" id="tab" name="tab">
                                                        <tr>
                                                            <th colspan="2">FEES SUMMARY</th>
                                                        </tr>
                                                        <tr bgcolor="yellow">
                                                            <th width="500">Account Heads</th>
                                                            <th width="100">Amount</th>

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

                                                            String sql11 = "SELECT examfeeheads.accheadcode,examfeeheads.acchead, examsubheadamt.amount \n"
                                                                    + " FROM shgdb.examsubheadamt,shgdb.examfeeheads \n"
                                                                    + "where examfeeheads.accheadcode = examsubheadamt.accheadcode ";
                                                            pst = con.prepareStatement(sql11);
                                                            System.out.println("Statements " + pst);
                                                            rs = pst.executeQuery();
                                                            while (rs.next()) {
                                                        %>

                                                        <tr>
                                                            <td><%=rs.getString(2)%></td>
                                                            <td align="right" class="num"><%=rs.getString(3)%></td>
                                                            <!--                                                            <td><input type="checkbox" name="amount1" id="amount1"></td>-->
                                                        </tr>

                                                        <%}%>
                                                        <tr>
                                                            <!--<td><input type="button" name="sub" class="sub" value="add"></td>-->
                                                        </tr>    

                                                    </table> 
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Nehu Rollno </td>
                                                <td> : </td>
                                                <td><input type="text" name="nehuroll" id="nehuroll" value="<%=nehuroll%>" size="12 " style="pointer-events:none"  />

                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Category </td>
                                                <td> : </td>
                                                <td><input type="text" name="status" id="status" value="<%=status%>" size="10 " style="pointer-events:none" />

                                                </td>
                                            </tr>


                                            <tr>
                                                <td>Year / Semester </td>
                                                <td> : </td>
                                                <td><input type="text" name="yos" id="yos" value="<%=yos%>" size="5 " style="pointer-events:none" />

                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <input type="button" value="Show Combination" name="generate" id="generate">

                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="3">
                                                    <c:if test="${param.submitted1 and !exam.isSubjectcodeValid(pageContext.request.servletContext)}" var="v3">
                                                        <span style="color: red">[No Papers Checked from Selected Subjects]</span>
                                                    </c:if>

                                                </td>
                                            </tr>
                                            <tr>
                                                <td> </td><td></td><td></td>
                                            </tr>
                                            <tr>
                                                <td>
                                                </td>
                                            </tr>
                                            <tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>
                                        </tbody></table>
                                    <table><tbody>
                                            <tr>
                                                <td></td><td></td>
                                                <td>       
                                                    <div id="subject1" style="background: " >
                                                    </div>                
                                                </td>
                                            </tr>
                                            <tr>

                                                <td> <input type="submit" value="SUBMIT" disabled="" name="subpaper" id="subpaper"> </td>

                                            </tr>
                                            </td>
                                        </tbody>
                                    </table>

                                </form>
                            </div>

                            <div id="msg" >

                                <c:if test="${param.submitted1 and !v3 }">


                                    <%                                        exampaper.insertPapers(getServletContext(), exam);

                                    %>

                                </c:if>
                            </div>
                            <br><br>
                            <div id="msg1">
                                <label align="center"><jsp:getProperty property="msg1" name="exam"/></label>

                            </div>
                            <div id="msg3">
                                <label align="center"><jsp:getProperty property="msg3" name="exam"/></label>
                            </div>
                            <span style="color: red">
                                <div id="msg2">
                                    <label align="center"><jsp:getProperty property="msg2" name="exam"/></label>
                                </div>
                            </span>

                            </body>

                            </html>
