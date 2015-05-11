/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shg.dao;

import shg.bean.AddMarks;
import DBConnection.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletContext;

/**
 *
 * @author Shgcomp
 */
public class AddMarksDAO {

    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private String sql = "";
    private ConnectionPool connectionPool = null;
    private int affectedRows;
    private int itemscount;
    public int UpdateMarks(ServletContext context, AddMarks AM) {
         String papercodes[]=AM.getTxtPaperCode();
         String subjectcodes[]=AM.getTxtSubjectCode();
         String marksth[]=AM.getTxtMarksTh();
         String markspr[]=AM.getTxtMarksPr();
         System.out.println("hello");
         try {
            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
            con = connectionPool.getConnection();
        } catch (Exception e) {
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
            return -1;
        }
        try {
            itemscount=papercodes.length;
            System.out.println("count"+itemscount+",roll="+AM.getTxtNehuRollNo());
            con.setAutoCommit(false);
            sql="delete from papersappear where lower(examid)=? and lower(nehurollno)=? ";
            pst = con.prepareStatement(sql);
            pst.setString(1, AM.getTxtExamID().toLowerCase());
            pst.setString(2,AM.getTxtNehuRollNo().toLowerCase());
            
            affectedRows = pst.executeUpdate();
            System.out.println("aff rows"+affectedRows);
            for(int i=0;i<itemscount;i++)
            {
            sql = "INSERT INTO papersappear(examid,nehurollno,rollno,subjectcode,papercode,marksth,markspr) "
                    + "    VALUES (?, ?,?,?,?,?,?) ";

            pst = con.prepareStatement(sql);
            pst.setString(1, AM.getTxtExamID());
            pst.setString(2, AM.getTxtNehuRollNo());
            pst.setString(3, AM.getTxtRollNo());
            pst.setString(4, subjectcodes[i]);
            pst.setString(5, papercodes[i]);
            
            pst.setInt(6, Integer.parseInt(marksth[i]));
            pst.setInt(7, Integer.parseInt(markspr[i]));        
            affectedRows = pst.executeUpdate();

            if (affectedRows <= 0) {
                throw new SQLException("Insert failed.. ");
            }
          /*  if(AM.getCmbYearOrSemNo().trim().equals("s6") || AM.getCmbYearOrSemNo().trim().equals("y3"))
            {
            //insert into result
            }*/
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
