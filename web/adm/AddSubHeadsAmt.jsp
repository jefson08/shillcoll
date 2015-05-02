<%-- 
    Document   : Add SubHeads Amount
    Created on : Feb 20, 2015, 9:52:10 PM
    Author     : A Mitri
--%>

<%
    //response.setHeader("Pragma", "no-cache");
    //response.setHeader("Cache-Control", "no-store");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="shg.util.shgUtil"%>
<jsp:useBean id="HeadsPopup" class="shg.util.ExamHeadsPopup"></jsp:useBean>
<jsp:useBean id="ASHA" class="shg.bean.AddSubHeadsAmt" ></jsp:useBean>
<jsp:useBean id="ASHADAO" class="shg.dao.AddSubHeadsAmtDAO" ></jsp:useBean>
<jsp:setProperty name="ASHA" property="*"></jsp:setProperty>


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
        <script type="text/javascript" src="../scripts/adm/AddSubHeadsAmt.js"></script>
        <script type="text/javascript" src="../scripts/jquery/jquery.maskedinput-1.3.js"></script>
        <script type="text/javascript" src="../scripts/util/net.js"></script>
        <script type="text/javascript" src="../scripts/util/populateComboBox.js"></script>

        <title>Add Amount For Exam Fees Subheads</title>
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
                            <div class="frame-header" >Sub Heads</div>
                            <div id="processing-area">
                                <div id="ASHADiv">
                                    <c:if test="${param.submitted and !v1 and !v2}">
                                        <%       StringBuilder ErrMsg = new StringBuilder();
                                            int res = ASHADAO.insertSubHeadsAmt(getServletContext(), ASHA, ErrMsg);
                                            if (res == 1) {
                                                out.println(" Operation sucessful");
                                                // dbutil.populateTreePopup(getServletContext(), ASH.getCmbHead());
                                            } else if (res == -1) {
                                                out.println(" Operation unsucessful");
                                            } else if (res == 2) {
                                                out.println(ErrMsg);
                                            }

                                        %>


                                    </c:if>
                                </div>
                                <form name="AddSubHeadsAmt" method="POST">
                                    <input type="hidden" name="submitted" value="true" />

                                    <table border="0">


                                        <tr>
                                            <td>Head *</td>
                                            <td> : </td>
                                            <td> 
                                                <select name="cmbHead" id="cmbHead">
                                                    <option value="-1">-</option>
                                                    <c:set var="accheadcode" value="${param.cmbHead}"></c:set>

                                                    <c:out escapeXml="false" value="${HeadsPopup.populateTreePopup(pageContext.request.servletContext,accheadcode)}">                                
                                                    </c:out>
                                                </select> 
                                                <c:if test="${param.submitted and !ASHA.cmbHeadValid}" var="v1">
                                                    <span style="color:red">Account Head Not Selected</span>
                                                </c:if>
                                                <%--</c:if> --%>  
                                            </td>
                                        </tr>

                                    </table>
                                    <table border="0" id="SHAmt">
                                        <tbody>
                                            <tr>
                                                <td colspan="4">
                                                    <c:if test="${param.submitted and !ASHA.txtAmountValid}" var="v2"> 
                                                        <span style="color:red">Amount is either blank or invalid!!!!</span>
                                                    </c:if>
                                                </td>
                                            </tr>
                                            <tr>
                                                <c:if test="${param.submitted}">
                                                    
                                                    <td>
                                                        <input type="hidden" name="lblsubhead" id="lblsubhead" value="${param.lblsubhead}" />
                                                        <c:out value="${param.lblsubhead}" ></c:out>
                                                    </td> 
                                           
                                            <td>
                                                <input type="text" name="txtAmount" id="txtAmount" value="${param.txtAmount}" />
                                            </td>

                                        </c:if>

                                        </tr>


                                        </tbody> 

                                    </table>
                                    <table>
                                        <tr>

                                            <td  style="text-align: center">
                                                <input type="submit" value="Save" />
                                            </td>


                                        </tr>

                                    </table>                
                                </form>

                                </body>

                                
                            </div>
                        </div>

                    </td>
            </table>
        </div>

        <div id="footer"><%=application.getInitParameter("pageFooter")%></div>

</html>