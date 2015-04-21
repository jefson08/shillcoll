/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shg.dao;

import shg.bean.StImprovement;
import DBConnection.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletContext;

/**
 *
 * @author hp
 */
public class StImprovementEditDAO {

    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private String sql = "";
    private ConnectionPool connectionPool = null;
    private int affectedRows;

    public int updateStudent(ServletContext context, StImprovement stuImprove) {
        try {
            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
            con = connectionPool.getConnection();
        } catch (SQLException e) {
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
            return -1;
        }

        try {
            con.setAutoCommit(false);

            sql = "UPDATE improvement SET papercode = ?,imprcode = ? WHERE lower(imprcode) = ? ";


            pst = con.prepareStatement(sql);

            String impid = genimpID(stuImprove.getCmbuniRollno(), stuImprove.gettxtpaperId(), stuImprove.getTxtSemEdit());

            if (impid == null) {
                throw new Exception("Error while generating Improvement ID");
            }

            String previousCode = getPreviousImpCode(stuImprove.getCmbuniRollno(), stuImprove.getCmbpaperName());

            if (previousCode == null) {
                throw new Exception("Error while finding previous Improvement ID");
            }

            pst.setString(1, stuImprove.gettxtpaperId());
            pst.setString(2, impid);
            pst.setString(3, previousCode.toLowerCase());

            affectedRows = pst.executeUpdate();
//            System.out.println(pst);
            if (affectedRows <= 0) {
                throw new SQLException("Student Improvement Update Failed. ");
            }
            con.commit();
            stuImprove.setErrorssuccmsg("Message :: Student Updated Succesfully");
        } catch (Exception e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                System.out.println("RollBack operation error.");

            }
            //System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
           stuImprove.setErrorssuccmsg("Message :: Updating Student Fail ! Please try again !");
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

    public String getPreviousImpCode(String unirollno, String pname) {
        String icode = "";
        Statement st = null;

        try {
            sql = "SELECT A.papercode,I.improvementsem FROM shgdb.papers A,shgdb.improvement I"
                    + " WHERE A.papercode=I.papercode "
                    + " AND lower(I.nehurollno) = '" + unirollno.toLowerCase() + "' AND lower(A.papercode)= '" + pname.toLowerCase() + "'";


            st = con.createStatement();
            rs = st.executeQuery(sql);
            //System.out.println(" --  --  "+sql);
            if (rs.next()) {
                icode = unirollno + rs.getString(1) + rs.getString(2).trim();
            } else {
            }

        } catch (Exception e) {
            System.out.println("Cannot Retrieve Code " + e);
            icode = null;
        } finally {
            rs = null;
            st = null;
        }
//        System.out.println("icode="+icode);
        return icode;
    }
}
