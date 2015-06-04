/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shg.util;

import shg.bean.examinfo_Bean;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import DBConnection.*;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author Ransly
 */
@WebServlet(name = "examinfo_generate_examid", urlPatterns = {"/examinfo_generate_examid"})
public class examinfo_generate_examid extends HttpServlet {

    Connection con = null;
    ServletContext context = null;
    ConnectionPool connectionPool = null;
    ResultSet rs = null;
    Statement st = null;
    String sql = "";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/xml");
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

            System.out.println("Data from ajax call " + term);

            generateExamid(term.trim());

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

    public void generateExamid(String term) {
        String exid = "";
        PreparedStatement ps = null;
        String cdata = "";
        String edata = "";
        int slno = -1;
        examinfo_Bean obj = new examinfo_Bean();
        try {
            ps = con.prepareStatement("SELECT coursecode,enrollyear FROM studentdetails  WHERE lower(rollno)= ? ");
            ps.setString(1, term.toLowerCase());
            rs = ps.executeQuery();
            
            while (rs.next()) {
                cdata = rs.getString("coursecode");
                edata = rs.getString("enrollyear");
            }
            slno = getMaxSerialNo(cdata.trim());
//            if (slno == -1) {
//                throw new SQLException("Serial Number is Invalid Less than Zero!");
//            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        slno += 1;
        exid = cdata + edata + slno;

//        System.out.println("cdata:" + cdata);
//        System.out.println("edata:" + edata);
//        System.out.println("slno:" + slno);
      //  System.out.println("Eid::" + exid);
        //setting the bean value
        obj.setSerial(slno);
        obj.setExamId(exid.trim());
    }

    public int getMaxSerialNo(String term) {
        int sln = -1;
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("SELECT MAX(serial) FROM examinfo WHERE lower(rollno) LIKE ? ");
            ps.setString(1, term.toLowerCase() + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                sln = rs.getInt("max");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return sln;
    }
}
