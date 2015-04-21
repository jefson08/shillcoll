<%-- 
    Document   : dynamic
    Created on : Feb 26, 2015, 11:53:47 PM
    Author     : B Mukhim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="dym" class="shg.bean.Dynamic" />
<jsp:setProperty name="dym" property="*" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <script type="text/javascript" src="../scripts/jquery/jquery-1.6.2.min.js"></script>
        <script type="text/javascript" src="../scripts/adm/dynamic.js"></script>
        <title>JSP Page</title>
    </head>

    <body>
        <form name="dymn" method="Post">
            <input type="hidden" name="submitted" value="true" />
            <table border="0" id="papers">

                <tbody>
                    <tr>
                        <td colspan="4">
                            <c:if test="${param.submitted and !dym.paperidValid}">
                                HHHmmm Errorrrrrr 
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>Paper Id</td>
                        <td><input type="text" name="paperid" value="${dym.paperid[0]}" /></td>
                        <td>Paper Name</td>
                        <td><input type="text" name="papername" value="${dym.papername[0]}" /></td>
                    </tr>
                    <tr>
                        <td>Paper Id</td>
                        <td><input type="text" name="paperid" value="${dym.paperid[1]}" /></td>
                        <td>Paper Name</td>
                        <td><input type="text" name="papername" value="${dym.papername[1]}" /></td>
                    </tr>
                    <tr>
                        <td>Paper Id</td>
                        <td><input type="text" name="paperid" value="${dym.paperid[2]}" /></td>
                        <td>Paper Name</td>
                        <td><input type="text" name="papername" value="${dym.papername[2]}" /></td>
                    </tr>

                    <c:forEach items="${dym.paperid}" var="cur" begin="3" varStatus="status">
                        <tr>
                            <td>Paper Id</td>
                            <td><input type="text" name="paperid" value="${cur}" /></td>
                            <td>Paper Name</td>
                            <td><input type="text" name="papername" value="${dym.papername[status.index]}" /></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <table>
                <tr>
                    <td>
                        <input type="button" value="Add" name="Add" />
                    </td>
                    <td  style="text-align: center">
                        <input type="submit" value="Save" />
                    </td>
                    
                </tr>
            </table>
        </form>

    </body>
</html>
