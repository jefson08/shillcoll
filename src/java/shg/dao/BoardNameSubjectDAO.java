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
import java.util.Date;
import javax.servlet.ServletContext;
import shg.bean.BoardNameSubject;

/**
 *
 * @author B Mukhim
 */
public class BoardNameSubjectDAO {

    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private String sql = "", Exist_board = "", boaId = "";
    private ConnectionPool connectionPool = null;
    private int affectedRows, exist;
    private Object response;

    public int insertBoard(ServletContext context, BoardNameSubject boaSub) {
        int len1;
        int msg = 1;//1 is succed 
        int len = boaSub.getTxtBoaName().trim().length();
        if (len < 3) {
            return 0;
        }
        String[] Subj = boaSub.getTxtSubName();
        for (String item1 : Subj) {
            len1 = item1.length();
            if (len1 < 3) {
                return 3;
            }
        }
        for (int i = 0; i < Subj.length; i++) {
            for (int j = i; j < Subj.length - 1; j++) {
                if (Subj[i].trim().toUpperCase().equals(Subj[j + 1].trim().toUpperCase())) {
                    return 5;
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
                sql = "select * from boardname";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                exist = 0;
                while (rs.next()) {
                    System.out.println("Boardname" + rs.getString("boardname"));
                    System.out.println("Boardid" + rs.getString("boardid"));
                    String bName = rs.getString("boardname");
                    if (bName.equals(boaSub.getTxtBoaName().trim().toUpperCase())) {
                        Exist_board = rs.getString("boardname");
                        msg = 2;
                        break;
                    }
                    String bid = rs.getString("boardid");
                    if (bid.equals(boaSub.getTxtBoaName().trim().toUpperCase())) {
                        Exist_board = rs.getString("boardid");
                        exist = 1; //no need to generate boardid
                        msg = 2;
                        break;
                    }
                }
                rs = null;
                if (msg != 2) {
                    con.setAutoCommit(false);
                    sql = "INSERT INTO boardname(boardname,boardid)"
                            + "    VALUES (?, ?)";
                    pst = con.prepareStatement(sql);
                    boaId = getBoardID(boaSub.getTxtBoaName());
                    if (boaId == null) {
                        throw new Exception("Error while generating BoardID");
                    }
                    pst.setString(1, boaSub.getTxtBoaName().toUpperCase());
                    pst.setString(2, boaId);
                    affectedRows = pst.executeUpdate();
                    if (affectedRows <= 0) {
                        throw new SQLException("Board Name Enrollment Failed. ");
                    }
                    con.commit();

                    String[] item = boaSub.getTxtSubName();
                    String SubjectId;
                    for (String item1 : item) {
                        con.setAutoCommit(false);
                        sql = "INSERT INTO clxiisubj(boardid,subjectname,subjectid,stream)"
                                + "    VALUES (?, ?, ?, ?)";
                        pst = con.prepareStatement(sql);
                        boaId = getBoardID(boaSub.getTxtBoaName());
                        SubjectId = getSubjectID(item1, boaId, boaSub.getTxtStream());
                        if (SubjectId == null) {
                            throw new Exception("Error while generating SubjectID");
                        }
                        pst.setString(1, boaId);
                        pst.setString(2, item1.toUpperCase());
                        pst.setString(3, SubjectId);
                        pst.setString(4, boaSub.getTxtStream().toUpperCase());
                        affectedRows = pst.executeUpdate();
                        if (affectedRows <= 0) {
                            throw new SQLException("Subject Enrollment Failed. ");
                        }
                        con.commit();
                    }
                } else {
                    if (exist == 0) {
                        Exist_board = getBoardID(boaSub.getTxtBoaName());
                    }
                    sql = "SELECT DISTINCT stream FROM clxiisubj WHERE boardid=?";
                    pst = con.prepareStatement(sql);
                    pst.setString(1, Exist_board);
                    rs = pst.executeQuery();
                    exist = 0;
                    while(rs.next()) {
                        if(rs.getString("stream").equals(boaSub.getTxtStream().toUpperCase())){
                        return 6;
                        }
                    }
                    String[] item = boaSub.getTxtSubName();
                    String SubjectId;
                    for (String item1 : item) {
                        con.setAutoCommit(false);
                        sql = "INSERT INTO clxiisubj(boardid,subjectname,subjectid,stream)"
                                + "    VALUES (?, ?, ?, ?)";
                        pst = con.prepareStatement(sql);
                        boaId = getBoardID(boaSub.getTxtBoaName());
                        SubjectId = getSubjectID(item1, boaId, boaSub.getTxtStream());
                        if (SubjectId == null) {
                            throw new Exception("Error while generating SubjectID");
                        }
                        pst.setString(1, Exist_board);
                        pst.setString(2, item1.toUpperCase());
                        pst.setString(3, SubjectId);
                        pst.setString(4, boaSub.getTxtStream().toUpperCase());
                        affectedRows = pst.executeUpdate();
                        if (affectedRows <= 0) {
                            throw new SQLException("Subject Enrollment Failed. ");
                        }
                        con.commit();
                        msg=1;//sucess
                    }
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
    }

    public String getSubjectID(String subjectname, String boardid, String Stream) {
        String Subid = "";
        try {
            Stream = Stream.substring(0, 2);
            subjectname = subjectname.substring(0, 4);
            Subid = subjectname.trim().toUpperCase().concat(Stream.trim().toUpperCase()).concat(boardid.trim().toUpperCase());
            if (Subid.length() > 10) {
                Subid = Subid.substring(0, 10);
            }
        } catch (Exception e) {
            System.out.println("Cannot Generate Subject ID. " + e);
            Subid = null;
        } finally {
        }
        return Subid;
    }

    public String getBoardID(String boaName) {
        String boaid = "";
        try {
            Date today = new Date();
            int hr = today.getHours();
            int min = today.getMinutes();
            int sec = today.getSeconds();
            String temp = "";
            temp = temp + Integer.toString(hr) + Integer.toString(min) + Integer.toString(sec);
            boaName = boaName.trim().toUpperCase();
            boaName = boaName.substring(0, 3);
            boaid = boaName + temp;
            if (boaid.length() > 10) {
                boaid = boaid.substring(0, 10);
            }
        } catch (Exception e) {
            System.out.println("Cannot Generate Board ID. " + e);
            boaid = null;
        } finally {

        }
        return boaid;
    }
}
