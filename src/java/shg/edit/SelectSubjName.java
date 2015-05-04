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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import shg.valid.Validator;

/**
 *
 * @author B Mukhim
 */
public class SelectSubjName extends HttpServlet {

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

        String srchby = request.getParameter("cmbBoardID");
        String Stream = request.getParameter("cmbStream");
        String String_subj = request.getParameter("Subject");
        String Subject[] = String_subj.split(",");
        /* for(String Sub:Subject){
         System.out.println("Subject "+Sub);
         }*/
        String Count = request.getParameter("Count");
        int count = Integer.parseInt(Count);
        boardid = getBoardID(srchby);
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
        Hashtable<String, String> Board = new Hashtable<String, String>();
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

            //out.print(output);
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
        Hashtable<String, String> BoardDisplay = new Hashtable<String, String>();
        int flag;
        int Present = 0;
        for (Map.Entry m : Board.entrySet()) {
            //System.out.println(m.getKey() + " " + m.getValue());
            flag = 0;
            for (String Subj : Subject) {
                if (m.getKey().toString().equals(Subj)) {
                    //System.out.println(m.getKey());
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                Present = 1;
                BoardDisplay.put(m.getKey().toString(), m.getValue().toString());
            }
        }
        if (Present == 1) {
            for (Map.Entry m : BoardDisplay.entrySet()) {
                output += "<tr id=" + count + "><td>Subject *</td>";
                output += "<td>" + m.getValue().toString() + "</td> <td><input type='text' name=\"txtSubject\" id=\"txtSubject\" value=\"" + m.getKey().toString() + "\" hidden /></td>";
                output += "<td>Marks*</td><td><input type=\"text\" name=\"txtMarks\" id=\"txtMarks\" value=\"\" size=\"3\"/>"
                        + "<img src=\"../images/remove.png\" alt=\"Remove\" imgno=" + count + " id=\"DelIcon\"/></td></tr>";
                count = count + 1;
            }
        }
        else{
            output+="Error: Empty record tobe added";
        }
        out.print(output);

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
