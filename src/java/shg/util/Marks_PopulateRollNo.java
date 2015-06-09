/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shg.util;

import DBConnection.ConnectionPool;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Shgcomp
 */
public class Marks_PopulateRollNo extends HttpServlet {

 
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
        String term = request.getParameter("term");

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

            //System.out.println("Data from ajax call " + term);

            ArrayList<String> list = getRecords(term);

            String searchList = new Gson().toJson(list);
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

    public ArrayList<String> getRecords(String term) {
        ArrayList<String> list = new ArrayList<String>();
        PreparedStatement ps = null;
        String data;
        try {
            ps = con.prepareStatement("SELECT nehurollno FROM examinfo  WHERE lower(nehurollno) LIKE ?  ORDER BY nehurollno ASC LIMIT 10 ");
            ps.setString(1, "%" + term.toLowerCase() + "%");
            rs = ps.executeQuery();
            System.out.println(ps);
            while (rs.next()) {
                data = rs.getString("nehurollno");
                list.add(data);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
       // System.out.println(list);
        return list;
    }
}
