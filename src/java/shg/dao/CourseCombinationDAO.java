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
import java.sql.Statement;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletContext;
import shg.bean.courseCombination;
import shg.util.DatabaseUtility;

/**
 *
 * @author B Mukhim
 */
public class CourseCombinationDAO {

    private Connection con = null;
    //private ResultSet rs = null;
    private PreparedStatement pst = null;
    private String sql = "";
    private ConnectionPool connectionPool = null;
    String combine = "", combine1 = "", subje = "";

    String sub1 = "", sub[];

    String subp = "";
    private int affectedRows;

    public int insertCourseCombination(ServletContext context, courseCombination ccomb) {
        try {
            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
            con = connectionPool.getConnection();
        } catch (SQLException e) {
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
            return -1;
        }

//        System.out.println(pstr);
        try {
            // Statement s1 = con.prepareStatement(sql);
            con.setAutoCommit(false);

            sub = ccomb.getSubjectcode();
            for (int i = 0; i < sub.length; i++) {
                // System.out.println(sub[i]);
                combine = combine + (sub[i].substring(0, 1));
                combine1 = "" + sub[i] + combine1;

                Statement s = con.createStatement();
                ResultSet r = s.executeQuery("select * from subjects");
                while (r.next()) {

                    if (r.getString(1).equals(sub[i])) {
                        // System.out.println(sub1);
                        subp = r.getString(2) + "/";
                        // String subp=sub1;
                        break;
                    }

                }
                sub1 = sub1 + "" + subp;
                //System.out.print(sub1);
            }
            String combine2 = combine;
            String sub2 = sub1;

            sql = "INSERT INTO coursedetails(coursecode, combinationcode, effectiveyear, status)"
                    + "    VALUES (?,?,?,?)";
            pst = con.prepareStatement(sql);

            pst.setString(1, ccomb.getCoursecode().toUpperCase());
            pst.setString(2, ccomb.getCoursecode() + "-" + combine.toUpperCase());
            pst.setString(3, ccomb.getTxteffectiveyear());
            pst.setBoolean(4, ccomb.getTxtstatus());
//            System.out.println(pst);
           
            affectedRows=pst.executeUpdate();

            String sql1 = "INSERT INTO combination(combinationcode, combinationname )"
                    + "    VALUES (?, ?)";
            pst = con.prepareStatement(sql1);
            pst.setString(1, ccomb.getCoursecode() + "-" + combine2);
            pst.setString(2, ccomb.getCoursecode().toUpperCase() + "-" + sub2.toUpperCase());
            affectedRows=pst.executeUpdate();

            for(int i = 0; i < sub.length; i++) {
                // System.out.println(sub[i]);
                combine = (sub[i].substring(0, 1)) + combine;
                combine1 = "" + sub[i] + combine1;

                String sqlsubcomb = "INSERT INTO comsubject(combcode,subcode)"
                        + "    VALUES (?, ?)";
                pst = con.prepareStatement(sqlsubcomb);
                pst.setString(1, (ccomb.getCoursecode() + "-" + combine2).toUpperCase());
                pst.setString(2, sub[i]);
                affectedRows=pst.executeUpdate();
            }
            //  int affectedRows2=

//            ResultSet rs1 = getSubjectPaperMapping(ccomb.getPapercode(), con);
            Map hp = new DatabaseUtility().getSubjectPaperMapping(ccomb.getPapercode(), context);
            
            Iterator itr=hp.entrySet().iterator();
            while (itr.hasNext()){
                Map.Entry <String,String> t= (Map.Entry)itr.next();
                String papercode=t.getKey();
                String subcode=t.getValue();
//                System.out.println(t.getKey() +" ---- "+t.getValue());
                String sql = "INSERT INTO combpapers(combcode, subcode, papercode) VALUES (?, ?, ?)";
                pst = con.prepareStatement(sql);
                pst.setString(1, ccomb.getCoursecode() + "-" + combine2);
                pst.setString(2, subcode);
                pst.setString(3, papercode);
                
                affectedRows=pst.executeUpdate();
            }
            if(affectedRows<=-1){
                throw new SQLException("Failed to insert new row --- ");
            }
//            setMsg(" Course Save Successfully.");
            ccomb.setMsg1("GENERATED COMBINATION :");
            ccomb.setMsg(ccomb.getCoursecode()+"-"+combine2);
            con.commit();

        } catch (SQLException e) {
            System.out.println("Error 1234 -- " + e);
            //JOptionPane.showMessageDialog(null,"Record Exist");
            try {
                con.rollback();
            } catch (SQLException ex) {
                System.out.println("RollBack operation error.");

            }
            return -1;

        } finally {
            pst = null;
            connectionPool.free(con);
            con = null;
            // rs = null;
            pst = null;
            connectionPool = null;
        }

        return 1;
    }
}
