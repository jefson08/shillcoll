<%-- 
    Document   : index
    Created on : Jan 21, 2015, 11:39:38 PM
    Author     : B Mukhim
--%>

<%@page import="DBConnection.ConnectionPool"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
     Connection con = null;
     PreparedStatement pst = null;
     String sql;
     ServletContext context = null;
     ConnectionPool connectionPool = null;
%>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
        try {
                    context = getServletContext();
                    connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
                    con = connectionPool.getConnection();
                    out.print("Successful");
                } catch (Exception e) {
                    System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
                    return;
                }
                finally{
                    connectionPool.free(con);
                }
        %>
    </body>
</html>
