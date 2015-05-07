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
import shg.bean.BoardNameSubject;

/**
 *
 * @author B Mukhim
 */
public class editBoardNameSubjectDAO {

    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private String sql = "";
    private ConnectionPool connectionPool = null;
    private int affectedRows;
   
    public int insertBoard(ServletContext context, BoardNameSubject boaSub) {
        // System.out.println("Inseide");
        int len1;
        int check = 1;
        int msg = 1;//1 is succed
        String[] Subj = boaSub.getTxtSubName();
        for (String item1 : Subj) {
            len1 = item1.length();
            if (len1 <= 4) {
                check = 0;
                msg = 0;
                break;
            }
        }
        for (int i = 0; i < Subj.length; i++) {
            for (int j = i; j < Subj.length - 1; j++) {
                if (Subj[i].trim().toUpperCase().equals(Subj[j + 1].trim().toUpperCase())) {
                    msg = 5;
                    System.out.println("Subject Repeat" + msg);
                    break;
                }
            }
            if (msg == 5) {
                break;
            }
        }
        if (check != 0 && msg != 5) {
            try {
                connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
                con = connectionPool.getConnection();
            } catch (Exception e) {
                System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
                return -1;
            }

            try {
                String[] item = boaSub.getTxtSubName();
                String SubjectId;
                con.setAutoCommit(false);
                sql = "DELETE FROM clxiisubj WHERE boardid=?";
                pst = con.prepareStatement(sql);
                pst.setString(1, boaSub.getCmbBoardID());
                affectedRows = pst.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("Subject of boardid "+boaSub.getCmbBoardID()+ " was deleted successfully!");
                }
                for (String item1 : item) {
                    con.setAutoCommit(false);
                    sql = "INSERT INTO clxiisubj(boardid,subjectname,subjectid,stream)"
                            + "    VALUES (?, ?, ?, ?)";
                    pst = con.prepareStatement(sql);
                    SubjectId = getSubjectID(item1, boaSub.getCmbBoardID(), boaSub.getTxtStream());
                    //System.out.println("Subject" + item1);
                    if (SubjectId == null) {
                        throw new Exception("Error while generating SubjectID");
                    }
                    pst.setString(1, boaSub.getCmbBoardID());
                    pst.setString(2, item1.toUpperCase());
                    pst.setString(3, SubjectId);
                    pst.setString(4, boaSub.getTxtStream().toUpperCase());
                    affectedRows = pst.executeUpdate();
                    if (affectedRows <= 0) {
                        throw new SQLException("Subject Enrollment Failed. ");
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
        } else {
            System.out.println("Msg" + msg);
            return msg;
        }
    }

    public String getSubjectID(String subjectname, String boardid, String Stream) {
        String Subid = "";
        try {
            Stream = Stream.substring(0, 2);
            subjectname = subjectname.substring(0, 5);
            boardid = boardid.substring(0, 3);
            Subid = subjectname.trim().toUpperCase().concat(boardid.trim().toUpperCase()).concat(Stream.trim().toUpperCase());
        } catch (Exception e) {
            System.out.println("Cannot Generate Subject ID. " + e);
            Subid = null;
        } finally {
        }
        return Subid;
    }
}
