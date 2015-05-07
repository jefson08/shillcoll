/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shg.util;

import DBConnection.ConnectionPool;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author root
 */
@WebServlet(name = "getStuForPromote", urlPatterns = {"/getStuForPromote"})
public class getStuForPromote extends HttpServlet {

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
   * methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    Connection con = null;
    ServletContext context = null;
    ConnectionPool connectionPool = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    String sql = "";
    String ccode="", sy="";
    PrintWriter out = response.getWriter();
    try {
      try {
        context = getServletContext();
        connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
        con = connectionPool.getConnection();
      } catch (Exception e) {
        response.sendRedirect("output.jsp?message=Connection not Established ");
        System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
        return;
      }
      ccode=request.getParameter("");
      sy=request.getParameter("");
      sql = "SELECT "
              + "  s.rollno, "
              + "  s.studentname, "
              + "  s.coursecode, "
              + "  e.nehurollno, "
              + "  e.regno, "
              + "  e.semoryear "
              + "FROM "
              + "  studentdetails AS s, "
              + "  exam AS e "
              + "WHERE "
              + "  s.rollno = e.rollno "
              + " AND lower(s.coursecode)=? AND lower(e.semoryear)=?";
      try {
        pst=con.prepareStatement(sql);
        rs=pst.executeQuery();
        while(rs.next()){
          
        }
      } catch (SQLException ex) {
        System.out.println(ex);
        Logger.getLogger(getStuForPromote.class.getName()).log(Level.SEVERE, null, ex);
      }
      
    } finally {
      out.close();
    }
  }
}
