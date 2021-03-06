
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
import shg.bean.ClXiiInfo;
import shg.bean.StudentEnroll;

/**
 *
 * @author B Mukhim
 */
public class ClXiiInfoDAO {

//    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private String sql = "";
    private ConnectionPool connectionPool = null;
    private int affectedRows;
    private int msg = 0;
//    public int insertBoard(ServletContext context, ClXiiInfo boaSub, Connection con) throws SQLException

    public int insertBoard(ClXiiInfo boaSub, String rollno, Connection con) {

//        try {
//            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
//            con = connectionPool.getConnection();
//        } catch (Exception e) {
//            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
//            return -1;
//        }
        try {
            //System.out.println("DEg Roll" +C);
            sql = "select * from clxii WHERE boardroll = ? ORDER BY boardid ASC";
            pst = con.prepareStatement(sql);
            pst.setString(1, boaSub.getTxtBoardRoll());
            rs = pst.executeQuery();
            while (rs.next()) {
                String bName = rs.getString("boardroll");
                if (bName.equals(boaSub.getTxtBoardRoll())) {
                    msg = 2;
                    break;
                }
            }
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
            if (msg != 2 && msg != 3) {
//            con.setAutoCommit(false);
                sql = "INSERT INTO clxii(rollno, boardroll,boardid,yearpass,stream,totalmark)"
                        + "    VALUES (?, ?, ?, ?, ?,?)";
                pst = con.prepareStatement(sql);
                pst.setString(1, rollno);
                pst.setString(2, boaSub.getTxtBoardRoll());
                pst.setString(3, boaSub.getCmbBoardID().toUpperCase());
                pst.setInt(4, Integer.parseInt(boaSub.getTxtYrPass().toUpperCase()));
                pst.setString(5, boaSub.getCmbStream().toUpperCase());
                pst.setInt(6, Integer.parseInt(boaSub.getTxtTotalMarks().toUpperCase()));
                //rollno here
                msg = pst.executeUpdate();

                if (msg <= 0) {
                    throw new SQLException("Board Name Enrollment Failed. ");
                }

                String[] item = boaSub.getTxtSubject();
                String[] mark = boaSub.getTxtMarks();
                int count = 0;
                for (String item1 : item) {
                    //con.setAutoCommit(false);
                    sql = "INSERT INTO clxiistudsub(boardroll,subjectid,mark)"
                            + "    VALUES (?, ?, ?)";
                    pst = con.prepareStatement(sql);
                    pst.setString(1, boaSub.getTxtBoardRoll());
                    pst.setString(2, item1.toUpperCase());
                    pst.setInt(3, Integer.parseInt(mark[count++]));
                    affectedRows = pst.executeUpdate();
                    if (affectedRows <= 0) {
                        throw new SQLException("Adding of Board Subject Failed. ");
                    }
//                con.commit();
                }
            }
        } catch (SQLException e) {
//            try {
//                con.rollback();
//            } catch (Exception ex) {
//                System.out.println("RollBack operation error.");
//
//            }
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
            return -1;

        } catch (NumberFormatException e) {
            //            try {
//                con.rollback();
//            } catch (Exception ex) {
//                System.out.println("RollBack operation error.");
//
//            }
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
            return -1;

        }
//        finally {
//                 try {
//                    if (con != null) {
//                        connectionPool.free(con);
//                        con.close();
//                    }
//                    if (rs != null) {
//                        rs.close();
//                    }
//                    if (pst != null) {
//                        pst.close();
//                    }
//                } catch(SQLException gc) {
//                    System.out.println("Error in Garbage Collection :"+gc);
//
//                }
//        }

//        finally {
//                 try {
//                    if (con != null) {
//                        connectionPool.free(con);
//                        con.close();
//                    }
//                    if (rs != null) {
//                        rs.close();
//                    }
//                    if (pst != null) {
//                        pst.close();
//                    }
//                } catch(SQLException gc) {
//                    System.out.println("Error in Garbage Collection :"+gc);
//
//                }
//        }
        return msg;
    }

}
