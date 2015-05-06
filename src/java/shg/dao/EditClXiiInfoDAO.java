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
import shg.bean.EditClXiiInfo;

/**
 *
 * @author B Mukhim
 */
public class EditClXiiInfoDAO {

    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private String sql = "";
    private ConnectionPool connectionPool = null;
    private int affectedRows;
    private int msg = 1;

    public int insertBoard(ServletContext context, EditClXiiInfo boaSub) {
        try {
            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
            con = connectionPool.getConnection();
        } catch (Exception e) {
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
            return -1;
        }

        try {
            con.setAutoCommit(false);
            String[] Subject = boaSub.getTxtSubject();
            int len = Subject.length;
            for (int i = 0; i < len; i++) {
                for (int j = i; j < len - 1; j++) {
                    if (Subject[i].trim().toUpperCase().equals(Subject[j + 1].trim().toUpperCase())) {
                        msg = 3;
                        break;
                    }
                }
                if (msg == 3) {
                    break;
                }
            }
            if (msg != 3) {
                String Temp_roll = null;
                sql = "SELECT boardroll FROM clxii WHERE rollno = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, boaSub.getRollno());
                rs = pst.executeQuery();
                if (rs.next()) {
                    Temp_roll = rs.getString("boardroll");
                } else {
                   // System.out.println("Not Matching Record(s) Found");
                }
                sql = "DELETE FROM clxii WHERE (boardroll = ? OR rollno= ?)";
                pst = con.prepareStatement(sql);
                pst.setString(1, boaSub.getTxtBoardRoll());
                pst.setString(2, boaSub.getRollno());
                affectedRows = pst.executeUpdate();
                if (affectedRows <= 0) {
                    throw new SQLException("1 . Board Name Enrollment Failed. ");
                }

                sql = "INSERT INTO clxii(rollno,boardroll,boardid,yearpass,stream,totalmark)"
                        + "    VALUES (?, ?, ?, ?, ?,?)";
                pst = con.prepareStatement(sql);
                pst.setString(1, boaSub.getRollno());
                pst.setString(2, boaSub.getTxtBoardRoll());
                pst.setString(3, boaSub.getCmbBoardID().toUpperCase());
                pst.setInt(4, Integer.parseInt(boaSub.getTxtYrPass().toUpperCase()));
                pst.setString(5, boaSub.getCmbStream().toUpperCase());
                pst.setInt(6, Integer.parseInt(boaSub.getTxtTotalMarks().toUpperCase()));
                affectedRows = pst.executeUpdate();
                if (affectedRows <= 0) {
                    throw new SQLException("2. Board Name Enrollment Failed. ");
                }
                //con.commit();

                sql = "DELETE FROM clxiistudsub WHERE boardroll LIKE ?";
                pst = con.prepareStatement(sql);
                pst.setString(1,Temp_roll);
                affectedRows = pst.executeUpdate();
//                if (affectedRows > 0) {
//                    System.out.println("A user was deleted successfully!");
//                }
                if (affectedRows <= 0) {
                    throw new SQLException("3. Editing Board Details Failed. ");
                }
                String[] item = boaSub.getTxtSubject();
                String[] mark = boaSub.getTxtMarks();
                int count = 0;
                for (String item1 : item) {
                    con.setAutoCommit(false);
                    sql = "INSERT INTO clxiistudsub(boardroll,subjectid,mark)"
                            + "    VALUES (?, ?, ?)";
                    pst = con.prepareStatement(sql);
                    pst.setString(1, boaSub.getTxtBoardRoll());
                    pst.setString(2, item1.toUpperCase());
                    pst.setInt(3, Integer.parseInt(mark[count++]));
                    affectedRows = pst.executeUpdate();
//                    System.out.println(pst);
                    if (affectedRows <= 0) {
                        throw new SQLException("4. Editing Board Details Failed. ");
                    }
                    con.commit();
                }
            }
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                System.out.println("RollBack operation error.");

            }
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
            return -1;
        } catch (NumberFormatException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                System.out.println("RollBack operation error.");

            }
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
            return -1;
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

        return msg;
    }

}
