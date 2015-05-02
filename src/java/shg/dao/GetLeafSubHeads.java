/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shg.dao;

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
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author Shgcomp
 */
@WebServlet(name = "Utility", urlPatterns = {"/GetLeafSubHeads"})
public class GetLeafSubHeads extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException i,an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection con = null;
        ServletContext context = null;
        ConnectionPool connectionPool = null;
        ResultSet rs = null;
        int rowcount = 0, index = 0;
        PreparedStatement pst = null;
        String sql = "", output = "";
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        int accheadcode;
        String amount = "",lbl;
        accheadcode = Integer.parseInt(request.getParameter("accheadcode"));
        System.out.println("accheadcode=" + accheadcode);

        try {
            context = getServletContext();
            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
            con = connectionPool.getConnection();
        } catch (SQLException e) {
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
            return;
        }

        try {

            sql = "select * from examfeeheads where parentcode=?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, accheadcode);//CCH.getCmbSubjectName());//subjectid);
            rs = pst.executeQuery();
            if (rs.next()) {
                out.print(" Select a subhead from this head");
                return;
            } else {
                sql = "select * from examsubheadsamt where accheadcode=? ";
                pst = con.prepareStatement(sql);
                pst.setInt(1, accheadcode);
                rs = pst.executeQuery();
                if (rs.next()) {
                    amount = rs.getString("amount");
                }
                sql = "select acchead from examfeeheads where accheadcode=?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, accheadcode);//CCH.getCmbSubjectName());//subjectid);
                rs = pst.executeQuery();
                // index++;
                while (rs.next()) {
                    lbl=rs.getString("acchead").toUpperCase() + " FEE : Rs.";
                    output += "<tr ><td>" + lbl + " </td>";
                    output+="<input type=\"hidden\" name=\"lblsubhead\" id=\"lblsubhead\" value=\""+lbl+ " \" />";
                    output += "<td><input type=\"text\" name=\"txtAmount\" id=\"txtAmount\" value=\""+amount+"\" /></td></tr>";

                }
            }
            out.print(output);
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
