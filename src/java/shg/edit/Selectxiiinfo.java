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
import java.sql.Statement;
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
public class Selectxiiinfo extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //System.out.println("Inseide server");
        Connection con = null;
        ServletContext context = null;
        ConnectionPool connectionPool = null;
        ResultSet rs = null, rs2;
        PreparedStatement pst = null;
        String sql = "", output = "",boardid = "";
        StringBuffer sb = new StringBuffer();
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        String srchby = request.getParameter("txtSearchBy");
        System.out.println("Search by:" + srchby);
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
            /*sql = "SELECT * from clxiisubj WHERE lower(subjectid) LIKE ?";
             sql2 ="SELECT COUNT(*) AS numrows from clxiisubj WHERE lower(subjectid) LIKE ?";
             pst = con.prepareStatement(sql2);
             pst.setString(1, "%" + subid + "%");
             rs2 = pst.executeQuery();
             rs2.next();
             int numrows = rs2.getInt("numrows");
             System.out.println("Subject ID:" +subid);
             pst = con.prepareStatement(sql);
             pst.setString(1, "%" + subid.toLowerCase() + "%");
             rs = pst.executeQuery();*/

            sql = "SELECT \n"
                    + "  clxii.boardroll, \n"
                    + "  clxii.degroll, \n"
                    + "  clxii.boardid, \n"
                    + "  clxii.yearpass, \n"
                    + "  clxii.div, \n"
                    + "  clxii.stream, \n"
                    + "  clxiistudsub.subject\n"
                    + "FROM \n"
                    + "  public.clxiistudsub, \n"
                    + "  public.clxii\n"
                    + "WHERE \n"
                    + "  clxii.boardroll = clxiistudsub.boardroll AND\n"
                    + "  clxii.degroll = clxiistudsub.degroll AND clxii.boardroll LIKE ? OR clxii.degroll LIKE ? ";
            pst = con.prepareStatement(sql);
            pst.setString(1, "%" + srchby + "%");
            pst.setString(2, "%" + srchby + "%");
            rs = pst.executeQuery();
            if (rs.next()) {
                output += "<table border=\"0\"";
                output += "<tr><td>BoardRoll</td><td><input type=\"text\" name=\"txtBoardRoll\" id=\"txtBoardRoll\" value=\"" + rs.getString("boardroll") + "\" size=\"50\" /></td></tr>";
                output += "<tr><td>Degree Roll</td><td><input type=\"text\" name=\"txtDegRoll\" id=\"txtDegRoll\" value=\"" + rs.getString("degroll") + "\" size=\"50\" /></td></tr>";
                output += "<tr><td>Board Name</td><td><select name=\"cmbBoardID\" id=\"cmbBoardID\">";
                output += getBoardName(rs.getString("boardid"));
                output += "</select></td></tr>";
                output += "<tr><td>Year Pass</td><td><input type=\"text\" name=\"txtYrPass\" id=\"txtYrPass\" value=\"" + rs.getString("yearpass") + "\" size=\"50\" /></td></tr>";
                output += "<tr><td>Division</td><td><select name=\"cmbDivision\" id=\"cmbDivision\">\n";
                String[] div = {"FIRST DIVISION", "SECOND DIVISION", "THIRD DIVISION"};
                output += "<option value=\"" + rs.getString("div") + "\">" + rs.getString("div") + "</option>";
                for (String item1 : div) {
                    if (!(rs.getString("div").equals(item1))) {
                        output += "<option value=\"" + item1 + "\">" + item1 + "</option>";
                    }
                }
                output+="</select></td></tr>";
                output += "<tr><td>Stream</td><td><select name=\"cmbStream\" id=\"cmbStream\">\n"
                        + "<option value=\"" + rs.getString("stream") + "\">" + rs.getString("stream") + "</option>";
                String[] stream={"SCIENCE","COMMERCE","ATRS"};
                for(String item1:stream){
                    if(!(rs.getString("stream").equals(item1))){
                        output+="<option value=\"" + item1 + "\">" + item1 + "</option>";
                    }
                }
                output+="</select></td></tr>";
                do {
                    output += "<tr><td>Subject *</td><td><input type=\"text\" name=\"txtSubject\" id=\"txtSubject\" value=\"" + rs.getString("subject") + "\" size=\"50\" /></td></tr>";
                } while (rs.next());
            } else {
                output = "Not Matching Record(s) Found";
            }
            output += "</table>";
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
                } catch(SQLException gc) {
                    System.out.println("Error in Garbage Collection :"+gc);

                }
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private String getBoardName(String boardid) {
        ServletContext context = getServletContext();
        Statement st = null;
        Connection con = null;
        String sql = "", opt = "";
        ResultSet rs = null;
        ConnectionPool connectionPool = null;
        try {
            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
            con = connectionPool.getConnection();

            sql = "SELECT * FROM boardname ";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                if (rs.getString("boardid").trim().equals(boardid)) {
                    opt += "<option value=\"" + rs.getString(2) + "\" selected> " + rs.getString(1) + "</option>";
                } else {
                    opt += "<option value=\"" + rs.getString(2) + "\"> " + rs.getString(1) + "</option>";
                }
                //recordCount = 1;
            }
        } catch (Exception e) {
            context.log(this.getClass() + "{populatePopup}  Error while trying to retreive the data  " + e);
            return null;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (Exception gc) {
                System.out.println("Error in garbage collection :" + this.getClass() + "/populatePopup()-method " + gc);
            }
        }

        return opt;
    }

}
