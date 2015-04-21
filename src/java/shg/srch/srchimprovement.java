/*
 * To change this template, choose Tools | Templates
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
 * @author hp
 */
public class srchimprovement extends HttpServlet {

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

//        String srchby = request.getParameter("srchby");
        String txtsrchval = request.getParameter("txtsrchval");

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

//            if (srchby.equals("rollno")) {
            //sql = "SELECT Distinct * from improvement I,studentdetails S,papers P WHERE lower(I.nehurollno) LIKE ? AND lower(S.nehurollno) LIKE ? AND I.papercode=P.papercode ORDER By S.nehurollno";
            sql = "SELECT DISTINCT I.nehurollno,I.papercode,I.improvementsem,S.nehurollno,S.studentname,P.papercode,P.papername "
                    + " FROM improvement I "
                    + " INNER JOIN studentdetails S ON S.nehurollno = I.nehurollno "
                    + " AND lower(I.nehurollno) LIKE ? AND lower(S.nehurollno) LIKE ? "
                    + " INNER JOIN papers P ON I.papercode = P.papercode ORDER BY I.nehurollno ASC ";
            sql2 = "SELECT Distinct COUNT(*) AS numrows from improvement I WHERE lower(I.nehurollno) LIKE ?";
//            } else if (srchby.equals("name")) {
//                sql = "SELECT * from studentdetails WHERE lower(studentname) LIKE ?";
//                sql2 = "SELECT COUNT(*) AS numrows from studentdetails WHERE lower(studentname) LIKE ?";
//            }
            pst = con.prepareStatement(sql2);
            pst.setString(1, "%" + txtsrchval.toLowerCase() + "%");

            rs2 = pst.executeQuery();
            rs2.next();
            int numrows = rs2.getInt("numrows");

            pst = con.prepareStatement(sql);
            pst.setString(1, "%" + txtsrchval.toLowerCase() + "%");
            pst.setString(2, "%" + txtsrchval.toLowerCase() + "%");
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
                output += "<form name=\"editstudent\" id=\"editstudent\" action=\"stimprovement.jsp\" method=\"post\">";
                output += "<div class=\"CSSTableGenerator\"><table border=\"0\" align=\"center\"><tr><td>University Roll Number</td><td>Name</td><td>Paper Code </td><td>Paper Name</td> <td>Applied Semester/Year</td></tr>";

                do {
                    output += "<tr><td><a class=\"editStu\" rollno=\"" + rs.getString("nehurollno") + "\" style=\"text-decoration:underline\">"
                            + rs.getString("nehurollno") + "</a></td><td>" + rs.getString("studentname") + "</td><td>" + rs.getString("papercode") + "</td><td>" + rs.getString("papername") + "</td><td>" + rs.getString("improvementsem") + "</td></tr>";
                } while (rs.next());
                output += "</table> </div>";
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
