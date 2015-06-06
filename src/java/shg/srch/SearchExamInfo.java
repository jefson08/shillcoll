/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shg.srch;

import javax.servlet.annotation.WebServlet;
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
 * @author Ransly
 */
@WebServlet(name = "SearchExamInfo", urlPatterns = {"/SearchExamInfo"})
public class SearchExamInfo extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.println("Inside server");
        
        Connection con = null;
        ServletContext context = null;
        ConnectionPool connectionPool = null;
        ResultSet rs = null, rs2;
        PreparedStatement pst = null;
        String sql = "", sql2 = "", output = "", navig = "";
        StringBuffer sb = new StringBuffer();
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        
        String txtsrchval = request.getParameter("txtexaminfosearch");

        int limit = Integer.parseInt(request.getParameter("limit"));
        int offset = Integer.parseInt(request.getParameter("offset"));

        if (Validator.isNullValue(txtsrchval)) {
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

            sql = "SELECT \n"
                    + "  examinfo.rollno, \n"
                    + "  studentdetails.rollno, \n"
                    + "  studentdetails.studentname, \n"
                    + "  examinfo.nehurollno, \n"
                    + "  examinfo.regno, \n"
                    + "  examinfo.semoryear, \n"
                    + "  examinfo.batch, \n"
                    + "  examinfo.exammonth, \n"
                    + "  examinfo.examyear\n"
                    + "FROM \n"
                    + "  shgdb.examinfo, \n"
                    + "  shgdb.studentdetails\n"
                    + "WHERE \n"
                    + " examinfo.rollno = studentdetails.rollno AND lower(examinfo.rollno)  LIKE ? ORDER BY examyear DESC LIMIT ? OFFSET ?";

            sql2 = "SELECT COUNT(*) AS numrows from examinfo WHERE lower(rollno) LIKE ?";

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
                output += "<form name=\"editstudent\" id=\"editstudent\" action=\"examinfo_edit.jsp\" method=\"post\">";
                output += "<div class=\"CSSTableGenerator\"><table><tr><td> Name </td> <td>NEHU Roll Number</td><td>Registration Number</td><td>Batch</td><td>Semester/Year</td><td>Exam Date</td></tr>";

                do {
                    output += "<tr><td>" + rs.getString("studentname") + "</td>" + "<td><a class=\"editStu\" rollno=\"" + rs.getString("rollno") + "\" style=\"text-decoration:underline\">"
                            + rs.getString("nehurollno") + "</a></td><td>" + rs.getString("regno")
                            + "</td><td>" + rs.getString("batch")
                            + "</td><td>" + rs.getString("semoryear")
                            + "</td><td>" + rs.getString("exammonth") + "-" + rs.getString("examyear");

                    output += "</td></tr>";
                    
                } while (rs.next());
                output += "</table>";
                output += "</div>";
                output += "<input type=\"hidden\" name=\"rollno\" id=\"rollno\" />";
                output += "</form>";
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
}
