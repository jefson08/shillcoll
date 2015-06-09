/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shg.util;

import DBConnection.ConnectionPool;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author nic
 */
public class shgUtil {

    public StringBuffer getUserProcess() {

        StringBuffer sb = new StringBuffer();
        sb.append("<ul style=\"list-style:none; font-size: 16px; font-weight: bold;\" >");
        sb.append("<li> <a href=\"#\">Data Entry</a></li>");
        //menus
        sb.append("<ul style=\"list-style:none; font-size: 15px; font-weight: normal;\" ><li> <a href=\"stuenroll.jsp\" > Enroll Student </a></li></ul>");
        sb.append("<ul style=\"list-style:none; font-size: 15px; font-weight: normal;\" ><li> <a href=\"clxii.jsp\" >  Student Board Subjects </a></li></ul>");
        sb.append("</ul>");

        sb.append("<ul style=\"list-style:none; font-size: 16px; font-weight: bold;\" >");
        sb.append("<li> <a href=\"#\">Edit Data</a></li>");
        //menus
        sb.append("<ul style=\"list-style:none; font-size: 15px; font-weight: normal;\" ><li> <a href=\"srchstudent.jsp\" > Student's Data </a></li></ul>");
        sb.append("<ul style=\"list-style:none; font-size: 15px; font-weight: normal;\" ><li> <a href=\"CourseClassHonsEdit2.jsp\" > Edit Papers  </a></li></ul>");
        sb.append("<ul style=\"list-style:none; font-size: 15px; font-weight: normal;\" ><li> <a href=\"editxii.jsp\" > Edit Board Subjects  </a></li></ul>");
        sb.append("</ul>");

        sb.append("<ul style=\"list-style:none; font-size: 16px; font-weight: bold;\" >");
        sb.append("<li> <a href=\"#\">Master Data</a></li>");
        //menus
        sb.append("<ul style=\"list-style:none; font-size: 15px; font-weight: normal;\" ><li> <a href=\"newsubject.jsp\" > Add Subject  </a></li></ul>");
        sb.append("<ul style=\"list-style:none; font-size: 15px; font-weight: normal;\" ><li> <a href=\"CourseClassHons2.jsp\" > Add Papers  </a></li></ul>");
        sb.append("<ul style=\"list-style:none; font-size: 15px; font-weight: normal;\" ><li> <a href=\"addcourse.jsp\" >  Add Course </a></li></ul>");
        sb.append("<ul style=\"list-style:none; font-size: 15px; font-weight: normal;\" ><li> <a href=\"coursecombination.jsp\" >  Add Combination </a></li></ul>");

        sb.append("<ul style=\"list-style:none; font-size: 15px; font-weight: normal;\" ><li> <a href=\"boardNameBoardSubject.jsp\" >  Add Board Subjects </a></li></ul>");
        sb.append("<ul style=\"list-style:none; font-size: 15px; font-weight: normal;\" ><li> <a href=\"editBoardSubject.jsp\" >  Edit Board Subjects </a></li></ul>");

        sb.append("</ul>");

        //Exam
        sb.append("<ul style=\"list-style:none; font-size: 16px; font-weight: bold;\" >");
        sb.append("<li> <a href=\"#\">Examination</a></li>");
        //menus
        sb.append("<ul style=\"list-style:none; font-size: 15px; font-weight: normal;\" ><li> <a href=\"examinfo.jsp\" >Infornmation Entry</a></li></ul>");
        sb.append("<ul style=\"list-style:none; font-size: 15px; font-weight: normal;\" ><li> <a href=\"examinfo_edit.jsp\" >Information Edit</a></li></ul>");
        sb.append("<ul style=\"list-style:none; font-size: 15px; font-weight: normal;\" ><li> <a href=\"examinfo_search.jsp\" >Information Search </a></li></ul>");

        sb.append("</ul>");
        //Exam
        return sb;
    }

