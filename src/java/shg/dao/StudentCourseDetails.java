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
import javax.servlet.ServletContext;
import shg.bean.courseDetailsBean;
import shg.util.Utility;

/**
 *
 * @author B Mukhim
 */
public class StudentCourseDetails {

    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private String sql = "";
    private ConnectionPool connectionPool = null;
    int slno;
    private int affectedRows,affectedRows1;

    public int insert(ServletContext context, courseDetailsBean course) {
       try {
            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
            con = connectionPool.getConnection();
            System.out.println("something");
        } catch (Exception e) {
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
            return -1;
        }

        try {
            con.setAutoCommit(false);
            String sql2="select * from course";
            pst = con.prepareStatement(sql2);
            rs=pst.executeQuery();
           String w="";
            while(rs.next())
            {
                String s=rs.getString(2);
                if(course.getTxtcoursecode().equals(s))
                {
                    w=rs.getString(1);
                    break;
                }
             }
            
            sql = "INSERT INTO coursedetails(coursecode, combination, effectiveyear,status )"
                    + "    VALUES (?, ?, ?,?)";
           
           // String cno="peter",cname="peter",no="7";
           //String sql1 = "INSERT INTO shgoff.course(coursecode, coursename, noofseats)"
           //         + "   values(?, ? ,?)";
             pst = con.prepareStatement(sql);
            //String coursecode=getNextRollno(StuEnroll.getCmbCourseName());
           
            String q= w+""+course.gettxtcombination();
            pst.setString(1,w); 
            pst.setString(2,q);
            pst.setString(3, course.gettxteffectiveyear());
            pst.setString(4, course.getRadStatus());
            
           //  pst.setString(1, "1");
          //  pst.setString(2, "1");
           // pst.setString(3, "1");
            affectedRows = pst.executeUpdate();
            if (affectedRows <= 0) {
                throw new SQLException("course  Failed. ");
            }
            
            String sql1 = "INSERT INTO combination(combination, combinationname )"
                    + "    VALUES (?, ?)";
            String s1=w+"-"+course.gettxtcombination();
            pst=con.prepareStatement(sql1);
            String z=w+"-"+  course.gettxtcombination();
            pst.setString(1, z);
            pst.setString(2, s1);
            affectedRows1 = pst.executeUpdate();
            if (affectedRows <= 0) {
                throw new SQLException("course  Failed. ");
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
