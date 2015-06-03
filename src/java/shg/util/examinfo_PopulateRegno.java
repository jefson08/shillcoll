/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shg.util;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import DBConnection.*;
import com.google.gson.Gson;
import javax.servlet.annotation.WebServlet;
import shg.valid.Validator;
/**
 *
 * @author Rans
 */
@WebServlet(name = "examinfo_PopulateRegno", urlPatterns = {"/examinfo_PopulateRegno"})
public class examinfo_PopulateRegno extends HttpServlet {

    Connection con = null;
    ServletContext context = null;
    ConnectionPool connectionPool = null;
    ResultSet rs = null;
    Statement st = null;
    String sql = "";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        String roll = request.getParameter("term");

        if (Validator.isNullValue(roll) && Validator.isNullValue(roll)) {
            out.print("<b>Error : Enter Roll Number</b>");
            return;
        }

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


            // System.out.println("Data from ajax call " + roll);

            String rec = getRecord(roll);

            String searchList = new Gson().toJson(rec);
            response.getWriter().write(searchList);

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

    public String getRecord(String rec) {

        PreparedStatement ps = null;
        String data = "";
        String sql = "";
        try {

            sql = "SELECT regno FROM examinfo  WHERE lower(nehurollno) = ? ";

            ps = con.prepareStatement(sql);
            ps.setString(1, rec.toLowerCase());
            rs = ps.executeQuery();

            while (rs.next()) {
                data = rs.getString("regno");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return data;
    }
}
