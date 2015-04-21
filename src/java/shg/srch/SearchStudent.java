/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shg.srch;

import DBConnection.ConnectionPool;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import shg.valid.Validator;

/**
 *
 * @author B Mukhim
 */
public class SearchStudent extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        System.out.println("Inseide server");
        Connection con = null;
        ServletContext context = null;
        ConnectionPool connectionPool = null;
        ResultSet rs = null, rs2;
        PreparedStatement pst = null;
        String sql = "", sql2="", output = "", navig="";
        StringBuffer sb= new StringBuffer();
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        String srchby = request.getParameter("radSrchBy");
        String txtsrchval = request.getParameter("txtSrchVal");

        int limit = Integer.parseInt(request.getParameter("limit"));
        int offset = Integer.parseInt(request.getParameter("offset"));

        if (Validator.isNullValue(srchby) && Validator.isNullValue(txtsrchval)) {
            out.print("<b>Error : Enter search Value</b>");
            return;
        }

        try {
            context = getServletContext();
            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
            con = connectionPool.getConnection();
        } catch (SQLException e) {
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
            return;
        }

        try {

            if (srchby.equals("rollno")) {
                sql = "SELECT * from studentdetails WHERE lower(rollno) LIKE ? ORDER BY rollno LIMIT ? OFFSET ?";
                sql2 ="SELECT COUNT(*) AS numrows from studentdetails WHERE lower(rollno) LIKE ?";
            } else if (srchby.equals("name")) {
                sql = "SELECT * from studentdetails WHERE lower(studentname) LIKE ? ORDER BY rollno  LIMIT ? OFFSET ?";
                sql2 = "SELECT COUNT(*) AS numrows from studentdetails WHERE lower(studentname) LIKE ?";
            }
            pst = con.prepareStatement(sql2);
            pst.setString(1, "%" + txtsrchval.toLowerCase() + "%");
            rs2 = pst.executeQuery();
            rs2.next();
            int numrows = rs2.getInt("numrows");
            
            pst = con.prepareStatement(sql);
            pst.setString(1, "%" + txtsrchval.toLowerCase() + "%");
            pst.setInt(2, limit);
            pst.setInt(3, offset);
            rs = pst.executeQuery();
            if (rs.next()) {
                navig += "<div align='right'>";
                if (limit < numrows) {
                    if (offset > 0 || ((offset + limit) >= numrows)) {
                        navig += "<a href='javascript:void()' id='previous' style='text-decoration: none; padding-right:5px; margin-right: 30px'> Previous </a>";
                    }

                    if (((offset + limit) < numrows)) {
                        navig += " <a href='javascript:void()' id='next' style='text-decoration: none; margin-right:50px;'> Next </a>";
                    }
                }
                navig += "</div>";
                output += navig;
                output += "<form name=\"editstudent\" id=\"editstudent\" action=\"stuenrolledit.jsp\" method=\"post\">";
                output +="<div class=\"CSSTableGenerator\"><table>  <tr><td>Rollno</td><td>Name</td></tr>";
                
                do {
                    output += "<tr><td><a class=\"editStu\" rollno=\"" + rs.getString("rollno") + "\" style=\"text-decoration:underline\">"
                            + rs.getString("rollno")+"</a></td><td>" + rs.getString("studentname");
                    
                    output += "</td></tr>";
                } while (rs.next());
                output += "</table>";
                output += "</div>";
                output += "<input type=\"hidden\" name=\"rollno\" id=\"rollno\" />";
                output +="</form>";
            } else {
                output = "Not Matching Record(s) Found";
            }
            out.print(output);
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                System.out.println("RollBack operation error.");

            }
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
        } finally {
            pst = null;
            connectionPool.free(con);
            con = null;
            rs = null;
            pst = null;
            connectionPool = null;
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
