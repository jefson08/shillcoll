/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shg.uploadimage;

import DBConnection.ConnectionPool;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nic
 */

public class showImageFromDB extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // PrintWriter out = response.getWriter();
        Connection con = null;
    ResultSet rs = null;
    
    ServletContext context = null;
    ConnectionPool connectionPool = null;
    Statement s=null;
    String id=request.getParameter("id");
    
    //Obtained connection object
    try {
            context = getServletContext();
            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
            con = connectionPool.getConnection();
    } catch (Exception e) {
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
            return;
    }
    
    //String sql1="SELECT * FROM pwddocument where pwdid='"+id+"'";
    String sql1="SELECT photo FROM pwdmaster where pwdid='"+id+"'";
    try {
        con.setAutoCommit(false);
        s=con.createStatement();
        rs=s.executeQuery(sql1);
        con.commit();
        if(rs.next()){
            byte barray[] = rs.getBytes("photo");
            if(barray==null){
                return;
            }
            response.setContentType("image/gif");
            response.getOutputStream().write(barray);
        }
    }catch(Exception e){
        try {
                con.rollback();
        } catch (Exception ex) {
            System.out.println("RollBack operation error.");
                
        }
        System.out.println("Error while retreiving data from database"+e);
        e.printStackTrace();
    }finally {
        connectionPool.free(con);
        con = null;
        rs = null;
        context = null;
        connectionPool = null;
        s=null;
    }
    
    
    }

    
}
