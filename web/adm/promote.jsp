<%-- 
    Document   : promote
    Created on : May 1, 2015, 10:13:41 PM
    Author     : B Mukhim
--%>

<%@page import="shg.util.shgUtil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="dbutil" class="shg.util.DatabaseUtility"></jsp:useBean>
<jsp:useBean id="promoteBean" class="shg.bean.PromoteBean"></jsp:useBean>
<jsp:useBean id="promoteDAO" class="shg.dao.PromoteDAO"></jsp:useBean>

<jsp:setProperty name="promoteBean" property="*"></jsp:setProperty>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../style/master-css/style.css" rel="stylesheet" />
        <link href="../style/master-css/master-layout.css" rel="stylesheet" />
        <link href="../style/master-css/menu-style.css" rel="stylesheet" />
        <link rel="stylesheet" href="../style/TableCSSCode.css" type="text/css" charset="utf-8" />

        <script type="text/javascript" src="../scripts/jquery/jquery-1.6.2.min.js"></script>
        <script type="text/javascript" src="../scripts/util/populateComboBox.js"></script>
        <script type="text/javascript" src="../scripts/util/net.js"></script>
        <script type="text/javascript" src="../scripts/adm/promote.js"></script>
        <title>Promote to next Class</title>
    </head>
    <body>
        <form name="promote" method="POST">
            <input type="hidden" name="submitted" value="true" />
            <table>
                <tr>
                    <td colspan="3" style="text-align: center">
                        <label><input type="radio" name="radYearOrSem" id="radYearOrSem" value="s" />Semester</label>
                        <label><input type="radio" name="radYearOrSem" id="radYearOrSem" value="y" />Year</label>
                    </td>
                </tr>
                <tr>
                    <td>Stream</td><td> : </td>
                    <td>
                        <select name="cmbStream" id="cmbStream">
                            <option value="-1"></option>
                            <c:set var="scode" value="${param.cmbStream}"></c:set>
                            <c:out escapeXml="false" value="${dbutil.populatePopup(pageContext.request.servletContext,'streams','streamcode','streamname',scode)}"> </c:out>                               
                            </select>

                        </td>
                    </tr>
                    <tr>
                        <td>Course</td><td> : </td><td><select id="cmbCourse" name="cmbCourse"></select></td>
                    </tr>
                    <tr>
                        <td>Select Year or Semester</td><td> : </td> <td><select id="cmbYearOrSemNo" name="cmbYearOrSemNo"></select></td>
                    </tr>
                    <tr>
                        <td colspan="3" style="text-align: center"><input type="button" name="search" id="search" value="Show" /></td>
                    </tr>
                </table>        
                <table>
                    <tr>
                        <td colspan="3">
                            <div id="srchlist"></div>
                        </td>
                    </tr>
                </table>
            </form>
        <c:if test="${param.submitted}">
            <%
                promoteDAO.promoteStudent(getServletContext(), promoteBean);
            %>
        </c:if>
    </body>
</html>
