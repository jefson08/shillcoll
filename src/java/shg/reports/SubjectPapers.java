/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shg.reports;

import DBConnection.ConnectionPool;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Comparator;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import megnic.util.Validator2;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author Shgcomp
 */
public class SubjectPapers extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement s = null;
    private ServletContext context = null;
    private ConnectionPool connectionPool = null;
    private String query = "";
    
   
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = null;
        
        
        
        HashMap jasperParameter = new HashMap();
        // String subjectCode = request.getParameter("cmbSubjectName");
      //  System.out.println(subjectCode);
        ServletOutputStream servletOutputStream = response.getOutputStream();
        
        InputStream reportStream = getServletConfig().getServletContext().getResourceAsStream("/Reports/SubjectPapers.jasper");
        
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
        
       
        try{
            query = "select papercode,papername,practical from papers ";//where subjectcode=?";
             s = con.prepareStatement(query);
               //s.setString(1, subjectCode);
                rs = s.executeQuery();//+subjectCode;
           
            JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(rs);
            
            
            JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream, jasperParameter, resultSetDataSource);
            //JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream, new HashMap(), con);
            s.close();
            rs.close();
            
        }catch (Exception e){
        // display stack trace in the browser
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            response.setContentType("text/plain");
            response.getOutputStream().print(stringWriter.toString());
        }
        finally {
            connectionPool.free(con);
            con = null;
            rs = null;
            s = null;
            context = null;
            connectionPool = null;
        }
    }
}
        
 