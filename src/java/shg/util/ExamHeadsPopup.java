/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shg.util;

import DBConnection.ConnectionPool;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletContext;

/**
 *
 * @author Shgcomp
 */
public class ExamHeadsPopup {
     public String populateTreePopup(ServletContext context, String defValue) {
        Statement st = null;
        Connection con=null;
        String sql="", opt="";
        ResultSet rs = null;
        ConnectionPool connectionPool = null;
        try {
            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
            con = connectionPool.getConnection();
          sql=" WITH RECURSIVE recursetree(level,id, parent_id, path, name) AS";
          sql+=" (SELECT	level, accheadcode, parentcode,array[accheadcode] AS path,acchead ";
          sql+="FROM shgdb.examfeeheads WHERE parentcode = 0  UNION ALL ";
          sql+="SELECT t.level,t.accheadcode, t.parentcode, rt.path || t.accheadcode, t.acchead ";
          sql+=" FROM shgdb.examfeeheads t JOIN recursetree rt ON rt.id = t.parentcode)";
          sql+="SELECT id,lpad('',2*(level-1),'--')|| name \"subhead\",level,parent_id FROM recursetree ORDER BY path";


            
            
            st = con.createStatement();
            //pst.setString(1, defValue);
            rs = st.executeQuery(sql);
            
            while (rs.next()) {
                if(rs.getString("id").trim().equals(defValue)){
                    opt += "<option value=\""+rs.getString(1) +"\" selected> "+rs.getString(2) +"</option>";  
                }
                else{
                    opt += "<option value=\""+rs.getString(1) +"\"> "+rs.getString(2) +"</option>";  
                }
                //recordCount = 1;
            }
        } catch (SQLException e) {
            context.log(this.getClass() + "{populateTreePopup}  Error while trying to retreive the data  " + e);
            return null;
        } finally {
            try {
                if (con != null) {
                    connectionPool.free(con);
                }
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (SQLException gc) {
                System.out.println("Error in garbage collection :" + this.getClass() + "/populateTreePopup()-method " + gc);
            }
        }
        
        return opt;
    }
    
}
