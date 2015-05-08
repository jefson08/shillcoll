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
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author root
 */
@WebServlet(name = "getStuForPromote", urlPatterns = {"/getStuForPromote"})
public class getStuForPromote extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Connection con = null;
        ServletContext context = null;
        ConnectionPool connectionPool = null;
        ResultSet rs = null;
        PreparedStatement pst = null;
        String sql = "";
        String ccode = "", sy = "", output = "", temp="";
        HashMap<String, String> semyr = new HashMap<String, String>();
        PrintWriter out = response.getWriter();
        semyr.put("s1", "First Semester");
        semyr.put("s2", "Second Semester");
        semyr.put("s3", "Third Semester");
        semyr.put("s4", "Fourth Semester");
        semyr.put("s5", "Five Semester");
        semyr.put("s6", "Six Semester");
        semyr.put("y1", "First Year");
        semyr.put("y2", "Second Year");
        semyr.put("y3", "Third Year");
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

            try {
                ccode = request.getParameter("cmbCourse").toLowerCase();
                sy = request.getParameter("cmbYearOrSemNo").toLowerCase();

                if (sy.toLowerCase().startsWith("s")) {
                    int n = Integer.parseInt(sy.substring(1));
                    n += 1;
                    temp = "s" + n;
                } else {
                    int n = Integer.parseInt(sy.substring(1));
                    n += 1;
                    temp = "y" + n;
                }
                sql = "SELECT "
                        + "  s.rollno, "
                        + "  s.studentname, "
                        + " s.coursecode, "
                        + "  c.coursename, "
                        + "  e.nehurollno, "
                        + "  e.regno, "
                        + "  e.semoryear "
                        + "FROM "
                        + "  studentdetails AS s, "
                        + "  exam AS e,"
                        + " course AS c "
                        + "WHERE "
                        + "  s.rollno = e.rollno "
                        + " AND s.coursecode=c.coursecode"
                        + " AND lower(s.coursecode)=? AND lower(e.semoryear)=? "
                        + "AND lower(e.category) = 'r' "
                        + " AND ? not in (SELECT max(yearorsemno) FROM studentsclass where rollno=s.rollno) "
                        + " ORDER BY s.rollno";
                pst = con.prepareStatement(sql);
                pst.setString(1, ccode.trim());
                pst.setString(2, sy.trim());
                pst.setString(3, temp);
                rs = pst.executeQuery();
                if (rs.next()) {
                    output = "<div class=\"CSSTableGenerator\"><table>";
                    output += "<tr>";
                    output += "<td>Rollno</td>";
                    output += "<td>NEHU Rollno</td>";
                    output += "<td>Registration No</td>";
                    output += "<td>Name</td>";
                    output += "<td>Course</td>";
                    output += "<td>Semester/Year</td>";
                    output += "<td>Promote</td>";
                    output += "</tr>";
                    do {
                        output += "<tr>";
                        output += "<td>" + rs.getString("rollno") + "<input type=\"hidden\" name=\"rollno\" value=\"\" id=\"rollno" + rs.getString("rollno").trim() + "\"/></td>";
                        output += "<td>" + rs.getString("nehurollno") + "</td>";
                        output += "<td>" + rs.getString("regno") + "</td>";
                        output += "<td>" + rs.getString("studentname") + "</td>";
                        output += "<td>" + rs.getString("coursename") + "<input type=\"hidden\" name=\"coursecode\" value=\"\" id=\"course" + rs.getString("rollno").trim() + "\"/></td>";
                        output += "<td>" + semyr.get(rs.getString("semoryear").trim().toLowerCase()) + "<input type=\"hidden\" name=\"semoryear\" value=\"\" id=\"smoryr" + rs.getString("rollno").trim() + "\"/></td>";
                        output += "<td>"
                                + "<input type=\"checkbox\" name=\"promote[]\" id=\"promote\" rollno=\"" + rs.getString("rollno").trim() + "\" "
                                + " course=\"" + rs.getString("coursecode").trim() + "\" "
                                + " smoryr=\"" + rs.getString("semoryear").trim() + "\" "
                                + "/>"
                                
                                + "<div id=\"err\"></div></td>";
                        output += "</tr>";
                    } while (rs.next());
                    output += "<tr><td colspan=\"7\" required title=\"sdsds\" style=\"text-align: center\"><input type=\"submit\" name=\"update\" value=\"Update\" /></td></tr>";
                    output += "</table></div>";
                } else {
                    output = "No Matching Record Found";
                }
            } catch (SQLException ex) {
                System.out.println(ex);
                Logger.getLogger(getStuForPromote.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                connectionPool.free(con);
                con = null;
                context = null;
                connectionPool = null;
                rs = null;
            }
            out.print(output);
        } finally {
            out.close();
        }
    }
}