    public StringBuffer getUserProcess(ServletContext context, int usercode, int districtcode) {

        StringBuffer sb = new StringBuffer();

        Connection con = null;
        ResultSet rs = null;
        Statement s = null;
        String sql = "";
        ConnectionPool connectionPool = null;
        String sql1 = "";

        int mcode;

        try {
            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
            con = connectionPool.getConnection();
        } catch (Exception e) {
            System.out.println("Exception thrown by class " + e);
            return null;
        }
        sql1 = "SELECT U.processcode, U.districtcode, P.modulecode, P.processname, M.modulename, U.usercode, P.pageurl ";
        sql1 += " FROM pwd.usersprocesses U, pwd.processes P, pwd.modules M  ";
        sql1 += " WHERE U.processcode = P.processcode AND P.modulecode = M.modulecode AND U.usercode =" + usercode + "AND districtcode=" + districtcode + " ORDER BY P.modulecode ASC, P.processname ASC";

        try {
            s = con.createStatement();
            rs = s.executeQuery(sql1);
            if (rs.next()) {
                mcode = rs.getInt("modulecode");
                sb.append("<ul style=\"list-style:none\" >");
                sb.append("<li> <a href=\"#\">" + rs.getString("modulename") + "</a></li>");
                do {
                    String target = "";
//                    if(rs.getString("pageurl").trim().toLowerCase().equals("pwdcertificate.jsp") || rs.getString("pageurl").trim().toLowerCase().equals("report.jsp")){
//                        target="target=\"_blank\"";
//                    }

                    if (mcode == rs.getInt("modulecode")) {
                        //if(rs.getString("pageurl").trim().toLowerCase().equals("pwdcertificate.jsp") || rs.getString("pageurl").trim().toLowerCase().equals("report.jsp")){
                        sb.append("<ul style=\"list-style:none\" ><li> <a href=\"" + rs.getString("pageurl") + "\" " + target + " >" + rs.getString("processname") + "</a></li></ul>");
                        //}else{
                        //  sb.append("<ul style=\"list-style:none\" ><li> <a href=\""+rs.getString("pageurl") +"\">"+ rs.getString("processname") +"</a></li></ul>");
                        //}
                    } else {
                        sb.append("</ul>");
                        mcode = rs.getInt("modulecode");
                        sb.append("<ul style=\"list-style:none\" >");
                        sb.append("<li> <a href=\"#\">" + rs.getString("modulename") + "</a></li>");
                        sb.append("<ul style=\"list-style:none\" ><li> <a href=\"" + rs.getString("pageurl") + "\" " + target + " >" + rs.getString("processname") + "</a></li></ul>");
                    }

                } while (rs.next());
                sb.append("</ul>");
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            connectionPool.free(con);
            con = null;
            rs = null;
            s = null;
            sql = null;
            connectionPool = null;
            sql1 = null;
        }

        return sb;
    }

    public StringBuffer loginDetails(ServletContext context, HttpServletRequest request, String username, String logintime) {
        StringBuffer sb = new StringBuffer();

        sb.append("<table border=\"0\" cellpadding=\"1\" cellspacing=\"1\">");
        sb.append("<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td> </tr>");
        sb.append("<tr><td>User</td><td>:</td><td>").append(username).append("</td> </tr>");
//            sb.append("<tr><td>Designation</td><td>:</td><td>").append(designation).append("</td> </tr>");
//            sb.append("<tr><td>District</td><td>:</td><td>").append(districtname).append("</td> </tr>");
        sb.append("<tr><td>Login time</td><td>:</td><td>").append(logintime).append("</td></tr>");
        sb.append("<tr><td>IP Address</td><td>:</td><td>").append(request.getRemoteAddr().toString()).append("</td></tr>");
        sb.append("<tr><td></td><td>&nbsp;</td><td>&nbsp;</td> </tr>");
        sb.append("</table>");
        return sb;
    }

    public String DateAndTime() {
        String DATE_FORMAT_NOW = "dd-MM-yyyy HH:mm:ss";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }

    public String Date() {
        String DATE_FORMAT_NOW = "dd-MM-yyyy";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }

}
