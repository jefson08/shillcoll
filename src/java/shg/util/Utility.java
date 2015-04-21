/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shg.util;

import DBConnection.ConnectionPool;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.servlet.ServletContext;

/**
 *
 * @author Gulrez Sohliya
 */
public class Utility {

    String page;
    String module;

    public String getModuleFromServletPath(String servletPath) {
        StringTokenizer stk = new StringTokenizer(servletPath, "/");
        int i = 1;

        while (stk.hasMoreTokens()) {
            if (i == stk.countTokens()) {
                page = stk.nextToken();
            } else {
                module = stk.nextToken();
            }
            //i++;
        }
//        while (stk.hasMoreTokens()) {
//            if (1==stk.countTokens()) {
//                page = stk.nextToken();
//            } 
//           module= stk.nextToken();
//        }
        return page;
    }

    public String formatStringAsDateForInsert(String s1) {
        String d = "";
        String m = "";
        String y = "";
        String s[] = s1.split("-");
        //since source date is in mm/dd/yyyy

        d = s[0];
        m = s[1];
        y = s[2];

        //d = s[1];
        // m = s[0];
        /// y = s[2];
        s1 = y + "-" + m + "-" + d;
        return s1;
    }

//Display date from Mysql In format dd-mm-yyyy
    public String formatDateForDisplay(String s1) {
        if (s1 == null) {
            return "";
        }
        try {
            String d = "";
            String m = "";
            String y = "";
            String date = "";
            String s[] = s1.split(" ");
        //since source date is in mm/dd/yyyy
            //  String ss[]=s[].split("-");
            date = s[0];
            String ss[] = date.split("-");

            y = ss[0];
            m = ss[1];
            d = ss[2];
            s1 = d + "-" + m + "-" + y;
            return s1;
        } catch (Exception e) {
            System.out.println("Error :: " + this.getClass() + " :: formatDateForDisplay :: " + e);
            return "Error";
        }
    }

    public String formatXMLAsHTMLTable(Vector headers, ResultSet rs, String tableSize) {
        int counter = 0;
        StringBuffer buf = new StringBuffer();
        int i = 0;

        try {
            while (rs.next()) {
                counter++;
            }
        } catch (Exception e) {
            return (this.getClass() + " {formatXMLAsHTMLTable} : Sorry an error occured while trying to format the table \n Error Description is " + e.getMessage());
        }

        if (counter == 0) {
            buf.append("");
            return buf.toString();
        } else {
            // HTML Table Headers
            buf.append("<div id=\"refGrid\" style=\"border:0px  solid; width:" + tableSize + "; height:160px; overflow:scroll;\">");
            buf.append("<table width=\"" + tableSize + " \" border=\"1\" align=\"center\" cellpadding=\"5\">");
            buf.append("<tr>");
            while (i < headers.size()) {
                buf.append("<th align=\"center\">" + headers.get(i) + "</th>");
                i++;
            }

            buf.append("</tr>");

            //Generate the body of the HTML Table
            try {
                rs.beforeFirst();
                while (rs.next()) {
                    buf.append(" <tr>");
                    for (int x = 0; x < headers.size(); x++) {
                        buf.append("  <td  align=\"left\">" + rs.getString(x + 1) + "</td> ");
                    }
                    buf.append(" </tr>");
                }
            } catch (Exception e) {
                return (this.getClass() + " {formatXMLAsHTMLTable} : Sorry an error occured while trying to format the table \n Error Description is " + e.getMessage());
            }
            buf.append("</table>");
        }
        return buf.toString();
    }

    public String getDateComponentFromString(String s, String mode) {

        String returnValue = "-1";
        if (s == null) {
            return "-1";
        }
        StringTokenizer stk = new StringTokenizer(s, "-");

        String strArray[] = new String[3];
        strArray = s.split("-");

        for (int i = 0; i < strArray.length; i++) {
            returnValue = strArray[i];
            if (i == 0 && mode.equals("dd")) {
                break;
            } else if (i == 1 && mode.equals("mm")) {
                break;
            } else if (i == 2 && mode.equals("yy")) {
                break;
            }
        }
        return returnValue;
    }

    public static int calculateAge(String dateOfBirth) {
        try {
            Calendar cal1 = new GregorianCalendar();
            Calendar cal2 = new GregorianCalendar();
            int age = 0;
            int factor = 0;
            Date dob = new SimpleDateFormat("dd-MM-yyyy").parse(dateOfBirth);
            Date today = new Date();
            cal1.setTime(dob);
            cal2.setTime(today);
            if (cal2.get(Calendar.DAY_OF_YEAR) < cal1.get(Calendar.DAY_OF_YEAR)) {
                factor = -1;
            }
            age = cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR) + factor;
            return age;
        } catch (Exception e) {
            System.out.println("Exception thrown by getAge() at " + new java.util.Date());
            return -1;
        }
    }
    
    public static int currentYear(){
        int year;
        Calendar cal = new GregorianCalendar();
        year = cal.get(Calendar.YEAR);
//        System.out.println("current yr");
        return year;
    }
}
