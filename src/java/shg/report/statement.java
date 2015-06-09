/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shg.report;

import DBConnection.ConnectionPool;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 *
 */
@WebServlet(name = "statement", urlPatterns = {"/statement"})
public class statement extends HttpServlet {

    private Connection con = null;
    private ResultSet rs = null;
    private Statement s = null;
    private PreparedStatement pt = null;
    private ServletContext context = null;
    private ConnectionPool connectionPool = null;
    private String query = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = null;

        HashMap jasperParameter = new HashMap();
        //String roll = request.getParameter("rollno");
        //System.out.println("Rollno" + roll);
        ServletOutputStream servletOutputStream = response.getOutputStream();

        String hon = request.getParameter("cmbhon");

        String cat = request.getParameter("nri").toUpperCase();
        String sem = request.getParameter("seye");

        System.out.println("hon   " + cat+" "+hon+" "+sem);
        InputStream reportStream = getServletConfig().getServletContext().getResourceAsStream("/report/statement.jasper");

        try {
            context = getServletContext();
            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
            con = connectionPool.getConnection();
        } catch (SQLException e) {
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
            con = null;
            rs = null;
            s = null;
            context = null;
            connectionPool = null;
            return;
        }

        try {
            query = "SELECT \n"
                    + "  studentdetails.gender, \n"
                    + "  examinfo.category, \n"
                    + "  examinfo.regno, \n"
                    + "  examinfo.nehurollno, \n"
                    + "  coursedetails.combinationcode, \n"
                    + "  studentdetails.studentname, \n"
                    + "  subjects.subjectname, \n"
                    + "  comsubject.subcode, \n"
                    + "  studentdetails.rollno, \n"
                    + "  course.honsubcode, \n"
                    + "  studentsclass.yearorsemno,\n"
                    + " examinfo.batch\n"
                    + "FROM \n"
                    + "  shgdb.studentdetails, \n"
                    + "  shgdb.examinfo, \n"
                    + "  shgdb.coursedetails, \n"
                    + "  shgdb.comsubject, \n"
                    + "  shgdb.subjects, \n"
                    + "  shgdb.course, \n"
                    + "  shgdb.studentsclass\n"
                    + "WHERE \n"
                    + "  studentdetails.rollno = examinfo.rollno AND\n"
                    + "  studentdetails.combinationcode = coursedetails.combinationcode AND\n"
                    + "  studentdetails.coursecode = course.coursecode AND\n"
                    + "  examinfo.rollno = studentsclass.rollno AND\n"
                    + "  coursedetails.combinationcode = comsubject.combcode AND\n"
                    + "  comsubject.subcode = subjects.subjectcode AND examinfo.category=? and   course.honsubcode=? and    studentsclass.yearorsemno=?";
            pt = con.prepareStatement(query);
            pt.setString(1, cat);
            pt.setString(2, hon);
            pt.setString(3, sem);
            rs = pt.executeQuery();
            //System.out.println(pt);

            JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(rs);
           // String reportDirectory = getServletContext().getRealPath("/") + "report\\";
           // jasperParameter.put("SUBREPORT_DIR", reportDirectory);
            //jasperParameter.put("conn", con);
            JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream, jasperParameter, resultSetDataSource);
            //JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream, new HashMap(), con);
            //System.out.println("hel");
            s.close();
            rs.close();

        } catch (SQLException e) {
            // display stack trace in the browser
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            response.setContentType("text/plain");
            response.getOutputStream().print(stringWriter.toString());
        } catch (JRException e) {
            // display stack trace in the browser
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            response.setContentType("text/plain");
            response.getOutputStream().print(stringWriter.toString());
        } finally {
            connectionPool.free(con);
            con = null;
            rs = null;
            s = null;
            context = null;
            connectionPool = null;
        }
    }

}
