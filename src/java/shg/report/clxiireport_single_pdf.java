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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 *
 */
@WebServlet(name = "clxiireport_single_pdf", urlPatterns = {"/clxiireport_single_pdf"})
public class clxiireport_single_pdf extends HttpServlet {

    private Connection con = null;
    private ResultSet rs = null;
    private Statement s = null;
    private PreparedStatement pst = null;
    private ServletContext context = null;
    private ConnectionPool connectionPool = null;
    private String query = "";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = null;
        String rollno = request.getParameter("rollno");
        System.out.println(rollno);
        HashMap jasperParameter = new HashMap();

        ServletOutputStream servletOutputStream = response.getOutputStream();

        InputStream reportStream = getServletConfig().getServletContext().getResourceAsStream("/report/clxiireport_single.jasper");

        try {
            context = getServletContext();
            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
            con = connectionPool.getConnection();
        } catch (Exception e) {
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
            con = null;
            rs = null;
            s = null;
            context = null;
            connectionPool = null;
            return;
        }

        try {
            //query = "select rollno, studentname from studentdetails";
            query = "SELECT DISTINCT \n"
                    + "  sd.rollno,\n"
                    + "  sd.studentname,\n"
                    + "  sd.photo,\n"
                    + "  cl.boardroll,\n"
                    + "  cl.yearpass,\n"
                    + "  cl.stream,\n"
                    + "  cl.totalmark,\n"
                    + "  clstud.mark,\n"
                    + "  clsub.subjectname\n"
                    + "FROM\n"
                    + "  studentdetails as sd,\n"
                    + "  clxii as cl,\n"
                    + "  clxiistudsub as clstud,\n"
                    + "  clxiisubj as clsub\n"
                    + "WHERE\n"
                    + "  sd.rollno = cl.rollno AND\n"
                    + "  cl.boardroll = clstud.boardroll AND\n"
                    + "  cl.boardid = clsub.boardid AND\n"
                    + "  cl.stream = clsub.stream AND\n"
                    + "  clstud.subjectid = clsub.subjectid AND\n"
                    + "  sd.rollno=?";
//            System.out.println(query);
//            s = con.createStatement();
//            rs = s.executeQuery(query);
            pst = con.prepareStatement(query);
            pst.setString(1,rollno);
            rs = pst.executeQuery();
            JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(rs);
            //jasperParameter.put("conn", con);
            //String reportDirectory = getServletContext().getRealPath("/") + "/report/";
            //jasperParameter.put("SUBREPORT_DIR", reportDirectory);
            JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream, jasperParameter, resultSetDataSource);
            //JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream, new HashMap(), con);
            s.close();
            rs.close();

        } catch (Exception e) {
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
