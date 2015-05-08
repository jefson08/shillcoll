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
    <script src="../scripts/validate/jquery.validate.js"></script>
    <script src="../scripts/validate/additional-methods.js"></script>
    <script src="../scripts/validate/validators.js"></script>
    <script type="text/javascript" src="../scripts/adm/promote.js"></script>

    <title>Promote to next Class</title>
  </head>
  <body>

    <div id="header" ><%@include file="common-menu.jsp" %>
      <span id="header-span"><%=application.getInitParameter("displayName")%></span>
    </div>
    <div class="master-layout">
      <table border="0" width="100%" cellpadding="0" cellspacing="0">
        <tr ><td width="20%" valign="top" class="leftcontainer">
            <div class="frame-header" >Menu</div><br>
            <!--<table width="100%" border="0" id="menu-container">-->
            <!--<tr> <td style="vertical-align: text-top" ><div id="menu">-->
            <span id="menu">           
              <%= new shgUtil().getUserProcess()%>
            </span>
            <!--</div></td></tr>-->
            <!--</table>-->
            <br />
            <div class="frame-header">Login Details</div>
            <div id="logindetails">

            </div>
          </td>
          <td width="80%" valign="top" class="rightcontainer">
            <div id="right-frame">
              <div class="frame-header" ></div>
              <div id="processing-area">

                <h3 id="summary"></h3>
                <form name="promote" id="promote" method="POST">
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
                          <c:set var="scode" value=""></c:set>
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
                  <h3 id="warning"></h3> 
                <c:if test="${param.submitted}">
                  <%
                    promoteDAO.promoteStudent(getServletContext(), promoteBean);
                  %>
                </c:if>

              </div>
            </div>

          </td>
      </table>
    </div>

    <div id="footer"><%=application.getInitParameter("pageFooter")%></div>
  </body>
</html>
