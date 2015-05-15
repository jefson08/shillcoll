/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shg.dao;

import DBConnection.ConnectionPool;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletContext;
import shg.bean.ModifiedCourseBean;
import shg.util.Utility;

/**
 *
 * @author B Mukhim
 */
public class ModifiedStudentCourse {

    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private String sql = "";
    private ConnectionPool connectionPool = null;
    int slno;
    private int affectedRows;

    public int insert(ServletContext context, ModifiedCourseBean course) {
        try {
            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
            con = connectionPool.getConnection();

        } catch (Exception e) {
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
            return -1;
        }

        try {
            con.setAutoCommit(false);
            String coursename = course.getTxtcoursename();
            String no = course.getTxtnoofseat();
            String stream = course.getStream();
            String hon = course.getCmbhon();

            String coursecode = coursename.substring(0, 2).toUpperCase() + "-" + hon;
            System.out.println("honours  " + coursecode);

            sql = "INSERT INTO course(coursecode, coursename, honsubcode, noofseat, stream )"
                    + "    VALUES (?, ?, ?, ?, ?)";

            pst = con.prepareStatement(sql);

            pst.setString(1, coursecode);
            pst.setString(2, coursename);
            pst.setString(3, hon);
            pst.setInt(4, Integer.parseInt(no));
            pst.setString(5, stream);
            affectedRows = pst.executeUpdate();

            if (affectedRows < 1) {
                course.setMsg("Message ::  Saving Course Failed! DUPLICATE RECORD");
                throw new SQLException("course  Failed. ");

            }
            course.setMsg(" Course Save Successfully.");
            course.setMsg2("GENERATED COURSECODE");
            course.setMsg1(coursecode.toUpperCase());

            con.commit();

        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                System.out.println("RollBack operation error.");
            }
            course.setMsg3("Message ::  Saving Course Failed! DUPLICATE RECORD");
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
        return 1;
    }

}
