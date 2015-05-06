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
import shg.bean.EditClXiiInfo;
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
    private int num;

    public int updateStudent(ServletContext context, StudentEnroll StuEnroll, EditClXiiInfo clxiiinfo) {
        try {
            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
            con = connectionPool.getConnection();
        } catch (SQLException e) {
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
            return -1;
        }

        try {
            con.setAutoCommit(false);
            sql = "SELECT COUNT(*) as num from studentsclass WHERE rollno=?";
            pst = con.prepareStatement(sql);
            pst.setString(1, StuEnroll.getRollno().trim());
            rs = pst.executeQuery();

            if (rs.next()) {
                num = rs.getInt(1);
            }

            sql = "UPDATE studentdetails SET section=?, coursecode=?, combinationcode=?, studentname=?, dob=?, gender=?,"
                    + " category=?, fathersname=?, "
                    + " mothersname=?,parentsphno=?, parentsoccupation=?, districtcode=?, statecode=?, coutrycode=?, "
                    + " mailingaddress=?, peraddress=?, mobileno=?, "
                    + " email=?, familyincome=? WHERE rollno=?";
            pst = con.prepareStatement(sql);
            // generate rollno
            pst.setString(1, StuEnroll.getCmbSection().trim());
            pst.setString(2, StuEnroll.getCmbCourseName().trim());
            pst.setString(3, StuEnroll.getCmbCombination().trim());

            pst.setString(4, StuEnroll.getTxtStuName().trim());
            pst.setDate(5, Date.valueOf(new Utility().formatStringAsDateForInsert(StuEnroll.getTxtDOB())));
            pst.setString(6, StuEnroll.getRadGender().trim());
//            pst.setString(8, StuEnroll.getTxtNationality());
            pst.setString(7, StuEnroll.getRadCategory().trim());
            pst.setString(8, StuEnroll.getTxtFName().trim());
            pst.setString(9, StuEnroll.getTxtMName().trim());
            pst.setString(10, StuEnroll.getTxtPPhno().trim());
            pst.setString(11, StuEnroll.getTxtPOccup().trim());
            pst.setString(12, StuEnroll.getCmbDistrict().trim());
            pst.setString(13, StuEnroll.getCmbState().trim());
            pst.setString(14, StuEnroll.getCmbCountry().trim());
            pst.setString(15, StuEnroll.getTxtMAddress().trim());
            pst.setString(16, StuEnroll.getTxtPAddress().trim());
            pst.setString(17, StuEnroll.getTxtMobile().trim());
            pst.setString(18, StuEnroll.getTxtEmail().trim());
            pst.setDouble(19, Double.parseDouble(StuEnroll.getTxtIncome()));
            pst.setString(20, StuEnroll.getRollno().trim());
            affectedRows = pst.executeUpdate();
            if (affectedRows <= 0) {
                throw new SQLException("Student Enrollment Failed. ");
            }
            if (num <= 1) {
                sql = "UPDATE studentsclass SET coursecode=?, yearorsemno=? WHERE rollno=?";
                pst = null;
                pst = con.prepareStatement(sql);
                pst.setString(1, StuEnroll.getCmbCourseName().trim());
                pst.setString(2, StuEnroll.getRadYearOrSem().trim().toLowerCase().equals("s") ? "1s" : "1y");
                pst.setString(3, StuEnroll.getRollno().trim());
                affectedRows = pst.executeUpdate();
//            System.out.println(pst);
                if (affectedRows <= 0) {
                    throw new SQLException("2--Student Enrollment Failed. ");
                }
            }
            affectedRows = new EditClXiiInfoDAO().insertBoard(context, clxiiinfo);
            if (affectedRows <= 0) {
                throw new SQLException("2--Student Enrollment Failed. ");
            }
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                System.out.println("RollBack operation error.");

            }
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
            return -1;
        } catch (NumberFormatException e) {
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
