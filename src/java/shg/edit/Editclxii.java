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
public class Editclxii {//extends HttpServlet {

   // @Override
    public String getStudentBoardDetails(ServletContext context, String rollno, String Count)
    {
//        System.out.println("roll " +rollno);
        Connection con = null;
        //ServletContext context = null;
        ConnectionPool connectionPool = null;
        ResultSet rs = null, rs2;
        PreparedStatement pst = null;
        String sql = "", output = null, boardid = "";
        StringBuffer sb = new StringBuffer();
//        PrintWriter out = response.getWriter();
//        response.setContentType("text/html");
        String srchby = rollno;
        //String Count = request.getParameter("Count");
        int count = Integer.parseInt(Count);
        //System.out.println("Serach Value by:" + srchby);
        boardid = srchby;
        //int limit = Integer.parseInt(request.getParameter("limit"));
        //int offset = Integer.parseInt(request.getParameter("offset"));
        if (Validator.isNullValue(srchby) && Validator.isNullValue(srchby)) {
            //out.print("Error: Enter search Value");
            return null;
        }

        try {
           // context = getServletContext();
            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
            con = connectionPool.getConnection();
        } catch (SQLException e) {
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
            return null;
        }

        try {
            Hashtable<String, String> BoardDisplay = new Hashtable<String, String>();
            String boaid = "";
            String boaname = "";
            sql = "SELECT boardid FROM clxii WHERE boardroll = ? OR rollno = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, srchby);
            pst.setString(2, srchby);
            rs = pst.executeQuery();
            if (rs.next()) {
                boaid = rs.getString("boardid");
            } else {
                output = "Not Matching Record(s) Found";
            }
            sql = "SELECT boardname, boardid FROM boardname";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                //boaname = rs.getString("boardname");
                do {
                    BoardDisplay.put(rs.getString("boardname"), rs.getString("boardid"));
                } while (rs.next());
            } else {
                output = "Not Matching Record(s) Found";
            }
            /* sql = "SELECT \n"
             + "  clxii.boardroll, \n"
             + "  clxii.boardid, \n"
             + "  clxii.yearpass, \n"
             + "  clxii.stream, \n"
             + "  clxii.totalmark, \n"
             + "  clxiistudsub.subjectid, \n"
             + "  clxiistudsub.mark\n"
             + "FROM \n"
             + "  clxii, \n"
             + "  clxiistudsub\n"
             + "WHERE \n"
             + "  clxii.boardroll = clxiistudsub.boardroll AND (clxii.boardroll = ? OR clxii.rollno = ?)"; */
            sql = "SELECT DISTINCT \n"
                    + "  clxii.boardroll, \n"
                    + "  clxii.boardid, \n"
                    + "  clxii.yearpass, \n"
                    + "  clxii.stream, \n"
                    + "  clxii.totalmark, \n"
                    + "  clxiistudsub.subjectid, \n"
                    + "  clxiistudsub.mark, \n"
                    + "  clxii.rollno, \n"
                    + "  clxiisubj.subjectname\n"
                    + "FROM \n"
                    + "  clxii, \n"
                    + "  clxiistudsub, \n"
                    + "  clxiisubj\n"
                    + "WHERE \n"
                    + "  clxii.boardroll = clxiistudsub.boardroll AND\n"
                    + "  clxii.boardid = clxiisubj.boardid AND\n"
                    + "  clxiistudsub.subjectid = clxiisubj.subjectid AND  clxii.rollno = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, srchby.trim());
            //pst.setString(2, srchby);
            rs = pst.executeQuery();
            if (rs.next()) {
              output="";
                //System.out.println("Sucess");
              output += "<tr><td>College Roll *</td><td>" + rs.getString("rollno") + "</td><td><input type=\"text\" name=\"rollno\" id=\"rollno\" value=\"" + rs.getString("rollno") + "\" size=\"10\" hidden /></td></tr>";
              output += "<tr><td>Board Roll *</td><td><input type=\"text\" name=\"txtBoardRoll\" id=\"txtBoardRoll\" value=\"" + rs.getString("boardroll") + "\" size=\"10\" /></td><td></td></tr>";
              
                
                
                output += "<tr><td>Year Pass *</td><td><input type=\"text\" name=\"txtYrPass\" id=\"txtYrPass\" value=\"" + rs.getString("yearpass") + "\" size=\"4\" /></td><td></td></tr>";
                //output += "<tr><td>Board Name </td><td>" + boaname + "</td><td><input type=\"text\" name=\"cmbBoardID\" id=\"cmbBoardID\" value=\"" + boaid + "\" hidden / ></td></tr>";
                output += "<tr><td>Board Name *</td>";
                output += "<td> <select name=\"cmbBoardID\" id=\"cmbBoardID\" >";
                for (Map.Entry m : BoardDisplay.entrySet()) {
                    if(m.getValue().toString().equals(rs.getString("boardid"))){
                        output+="<option value=\"" +m.getValue().toString() + "\">"+ m.getKey().toString() +"</option>";
                    }
                }
                for (Map.Entry m : BoardDisplay.entrySet()) {
                    if(!(m.getValue().toString().equals(rs.getString("boardid")))){
                        output+="<option value=\"" +m.getValue().toString() + "\">"+ m.getKey().toString() +"</option>";
                    }
                }
                 output += "</select></td>";
                      //  + "<option value=\"-1\">-</option>";
                output += "<tr><td>Stream </td><td>" + rs.getString("stream") + "</td><td><input type=\"text\" name=\"cmbStream\" id=\"cmbStream\" value=\"" + rs.getString("stream") + "\" hidden/ ></td></tr>";
                output += "<tr><td>Total Mark *</td><td><input type=\"text\" name=\"txtTotalMarks\" id=\"txtTotalMarks\" value=\"" + rs.getString("totalmark") + "\" size=\"4\" /></td><td></td></tr>";
                
                output+="<tr><td colspan=3><table id=\"add_subject\"><tbody id=\"clear_subject\">";
                int i=0;
                do {
                    output += "<tr id=" + count + "><td>Subject * &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</td><td>" + rs.getString("subjectname") + "</td><td><input type=\"text\" name=\"txtSubject\" id=\"txtSubject\" value=\"" + rs.getString("subjectid") + "\" size=\"50\" hidden /></td>";
                    output += "<td>Marks *</td><td><input type=\"text\" name=\"txtMarks\" id=\"txtMarks["+i+"]\" value=\"" + rs.getString("mark") + "\" size=\"3\" /><img src=\"../images/remove.png\" alt=\"Remove\" imgno=" + count + " id=\"DelIcon\"/></td><td></td></tr>";
                    i++;
                    count++;
                } while (rs.next());
               output+="</tbody></table></td></tr>";
            } else {
                output = "Not Matching Record(s) Found";
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
    return output;
    }
}



