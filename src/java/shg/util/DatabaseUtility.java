/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shg.util;

import DBConnection.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TreeSet;
import java.util.HashMap;
import javax.servlet.ServletContext;

/**
 *
 * @author nic
 */
public class DatabaseUtility {

    //Connection con;
    public HashMap<String, String> getSubjectPaperMapping(String paper[], ServletContext context) throws SQLException {
        Statement st;
        ResultSet rs = null;
        StringBuffer pstr = new StringBuffer();
        String sql = "";
        HashMap<String, String> mp = new HashMap<String, String>();

        Connection con = null;
        ConnectionPool connectionPool = null;

        try {
            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
            con = connectionPool.getConnection();
        } catch (SQLException e) {
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
            return null;
        }
        try {

            for (String p : paper) {
//                System.out.println("Paper----" + p);
                pstr.append("'").append(p).append("'").append(",");
            }
            pstr.deleteCharAt(pstr.length() - 1);
            sql = "select papercode, subjectcode from papers where papercode in (" + pstr + ")";
//            System.out.println(sql);
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
//                System.out.println("from db --- " + rs.getString("papercode") + " --- " + rs.getString("subjectcode"));
                mp.put(rs.getString("papercode"), rs.getString("subjectcode"));
            }

        } catch (SQLException ex) {
            throw ex;
            //Logger.getLogger(CourseCombinationDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connectionPool.free(con);
        }
        return mp;
    }

    //*************************NEWLY ADDED METHOD*********************************
    public String populateDependentPopupFromTwoTableOneCondition(ServletContext context, String tablename1, String tablename2, String ijField1, String ijField2, String valueField, String optionField, String dependsOnField, String dependsOnVal, String defValue) {
        PreparedStatement pst = null;
        Connection con = null;
        String sql = "", opt = "";
        ResultSet rs = null;
        ConnectionPool connectionPool = null;
        try {
            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
            con = connectionPool.getConnection();

            sql = "SELECT DISTINCT A." + valueField + ", A." + optionField + " FROM " + tablename1
                    + " A INNER JOIN " + tablename2 + " I ON A." + ijField1 + " = I." + ijField2
                    + " AND I." + dependsOnField + " = ? ";

            pst = con.prepareStatement(sql);
            pst.setString(1, dependsOnVal);

            rs = pst.executeQuery();

            while (rs.next()) {
                if (rs.getString(valueField).trim().equals(defValue)) {
                    opt += "<option value=\"" + rs.getString(1) + "\" selected> " + rs.getString(2) + "</option>";
                } else {
                    opt += "<option value=\"" + rs.getString(1) + "\"> " + rs.getString(2) + "</option>";
                }
                //recordCount = 1;
            }
        } catch (SQLException e) {
            context.log(this.getClass() + "{populateDependentPopupFromTwoCondition}  Error while trying to retreive the data  " + e);
            return null;
        } finally {
            try {
                if (con != null) {
                    connectionPool.free(con);
                    con.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException gc) {
                System.out.println("Error in garbage collection :" + this.getClass() + "/populatePopup()-method " + gc);
            }
        }
//        System.out.println(defValue);
        return opt;
    }

    public String populatePopupInnerJoinThreeTableOneCondition(ServletContext context, String tablename1, String tablename2, String tablename3, String ijField1, String ijField2, String ijField3, String ijField4, String valueField, String dependsOnField, String dependsOnVal, String defValue) {
        PreparedStatement pst = null;
        Connection con = null;
        String sql = "", opt = "";
        ResultSet rs = null;
        Statement st = null;
        ConnectionPool connectionPool = null;
        try {
            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
            con = connectionPool.getConnection();

            sql = "SELECT DISTINCT C." + valueField + " FROM " + tablename1
                    + " C INNER JOIN " + tablename2 + " A ON C." + ijField1 + " = A." + ijField2
                    + "  INNER JOIN " + tablename3 + " I ON A." + ijField3 + " = I." + ijField4
                    + " AND I." + ijField4 + " = ? " + " ORDER BY C." + valueField + " ASC ";

            pst = con.prepareStatement(sql);
            pst.setString(1, dependsOnVal);
            rs = pst.executeQuery();

            while (rs.next()) {
                if (rs.getString(valueField).trim().equals(defValue)) {

                    opt += "<option value=\"" + rs.getString(1) + "\" selected> " + rs.getString(1) + "</option>";
                } else {
                    opt += "<option value=\"" + rs.getString(1) + "\"> " + rs.getString(1) + "</option>";
                }
            }
        } catch (SQLException e) {
            context.log(this.getClass() + "{populateDependentPopupInnerJoinTwoTableTwoCondition}  Error while trying to retreive the data  " + e);
            return null;
        } finally {
            try {
                if (con != null) {
                    connectionPool.free(con);
                    con.close();
//                    con=null;
                }
                if (rs != null) {
                    rs.close();
//                    rs=null;
                }
            } catch (SQLException gc) {
                System.out.println("Error in garbage collection :" + this.getClass() + "/populatePopup()-method " + gc);
            }
        }
//        System.out.println(defValue);
        return opt;
    }

    public String populatePopupInnerJoinThreeTable(ServletContext context, String tablename1, String tablename2, String tablename3, String ijField1, String ijField2, String ijField3, String ijField4, String valueField, String optionField, String defValue) {

        Connection con = null;
        String sql = "", opt = "";
        ResultSet rs = null;
        Statement st = null;
        ConnectionPool connectionPool = null;
        try {
            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
            con = connectionPool.getConnection();

            st = con.createStatement();

            sql = "SELECT DISTINCT C." + valueField + " , C." + optionField + " FROM " + tablename1
                    + " C INNER JOIN " + tablename2 + " A ON C." + ijField1 + " = A." + ijField2
                    + "  INNER JOIN " + tablename3 + " I ON A." + ijField3 + " = I." + ijField4
                    + " ORDER BY C." + valueField + " ASC ";

            rs = st.executeQuery(sql);

            while (rs.next()) {
                if (rs.getString(valueField).trim().equals(defValue)) {

                    opt += "<option value=\"" + rs.getString(1) + "\" selected> " + rs.getString(2) + "</option>";
                } else {
                    opt += "<option value=\"" + rs.getString(1) + "\"> " + rs.getString(2) + "</option>";
                }
//                System.out.println(rs.getString(2));
//                System.out.println(rs.getString(1));
            }
        } catch (SQLException e) {
            context.log(this.getClass() + "{populateDependentPopupInnerJoinTwoTableTwoCondition}  Error while trying to retreive the data  " + e);
            return null;
        } finally {
            try {
                if (con != null) {
                    connectionPool.free(con);
                    con.close();
//                    con=null;
                }
                if (rs != null) {
                    rs.close();
//                    rs=null;
                }
            } catch (SQLException gc) {
                System.out.println("Error in garbage collection :" + this.getClass() + "/populatePopup()-method " + gc);
            }
        }
//        System.out.println(defValue);
        return opt;
    }

    public String populateDependentPopupInnerJoinTwoTableTwoCondition(ServletContext context, String tablename1, String tablename2, String ijField1, String ijField2, String valueField, String dependsOnField1, String dependsOnField2, String dependsOnVal1, String dependsOnVal2, String defValue) {
        PreparedStatement pst = null;
        Connection con = null;
        String sql = "", opt = "";
        ResultSet rs = null;
        ConnectionPool connectionPool = null;
        try {
            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
            con = connectionPool.getConnection();

            //   sql="SELECT DISTINCT "+ valueField + ", " + optionField + " FROM " + tablename + " WHERE "+ dependsOnField + " = ?";
            sql = "SELECT DISTINCT B." + valueField + " FROM " + tablename1 + " B INNER JOIN " + tablename2 + " S ON B." + ijField1 + " = S." + ijField2
                    + " WHERE " + dependsOnField1 + " = ? AND " + dependsOnField2 + " = ? ";

            pst = con.prepareStatement(sql);
            pst.setString(1, dependsOnVal1);
            pst.setString(2, dependsOnVal2);
            rs = pst.executeQuery();

            while (rs.next()) {
                if (rs.getString(valueField).trim().equals(defValue)) {

                    opt += "<option value=\"" + rs.getString(1) + "\" selected> " + rs.getString(1) + "</option>";
                } else {
                    opt += "<option value=\"" + rs.getString(1) + "\"> " + rs.getString(1) + "</option>";
                }
                //recordCount = 1;
            }
        } catch (SQLException e) {
            context.log(this.getClass() + "{populateDependentPopupInnerJoinTwoTableTwoCondition}  Error while trying to retreive the data  " + e);
            return null;
        } finally {
            try {
                if (con != null) {
                    connectionPool.free(con);
                    con.close();
//                    con=null;
                }
                if (rs != null) {
                    rs.close();
//                    rs=null;
                }
                if (pst != null) {
                    pst.close();
//                    pst=null;
                }
            } catch (SQLException gc) {
                System.out.println("Error in garbage collection :" + this.getClass() + "/populatePopup()-method " + gc);
            }
        }
//        System.out.println(defValue);
        return opt;
    }

    public String populateDependentPopupInnerJoinTwoTable(ServletContext context, String tablename1, String tablename2, String ijField1, String ijField2, String valueField, String dependsOnField, String dependsOnVal, String defValue) {
        PreparedStatement pst = null;
        Connection con = null;
        String sql = "", opt = "";
        ResultSet rs = null;
        ConnectionPool connectionPool = null;
        try {
            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
            con = connectionPool.getConnection();

            //   sql="SELECT DISTINCT "+ valueField + ", " + optionField + " FROM " + tablename + " WHERE "+ dependsOnField + " = ?";
            sql = "SELECT DISTINCT B." + valueField + " FROM " + tablename1 + " B INNER JOIN " + tablename2 + " S ON B." + ijField1 + " = S." + ijField2
                    + " WHERE " + dependsOnField + " = ? ORDER BY B." + valueField + " ASC ";

            pst = con.prepareStatement(sql);
            pst.setString(1, dependsOnVal);

            rs = pst.executeQuery();

            while (rs.next()) {
                if (rs.getString(valueField).trim().equals(defValue)) {

                    opt += "<option value=\"" + rs.getString(1) + "\" selected> " + rs.getString(1) + "</option>";
                } else {
                    opt += "<option value=\"" + rs.getString(1) + "\"> " + rs.getString(1) + "</option>";
                }
                //recordCount = 1;
            }
        } catch (SQLException e) {
            context.log(this.getClass() + "{populateDependentPopupInnerJoinTwoTable}  Error while trying to retreive the data  " + e);
            return null;
        } finally {
            try {
                if (con != null) {
                    connectionPool.free(con);
                    con.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException gc) {
                System.out.println("Error in garbage collection :" + this.getClass() + "/populatePopup()-method " + gc);
            }
        }
//        System.out.println(defValue);
        return opt;
    }

    public String populateDependentPopupFromTwoCondition(ServletContext context, String tablename, String valueField, String optionField, String dependsOnField1, String dependsOnField2, String dependsOnVal1, String dependsOnVal2, String defValue) {
        PreparedStatement pst = null;
        Connection con = null;
        String sql = "", opt = "";
        ResultSet rs = null;
        ConnectionPool connectionPool = null;
        try {
            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
            con = connectionPool.getConnection();

            sql = "SELECT DISTINCT " + valueField + ", " + optionField + " FROM " + tablename + " WHERE " + dependsOnField1 + " = ? " + " AND " + dependsOnField2 + " = ? ";

//            System.out.println(valueField+ " " + optionField+" " +dependsOnField1+" " + dependsOnField2+" " + dependsOnVal1+ " " +dependsOnVal2+" " +defValue);
            pst = con.prepareStatement(sql);
            pst.setString(1, dependsOnVal1);
            pst.setString(2, dependsOnVal2);
            rs = pst.executeQuery();

            while (rs.next()) {
                if (rs.getString(valueField).trim().equals(defValue)) {
                    opt += "<option value=\"" + rs.getString(1) + "\" selected> " + rs.getString(2) + "</option>";
                } else {
                    opt += "<option value=\"" + rs.getString(1) + "\"> " + rs.getString(2) + "</option>";
                }
                //recordCount = 1;
            }
        } catch (SQLException e) {
            context.log(this.getClass() + "{populateDependentPopupFromTwoCondition}  Error while trying to retreive the data  " + e);
            return null;
        } finally {
            try {
                if (con != null) {
                    connectionPool.free(con);
                    con.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException gc) {
                System.out.println("Error in garbage collection :" + this.getClass() + "/populatePopup()-method " + gc);
            }
        }
//        System.out.println(defValue);
        return opt;
    }

    //**************************END*******************************
    public String populatePopup(ServletContext context, String tablename, String valueField, String optionField, String defValue) {
        Statement st = null;
        Connection con = null;
        String sql = "", opt = "";
        ResultSet rs = null;
        ConnectionPool connectionPool = null;
        try {
            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
            con = connectionPool.getConnection();

            sql = "SELECT " + valueField + ", " + optionField + " FROM " + tablename;// + " WHERE "+ valueField + " = ?";
            st = con.createStatement();
            //pst.setString(1, defValue);
            rs = st.executeQuery(sql);

            while (rs.next()) {
                if (rs.getString(valueField).trim().equals(defValue)) {
                    opt += "<option value=\"" + rs.getString(1) + "\" selected> " + rs.getString(2) + "</option>";
                } else {
                    opt += "<option value=\"" + rs.getString(1) + "\"> " + rs.getString(2) + "</option>";
                }
                //recordCount = 1;
            }
        } catch (SQLException e) {
            context.log(this.getClass() + "{populatePopup}  Error while trying to retreive the data  " + e);
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
                System.out.println("Error in garbage collection :" + this.getClass() + "/populatePopup()-method " + gc);
            }
        }
<<<<<<< HEAD
<<<<<<< HEAD
        System.out.println(opt);
=======
        //System.out.println(opt);
>>>>>>> origin/master
=======
        //System.out.println(opt);
>>>>>>> origin/master
        return opt;
    }

    public String populateDependentPopup(ServletContext context, String tablename, String valueField, String optionField, String dependsOnField, String dependsOnVal, String defValue) {
        PreparedStatement pst = null;
        Connection con = null;
        String sql = "", opt = "";
        ResultSet rs = null;
        ConnectionPool connectionPool = null;
        try {
            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
            con = connectionPool.getConnection();

            sql = "SELECT DISTINCT " + valueField + ", " + optionField + " FROM " + tablename + " WHERE " + dependsOnField + " = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, dependsOnVal);
            rs = pst.executeQuery();

            while (rs.next()) {
                if (rs.getString(valueField).trim().equals(defValue)) {
                    opt += "<option value=\"" + rs.getString(1) + "\" selected> " + rs.getString(2) + "</option>";
                } else {
                    opt += "<option value=\"" + rs.getString(1) + "\"> " + rs.getString(2) + "</option>";
                }
                //recordCount = 1;
            }
        } catch (SQLException e) {
            context.log(this.getClass() + "{populatePopup}  Error while trying to retreive the data  " + e);
            return null;
        } finally {
            try {
                if (con != null) {
                    connectionPool.free(con);
                }
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException gc) {
                System.out.println("Error in garbage collection :" + this.getClass() + "/populatePopup()-method " + gc);
            }
        }

        return opt;
    }

    public int checkFieldExistence(ServletContext context, Connection con, String fieldname, String tablename, String whereClause) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        int recordCount = 0;
        try {
            //con = (Connection) context.getAttribute("connection");
            pst = con.prepareStatement(("SELECT ") + fieldname + (" FROM ") + tablename + (" WHERE ") + whereClause);
            rs = pst.executeQuery();
            if (rs.next()) {
                recordCount = 1;
            }
        } catch (SQLException e) {
            context.log(this.getClass() + "{checkFieldExistence()}  Error while trying to retreive the data  " + e);
            return -1;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException gc) {
                System.out.println("Error in garbage collection :" + this.getClass() + "/checkFieldExistence()-method " + gc);
            }
        }
        return recordCount;
    }

    public long getNextSerialNumber(Connection con, String table, String column) {

        PreparedStatement pst = null;
        ResultSet rs = null;
        long toBeApplicationId = -1;
        long maxapplicationId = -1;

        try {
            pst = con.prepareStatement("SELECT MAX(" + column + ") FROM " + table);
            rs = pst.executeQuery();
            while (rs.next()) {
                maxapplicationId = rs.getInt(1);
            }
            toBeApplicationId = maxapplicationId + 1;

        } catch (Exception ex) {
            System.out.println("Error while trying to retreive the data: " + this.getClass() + "/getApplicationId()-method " + ex);
            return -1;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException gc) {
                System.out.println("Error in garbage collection :" + this.getClass() + "/getApplicationId()-method " + gc);
            }
        }
        return toBeApplicationId;

    }

    public long getNextSerialNumber(Connection con, String table, String column, String whereCondition) {

        PreparedStatement pst = null;
        ResultSet rs = null;
        long toBeApplicationId = -1;
        long maxapplicationId = -1;

        try {
            pst = con.prepareStatement("SELECT MAX(" + column + ") FROM " + table + " WHERE " + whereCondition + " ");
            rs = pst.executeQuery();
            if (rs.next()) {
                maxapplicationId = rs.getInt(1);
                System.out.println("firt suer code");
            }
            toBeApplicationId = maxapplicationId + 1;
            // System.out.println("tobeapplicationid" + toBeApplicationId);
        } catch (SQLException ex) {
            System.out.println("Error while trying to retreive the data: " + this.getClass() + "/getApplicationId()-method " + ex);
            return -1;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException gc) {
                System.out.println("Error in garbage collection :" + this.getClass() + "/getApplicationId()-method " + gc);
            }
        }
        return toBeApplicationId;

    }

    // Returns  null on Error
    //empty string if no data found
    // else returns the string
    public String getFieldValue(Connection con, String table, String fieldName, String whereClause) {

        PreparedStatement pst = null;
        ResultSet rs = null;
        int data = 0;
        String sql = "";
        String returnValue = null;
        try {

            sql = "SELECT " + fieldName + " FROM " + table + " WHERE  " + whereClause;
            pst = con.prepareStatement(sql);
            System.out.println(pst.toString());
            rs = pst.executeQuery();
            while (rs.next()) {
                returnValue = rs.getString(1);
                data++;
            }

            if (data > 0) {
                return returnValue;
            } else {
                return "";
            }

        } catch (SQLException e) {
            System.out.println(this.getClass() + "{" + this.getClass() + ".getFieldValue()}  Error while trying to check the existince of a field  " + e.getMessage());
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (Exception gc) {
                System.out.println(this.getClass() + "{" + this.getClass() + ".checkFieldExistence()}  Error while trying to check the existince of a field  " + gc.getMessage());
            }
        }
    }

    public String getCategory(ServletContext context, String def) {
        String cat = "";
        Statement st;
        ResultSet rs = null;
        Connection con = null;
        ConnectionPool connectionPool = null;

        try {
            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
            con = connectionPool.getConnection();
        } catch (SQLException e) {
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
            return null;
        }

        try {
            String sql = "select * from category";
            st = con.createStatement();
            int i = 0;

            rs = st.executeQuery(sql);
            while (rs.next()) {
                i++;
                cat += "<label><input type=\"radio\" name=\"radCategory\" id=\"radCategory\" ";
                cat += "value=\"" + rs.getString(1) + "\" ";
                cat += "required=\"\" title=\"Category not Selected\" ";
                cat += rs.getString(1).toLowerCase().equals(def.toLowerCase()) ? " checked />" : " />";
                cat += rs.getString(2) + "</label>";
                cat += i % 2 == 0 ? "<br />" : "";
            }
        } catch (SQLException ex) {
            System.out.println("Error while retreiving category");
            //Logger.getLogger(CourseCombinationDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connectionPool.free(con);
        }
//        System.out.println(cat);
        return cat;
    }

    public TreeSet<String> getFieldValue(ServletContext context, String table, String field) {
        TreeSet<String> cat = new TreeSet<String>();
        Statement st;
        ResultSet rs = null;
        Connection con = null;
        ConnectionPool connectionPool = null;
        int i = 0;

        try {
            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
            con = connectionPool.getConnection();
        } catch (SQLException e) {
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
            return null;
        }

        try {
            String sql = "select " + field + " from " + table;
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                cat.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            System.out.println("Error while retreiving category");
            //Logger.getLogger(CourseCombinationDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connectionPool.free(con);
        }
//        System.out.println(cat);
        return cat;
    }
}
