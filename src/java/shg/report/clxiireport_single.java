/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shg.report;

import DBConnection.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import shg.bean.searchPrevXii;

/**
 *
 * @author B Mukhim
 */
@WebServlet(name = "clxiireport_single", urlPatterns = {"/clxiireport_single"})
public class clxiireport_single {

    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private String sql = "", Exist_board = "", boaId = "";
    private ConnectionPool connectionPool = null;
    private int affectedRows, exist;
    private Object response;
    private List<String> arrList = new ArrayList<String>();

    public List<String> search(ServletContext context, searchPrevXii srch) {
        try {
            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
            con = connectionPool.getConnection();
        } catch (Exception e) {
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
            //return -1;
        }

        try {
            con.setAutoCommit(false);
            String sql2 = "SELECT \n"
                    + "  sd.rollno, \n"
                    + "  sd.studentname\n"
                    + "FROM \n"
                    + "  studentdetails as sd WHERE sd.rollno=?";
            pst = con.prepareStatement(sql2);
            pst.setString(1, srch.getRollno());
            rs = pst.executeQuery();
            if (rs.next()) {
//                System.out.println(rs.getString("rollno"));
//                System.out.println(rs.getString("studentname"));
                arrList.add(rs.getString("rollno"));
                arrList.add(rs.getString("studentname"));
            } else {
                arrList.add("Record Does't Exist");
                throw new SQLException("Roll no does not exist. ");
            }
            con.commit();
        } catch (Exception e) {
            try {
                con.rollback();
            } catch (Exception ex) {
                System.out.println("RollBack operation error.");

            }
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
            //return -1;
        } finally {
            pst = null;
            connectionPool.free(con);
            con = null;
            rs = null;
            pst = null;
            connectionPool = null;
        }
        return arrList;
    }
}
