/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shg.dao;

import DBConnection.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import shg.bean.PromoteBean;
import shg.util.Utility;

/**
 *
 * @author B Mukhim
 */
public class PromoteDAO {

    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private String sql = "";
    private ConnectionPool connectionPool = null;
    int slno;
    private int affectedRows;
    String sy="";

    public int promoteStudent(ServletContext context, PromoteBean p) {
        if(p.getSemoryear().toLowerCase().startsWith("s")){
            int n=Integer.parseInt(p.getSemoryear().substring(1));
            n +=1;
            sy="s"+n;
        }else{
            int n=Integer.parseInt(p.getSemoryear().substring(1));
            n +=1;
            sy="y"+n;
        }
        try {
            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
            con = connectionPool.getConnection();
        } catch (Exception e) {
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
            return -1;
        }
        try {
            con.setAutoCommit(false);
            sql="INSERT INTO studentsclass(rollno, coursecode, yearorsemno, enrollyear) VALUES (?, ?, ?, ?)";
            pst=con.prepareStatement(sql);
            pst.setString(1, p.getTxtPromote().trim());
            pst.setString(2, p.getCoursecode());
            pst.setString(3, sy);
            pst.setString(4, ""+Utility.currentYear());
            affectedRows = pst.executeUpdate();
            if (affectedRows <= 0) {
                System.out.println(" PromoteDAO has failed");
                throw new SQLException("Fail to promote Student ");
            }
            con.commit();
        } catch (Exception e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                System.out.println("RollBack operation error.");
            }
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
            return -1;
        } finally {
            pst = null;
            connectionPool.free(con);
            con = null;
            rs = null;
            pst = null;
            connectionPool = null;
        }
        return affectedRows;
    }
}
