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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import shg.valid.Validator;

/**
 *
 * @author Shgcomp
 */
@WebServlet(name = "GetExamPapers", urlPatterns = {"/GetExamPapers"})
public class GetExamPapers extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection con = null;
        ServletContext context = null;
        ConnectionPool connectionPool = null;
        ResultSet rs = null;
        int rowcount = 0;
        String div="",pos="",h;
        PreparedStatement pst = null;
        String sql = "", output = "", practical, cat, yorsno, exists;
        String examid;
        //StringBuffer sb = new StringBuffer();
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        //String CCode = request.getParameter("cmbCourseName");
        String nehurollno = request.getParameter("txtNehuRollNo");
        //System.out.println("CCode:" + CCode);
        System.out.println("Roll:" + nehurollno);
        cat = request.getParameter("cmbNR");
        yorsno = request.getParameter("cmbYearOrSemNo");
        if (Validator.isNullValue(nehurollno)) {
            out.print("<b>Error : Enter the Roll Number</b>");
            return;
        }
        if (Validator.isNullValue(yorsno)) {
            out.print("<b>Error : Select the Year/Semester Number</b>");
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

            sql = "SELECT E.rollno,E.examid,S.subjectcode,S.subjectname,PA.marksth,PA.markspr,P.papercode,P.papername,P.practical from papersappear PA inner join subjects S on lower(PA.subjectcode)= lower(S.subjectcode)";
            sql += " inner join papers  P on lower(PA.papercode)=lower(P.papercode) Inner join examinfo  E on lower(PA.examid)=lower(E.examid) where lower(PA.nehurollno)=? and";
            sql += " lower(E.category)=? and lower(E.nehurollno)=lower(PA.nehurollno)";
            //sql += "P.papercode= PA.papercode and  and E.examid=PA.examid and PA.subjectcode=S.subjectcode Group By subjectcode";
            pst = con.prepareStatement(sql);
            //CCH.getCmbSubjectName());//subjectid);
            pst.setString(1, nehurollno.toLowerCase());
            pst.setString(2, cat.toLowerCase());
            rs = pst.executeQuery();
            if (rs.next()) {
                examid = rs.getString("examid");
                 System.out.println(rs.getString("examid"));
                output = "<div class=\"CSSTableGenerator\"><table>";
                output += "<tr><td>Subject</td>";
                output += "<td>Paper Id</td>";
                output += "<td>Paper Name</td>";
                output += "<td>Theory Marks</td>";
                output += "<td>Practical Marks</td><input type=\"hidden\" name=\"txtExamID\" id=\"txtExamID\" value=\"" + rs.getString("examid") + "\" />";
                output += "<input type=\"hidden\" name=\"txtRollNo\" id=\"txtRollNo\" value=\"" + rs.getString("rollno") + "\" />";
                output += "<input type=\"hidden\" name=\"txtNehuRollNo\" id=\"txtNehuRollNo\" value=\"" + nehurollno + "\" />";
                do {

                    output += "<tr><td><label>" + rs.getString("subjectname") + "</label>";
                    output += "<input type=\"hidden\" name=\"txtSubjectCode\" id=\"txtSubjectCode\" value=\"" + rs.getString("subjectcode") + "\"</td>";
                    output += "<td><label>" + rs.getString("papercode") + "</label>";
                    output += "<input type=\"hidden\" name=\"txtPaperCode\" id=\"txtPaperCode\" value=\"" + rs.getString("papercode") + "\"</td>";
                    output += "<td><label>" + rs.getString("papername") + "</label></td>";
                    exists = (rs.getString("marksth") == null) ? "" : rs.getString("marksth");

                    output += "<td><input type=\"text\" name=\"txtMarksTh\" id=\"txtMarksTh\" value=\"" + exists + "\" size=\"10\" /></td>";
                    output += "<input type=\"Hidden\" name=\"txtSubjectName\" id=\"txtSubjectName\" value=\"" + rs.getString("subjectname") + "\" />";
                    if (rs.getBoolean("practical")) {
                        exists = (rs.getString("markspr")) == null ? "" : rs.getString("markspr");
                        output += "<td><input type=\"text\" name=\"txtMarksPr\" id=\"txtMarksPr\" value=\"" + exists + "\" size=\"10\" /></td>";
                    } else {
                        output += "<td><input type=\"Hidden\" name=\"txtMarksPr\" id=\"txtMarksPr\" value=\"-1\"  /></td>";
                        //output += "<td><label>N/A</label></td>";
                    }
                } while (rs.next());
                output += "</tr><tr>";
                output += "<td><b>Division &nbsp;&nbsp;</b>";
               // if((yorsno.trim().equals("s6")) || (yorsno.trim().equals("y3")))
                // {

                // }
                // else
                // {
                //    output += "<select name=\"cmbDiv\" id=\"cmbDiv\" disabled>";
                //  }
                sql = "select * from result where lower(examid)=? and lower(nehurollno)=? and lower(yearorsemno)=?";
                pst = con.prepareStatement(sql);
                pst.setString(1, examid.trim().toLowerCase());
                pst.setString(2, nehurollno.toLowerCase());
                pst.setString(3, yorsno.toLowerCase());
                rs = pst.executeQuery();
                while (rs.next()) {
                    div=rs.getString("division").trim().toUpperCase();
                    System.out.println("yors="+yorsno);
                    pos=rs.getString("pos").trim();
                     
                    //rowcount = rs.getInt(1);
                }

                if ((yorsno.trim().equals("s6")) || (yorsno.trim().equals("y3"))) {
                    output += "<select name=\"cmbDiv\" id=\"cmbDiv\">";
                    output += "<option value=\"-1\">-</option>";
                   h = div.equals("I") ? "selected" : "";
                   output += "<option value=\"I\" "+ h+ ">I</option>";
                     h =div.equals("II") ? "selected" : "";
                    output += "<option value=\"II\" "+ h+">II</option>";
                      h = div.equals("III") ? "selected" : "";
                    output += "<option value=\"III\" "+ h+">III</option></select>";
                      
                    output += "</td>";
                    output += "<td><b>Position &nbsp;&nbsp;</b>";
                    output += "<select name=\"cmbPos\" id=\"cmbPos\">";
                    output += "<option value=\"-1\">-</option>";
                    h = pos.equals("1") ? "selected" : "";
                    output += "<option value=\"1\" "+ h+">1st</option>";
                     h = pos.equals("2") ? "selected" : "";
                    output += "<option value=\"2\" "+h+">2nd</option>";
                     h = pos.equals("3") ? "selected" : "";
                    output += "<option value=\"3\" "+ h+">3rd</option>";
                     h = pos.equals("4") ? "selected" : "";
                    output += "<option value=\"4\" "+ h+">4th</option>";
                     h = pos.equals("5") ? "selected" : "";
                    output += "<option value=\"5\" "+ h+">5th</option>";
                     h = pos.equals("6") ? "selected" : "";
                    output += "<option value=\"6\" "+h+">6th</option>";
                     h = pos.equals("7") ? "selected" : "";
                    output += "<option value=\"7\" "+h+">7th</option>";
                     h = pos.equals("8") ? "selected" : "";
                    output += "<option value=\"8\" "+ h+">8th</option>";
                     h = pos.equals("9") ? "selected" : "";
                    output += "<option value=\"9\" "+ h+">9th</option>";
                     h = pos.equals("10") ? "selected" : "";
                    output += "<option value=\"10\" "+ h+">10th</option></select>";
                    output += "</td>";
                } else {
                    output += "<select name=\"cmbDiv\" id=\"cmbDiv\">";
                    output += "<option value=\"-1\">-</option>";
                    h = div.equals("PASS") ? "selected" : "";
                      output += "<option value=\"PASS\" "+ h+">Passed</option>";
                        h = div.equals("FAIL") ? "selected" : "";
                    output += "<option value=\"FAIL\" "+ h+">Failed</option></select></td>";
                  
                    output += "<td><b>Position &nbsp;&nbsp;</b>";
                    output += "<select name=\"cmbPos\" id=\"cmbPos\" disabled>";
                    output += "<option value=\"-1\">-</option>";
                     h = pos.equals("1") ? "selected" : "";
                    output += "<option value=\"1\" "+ h+">1st</option>";
                     h = pos.equals("2") ? "selected" : "";
                    output += "<option value=\"2\" "+h+">2nd</option>";
                     h = pos.equals("3") ? "selected" : "";
                    output += "<option value=\"3\" "+ h+">3rd</option>";
                     h = pos.equals("4") ? "selected" : "";
                    output += "<option value=\"4\" "+ h+">4th</option>";
                     h = pos.equals("5") ? "selected" : "";
                    output += "<option value=\"5\" "+ h+">5th</option>";
                     h = pos.equals("6") ? "selected" : "";
                    output += "<option value=\"6\" "+h+">6th</option>";
                     h = pos.equals("7") ? "selected" : "";
                    output += "<option value=\"7\" "+h+">7th</option>";
                     h = pos.equals("8") ? "selected" : "";
                    output += "<option value=\"8\" "+ h+">8th</option>";
                     h = pos.equals("9") ? "selected" : "";
                    output += "<option value=\"9\" "+ h+">9th</option>";
                     h = pos.equals("10") ? "selected" : "";
                    output += "<option value=\"10\" "+ h+">10th</option></select>";
                    output += "</td>";
                    
                }
                output += "<td><input type=\"submit\" name=\"cmdSave\" id=\"cmdSave\" value=\"Save\" /></td></tr>";
                output += "</table></div>";

            } else {
                output = "No Matching Record Found";
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
