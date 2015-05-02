<%-- 
    Document   : Add SubHeads
    Created on : Feb 20, 2015, 9:52:10 PM
    Author     : A Mitri
--%>

<%
    //response.setHeader("Pragma", "no-cache");
    //response.setHeader("Cache-Control", "no-store");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="shg.util.shgUtil"%>
<jsp:useBean id="ExamHeads" class="shg.util.ExamHeadsPopup"></jsp:useBean>
<jsp:useBean id="ASH" class="shg.bean.AddSubHeads" ></jsp:useBean>
<jsp:useBean id="ASHDAO" class="shg.dao.AddSubHeadsDAO" ></jsp:useBean>
<jsp:setProperty name="ASH" property="*"></jsp:setProperty>


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
        <script type="text/javascript" src="../scripts/adm/AddSubHeads.js"></script>
        <script type="text/javascript" src="../scripts/jquery/jquery.maskedinput-1.3.js"></script>
        <script type="text/javascript" src="../scripts/util/net.js"></script>
        <script type="text/javascript" src="../scripts/util/populateComboBox.js"></script>

        <title>Add Exam Fees Subheads</title>
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
                                <div id="ASHDiv">
                                    <c:if test="${param.submitted and !v1 and !v2}">
                                        <%       StringBuilder ErrMsg = new StringBuilder();
                                            int res = ASHDAO.insertToexamfeeheads(getServletContext(), ASH, ErrMsg);
                                            if (res == 1) {
                                                out.println(" Sub Heads created sucessfully");
                                                // dbutil.populateTreePopup(getServletContext(), ASH.getCmbHead());
                                            } else if (res == -1) {
                                                out.println(" Sub Head creation unsucessful");
                                            } else if (res == 2) {
                                                out.println(ErrMsg);
                                            }

                                        %>


                                    </c:if>
                                </div>
                                <form name="AddSubHeads" method="POST">
                                    <input type="hidden" name="submitted" value="true" />

                                    <table border="0" id="SubHeads">
                                        <tbody>

                                            <tr>
                                                <td>Head *</td>
                                                <td> : </td>
                                                <td> 
                                                    <select name="cmbHead" id="cmbHead">
                                                        <option value="-1">-</optionnd >
                                                            <c:set var="accheadcode" value="${param.cmbHead}"></c:set>

                                                            <c:out escapeXml="false" value="${ExamHeads.populateTreePopup(pageContext.request.servletContext,accheadcode)}">                                
                                                            </c:out>
                                                    </select> 
                                                    <c:if test="${param.submitted and !ASH.cmbHeadValid}" var="v1">
                                                        <span style="color:red"> Account Head Not Selected</span>
                                                    </c:if>
                                                    <%--</c:if> --%>  
                                                </td>
                                            </tr>


                                            <tr>
                                                <td colspan="4">
                                                    <c:if test="${param.submitted and !ASH.txtSubHeadValid}" var="v2"> 
                                                        <span style="color:red">Sub head is either blank or invalid!!!!</span>
                                                    </c:if>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>Sub head</td>
                                                <td>
                                                    <input name="txtSubHead" type="text" id="txtSubHead"  value="${param.submitted?paramValues.txtSubHead[0]:''}"/>
                                                </td>

                                                </td>

                                            </tr>
                                            <c:if test="${param.submitted}" >
                                                <c:forEach items="${ASH.txtSubHead}" var="cur" begin="1" varStatus="status">
                                                    <tr id="${status.index}" >
                                                        <c:set var="row" value="${status.index}" ></c:set>
                                                            <td>Sub Head</td>
                                                            <td><input type="text" name="txtSubHead" value="${cur}" /></td>

                                                    </tr>
                                                </c:forEach>

                                            <input type ="hidden" name="row" id="row" value="${row}" />
                                        </c:if>
                                        </tbody> 

                                    </table>
                                    <table>
                                        <tr>
                                            <td>
                                                <input type="button" value="Add More Sub-heads" name="cmdAddMore" ${(param.submitted and !v1 and !v2)?'disabled':''}/>
                                            </td>
                                            <td  style="text-align: center">
                                                <input type="submit" value="Save" />
                                            </td>
                                            <td>
                                                <input type="button" value="Next" name="cmdNext" ${(param.submitted and !v1 and !v2 )?'':'disabled'} />
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