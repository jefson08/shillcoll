<%-- 
    Document   : stuenroll
    Created on : Feb 20, 2015, 9:52:10 PM
    Author     : B Mukhim
--%>
<%
    //response.setHeader("Pragma", "no-cache");
    //response.setHeader("Cache-Control", "no-store");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="sub" class="shg.bean.SubjectBean" ></jsp:useBean>
<jsp:useBean id="Subject" class="shg.dao.Subject" ></jsp:useBean>
<jsp:setProperty name="sub" property="*"></jsp:setProperty>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="dbutil" class="shg.util.DatabaseUtility"></jsp:useBean>

<%@page import="shg.util.shgUtil"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../scripts/jquerydatepicker/css/jquery.datepick.css" type="text/css" charset="utf-8" />

        <script type="text/javascript" src="../scripts/jquery/jquery-1.6.2.min.js"></script>
        <script type="text/javascript" src="../scripts/jquerydatepicker/jquery.datepick.js"></script>
        <script type="text/javascript" src="../scripts/jquery/jquery.maskedinput-1.3.js"></script>
        <script type="text/javascript" src="../scripts/util/populateComboBox.js"></script>
        <script type="text/javascript" src="../scripts/adm/course.js"></script>
        <link href="../style/master-css/style.css" rel="stylesheet" />
        <link href="../style/master-css/master-layout.css" rel="stylesheet" />
        <link href="../style/master-css/menu-style.css" rel="stylesheet" />

        <style type="text/css">
            div#question { margin-bottom:5px; }

            div#question a { 	display: block;	margin-left:20px;
                              text-decoration: none;color:black;
            }

            div#question a:hover { background: none; }

            div#question a span { display: none;  }

            div#question a:hover span { display: inline; padding: 25px;
                                        width:250px;
            }
        </style>


        <title>New Subject</title>
    </head>
    <body>
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
                            <div class="frame-header" >New Subject</div>
                            <div id="processing-area">
                                <form name="course" method="POST">
                                    <input type="hidden" name="submitted" value="true" />
                                    <table border="0">
                                        <tbody>
                                            <tr>
                                                <td> Stream *</td>
                                                <td>:</td>
                                                <td>
                                                   <select name="stream" id="stream" title ="Please Select Stream">
                                                            <option value="-1">-</option>
                                                            <c:set var="scode" value="${param.stream}"></c:set>
                                                            <c:out escapeXml="false" value="${dbutil.populatePopup(pageContext.request.servletContext,'streams','streamcode','streamname',scode)}"> </c:out>                               
                                                            </select>
                                                          
                                                        <c:if test="${param.submitted and !sub.streamValid}" var="v1">
                                                            <br><span style="color: red"> Invalid Stream: <br>Please Select Stream</span>
                                                        </c:if>
                                                </td>
                                                <td>
                                                     <div id="question">
                                                        <a href="">[??] 
                                                            <span style ="color: green"> Please Select Stream </span></a>
                                                     </div>
                                                </td>
                                            </tr>




                                            <tr>
                                                <td>Subject Name *</td>
                                                <td> : </td>
                                                <td>
                                                    <input type="text" name="txtsubjectname" id="txtsubjectname" value="${param.txtsubjectname}"  size="16" title="Please Select Stream"/>
                                                    <c:if test="${param.submitted and !sub.txtsubjectnameValid}" var="v2">
                                                        <br> <span style="color: red">Invalid Subject Name<br>/ Subject Name is blank</span>
                                                    </c:if>
                                                </td>
                                                <td>
                                                    <div id="question">
                                                        <a href="">[??]    
                                                            <span style ="color: green"> Please Enter Subject Name| No Special Character Allowed </span></a>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td></td><td></td>
                                                <td>
                                                    <input type="submit" value="Save" name="cmdSave" />
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </form>

                                <c:if test="${param.submitted and !v1 and !v2}">
                                    <%
                                        Subject.insert(getServletContext(), sub);
                                    %>

                                </c:if>
                                <div id="msg"> <label align="center"><jsp:getProperty property="msg" name="sub"/></label></div>
                                <div id="msg1"> 
                                    <label align="center"> <b><jsp:getProperty property="msg1" name="sub"/></label>
                                    <label align="center"> <b><jsp:getProperty property="msg2" name="sub"/></label>
                                </div>
                            </div>
                        </div>

                    </td>

            </table>
        </div>

        <div id="footer"><%=application.getInitParameter("pageFooter")%></div>

    </body>
</html> 

</body>
</html>
