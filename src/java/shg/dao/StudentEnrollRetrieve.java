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
import shg.bean.StudentEnroll;
import shg.valid.Validator;

/**
 *
 * @author B Mukhim
 */
public class StudentEnrollRetrieve {

    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private String sql = "";
    private ConnectionPool connectionPool = null;

    public void retrieveStudent(ServletContext context, StudentEnroll se, String rollno) {
        if (Validator.isNullValue(rollno)) {
            System.out.println("Rollno is Null at : "+this.getClass());
            return;
        }
        try {
            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
            con = connectionPool.getConnection();
        } catch (Exception e) {
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
            return;
        }

        try {
            con.setAutoCommit(false);
            sql = "SELECT * from studentdetails WHERE rollno=?";
            pst = con.prepareStatement(sql);
            pst.setString(1, rollno);
            rs = pst.executeQuery();

            if (rs.next()) {
                se.setRollno(rs.getString("rollno"));//rollno
                se.setCmbCourseName(rs.getString("coursecode"));
                se.setCmbCombination(rs.getString("combinationcode"));
                se.setEnrollYear(rs.getString("enrollyear"));
                se.setTxtStuName(rs.getString("studentname"));                
                se.setTxtDOB(new shg.util.Utility().formatDateForDisplay(rs.getString("dob")));
                se.setRadGender(rs.getString("gender"));
                se.setTxtNationality(rs.getString("nationality"));
                se.setRadCategory(rs.getString("category"));
                se.setTxtFName(rs.getString("fathersname"));
                se.setTxtMName(rs.getString("mothersname"));
                se.setTxtPPhno(rs.getString("parentsphno"));
                se.setTxtPOccup(rs.getString("parentsoccupation"));
                se.setTxtMAddress(rs.getString("mailingaddress"));
                se.setTxtPAddress(rs.getString("peraddress"));
                se.setTxtEmail(rs.getString("mobileno"));
                se.setTxtEmail(rs.getString("email"));
                se.setTxtIncome(rs.getString("familyincome"));
            }
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                System.out.println("RollBack operation error.");

            }
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
        } finally {
            pst = null;
            connectionPool.free(con);
            con = null;
            rs = null;
            pst = null;
            connectionPool = null;
        }
    }
}
