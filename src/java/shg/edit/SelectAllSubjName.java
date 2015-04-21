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
public class SelectAllSubjName extends HttpServlet {

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
        String Count = request.getParameter("Count");
        int count = Integer.parseInt(Count);
        System.out.println("Search by:" + srchby.trim().toUpperCase());
        System.out.println("Stream by:" + Stream.trim().toUpperCase());
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
        try {
            sql = " SELECT * FROM boardname WHERE boardid = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1,boardid.trim().toUpperCase());
            rs = pst.executeQuery();
            if (rs.next()) {
                output += "<tr><td>Board Name *</td>";
                //System.out.println("BoardName is "+rs.getString("boardname"));
                output += "<td>"+rs.getString("boardname")+"</td></tr>";
                output += "<tr><td>Stream *</td>";
                output += "<td>"+Stream+"</td></tr>";
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
            pst.setString(1, "%" + boardid.trim().toUpperCase() + "%");
            pst.setString(2, "%" + Stream.trim().toUpperCase() + "%");
            rs = pst.executeQuery();
//            System.out.println(pst);
            /*if (rs.next()) {              
             output += "<tr id="+count+"><td>Subject *</td>";
             output += "<td> <select name=\"txtSubject\" id=\"txtSubject\">\n"
             + "<option value=\"-1\">-</option>";
             do {
             output += "<option value=\" " + rs.getString("subjectname") + " \">" + rs.getString("subjectname") + "</option>";

             } while (rs.next());
             output += "</select></td>";
             output += "<td>Marks*</td><td><input type=\"text\" name=\"txtMarks\" id=\"txtMarks\" value=\"\" size=\"3\"/>"
             + "<img src=\"../images/remove.png\" alt=\"Remove\" imgno="+count+" id=\"DelIcon\"/></td></tr>";
             } else {
             output = "DATABASE RECORD:Not Matching Record(s) Found";
             }*/

            while (rs.next()) {
                output += "<tr id=" + count + "><td>Subject *</td>";
                output += "<td>" + rs.getString("subjectname")+ "</td> <td><input type='text' name=\"txtSubject\" id=\"txtSubject\" value=\""+rs.getString("subjectname")+"\" hidden /></td>";
                output += "<td>Marks*</td><td><input type=\"text\" name=\"txtMarks\" id=\"txtMarks\" value=\"\" size=\"3\"/>"
                        + "<img src=\"../images/remove.png\" alt=\"Remove\" imgno=" + count + " id=\"DelIcon\"/></td></tr>";
                count = count + 1;
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
