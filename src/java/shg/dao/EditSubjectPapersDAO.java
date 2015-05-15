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
import shg.bean.AddSubjectPapers;

/**
 *
 * @author Shgcomp
 */
public class EditSubjectPapersDAO {
    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */

    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst;
    private String sql1 = "";
    private ConnectionPool connectionPool = null;
    // int slno;
    private int affectedRows;

    public int updateToAllPapers(ServletContext context, AddSubjectPapers ASP) {

        String PaperIds[] = ASP.getTxtPaperId();
        String PaperNames[] = ASP.getTxtPaperName();
        String YearOrSemNos[] = ASP.getCmbYearOrSemNo();
        boolean ChkCategories[] = ASP.getChkCategory();
        boolean ChkPracts[] = ASP.getChkPract();
        //String CCode=ASP.getCmbCourseName();
        String SubjectId = ASP.getCmbSubjectName();
        boolean cat, pract;
        int ItemsCount = ASP.getChkCategory().length;//PaperIds.length;
        System.out.println("itemscount=" + ItemsCount);
        // System.out.println("id=" + PaperIds[3]);
        for (int i = 0; i < ItemsCount - 1; i++) {
            for (int j = i + 1; j < ItemsCount; j++) {
                if (PaperIds[i].equals(PaperIds[j])) {
                    return 2;//duplicate paper id in textfields
                } else if (PaperNames[i].equals(PaperNames[j])) {
                    return 3;//duplicate paper name in textfields
                }
            }
        }
        try {
            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
            con = connectionPool.getConnection();
            sql1 = "SELECT papercode,papername from papers WHERE lower(subjectcode)=?";

            pst = con.prepareStatement(sql1);
            //pst.setString(1, CCode.trim().toLowerCase());
            pst.setString(1, SubjectId.trim().toLowerCase());

            rs = pst.executeQuery();

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

                //System.out.println("subid="+ASP.getCmbSubjectName());   
                throw new SQLException("Delete failed.. ");
            }
            for (int i = 0; i < ItemsCount; i++) {
                if (ChkCategories[i]) {
                    cat = true;
                } else {
                    cat = false;
                }
                if (ChkPracts[i]) {
                    pract = true;
                } else {
                    pract = false;
                }
                sql1 = "INSERT INTO papers(papercode, papername,practical, subjectcode, honsorpass, yearorsemno) "
                        + "    VALUES (?, ?, ?, ?, ?, ? ) ";

                pst = con.prepareStatement(sql1);
                pst.setString(1, PaperIds[i]);
                pst.setString(2, PaperNames[i]);
                pst.setBoolean(3,pract);
                pst.setString(4, SubjectId);
                pst.setBoolean(5, cat);
                pst.setString(6, YearOrSemNos[i]);

                affectedRows = pst.executeUpdate();
                System.out.println("id=" + PaperIds[i] + " Nam=" + PaperNames[i] + " cat=" + cat + " yors=" + YearOrSemNos[i] + " Sid=" + SubjectId );
                if (affectedRows <= 0) {

                    //System.out.println("subid="+ASP.getCmbSubjectName());   
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
