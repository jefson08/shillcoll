/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shg.dao;

import DBConnection.ConnectionPool;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import shg.bean.CourseClassHons;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;


import shg.util.Utility;

/**
 *
 * @author shillong
 */
public class CourseClassHonsEditDAO {

    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst;
    private String sql1 = "";
    private ConnectionPool connectionPool = null;
    // int slno;
    private int affectedRows;

    public int updateToAllPapers(ServletContext context, CourseClassHons CCH,StringBuilder ErrMsg){
  
        String PaperIds[] = CCH.getTxtPaperId();
        String PaperNames[] = CCH.getTxtPaperName();
        String YearOrSemNos[] = CCH.getCmbYearOrSemNo();
        boolean ChkCategories[] = CCH.getChkCategory();
        String CCode=CCH.getCmbCourseName();
        String SubjectId=CCH.getCmbSubjectName();
        boolean cat;
        int ItemsCount = CCH.getChkCategory().length;//PaperIds.length;
        System.out.println("itemscount=" + ItemsCount);
       // System.out.println("id=" + PaperIds[3]);
          for (int i = 0; i < ItemsCount - 1; i++) {
            for (int j = i + 1; j < ItemsCount; j++) {
                if (PaperIds[i].equals(PaperIds[j])) {
                    return 4;//duplicate paper id in textfields
                } else if (PaperNames[i].equals(PaperNames[j])) {
                    return 5;//duplicate paper name in textfields
                }
            }
        }
        try {
            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
            con = connectionPool.getConnection();
           sql1 = "SELECT papercode,papername from papers WHERE coursecode=? and subjectcode=?";

            pst = con.prepareStatement(sql1);
            pst.setString(1, CCode);
            pst.setString(2, SubjectId);

            rs = pst.executeQuery();

            while (rs.next()) {
                if (Arrays.asList(PaperIds).contains(rs.getString("papercode"))) {
                   ErrMsg.append("Paper Code:  " + rs.getString("papercode") + " already exists for this subject");
                   return 2;// 2 for duplicate paper code
                }
                if (Arrays.asList(PaperNames).contains(rs.getString("papername"))) {
                    System.out.println("Paper Name " + rs.getString("papername") + "already exists for this subject");
                    ErrMsg.append("Paper Name: " +rs.getString("papername") + "already exists for this subject");
                    return 3; // 3 for duplicate paper name
                }
            }
        } catch (Exception e) {
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
            return -1;
        }

        try {
            con.setAutoCommit(false);
            sql1 = "delete from papers where subjectcode=?";
            pst = con.prepareStatement(sql1);
            pst.setString(1, SubjectId);
            affectedRows = pst.executeUpdate();
            if (affectedRows <= 0) {

                //System.out.println("subid="+CCH.getCmbSubjectName());   
                throw new SQLException("Delete failed.. ");
            }
            for (int i = 0; i < ItemsCount; i++) {
                if (ChkCategories[i]) {
                    cat = true;
                } else {
                    cat = false;
                }

                sql1 = "INSERT INTO papers(papercode, papername, coursecode, subjectcode, honsorpass, yearorsemno) "
                        + "    VALUES (?, ?, ?, ?, ?, ? ) ";

                pst = con.prepareStatement(sql1);
                pst.setString(1, PaperIds[i]);
                pst.setString(2, PaperNames[i]);
                pst.setString(3, CCode);
                pst.setString(4, SubjectId);
                pst.setBoolean(5, cat);
                pst.setString(6, YearOrSemNos[i]);

                affectedRows = pst.executeUpdate();
                System.out.println("id=" + PaperIds[i] + " Nam=" + PaperNames[i] + " cat=" + cat + " yors=" + YearOrSemNos[i] + " Sid=" + SubjectId + " ccode=" + CCode);
                if (affectedRows <= 0) {

                    //System.out.println("subid="+CCH.getCmbSubjectName());   
                    throw new SQLException("Update failed.. ");
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
