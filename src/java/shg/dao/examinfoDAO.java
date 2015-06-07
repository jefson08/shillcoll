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
import shg.bean.examinfo_Bean;
import shg.util.Utility;

/**
 *
 * @author Ransly
 */
public class examinfoDAO {

    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private String sql = "";
    private ConnectionPool connectionPool = null;
    private int affectedRows;

    public int insertExamInfo(ServletContext context, examinfo_Bean examinfo) {
        try {

            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
            con = connectionPool.getConnection();

        } catch (Exception e) {
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
            return -1;
        }

        try {
            con.setAutoCommit(false);
            //resetting values since declared as static
            examinfo.setExamId(null);
        

            String exid = generateExamid(examinfo.getRollno());

//            System.out.println("ExamID::" + examinfo.getExamId());
//            System.out.println("SLNO::" + examinfo.getSerial());
//            System.out.println("rollno::" + examinfo.getRollno());
//            System.out.println("nehuroll::" + examinfo.getTxtNehurollno());
//            System.out.println("regno::" + examinfo.getTxtRegno());
//            System.out.println("Category::" + examinfo.getNri());
//            System.out.println("semoryear::" + examinfo.getSeye());
//            System.out.println("pdate::" + examinfo.getTxtPmtDate());
//            System.out.println("batch::" + examinfo.getTxtBatch());
//            System.out.println("ExMonth::" + examinfo.getExmonth());
//            System.out.println("ExYear::" + examinfo.getExyear());

            sql = "INSERT INTO examinfo (examid,serial,rollno,nehurollno,regno,category,semoryear,dop,batch,pmtstatus,exammonth,examyear) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

            pst = con.prepareStatement(sql);

            pst.setString(1, examinfo.getExamId());
            pst.setInt(2, examinfo.getSerial());
            pst.setString(3, examinfo.getRollno());
            pst.setString(4, examinfo.getTxtNehurollno());
            pst.setString(5, examinfo.getTxtRegno());
            pst.setString(6, examinfo.getNri());
            pst.setString(7, examinfo.getSeye());
            pst.setDate(8, Date.valueOf(new Utility().formatStringAsDateForInsert(examinfo.getTxtPmtDate())));
            pst.setString(9, examinfo.getTxtBatch());
            pst.setBoolean(10, Boolean.valueOf(examinfo.getPmtstatus()));
            pst.setString(11, examinfo.getExmonth());
            pst.setString(12, examinfo.getExyear());

            affectedRows = pst.executeUpdate();
            if (affectedRows <= 0) {
                throw new SQLException("Student Exam Information  Update Failed! ");
            }
            con.commit();
            examinfo.setErrorssuccmsg("Message :: Exam Information Added Succesfully");

            //resetting values since declared as static
            examinfo.setExmonth("");
            examinfo.setExyear("");

        } catch (Exception e) {
            try {
                con.rollback();
            } catch (Exception ex) {
                System.out.println("RollBack operation error.");

            }
            examinfo.setErrorssuccmsg("Message :: Adding Exam Information Failed !");
            // System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
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

    public String generateExamid(String term) {
        String exid = "";
        PreparedStatement ps = null;
        String cdata = "";
        int slno = 0;
        examinfo_Bean obj = new examinfo_Bean();
        try {
            ps = con.prepareStatement("SELECT coursecode FROM studentdetails  WHERE lower(rollno)= ? ");
            ps.setString(1, term.toLowerCase());
            rs = ps.executeQuery();
            while (rs.next()) {
                cdata = rs.getString("coursecode");
            }
            slno = getMaxSerialNo(cdata.trim());

            //get previous examid and check for duplicate entry
            String peid = getPreviousExamId(term);
            exid = term + obj.getExmonth() + obj.getExyear() + obj.getSeye() + slno;

            if (peid.equalsIgnoreCase(exid)) {
                throw new SQLException("Duplicate Entry !!");
            }

            slno = slno + 1;
            exid = term + obj.getExmonth() + obj.getExyear() + obj.getSeye() + slno;

            //setting the bean value
            obj.setSerial(slno);
            obj.setExamId(exid.trim());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return exid.trim();
    }

    public int getMaxSerialNo(String term) {
        int sln = 0;
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement("SELECT MAX(serial) FROM examinfo WHERE lower(rollno) LIKE ? ");
            ps.setString(1, term.toLowerCase() + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                sln = rs.getInt("MAX");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return sln;
    }

    public String getPreviousExamId(String examid) {

        String exist = "";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("SELECT examid FROM examinfo WHERE lower(rollno)=? ");
            ps.setString(1, examid.toLowerCase());
            rs = ps.executeQuery();
            while (rs.next()) {
                exist = rs.getString("examid");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return exist;
    }
}
