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
import shg.bean.ExamPaperBean;
import shg.util.DatabaseUtility;
import shg.bean.examinfo_Bean;

/**
 *
 * @author B Mukhim
 */
public class ExamPapers {

    private Connection con = null;
    //private ResultSet rs = null;
    private PreparedStatement pst = null;
    private String sql = "";
    private ConnectionPool connectionPool = null;
    String combine = "", combine1 = "", subje = "";

    String sub1 = "", sub[],paper[];

    String subp = "";
    private int affectedRows;

    public int insertPapers(ServletContext context, ExamPaperBean exam) {
         
         
          try {
            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
            con = connectionPool.getConnection();
        } catch (SQLException e) {
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
            return -1;
        }
try {
            // Statement s1 = con.prepareStatement(sql);
            con.setAutoCommit(false);
           // System.out.println("hello peter123");  
            sub = exam.getSubjectcode();
      
            

          

//            ResultSet rs1 = getSubjectPaperMapping(ccomb.getPapercode(), con);
            Map hp = new DatabaseUtility().getSubjectPaperMapping(exam.getPapercode(), context);
            
            Iterator itr=hp.entrySet().iterator();
            int i=1001;
            while (itr.hasNext()){
                Map.Entry <String,String> t= (Map.Entry)itr.next();
                String papercode=t.getKey();
                String subcode=t.getValue();
//                System.out.println(t.getKey() +" ---- "+t.getValue());
                String sql = "INSERT INTO papersappear VALUES (?, ?, ?, ?, ?, ?)";
                pst = con.prepareStatement(sql);
                pst.setString(1,exam.getExamid());
                pst.setString(2, subcode);
                pst.setString(3, papercode);
                pst.setString(4, exam.getNehuroll());
                pst.setInt(5, 0);
                pst.setString(6, exam.getRoll1() );
               
                affectedRows=pst.executeUpdate();
              
            }
           // System.out.println("Successfully");
            
            if(affectedRows<=-1){
                exam.setMsg2("Duplicate Records : Please Re-generate Combination");
                throw new SQLException("Failed to insert new row --- ");
            }
//            setMsg(" Course Save Successfully.");
            
               exam.setMsg1("Records Saved Successfully");
               
               //exam.setMsg3("Show Papers");
//            ccomb.setMsg(ccomb.getCoursecode()+"-"+combine2);
            con.commit();

        } catch (SQLException e) {  
            exam.setMsg2("Duplicate Records : Please Re-generate Combination");
           // exam.setMsg3("Show Papers");
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



          
