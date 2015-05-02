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
import java.util.Arrays;
import javax.servlet.ServletContext;
import shg.bean.AddSubHeads;

/**
 *
 * @author Shgcomp
 */
public class AddSubHeadsDAO {

    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private String sql = "";
    private ConnectionPool connectionPool = null;
    private int affectedRows, level, accheadcode;

    public int insertToexamfeeheads(ServletContext context, AddSubHeads ASH, StringBuilder ErrMsg) {
        String SubHeads[] = ASH.getTxtSubHead();
        int ItemsCount = SubHeads.length;
        for (int i = 0; i < ItemsCount - 1; i++) {
            for (int j = i + 1; j < ItemsCount; j++) {
                if (SubHeads[i].equals(SubHeads[j])) {
                    return 2;//duplicate sub heads in textfields
                }
            }
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
            sql = "SELECT acchead from examfeeheads";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                if (Arrays.asList(SubHeads).contains(rs.getString("acchead"))) {
                    System.out.println("hello " + rs.getString("acchead"));
                    ErrMsg.append(rs.getString("acchead") + " already exists");
                    System.out.println("Sub Head " + rs.getString("acchead") + "already exists");
                    return 2;// 2 for duplicate paper code
                } else {
                    System.out.println("hello ");
                }

            }
            for (int i = 0; i < ItemsCount; i++) {
                // System.out.println(i);
                sql = "select max(accheadcode) from examfeeheads";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    accheadcode = rs.getInt(1)+1;
                } else {
                    throw new SQLException("Insert failed.. ");
                }
                sql = "INSERT INTO examfeeheads(acchead, accheadcode, parentcode, level) "
                        + "    VALUES (?, ?, ?, ?) ";

                pst = con.prepareStatement(sql);
                pst.setString(1, SubHeads[i]);
                pst.setInt(2, accheadcode);
                pst.setInt(3, ASH.getCmbHead());
                level = ASH.getCmbHead() + 1;
                pst.setInt(4, level);
                affectedRows = pst.executeUpdate();
                if (affectedRows <= 0) {
                    throw new SQLException("Insert failed.. ");
                }
                con.commit();
            }
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
