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
    private int num=0;
    public void retrieveStudent(ServletContext context, StudentEnroll se, String rollno) {
        if (Validator.isNullValue(rollno)) {
            System.out.println("Rollno is Null at : " + this.getClass());
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
            //Allowing Edit for Annual 0r Semester only for 1st yr
            sql = "SELECT COUNT(*) as num from studentsclass WHERE rollno=?";
            pst = con.prepareStatement(sql);
            pst.setString(1, rollno.trim());
            rs = pst.executeQuery();
            
            if(rs.next()){
                num = rs.getInt(1);
            }
            
            sql = "SELECT sd.rollno, sd.section, sd.coursecode, sd.combinationcode, sd.enrollyear,  sd.studentname, sd.dob, sd.gender, sd.category, \n"
                    + "  sd.fathersname, sd.mothersname, sd.parentsphno, sd.parentsoccupation, sd.districtcode, sd.statecode, sd.coutrycode, sd.mailingaddress, \n"
                    + "  sd.peraddress, sd.mobileno, sd.email, sd.familyincome, sd.slno, sd.photo, scl.yearorsemno "
                    + " FROM studentdetails AS sd, studentsclass AS scl WHERE sd.rollno = scl.rollno AND sd.rollno=?";
            pst = con.prepareStatement(sql);
            pst.setString(1, rollno);
            rs = pst.executeQuery();

            if (rs.next()) {
                se.setRollno(rs.getString("rollno"));//rollno
                se.setCmbSection(rs.getString("section"));
                se.setCmbCourseName(rs.getString("coursecode"));
                se.setCmbCombination(rs.getString("combinationcode"));
                se.setEnrollYear(rs.getString("enrollyear"));
                se.setTxtStuName(rs.getString("studentname"));
                se.setTxtDOB(new shg.util.Utility().formatDateForDisplay(rs.getString("dob")));
                se.setRadGender(rs.getString("gender"));
                //se.setTxtNationality(rs.getString("nationality"));
                se.setRadCategory(rs.getString("category"));
                se.setTxtFName(rs.getString("fathersname"));
                se.setTxtMName(rs.getString("mothersname"));
                se.setTxtPPhno(rs.getString("parentsphno"));
                se.setTxtPOccup(rs.getString("parentsoccupation"));
                se.setCmbCountry(rs.getString("coutrycode"));
                se.setCmbState(rs.getString("statecode"));
                se.setCmbDistrict(rs.getString("districtcode"));
                se.setTxtMAddress(rs.getString("mailingaddress"));
                se.setTxtPAddress(rs.getString("peraddress"));
                se.setTxtMobile(rs.getString("mobileno"));
                se.setTxtEmail(rs.getString("email"));
                se.setTxtIncome(rs.getString("familyincome"));
                if(num > 1){ // IF TRUE indicates class not 1st year/semester
                    se.setRadYearOrSem(""+num);
                }
                else{
                    se.setRadYearOrSem(rs.getString("yearorsemno").endsWith("s") ? "s" : "y");
                }
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
