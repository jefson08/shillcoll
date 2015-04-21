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
public class populateUniRollNoEdit extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //  processRequest(request, response);

        Connection con = null;
        ServletContext context = null;
        ConnectionPool connectionPool = null;
        ResultSet rs = null;
        Statement st = null;
        String sql = "";
        PreparedStatement pst = null;
        PrintWriter out = response.getWriter();
        response.setContentType("text/xml");
        String strForm = request.getParameter("f");
        String strElem = request.getParameter("e");
        String coursecode = request.getParameter("q").trim().toUpperCase();
        String strXML = "";

        try {
            try {
                context = getServletContext();
                connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
                con = connectionPool.getConnection();
            } catch (Exception e) {
               // response.sendRedirect("output.jsp?message=Connection not Established ");
                System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
                return;
            }

            // sql = "SELECT DISTINCT enrollyear FROM studentenroll WHERE  upper(courseCode)=? ORDER BY enrollyear ASC";

            sql = " SELECT DISTINCT nehurollno FROM shgdb.improvement "
                    + " INNER JOIN shgdb.papers ON shgdb.improvement.papercode = shgdb.papers.papercode "
                    + " INNER JOIN shgdb.course ON shgdb.papers.coursecode = shgdb.course.coursecode AND shgdb.course.coursecode = ? ";

            pst = con.prepareStatement(sql);
            pst.setString(1, coursecode);
            rs = pst.executeQuery();

            strXML = strXML + "<?xml version='1.0' ?>";
            strXML = strXML + "<selectChoice>";
            strXML = strXML + "<selectElement>";
            strXML = strXML + "<formName>" + strForm + "</formName>";
            strXML = strXML + "<formElem>" + strElem + "</formElem>";
            strXML = strXML + "</selectElement>";

            strXML = strXML + "<entry>";
            strXML = strXML + "<optionText>" + "Select" + "</optionText>";
            strXML = strXML + "<optionValue>-1" + "</optionValue>";
            strXML = strXML + "</entry>";

            // String selected = null;
            while (rs.next()) {
                strXML = strXML + "<entry>";
                strXML = strXML + "<optionText>" + rs.getString(1).toUpperCase() + "</optionText>";
                strXML = strXML + "<optionValue>" + rs.getString(1).toUpperCase() + "</optionValue>";
                strXML = strXML + "</entry>";
            }
            strXML = strXML + "</selectChoice>";
            out.write(strXML.toString());
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
