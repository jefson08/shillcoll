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
public class SubjectAndPaper2 {

    public String getSubjectAndPaper(ServletContext context, String rollno, String examid, String scode[], String pcode[]) {

        String str = "";

//        if(stat.equals("R"))
        //System.out.println("Called bla bla bla "+scode);
//        
        // System.out.println("hello boss"+ stat);
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

//           
            String sql = "SELECT \n"
                    + "  studentdetails.rollno, \n"
                    + "  studentdetails.combinationcode, \n"
                    + "  comsubject.subcode, \n"
                    + "  subjects.subjectcode, \n"
                    + "  subjects.subjectname, \n"
                    + "  combpapers.papercode, \n"
                    + "  papers.papername, \n"
                    + "  papers.papercode, \n"
                    + "  studentsclass.yearorsemno, \n"
                    + "  PAPERSAPPEAR.SUBJECTCODE \n"
                    + "FROM \n"
                    + "  shgdb.studentdetails, \n"
                    + "  shgdb.subjects, \n"
                    + "  shgdb.comsubject, \n"
                    + "  shgdb.combpapers, \n"
                    + "  shgdb.papers,\n"
                    + "  shgdb.studentsclass,\n"
                    + "   shgdb.papersappear \n"
                    + "WHERE \n"
                    + "studentdetails.rollno=PAPERSAPPEAR.rollno and \n"
                    + "  studentdetails.combinationcode = comsubject.combcode AND\n"
                    + "  studentdetails.combinationcode = combpapers.combcode AND\n"
                    + "  studentsclass.yearorsemno=papers.yearorsemno  and \n"
                    + "  comsubject.subcode = subjects.subjectcode AND\n"
                    + "  subjects.subjectcode=papers.subjectcode AND  \n"
                    + "  studentdetails.rollno=studentsclass.rollno and \n"
                    + "  PAPERSAPPEAR.SUBJECTCODE=comsubject.subcode AND \n"
                    + " PAPERSAPPEAR.papercode=  combpapers.papercode and \n "
                    + " combpapers.papercode = papers.papercode AND studentdetails.rollno =? order by subjects.subjectcode asc";

            st = con.prepareStatement(sql);
             System.out.println("vlllllllll " + st);
            st.setString(1, rollno);
            rs = st.executeQuery();
            String stat1 = "s";
            System.out.println("vlllllllll " + st);

            if (rs.next()) {
                //System.out.println("hello peter" + rs.getString(1));
                String subcode = rs.getString(3);

                String sname = rs.getString(5);
                String papercode = "", papername = "";
                String checked = scode_v.contains(subcode) ? "checked" : "";
                str = "<label>"
                        + "PLEASE CHOOSE THE SUBJECTS AND PAPERS "
                        + "<BR>"
                        + "<BR>"
                        + "        <input type=\"checkbox\"  name=\"subjectcode\" id=\"subjectcode\" subval=\"" + subcode + "\" value=\""
                        + subcode + "\"" + checked + " /> " + sname + "        </label><br />";
                str += scode_v.contains(subcode) ? "<span id=\"" + subcode + "\" style=\"display: inline;\">" : "<span id=\"" + subcode + "\" style=\"display: none;\">";
                // System.out.println("msdhbfmhbvdshmfds"+sname);
                int i=1;
                do {

                    if (!subcode.equals(rs.getString(3))) {
                        subcode = rs.getString(3);
                        sname = rs.getString(5);
                        str += "<br></span>";
                        checked = scode_v.contains(subcode) ? "checked" : "";
                        str += " <label>"
                                + "       <input type=\"checkbox\"  name=\"subjectcode\" id=\"subjectcode\" subval=\"" + subcode + "\" value=\""
                                + subcode + "\"" + checked + " /> " + sname + "        </label><br />";
                        str += scode_v.contains(subcode) ? "<span id=\"" + subcode + "\" style=\"display: inline;\">" : "<span id=\"" + subcode + "\" style=\"display: none;\">";

                    }
                    papercode = rs.getString(6);
                    papername = rs.getString(7);

                    String pchecked = pcode_v.contains(papercode) ? "checked" : "";
                    str += " <<>><label><input type=\"checkbox\"  name=\"papercode\" id=\"papercode\" value=\"" + papercode + "\" " + pchecked + " /> " + papername + "<br></label>";

                } while (rs.next());

                str += "<br></span>";
            } else {
                str = null;
            }

        } catch (SQLException e) {
            //connectionPool.free(con);

            str = null;
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
