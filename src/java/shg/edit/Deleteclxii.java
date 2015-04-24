/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shg.edit;

import DBConnection.ConnectionPool;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import shg.valid.Validator;

/**
 *
 * @author B Mukhim
 */
public class Deleteclxii extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //System.out.println("Inseide server");
        Connection con = null;
        ServletContext context = null;
        ConnectionPool connectionPool = null;
        ResultSet rs = null, rs2;
        PreparedStatement pst = null;
        String sql = "", output = "", boardid = "";
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        String srchby = request.getParameter("txtSearchBy");
        //int limit = Integer.parseInt(request.getParameter("limit"));
        //int offset = Integer.parseInt(request.getParameter("offset"));

        if (Validator.isNullValue(srchby) && Validator.isNullValue(srchby)) {
            out.print("<b>Error : Enter search Value</b>");
            return;
        }

        try {
            context = getServletContext();
            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
            con = connectionPool.getConnection();
        } catch (SQLException e) {
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
            return;
        }

        try {
            String boardroll=null;
            sql = " SELECT boardroll FROM clxii WHERE boardroll = ? OR rollno= ?";
            pst = con.prepareStatement(sql);
            pst.setString(1,srchby);
            pst.setString(2,srchby);
            rs = pst.executeQuery();
            if (rs.next()) {
                boardroll=rs.getString("boardroll");
            }
            sql = "DELETE FROM clxii WHERE boardroll = ? OR rollno= ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, srchby);
            pst.setString(2, srchby);
            int affectedRows = pst.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("A user was deleted successfully!");
            }
            sql = "DELETE FROM clxiistudsub WHERE boardroll = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1,boardroll);
            affectedRows = pst.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("A user was deleted successfully!");
                out.print("Delete SUCEESSFULLY");
            }
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                System.out.println("RollBack operation error.");

            }
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
        } finally {
            try {
                if (con != null) {
                    connectionPool.free(con);
                    con.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException gc) {
                System.out.println("Error in Garbage Collection :" + gc);

            }
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
