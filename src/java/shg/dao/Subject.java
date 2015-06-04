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
import shg.bean.SubjectBean;
import shg.util.Utility;

/**
 *
 * @author B Mukhim
 */
public class Subject {

    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private String sql = "";
    private ConnectionPool connectionPool = null;
    int slno;
    private int affectedRows, affectedRows1;

    public int insert(ServletContext context, SubjectBean sub) {
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
            String code1 = sub.getTxtsubjectname().substring(0, 3);
            String code2 = code1.toUpperCase();
            String sql2 = "select * from subjects";
            pst = con.prepareStatement(sql2);
            rs = pst.executeQuery();
            String w = "";
            while (rs.next()) {
                String s = rs.getString(2);
                if (sub.getTxtsubjectname().equals(s)) {
                    sub.setMsg("Message ::  Saving Subject Failed: DUPLICATE ");

                    throw new SQLException("course  Failed. ");

                }

            }
            String sql3 = "select serial from subjects order by serial desc limit 1";
            pst = con.prepareStatement(sql3);
            rs = pst.executeQuery();
            int count = 0;
//            String x="";
            if (rs.next()) {

                count = Integer.parseInt(rs.getString(1));
                //if (count==0) {
                  //  count=0;
               // } else {
                    count++;
               // }//

            }else{
                count=1;
            }
            System.out.println("serial=" + count);

            //int a=100;
            sql = "INSERT INTO subjects(subjectcode, subjectname, streamcode, serial )"
                    + "    VALUES (?, ?, ?, ?)";

            pst = con.prepareStatement(sql);

            pst.setString(1, code2+""+count);
            pst.setString(2, sub.getTxtsubjectname());
            pst.setString(3, sub.getStream());
            pst.setInt(4, count);
            affectedRows = pst.executeUpdate();
            if (affectedRows <= 0) {
                sub.setMsg("Message ::  Saving Subject Failed: DUPLICATE ");

                throw new SQLException("SUBJECT  Failed. ");
            }
            sub.setMsg("Message ::  Saving Subject Succesfully!");

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
