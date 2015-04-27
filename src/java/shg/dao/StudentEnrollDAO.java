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
import shg.bean.ClXiiInfo;
import shg.bean.StudentEnroll;
import shg.util.Utility;

/**
 *
 * @author B Mukhim
 */
public class StudentEnrollDAO {

    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private String sql = "";
    private ConnectionPool connectionPool = null;
    int slno;
    private int affectedRows;

//    public int insertStudent(ServletContext context, StudentEnroll StuEnroll) {
    public int insertStudent(ServletContext context, StudentEnroll StuEnroll, ClXiiInfo boaSub) {
//        System.out.println("-----StudentEnroll----"+StuEnroll.getTxtStuName());
       try {
            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
            con = connectionPool.getConnection();
        } catch (Exception e) {
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
            return -1;
        }

        try {
            con.setAutoCommit(false);
            sql = "INSERT INTO studentdetails(rollno, coursecode, combinationcode, enrollyear, studentname, dob, "
                    + "            gender,  category, fathersname, mothersname, parentsphno, "
                    + "            parentsoccupation, mailingaddress, peraddress, mobileno, email, "
                    + "            familyincome, slno)"
                    + "    VALUES (?, ?, ?, ?, ?, "
                    + "            ?, ?, ?, ?, ?, ?, "
                    + "            ?, ?, ?, ?, ?, ?, ?)";
            pst = con.prepareStatement(sql);
            String rollno=getNextRollno(StuEnroll.getCmbCourseName());
            if(rollno==null){
                throw new Exception("Error while generating Rollno");
            }
            //System.out.println("roll "+rollno);
            pst.setString(1, rollno); // generate rollno
            pst.setString(2, StuEnroll.getCmbCourseName());
            pst.setString(3, StuEnroll.getCmbCombination());
            pst.setString(4, ""+Utility.currentYear()); // current year
            pst.setString(5, StuEnroll.getTxtStuName());
            pst.setDate(6, Date.valueOf(new Utility().formatStringAsDateForInsert(StuEnroll.getTxtDOB())));
            pst.setString(7, StuEnroll.getRadGender());
//            pst.setString(8, StuEnroll.getTxtNationality());
            pst.setString(8, StuEnroll.getRadCategory());
            pst.setString(9, StuEnroll.getTxtFName());
            pst.setString(10, StuEnroll.getTxtMName());
            pst.setString(11, StuEnroll.getTxtPPhno());
            pst.setString(12, StuEnroll.getTxtPOccup());
            pst.setString(13, StuEnroll.getTxtMAddress().trim());
            pst.setString(14, StuEnroll.getTxtPAddress().trim());
            pst.setString(15, StuEnroll.getTxtMobile());
            pst.setString(16, StuEnroll.getTxtEmail());
            pst.setDouble(17, Double.parseDouble(StuEnroll.getTxtIncome()));
            pst.setInt(18, slno);
            affectedRows = pst.executeUpdate();
            if (affectedRows <= 0) {
                throw new SQLException("Student Enrollment Failed. ");
            }
            
            
            affectedRows = new ClXiiInfoDAO().insertBoard(boaSub, rollno, con);
//            System.out.println("-----affecttt "+affectedRows);
            if (affectedRows <= 0 || affectedRows==2 || affectedRows==3) {
                throw new SQLException("Addition of Board Subjects Failed. ");
            }
//            System.out.println("b4 commit");
            con.commit();
            StuEnroll.setRollno(rollno);
        } catch (Exception e) {
            try {
//                System.out.println("Roll backkk");
                con.rollback();
                
            } catch (SQLException ex) {
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
        return affectedRows;
    }
    
    public String getNextRollno(String coursecode) {
        String rollno = "";
        Statement st = null;
        ResultSet rs = null;
        StringBuffer s1;
        String slno_str;
//        System.out.println("coursecodecoursecode  "+coursecode);
        try {
            String sql = "SELECT slno,coursecode FROM studentdetails WHERE coursecode='" + coursecode + "'"
                    + " AND enrollyear='" + Utility.currentYear() + "' ORDER BY slno DESC LIMIT 1";
            st = con.createStatement();
            rs = st.executeQuery(sql);
                     
            if(rs.next()){
                slno=rs.getInt("slno")+1;     
            } 
            else {
                slno=1;
            }
            s1=new StringBuffer("000");
            slno_str = Integer.toString(slno);
            s1.replace(3-slno_str.length(), 3, slno_str);
            rollno=coursecode.trim().toUpperCase()+Utility.currentYear()+s1;
//            System.out.println("rolll   "+rollno);
        } catch (Exception e) {
            System.out.println("Cannot Generate Rollno. "+e);
            rollno=null;
        } finally {
            rs = null;
            st = null;
        }
        return rollno;
    }
}
