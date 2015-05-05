<%-- 
    Document   : subjectmasterboard
    Created on : Feb 20, 2015, 9:52:10 PM
    Author     : B Mukhim
--%>
<%@page import="shg.util.shgUtil"%>
<%@page import="shg.edit.Editclxii"%>
<%
    //response.setHeader("Pragma", "no-cache");
  //response.setHeader("Cache-Control", "no-store");
%>
<%if ("GET".equalsIgnoreCase(request.getMethod())) {
        out.print("Invalid request -- Please try again");
        //return;
    }%>
    
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="clxiiinfo" class="shg.bean.EditClXiiInfo" ></jsp:useBean> 
<jsp:useBean id="clxiiinfoDAO" class="shg.dao.EditClXiiInfoDAO" ></jsp:useBean>
  <jsp:useBean id="stuEnroll" class="shg.bean.StudentEnroll" scope="session" ></jsp:useBean>
<jsp:useBean id="dbutil" class="shg.util.DatabaseUtility"></jsp:useBean>
<jsp:setProperty name="clxiiinfo" property="*"></jsp:setProperty>
<jsp:useBean id="edtxii" class="shg.edit.Editclxii" ></jsp:useBean>
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
    <script src="../scripts/jquery/jquery.js"></script>
    <script src="../scripts/jquery/jquery.validate.js"></script>
    <script src="../scripts/jquery/additional-methods.js"></script>
    <script type="text/javascript" src="../scripts/adm/Searchxii.js"></script>   
    <script type="text/javascript" src="../scripts/jquery/sweet-alert.min.js"></script>
    <title>Edit Class XII Details</title>
    <style>
      /*form.clxiiinfo {
          width: 50em;
      }*/
      em.error {
        background:url("../images/unchecked.gif") no-repeat 0px 0px;
        padding-left: 16px;
      }
      em.success {
        background:url("../images/checked.gif") no-repeat 0px 0px;
        padding-left: 16px;
      }
      form.clxiiinfo label.error {
        margin-left: auto;
        width: 250px;
      }
      em.error {
        color: red;
      }
      #warning {
        display: none;
      }
    </style>
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
              <div id="processing-area"><h2 id="summary"></h2> <!-- Validation error message display -->
                <form name="clxiiinfo" class="clxiiinfo" id="clxiiinfo" method="Post">
                  <input type="hidden" name="submitted1" value="true" />
                  <table border="0" id="clxiiinfo">
                    <tbody>
                      
                      <c:if test="${!param.submitted}">
                        <c:set var="rollno" value="${param.rollno}" />
                        ${edtxii.getStudentBoardDetails(pageContext.request.servletContext, rollno, "1")}
                      </c:if>
                    </tbody>
                  </table>
                  <table id="clear_content">
                    <tbody>
                      <tr>
                        <td colspan="2" style="text-align: center">
                          <c:if test="${param.submitted1 and !clxiiinfo.txtBoardRollValid}" var="v2">
                            <span style="color: red"> Board Roll is either be Blank OR invalid </span>
                          </c:if>
                        </td>
                      </tr>
                      <tr>
                        <td colspan="2" style="text-align: center">
                          <c:if test="${param.submitted1 and !clxiiinfo.cmbBoardIDValid}" var="v4">
                            <span style="color: red">Board Name is either be Blank OR invalid </span>
                          </c:if>
                        </td>
                      </tr>
                      <tr>
                        <td colspan="2" style="text-align: center">
                          <c:if test="${param.submitted1 and !clxiiinfo.txtYrPassValid}" var="v5">
                            <span style="color: red">Year Pass is either be Blank OR invalid </span>
                          </c:if>
                        </td>
                      </tr>
                      <tr>
                        <td colspan="2" style="text-align: center">
                          <c:if test="${param.submitted1 and !clxiiinfo.cmbStreamValid}" var="v6">
                            <span style="color: red">Stream is either be Blank OR invalid </span>
                          </c:if>
                        </td>
                      </tr>
                      <tr>
                        <td colspan="2" style="text-align: center">
                          <c:if test="${param.submitted1 and !clxiiinfo.txtSubjectValid}" var="v7">
                            <span style="color: red"> Subject Name is either be Blank OR invalid </span>
                          </c:if>
                        </td>
                      </tr>
                      <tr>
                        <td colspan="2" style="text-align: center">
                          <c:if test="${param.submitted1 and !clxiiinfo.txtMarksValid}" var="v8">
                            <span style="color: red">Marks is either be Blank OR invalid </span>
                          </c:if>
                        </td>
                      </tr>
                      <tr>
                        <td colspan="2" style="text-align: center">
                          <c:if test="${param.submitted1 and !clxiiinfo.txtTotalMarksValid}" var="v9">
                            <span style="color: red"> Total Marks is either be Blank OR invalid OR Total Marks can have maximum of 4 Digits </span>
                          </c:if>
                        </td>                                            
                      </tr>
                    </tbody>
                  </table> 
                  <table>
                    <tbody>
                      <tr>
                        <td style="text-align: right">
                           </td>
                        <td style="text-align: right">
                          <input type="button" value="Delete" name="Delete" id="Delete" /> </td>
                        <td style="text-align: right">
                          <input type="button" value="Add Subject" name="Add" id="Add" /> </td>
                        <td colspan="3" style="text-align: center"><input type="submit" value="Save" name="cmdSave" id="cmdSave"/> </td>
                      </tr>
                    </tbody>
                  </table>
                </form>

                <c:if test="${param.submitted1 and !v1 and !v2 and !v3 and !v4 and !v5 and !v6 and !v7 and !v8 and !v9}">
                  <%
                    int i;
                    i = clxiiinfoDAO.insertBoard(getServletContext(), clxiiinfo);
                    if (i == 1) {
                      out.println("<script>swal(\"Good job!\", \"Record Updated!\", \"success\");</script>");
                    } else if (i == 3) {
                      out.println("<script>swal(\"Oops...\", \"Subject Name Repitation!\", \"error\");</script>");
                    }
                  %> 
                </c:if>
                <div id="waitbox" title="Loading Details">
                  <div id="waitboxmsg" style="font-size: 20px;font-weight: bold;">
                    Sending Request. Please Wait....<span id="processing" style="display: visible;"><img width="26" height="28" src="../misc/images/loading_1.gif"></span>
                  </div><br>
                </div>
              </div>
            </div>
            <h3 id="warning">Your form contains tons of errors! Please try again.</h3> <!-- Error Message Display -->
          </td>
        </tr>
      </table>
    </div>

    <div id="footer"><%=application.getInitParameter("pageFooter")%></div>

  </body>
</html>
