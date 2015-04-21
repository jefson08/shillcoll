/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shg.util;

import DBConnection.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.ServletContext;

/**
 *
 * @author B Mukhim
 */
public class SubjectAndPaper {

    public String getSubjectAndPaper(ServletContext context, String course, String scode[], String pcode[]) {
//        System.out.println("Called "+this.getClass());
        String str = "";
        Connection con = null;
//        ServletContext context = null;
        ConnectionPool connectionPool = null;
        ResultSet rs = null, r1 = null;
        PreparedStatement st = null, st1 = null;

        ArrayList scode_v = new ArrayList();
        ArrayList pcode_v = new ArrayList();

        if (scode != null) {
            scode_v.addAll(Arrays.asList(scode));
        }

        if (pcode != null) {
            pcode_v.addAll(Arrays.asList(pcode));
        }

        try {
//            context = getServletContext();
            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
            con = connectionPool.getConnection();
        } catch (SQLException e) {
//            response.sendRedirect("output.jsp?message=Connection not Established ");
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
            return null;
        }

        try {

//            String course = request.getParameter("course");
//            String sql = "select sid.subjectid, sid.subjectname,sp.paperid, p.papername from subjectid as sid, "
//                    + "subjectpaper as sp, papers as p where p.paperid=sp.paperid and sid.subjectid=sp.subjectid "
//                    + "and sid.stream in (select stream from course where coursecode like ?)";
            String sql = "select sub.subjectcode, sub.subjectname,p.papercode, p.papername from subjects as sub, "
                    + "papers as p where p.subjectcode=sub.subjectcode and sub.streamcode in "
                    + "(select stream from course where coursecode like ?)";
            st = con.prepareStatement(sql);
            st.setString(1, course);

            rs = st.executeQuery();

            if (rs.next()) {

                String subcode = rs.getString(1);
                String sname = rs.getString(2);
                String papercode = "", papername = "";
                String checked = scode_v.contains(subcode) ? "checked" : "";
                str = "<label>"
                        + "        <input type=\"checkbox\" name=\"subjectcode\" id=\"subjectcode\" subval=\"" + subcode + "\" value=\""
                        + subcode + "\"" + checked + " /> " + sname + "        </label><br />";
                str += scode_v.contains(subcode) ? "<span id=\"" + subcode + "\" style=\"display: inline;\">" : "<span id=\"" + subcode + "\" style=\"display: none;\">";

                do {

                    if (!subcode.equals(rs.getString(1))) {
                        subcode = rs.getString(1);
                        sname = rs.getString(2);
                        str += "<br></span>";
                        checked = scode_v.contains(subcode) ? "checked" : "";
                        str += " <label>"
                                + "       <input type=\"checkbox\" name=\"subjectcode\" id=\"subjectcode\" subval=\"" + subcode + "\" value=\""
                                + subcode + "\"" + checked + " /> " + sname + "        </label><br />";
                        str += scode_v.contains(subcode) ? "<span id=\"" + subcode + "\" style=\"display: inline;\">" : "<span id=\"" + subcode + "\" style=\"display: none;\">";

                    }
                    papercode = rs.getString(3);
                    papername = rs.getString(4);
                    String pchecked = pcode_v.contains(papercode) ? "checked" : "";
                    str += " ><label><input type=\"checkbox\" name=\"papercode\" id=\"papercode\" value=\"" + papercode + "\" " + pchecked + " /> " + papername + "<br></label>";

                } while (rs.next());

                str += "<br></span>";
            }

        } catch (SQLException e) {
            //connectionPool.free(con);
            str=null;
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
        } finally {
            try {
                if (con != null) {
                    connectionPool.free(con);
                }
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (SQLException gc) {
                System.out.println("Error in garbage collection :" + this.getClass() + "/populatePopup()-method " + gc);
            }
        }
//        System.out.println(str);
        return str;
    }
}
