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
@WebServlet(name = "attendanceSheetReg", urlPatterns = {"/attendanceSheetReg"})
public class attendanceSheetReg extends HttpServlet {

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
        String roll = request.getParameter("rollno");
        System.out.println("Rollno" + roll);
        ServletOutputStream servletOutputStream = response.getOutputStream();

        String hon = request.getParameter("cmbhon");

        String cat = request.getParameter("nri").toUpperCase();
        String sem = request.getParameter("seye");

        System.out.println("hon   " + hon);
        InputStream reportStream = getServletConfig().getServletContext().getResourceAsStream("/report/attendanceSheetReg.jasper");

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
            query = "SELECT distinct\n"
                    + "  s.rollno,\n"
                    + "  s.studentname, \n"
                    + "  e.nehurollno, \n"
                    + "  e.regno, \n"
                    + "  e.category, \n"
                    + "  s.gender, \n"
                    + "  su.subjectname,\n"
                    +   " e.semoryear\n"
                    + "FROM \n"
                    + "  studentdetails as s, \n"
                    + "  examinfo as e, \n"
                    + "  course as c, \n"
                    + "  subjects as su, \n"
                    + "  papersappear as pp, \n"
                    + "  papers\n"
                    + "WHERE \n"
                    + "  s.rollno = e.rollno AND\n"
                    + "  s.coursecode = c.coursecode AND\n"
                    + "  e.examid = pp.examid AND\n"
                    + "  c.honsubcode = su.subjectcode AND\n"
                    + "  c.honsubcode=? and \n"
                    + "  e.category =? and e.semoryear=? ";
            pt = con.prepareStatement(query);
            pt.setString(1, hon);
            pt.setString(2, cat);
            pt.setString(3, sem);
            rs = pt.executeQuery();
            System.out.println(pt);

            JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(rs);
            String reportDirectory = getServletContext().getRealPath("/") + "report\\";
            jasperParameter.put("SUBREPORT_DIR", reportDirectory);
            jasperParameter.put("conn", con);
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
