/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shg.dao;

/**
 *
 * @author hp
 */
import DBConnection.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import shg.bean.StImprovement;

public class StImprovementDAO {

    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private String sql = "";
    private ConnectionPool connectionPool = null;
    int slno;
    private int affectedRows;

    public int insertStudentImprovement(ServletContext context, StImprovement stimprovemnt) {
        try {

            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
            con = connectionPool.getConnection();

        } catch (Exception e) {
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
            return -1;
        }

        try {
            con.setAutoCommit(false);

            sql = "INSERT INTO shgdb.improvement(nehurollno, papercode, improvementsem,imprcode) VALUES (?, ?, ?,?)";

            pst = con.prepareStatement(sql);

            String impid = genimpID(stimprovemnt.getCmbuniRollno(), stimprovemnt.gettxtpaperId(), stimprovemnt.getCmbsemImproveSem());

            if (impid == null) {
                throw new Exception("Error while generating Improvement ID");
            }
            pst.setString(1, stimprovemnt.getCmbuniRollno());
            pst.setString(2, stimprovemnt.gettxtpaperId());
            pst.setString(3, stimprovemnt.getCmbsemImproveSem());
            pst.setString(4, impid);

            affectedRows = pst.executeUpdate();
            if (affectedRows <= 0) {
                throw new SQLException("Student Improvement Update Failed! ");
            }
            con.commit();
            stimprovemnt.setErrorssuccmsg("Message :: Student Added Succesfully");
        } catch (Exception e) {
            try {
                con.rollback();
            } catch (Exception ex) {
                System.out.println("RollBack operation error.");

            }
            stimprovemnt.setErrorssuccmsg("Message :: Adding Student Fail Duplicate Entry !");
            // System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
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

    public String genimpID(String roll, String pid, String sem) {
        return ((roll + pid + sem).trim());
    }
}
