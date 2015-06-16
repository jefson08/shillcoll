<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="shg.util.shgUtil"%>
<%
    //response.setHeader("Pragma", "no-cache");
    //response.setHeader("Cache-Control", "no-store");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="clxiiinfo" class="shg.bean.searchPrevXii" ></jsp:useBean> 
<jsp:useBean id="search_single" class="shg.report.clxiireport_single"></jsp:useBean>
<jsp:setProperty name="clxiiinfo" property="*"></jsp:setProperty>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../scripts/jqueryui/ui.css" rel="stylesheet" />
        <link href="../style/master-css/style.css" rel="stylesheet" />
        <link href="../style/master-css/master-layout.css" rel="stylesheet" />
        <link href="../style/master-css/menu-style.css" rel="stylesheet" />
        <link rel="stylesheet" href="../style/master-css/sweet-alert.css">
        <script type="text/javascript" src="../scripts/jquery/jquery-1.6.2.min.js"></script>
        <script type="text/javascript" src="../scripts/jqueryui/jquery-ui-1.8.16.custom.min.js"></script>
        <script type="text/javascript" src="../scripts/util/populateComboBox.js"></script> 
        <script type="text/javascript" src="../scripts/util/net.js"></script>  
        <script src="../scripts/validate/jquery.validate.js"></script>
        <script src="../scripts/validate/additional-methods.js"></script>
        <script src="../scripts/validate/validators.js"></script>
        <script type="text/javascript" src="../scripts/adm/searchPrevXii.js"></script>  
        <script type="text/javascript" src="../scripts/jquery/sweet-alert.min.js"></script>
        <title>Edit Class XII Details</title>
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
                            <div class="frame-header" >Student Details</div>
                            <div id="processing-area">
                                <h2 id="summary"></h2> <!-- Validation error message display -->
                                <form name="clxiiinfo" id="clxiiinfo" method="Post">
                                    <input type="hidden" name="submitted" value="true" />
                                    <table border="0" id="clxiiinfo">
                                        <tbody>
                                            <tr>
                                                <td>College Roll No*</td>
                                                <td><input type="text" name="rollno" id="rollno" value="${param.rollno}" size="12" />
                                                    <c:if test="${param.submitted and !clxiiinfo.rollnoValid}" var="v1">
                                                        <span style="color: red">College Roll no is either be Blank OR invalid</span> 
                                                    </c:if>
                                                </td>
                                                <td></td>
                                            </tr> 
                                            <tr>
                                                <td><input type="button" value="Search All" name="SeachAll" id="SeachAll" /> </td>
                                                <td><input type="submit" value="Search" name="cmdSave" /> </td>
                                            </tr>
                                        </tbody>
                                </form>
                                <h3 id="warning"></h3> <!-- Error Message Display -->
                                <c:if test="${param.submitted and !v1}">
                                    <%
                                        List<String> arrList = new ArrayList<String>();
                                        arrList = search_single.search(getServletContext(), clxiiinfo);
                                        if (arrList.get(0).equals("Record Does't Exist")) {
                                            out.println("<script>swal(\"Oops...\", \"Roll No Doesn't Exist!\", \"error\");</script>");
                                        } else {
                                            out.println("<table border='0'><tbody><tr><th>Roll No</th><th>Name</th></tr><tr>");
                                            int i = 0;
                                            String roll = "";
                                            for (String item : arrList) {
                                                if (i == 0) {
                                                    roll = item;
                                                    i++;
                                                }
                                                out.println("<td>" + item + "</td>");
                                            }
                                            out.println("<td><input type='text' id='roll' name='roll' value=" + roll + " hidden/></td>");
                                            out.println("<td><input type='button' id='print' name='print' value='PRINT'/></td>");
                                            out.println("</tr></tbody></table>");
                                        }
                                    %> 
                                </c:if>
                            </div>
                        </div>

                    </td>
                </tr>
            </table>
        </div>

        <div id="footer"><%=application.getInitParameter("pageFooter")%></div>

    </body>
</html>
