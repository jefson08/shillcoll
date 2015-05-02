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
import shg.bean.AddSubHeadsAmt;

/**
 *
 * @author Shgcomp
 */
public class AddSubHeadsAmtDAO {

    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private String sql = "";
    private ConnectionPool connectionPool = null;
    private int affectedRows;

    public int insertSubHeadsAmt(ServletContext context, AddSubHeadsAmt ASHA, StringBuilder ErrMsg) {
        try {
            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
            con = connectionPool.getConnection();
        } catch (Exception e) {
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
            return -1;
        }
        try {
            con.setAutoCommit(false);
            sql="Delete from examsubheadsamt where accheadcode=?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, ASHA.getCmbHead());
            affectedRows = pst.executeUpdate();

            sql = "INSERT INTO examsubheadsamt(accheadcode, amount) "
                    + "    VALUES (?, ?) ";

            pst = con.prepareStatement(sql);
            pst.setInt(1, ASHA.getCmbHead());
            pst.setFloat(2, Float.parseFloat(ASHA.getTxtAmount()));
            affectedRows = pst.executeUpdate();

            if (affectedRows <= 0) {
                throw new SQLException("Insert failed.. ");
            }
            con.commit();

        } catch (Exception e) {
            try {
                con.rollback();
            } catch (Exception ex) {
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
        return 1;
    }
}
