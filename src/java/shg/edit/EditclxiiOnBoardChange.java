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
import java.util.Hashtable;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import shg.valid.Validator;

/**
 *
 * @author B Mukhim
 */
@WebServlet(name = "EditclxiiOnBoardChange", urlPatterns = {"/EditclxiiOnBoardChange"})
public class EditclxiiOnBoardChange extends HttpServlet {

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
        StringBuffer sb = new StringBuffer();
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        String Stream = request.getParameter("cmbStream");
        boardid = request.getParameter("cmbboardID");

        String Count = request.getParameter("Count");
        int count = Integer.parseInt(Count);

        Hashtable<String, String> Board = new Hashtable<String, String>();
        try {
            context = getServletContext();
            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
            con = connectionPool.getConnection();
        } catch (SQLException e) {
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
            return;
        }

        try {
            sql = " SELECT \n"
                    + "  clxiisubj.subjectname, clxiisubj.subjectid\n"
                    + "FROM \n"
                    + "  boardname, \n"
                    + "  clxiisubj\n"
                    + "WHERE \n"
                    + "  boardname.boardid = clxiisubj.boardid AND\n"
                    + "  boardname.boardid LIKE ? AND clxiisubj.stream LIKE ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, "%" + boardid.trim().toUpperCase() + "%");
            pst.setString(2, "%" + Stream.trim().toUpperCase() + "%");
            rs = pst.executeQuery();
            if (rs.next()) {
                do {
                    Board.put(rs.getString("subjectid"), rs.getString("subjectname"));
                } while (rs.next());
            } else {
                output = "DATABASE RECORD:Not Matching Record(s) Found";
            }

            // out.print(output);
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
        output += "<tr><td colspan=3> <div id=\"clear_subject\"><table id=\"add_subject\">";
        for (Map.Entry m : Board.entrySet()) {
            //System.out.println("Subject ID:"+m.getKey()+"SUbject Name:"+m.getValue());
            output += "<tr id=" + count + "><td>Subject * &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</td>";
            output += "<td>" + m.getValue().toString() + "</td> <td><input type='text' name=\"txtSubject\" id=\"txtSubject\" value=\"" + m.getKey().toString() + "\" hidden /></td>";
            output += "<td>Marks*</td><td><input type=\"text\" name=\"txtMarks\" id=\"txtMarks\" value=\"\" size=\"3\"/>"
                    + "<img src=\"../images/remove.png\" alt=\"Remove\" imgno=" + count + " id=\"DelIcon\"/></td></tr>";
            count = count + 1;
        }
        output += "</table></div></td></tr>";
        out.print(output);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
