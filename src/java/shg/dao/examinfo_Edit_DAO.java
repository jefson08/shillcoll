/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shg.dao;

import shg.bean.examinfo_Bean;
import DBConnection.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletContext;

/**
 *
 * @author Ransly
 */
public class examinfo_Edit_DAO {

    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private String sql = "";
    private ConnectionPool connectionPool = null;
    private int affectedRows;

    public int examinfoUpdate(ServletContext context, examinfo_Bean examinfo) {
        try {
            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
            con = connectionPool.getConnection();
        } catch (SQLException e) {
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
            return -1;
        }

        try {
            con.setAutoCommit(false);

            //examid serial rollno nehurollno regno category semoryear dop batch pmtstatus 
            sql = "UPDATE examinfo SET nehurollno = ?,regno = ? WHERE lower(rollno) = ? ";

            pst = con.prepareStatement(sql);

            pst.setString(1, examinfo.getTxtNehurollno());
            pst.setString(2, examinfo.getTxtRegno());
            pst.setString(3, examinfo.getRollno().toLowerCase());

            affectedRows = pst.executeUpdate();
            if (affectedRows <= 0) {
                throw new SQLException("Student Examination Information Update Failed!");
            }
            con.commit();
            examinfo.setErrorssuccmsg("Message :: Student Updated Succesfully");
        } catch (Exception e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                System.out.println("RollBack operation error.");

            }
            //System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
            examinfo.setErrorssuccmsg("Message :: Updating Student Failed ! Please try again !");
            return -1;
        } finally {
            pst = null;
            connectionPool.free(con);
            con = null;
            rs = null;
            pst = null;
            connectionPool = null;
        }
        return 1;
    }
}
