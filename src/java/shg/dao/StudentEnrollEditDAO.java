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
import javax.servlet.ServletContext;
import shg.bean.StudentEnroll;
import shg.util.Utility;

/**
 *
 * @author B Mukhim
 */
public class StudentEnrollEditDAO {

    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private String sql = "";
    private ConnectionPool connectionPool = null;
    int slno;
    private int affectedRows;

    public int updateStudent(ServletContext context, StudentEnroll StuEnroll) {
        try {
            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
            con = connectionPool.getConnection();
        } catch (SQLException e) {
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
            return -1;
        }

        try {
            con.setAutoCommit(false);
            sql = "UPDATE studentdetails "
                    + "   SET coursecode=?, combinationcode=?, studentname=?, "
                    + "       dob=?, gender=?, category=?, fathersname=?, mothersname=?, "
                    + "       parentsphno=?, parentsoccupation=?, mailingaddress=?, peraddress=?, "
                    + "       mobileno=?, email=?, familyincome=?, nehurollno=?"
                    + " WHERE rollno=?";
            pst = con.prepareStatement(sql);
                         // generate rollno
            pst.setString(1, StuEnroll.getCmbCourseName());
            pst.setString(2, StuEnroll.getCmbCombination());
            pst.setString(3, StuEnroll.getTxtStuName());
            pst.setDate(4, Date.valueOf(new Utility().formatStringAsDateForInsert(StuEnroll.getTxtDOB())));
            pst.setString(5, StuEnroll.getRadGender());
           // pst.setString(6, StuEnroll.getTxtNationality());
            pst.setString(7, StuEnroll.getRadCategory());
            pst.setString(8, StuEnroll.getTxtFName());
            pst.setString(9, StuEnroll.getTxtMName());
            pst.setString(10, StuEnroll.getTxtPPhno());
            pst.setString(11, StuEnroll.getTxtPOccup());
            pst.setString(12, StuEnroll.getTxtMAddress().trim());
            pst.setString(13, StuEnroll.getTxtPAddress().trim());
            pst.setString(14, StuEnroll.getTxtMobile());
            pst.setString(15, StuEnroll.getTxtEmail());
            pst.setDouble(16, Double.parseDouble(StuEnroll.getTxtIncome()));
            pst.setString(17, StuEnroll.getTxtnehuRollno());
            pst.setString(18, StuEnroll.getRollno());
            
            affectedRows = pst.executeUpdate();
            if (affectedRows <= 0) {
                throw new SQLException("Student Enrollment Failed. ");
            }
            con.commit();
        } catch (Exception e) {
            try {
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
        return 1;
    }
}