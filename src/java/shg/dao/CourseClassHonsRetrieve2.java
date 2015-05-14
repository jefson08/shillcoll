/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shg.dao;

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
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author Shgcomp
 */
public class CourseClassHonsRetrieve2 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection con = null;
        ServletContext context = null;
        ConnectionPool connectionPool = null;
        ResultSet rs = null;
        int rowcount = 0, index = 0;
        PreparedStatement pst = null;
        String sql = "", output = "", h;
        boolean cat;
        char yors;
        //StringBuffer sb = new StringBuffer();
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        //String CCode = request.getParameter("cmbCourseName");
        String SubjectId = request.getParameter("cmbSubjectName");
        //System.out.println("CCode:" + CCode);
        System.out.println("SubjectId:" + SubjectId);

        if (Validator.isNullValue(SubjectId)) {
            out.print("<b>Error : Select Course and Subject</b>");
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
            sql = "SELECT count(*) from papers WHERE lower(subjectcode)=?";
            pst = con.prepareStatement(sql);
            pst.setString(1, SubjectId.trim().toLowerCase());//CCH.getCmbSubjectName());//subjectid);
            rs = pst.executeQuery();
            while (rs.next()) {
                rowcount = rs.getInt(1);
            }
            System.out.println("rows=" + rowcount);
            sql = "SELECT * from papers WHERE lower(subjectcode)=?";
            pst = con.prepareStatement(sql);
            pst.setString(1, SubjectId.trim().toLowerCase());//CCH.getCmbSubjectName());//subjectid);
            rs = pst.executeQuery();
            if (rs.next()) {
                yors = rs.getString("yearorsemno").trim().toLowerCase().charAt(0);
                do {
                    index++;
                    output += "<tr id=\"" + index + "\"><td>Paper Id</td>";
                    output += "<td><input type=\"text\" name=\"txtPaperId\" id=\"txtPaperId\" value=\"" + rs.getString("papercode") + "\" /></td>";
                    output += "<td>Paper Name</td>";
                    output += "<td><input type=\"text\" name=\"txtPaperName\" id=\"txtPaperName\" value=\"" + rs.getString("papername") + "\" /></td>";
                    output += "<td>Year/Semester Number</td>";
                    output += "<td>";
                    output += "<select name=\"cmbYearOrSemNo\" id=\"cmbYearOrSemNo\">";
                    output += "<option value=\"-1\">-</option>";
                    if (yors == 's') {
                        h = rs.getString("yearorsemno").equals("s1") ? "selected" : "";
                        output += "<option value=\"s1\"" + h + ">1</option>";
                        h = rs.getString("yearorsemno").equals("s2") ? "selected" : "";
                        output += "<option value=\"s2\"" + h + ">2</option>";
                        h = rs.getString("yearorsemno").equals("s3") ? "selected" : "";
                        output += "<option value=\"s3\"" + h + ">3</option>";
                        h = rs.getString("yearorsemno").equals("s4") ? "selected" : "";
                        output += "<option value=\"s4\"" + h + ">4</option>";
                        h = rs.getString("yearorsemno").equals("s5") ? "selected" : "";
                        output += "<option value=\"s5\"" + h + ">5</option>";
                        h = rs.getString("yearorsemno").equals("s6") ? "selected" : "";
                        output += "<option value=\"s6\"" + h + ">6</option>";
                    }
                    else
                    {
                        h = rs.getString("yearorsemno").toLowerCase().equals("y1") ? "selected" : "";
                        output += "<option value=\"y1\"" + h + ">1</option>";
                        h = rs.getString("yearorsemno").toLowerCase().equals("y2") ? "selected" : "";
                        output += "<option value=\"y2\"" + h + ">2</option>";
                        h = rs.getString("yearorsemno").toLowerCase().equals("y3") ? "selected" : "";
                        output += "<option value=\"y3\"" + h + ">3</option>";
                    }
                    output += " </select>";
                    output += " </td><td><label>";
                    h = rs.getBoolean("honsorpass") ? "checked" : "";
                    System.out.println("cat=" + h);
                    output += "<input name =\"chkCategorydummy\" type = \"checkbox\" id = \"chkCategorydummy\" chkstat = \"" + index + "\" " + h + " />";
                    output += "Honours</label>";
                    output += "<input type = \"hidden\" name = \"chkCategory\" id = \"chkCategory\" chkVal = \"" + index + "\" value = \"${param.submitted?param.chkCategory:'false'}\" />";
                    output += "<td>   <img src=\"../images/remove.png\" id=\"delIcon\" val=\"" + index + "\"/></td>";
                    output += "</tr>";

                } while (rs.next());
            }
            output += "<input type =\"hidden\" name=\"nextrow\" id=\"nextrow\" value=\"" + index + "\"/>";

            out.print(output);
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                System.out.println("RollBack operation error.");

            }
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
        } finally {
            try {
                if (con != null) {
                    connectionPool.free(con);
                    con.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException gc) {
                System.out.println("Error in Garbage Collection :" + gc);

            }
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
