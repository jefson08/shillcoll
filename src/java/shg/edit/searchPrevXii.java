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
import java.util.Collection;
import java.util.Enumeration;
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
public class searchPrevXii extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //System.out.println("Inseide server");
        Connection con = null;
        ServletContext context = null;
        ConnectionPool connectionPool = null;
        ResultSet rs = null, rs2=null;
        PreparedStatement pst = null;
         PreparedStatement pst1 = null;
        String sql = "",sql1 = "", output = "", boardid = "";
        StringBuffer sb = new StringBuffer();
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        String cmbboardID = request.getParameter("cmbboardID");
        String cmbstream = request.getParameter("cmbstream");
        //int limit = Integer.parseInt(request.getParameter("limit"));
        //int offset = Integer.parseInt(request.getParameter("offset"));

        if (Validator.isNullValue(cmbboardID) && Validator.isNullValue(cmbboardID)) {
            out.print("<b>Error : Enter search Value</b>");
            return;
        }

        if (Validator.isNullValue(cmbstream) && Validator.isNullValue(cmbstream)) {
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
            String boaname = "";
            sql = "SELECT boardname FROM boardname WHERE boardid = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, cmbboardID);
            rs = pst.executeQuery();
            if (rs.next()) {
                boaname = rs.getString("boardname");
            } else {
                output = "Not Matching Record(s) Found";
            }
            output += "<tr><th bgcolor=\"#c1c1c9\">Board Roll</th><th bgcolor=\"#c1c1c9\">Degree Roll</th><th bgcolor=\"#c1c1c9\">Year Pass</th><th bgcolor=\"#c1c1c9\">Board Name</th><th bgcolor=\"#c1c1c9\">Stream</th><th bgcolor=\"#c1c1c9\">Subject & Marks</th><th bgcolor=\"#c1c1c9\">Percentage(%)</th></tr>";
            sql = "SELECT * FROM clxii WHERE boardid=? AND stream=?";
            pst = con.prepareStatement(sql);
            pst.setString(1, cmbboardID);
            pst.setString(2, cmbstream);
            rs = pst.executeQuery();
            int check=0;
            while(rs.next()) {
                check=1;
                output += "<tr><td>" + rs.getString("boardroll") + "</td>";
                output += "<td>" + rs.getString("degroll") + "</td>";
                output += "<td>" + rs.getString("yearpass") + "</td>";
                output += "<td>" + boaname + "</td>";
                output += "<td>" + rs.getString("stream") + "</td>";
                int totalmark = 0;
                totalmark = Integer.parseInt(rs.getString("totalmark"));
                sql1 = "SELECT * FROM clxiistudsub WHERE boardroll=?";
                pst1 = con.prepareStatement(sql1);
                pst1.setString(1, rs.getString("boardroll"));
                rs2 = pst1.executeQuery();                
                output += "<td><table>";
                int sum = 0;
                while(rs2.next()) {
                    output += "<tr><td>" + rs2.getString("subject") + "</td></td>";
                    output += "<td>" + rs2.getString("mark") + "</td></tr>";
                    sum = sum + Integer.parseInt(rs2.getString("mark"));
                }
                output += "</table></td>";
                //System.out.println(" Percentage "+percentage);
                float percentage = (sum / (float)totalmark) * 100;
                output += "<td>" + percentage + "</td>";
            } 
                if(check==0) {
                output = "Not Matching Record(s) Found";
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
                 if (rs2 != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (pst1 != null) {
                    pst.close();
                }
            } catch (SQLException gc) {
                System.out.println("Error in Garbage Collection :" + gc);

            }
        }

    }

    public String getBoardID(String boaName) {
        String boaid = "";
        try {
            boaName = boaName.substring(0, 4);
            boaid = boaName.trim().toUpperCase();
        } catch (Exception e) {
            System.out.println("Cannot Generate Board ID. " + e);
            boaid = null;
        } finally {

        }
        return boaid;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
