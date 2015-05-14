/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shg.dao;

import DBConnection.ConnectionPool;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import javax.servlet.ServletContext;
import shg.bean.CourseClassHons;
import shg.util.Utility;

  /**
 *
 * @author shillong
 */
public class CourseClassHonsDAO {
 
    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private String sql = "";
    private ConnectionPool connectionPool = null;
    int slno;
    private int affectedRows;

    public int insertToAllPapers(ServletContext context, CourseClassHons CCH,StringBuilder ErrMsg) {
       
        //String CCode = CCH.getCmbCourseName();
        String SubjectId = CCH.getCmbSubjectName();
        String PaperIds[] = CCH.getTxtPaperId();
        String PaperNames[] = CCH.getTxtPaperName();
        String YearOrSemNos[] = CCH.getCmbYearOrSemNo();
        boolean ChkCategories[] = CCH.getChkCategory();
        boolean cat;
        int ItemsCount = PaperIds.length;
        //System.out.println(CCode+"  "+SubjectId); 
        for (int i = 0; i < ItemsCount - 1; i++) {
            for (int j = i + 1; j < ItemsCount; j++) {
                if (PaperIds[i].equals(PaperIds[j])) {
                    return 4;//duplicate paper id in textfields
                } else if (PaperNames[i].equals(PaperNames[j])) {
                    return 5;//duplicate paper name in textfields
                }
            }
        }
        System.out.println("itemscount" + ItemsCount);

        try {
            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
            con = connectionPool.getConnection();
            System.out.println("con=" + con);
        } catch (Exception e) {
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
            return -1;
        }

        try {
            con.setAutoCommit(false);
            sql = "SELECT papercode,papername from papers WHERE lower(subjectcode)=?";

            pst = con.prepareStatement(sql);
            //pst.setString(1, CCode.trim().toLowerCase());
            pst.setString(1, SubjectId.trim().toLowerCase());

            rs = pst.executeQuery();

            while (rs.next()) {
                if (Arrays.asList(PaperIds).contains(rs.getString("papercode"))) {
                    System.out.println("hello "+rs.getString("papercode"));
                    ErrMsg.append(rs.getString("papercode") + " already exists for this subject");
                    System.out.println("Paper ID " + rs.getString("papercode") + "already exists for this subject");
                    return 2;// 2 for duplicate paper code
                }
                else
                {
                 System.out.println("hello ");
                }
                if (Arrays.asList(PaperNames).contains(rs.getString("papername"))) {
                    ErrMsg.append(rs.getString("papername") + "already exists for this subject");
                    return 3; // 3 for duplicate paper name
                }
            }
            for (int i = 0; i < ItemsCount; i++) {
                System.out.println(i);
                if (ChkCategories[i] == true) {
                    cat = true;
                } else {
                    cat = false;
                }
                System.out.println("subid=" + CCH.getCmbSubjectName());

                sql = "INSERT INTO papers(papercode, papername, subjectcode, honsorpass, yearorsemno) "
                        + "    VALUES (?, ?, ?, ?, ?) ";

                pst = con.prepareStatement(sql);
                pst.setString(1, PaperIds[i]);
                pst.setString(2, PaperNames[i]);
               // pst.setString(3, CCH.getCmbCourseName());
                pst.setString(3, CCH.getCmbSubjectName());
                pst.setBoolean(4, cat);
                pst.setString(5, YearOrSemNos[i]);
                System.out.println("pst=" + pst);
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
