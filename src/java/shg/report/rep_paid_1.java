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
@WebServlet (name="rep_paid", urlPatterns={"/rep_paid"})
public class rep_paid_1 extends HttpServlet {
    private Connection con = null;
    private ResultSet rs = null;
    private Statement s = null;
    private ServletContext context = null;
    private ConnectionPool connectionPool = null;
    private String query = "";
    
   
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = null;
        
        
        
        HashMap jasperParameter = new HashMap();
        
        
        ServletOutputStream servletOutputStream = response.getOutputStream();
        
        InputStream reportStream = getServletConfig().getServletContext().getResourceAsStream("/report/rep_paid.jasper");
        
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
            query = "select examid from papersappear";
            s = con.createStatement();
            rs = s.executeQuery(query);
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


