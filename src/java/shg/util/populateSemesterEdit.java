/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shg.util;

import DBConnection.ConnectionPool;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.PreparedStatement;

/**
 *
 * @author hp
 */
public class populateSemesterEdit extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // processRequest(request, response);
        Connection con = null;
        ServletContext context = null;
        ConnectionPool connectionPool = null;
        ResultSet rs = null;
        Statement st = null;
        PreparedStatement pst = null;
        String sql = "";
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        String strForm = request.getParameter("f");
        String strElem = request.getParameter("e");

        String nehurollno = request.getParameter("uniroll").trim();
        String paperid = request.getParameter("pid").trim();

        String strXML = "";
        try {
            try {
                context = getServletContext();
                connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
                con = connectionPool.getConnection();

            } catch (Exception e) {
                response.sendRedirect("output.jsp?message=Connection not Established ");
                System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
                return;
            }

            sql = "SELECT DISTINCT improvementsem FROM improvement WHERE upper(nehurollno) = ? AND upper(papercode) = ? ";

            pst = con.prepareStatement(sql);
            pst.setString(1, nehurollno.toUpperCase());
            pst.setString(2, paperid.toUpperCase());
            rs = pst.executeQuery();
//            System.out.println(pst);
            if (rs.next()) {
//            System.out.println("---"+rs.getString(1));

                out.print(rs.getString(1));
            }
//            strXML = strXML + "<?xml version='1.0' ?>";
//            strXML = strXML + "<selectChoice>";
//            strXML = strXML + "<selectElement>";
//            strXML = strXML + "<formName>" + strForm + "</formName>";
//            strXML = strXML + "<formElem>" + strElem + "</formElem>";
//            strXML = strXML + "</selectElement>";
//          
//            while (rs.next()) {
//                strXML = strXML + "<entry>";
//
//                strXML = strXML + "<optionText>" + rs.getString(1) + "</optionText>";
//                strXML = strXML + "<optionValue>" + rs.getString(1) + "</optionValue>";
//                strXML = strXML + "</entry>";
//            }
//            strXML = strXML + "</selectChoice>";
//            out.write(strXML.toString());

        } catch (Exception e) {
            //connectionPool.free(con);
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
        } finally {
            connectionPool.free(con);
            con = null;
            context = null;
            connectionPool = null;
            rs = null;
            st = null;
        }
    }
}
