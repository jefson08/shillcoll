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
public class EditBoardSubj extends HttpServlet {

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

        String srchby = request.getParameter("cmbBoardID");
        String stream = request.getParameter("cmbStream");
        //System.out.println(srchby);
        //System.out.println("Search by boardid:" + srchby);
        //System.out.println("Search by stream:" + stream);
        //boardid = getBoardID(srchby);
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
            sql = "SELECT boardname FROM boardname WHERE boardid=?";
            pst = con.prepareStatement(sql);
            pst.setString(1,srchby.toUpperCase());
            rs = pst.executeQuery();
            if(rs.next()){
                output += "<tr><td>Board *</td><td>" + rs.getString("boardname") + "</td></tr>";
            }
            sql = " SELECT \n"
                    + "  clxiisubj.subjectname\n"
                    + "FROM \n"
                    + "  boardname, \n"
                    + "  clxiisubj\n"
                    + "WHERE \n"
                    + "  boardname.boardid = clxiisubj.boardid AND\n"
                    + "  boardname.boardid LIKE ? AND clxiisubj.stream LIKE ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, "%" + srchby.toUpperCase() + "%");
            pst.setString(2, "%" + stream.toUpperCase() + "%");
            rs = pst.executeQuery();
            if (rs.next()) {
                output += "<tr><td>Stream *</td><td>" + stream.toUpperCase() + "</td></tr>";
                int i=0;
                do {
                    output += "<tr id="+i+"><td>Subject *</td><td>" + rs.getString("subjectname") + "</td><td align=\"left\"><img src=\"../images/remove.png\" alt=\"Remove\" imgno="+i+" id=\"DelIcon\"/></td><td><input type=\"text\" name=\"txtSubName\" id=\"txtSubName["+i+"]\" size=\"50\" value=\"" + rs.getString("subjectname") + "\" hidden /></td><td></td></tr>"; 
                    i++;
                } while (rs.next());
            } else {
                output = "Not Matching Record(s) Found";
            }
           /* try{
                Thread.sleep(5000);
            }catch(InterruptedException t){
               
            }*/
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

   /* public String getBoardID(String boaName) {
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
    }*/

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
