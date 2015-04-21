/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shg.valid;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;


public class Validator {
    
    public static boolean isEmpty(String value) {

        try {
            if (value != null && value.trim().length() > 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Exception thrown by " + new Validator().getClass() + " at " + new java.util.Date() + " Error Description " + e);
            return true;
        }
    }

    public static boolean containsIllegalCharacters(String value) {
        if (value == null) {
            return false;
        }

        try {
            if (value.indexOf('$') != -1
                    || value.indexOf('%') != -1
                    || value.indexOf('<') != -1
                    || value.indexOf('>') != -1
                    || value.indexOf('+') != -1
                    || value.indexOf('^') != -1
                    || value.indexOf('\\') != -1
                    || value.indexOf(';') != -1
                    || value.indexOf('\'') != -1
                    || value.indexOf('\"') != -1) {
                System.out.println("Invalid input detected " + value);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Exception thrown by " + new Validator().getClass() + " at " + new java.util.Date() + " Error Description " + e);
            return true;
        }
    }

    //the field value must have a value and length > 0
    public static boolean isNullValue(String value) {

        try {
            if (value != null && value.trim().length() > 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Exception thrown by " + new Validator().getClass() + " at " + new java.util.Date() + " Error Description " + e);
            return true;
        }
    }
    //validate floating point numbers
    private static final String FLOAT_REGX = "[0-9][0-9]*.?[0-9]?[0-9]*";
    private static boolean floatExpMatched;

    public static boolean isFloatPointNumber(String value) {
        try {
            if (value == null) {
                return false;
            } else {
                floatExpMatched = Pattern.matches(FLOAT_REGX, value);
                if (!floatExpMatched) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println("Exception thrown by " + new Validator().getClass() + "111 at " + new java.util.Date() + " Error Description " + e);
            return false;
        }
    }

    //validates if value is a number or not
    private static final String NUMERIC_REGX = "[0-9]*";
    private static boolean expressionMatched;

    public static boolean isNumeric(String value) {
        try {
            if (value == null) {
                return false;
            } else {
                expressionMatched = Pattern.matches(NUMERIC_REGX, value);
                if (!expressionMatched) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println("Exception thrown by " + new Validator().getClass() + "111 at " + new java.util.Date() + " Error Description " + e);
            return false;
        }
    }

    //validates if value is a number or not and within a given range
    public static boolean validateNumberRange(String value, long minValue, long maxValue) {

        if (isNumeric(value) == false) {
            return false;
        }

        long longValue = Long.parseLong(value);

        try {
            if (longValue >= minValue && longValue <= maxValue) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Exception thrown by " + new Validator().getClass() + "222 at " + new java.util.Date() + " Error Description " + e);
            return false;
        }
    }

    //validates the value if the lenth is acceptible
    public static boolean isValidLength(String value, int maxLength) {

        if (value == null) {
            return false;
        }

        int stringLength = value.trim().length();

        try {
            if (stringLength <= maxLength) {

                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Exception thrown by " + new Validator().getClass() + "333 at " + new java.util.Date() + " Error Description " + e);
            return false;
        }
    }

    // value is passed in dd-mm-yyyy format e.g. 31-07-1975
    public static boolean isValidDate(String value) {

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        try {
            df.parse(value);
            return true;
        } catch (Exception e) {
            System.out.println("Exception thrown by " + new Validator().getClass() + "444 at " + new java.util.Date() + " Error Description " + e);
            return false;
        }
    }

    /*
     //value to be entered in dd-mm-yyyy format; e.g. 31-07-1975
     public static boolean isDateInRange(String value, Date startDate, Date endDate){

     DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

     try{
     Date enteredDate = df.parse(value);



     return true;
     }catch (Exception e){
     System.out.println("Exception thrown by " + new Validator().getClass() + " at " + new java.util.Date() + " Error Description "+ e);
     return false;
     }


     }
     */
    public static String filterInput(String value) {
        if (value == null) {
            return null;
        }

        StringBuffer result = new StringBuffer(value.length());
        for (int i = 0; i < value.length(); ++i) {
            switch (value.charAt(i)) {
                case '<':
                    result.append("&lt;");
                    break;
                case '>':
                    result.append("&gt;");
                    break;
                case '"':
                    result.append("&quot;");
                    break;
                case '\'':
                    result.append("&#39;");
                    break;
                case '%':
                    result.append("&#37;");
                    break;
                case ';':
                    result.append("&#59;");
                    break;
                case '(':
                    result.append("&#40;");
                    break;
                case ')':
                    result.append("&#41;");
                    break;
                case '&':
                    result.append("&amp;");
                    break;
                case '+':
                    result.append("&#43;");
                    break;
                case ',':
                    result.append("&#44");
                    break;
                default:
                    result.append(value.charAt(i));
                    break;
            }
        }
        return result.toString();
    }

    public static boolean isDateInRange(Date dateToSearch, Date startdate, Date enddate) {
        Calendar calstart = Calendar.getInstance();
        calstart.setTime(startdate);
        calstart.set(Calendar.HOUR, 0);
        calstart.set(Calendar.MINUTE, 0);
        calstart.set(Calendar.SECOND, 0);

        Calendar calend = Calendar.getInstance();
        calend.setTime(enddate);
        calend.set(Calendar.HOUR, 0);
        calend.set(Calendar.MINUTE, 0);
        calend.set(Calendar.SECOND, 0);

        Calendar calsearch = Calendar.getInstance();
        calsearch.setTime(dateToSearch);
        calsearch.set(Calendar.HOUR, 0);
        calsearch.set(Calendar.MINUTE, 0);
        calsearch.set(Calendar.SECOND, 0);

        if (((isDateEqual(calstart.getTime(), calsearch.getTime())) || (calstart.getTime().before(calsearch.getTime())))
                && ((isDateEqual(calend.getTime(), calsearch.getTime())) || (calend.getTime().after(calsearch.getTime())))) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isDateEqual(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        if ((cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) && (cal1.get(Calendar.DATE) == cal2.get(Calendar.DATE)) && (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR))) {
            return true;
        } else {
            return false;
        }
    }
    /*--------- function to validate time format viz: 10:00,12:45 etc . --------*/
    private static boolean expressionOneMatched, expressionTwoMatched, invalidExpressionOneMatched, invalidExpressionTwoMatched;
    private static final String VALID_REGX_ONE = "[0-1][0-2]\\:[0-5][0-9]",
            VALID_REGX_TWO = "[0]?[0-9]\\:[0-5][0-9]",
            INVALID_ONE_REGX = "[0]?[0]\\:[0-5][0-9]",
            INVALID_TWO_REGX = "[0]?[0]\\:[0]?[0]";

    public static boolean isValidTimeFormat(String value) {
        try {

            expressionOneMatched = Pattern.matches(VALID_REGX_ONE, value);
            expressionTwoMatched = Pattern.matches(VALID_REGX_TWO, value);
            invalidExpressionOneMatched = Pattern.matches(INVALID_ONE_REGX, value);
            invalidExpressionTwoMatched = Pattern.matches(INVALID_TWO_REGX, value);

            if (!invalidExpressionTwoMatched && !invalidExpressionOneMatched && (expressionOneMatched || expressionTwoMatched)) {
                return true;
            }
            return false;
        } catch (Exception ex) {
            System.out.println("Exception caught: " + ex);
            return false;
        }
    }

    /**
     * *************************************************************************
     */

    /*   validate input date if it is after today */
    public static boolean isDateAfterToday(String date) {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

        try {
            java.util.Date today = new java.util.Date();
            java.util.Date inputdate = df.parse(date);
            if (inputdate.after(today)) {
                System.out.println(" The date of issue cannot be after the current date ");
                return true;
            }
        } catch (Exception e) {
            System.out.println("Exception thrown by {isDateAfterToday(String date)} at " + new java.util.Date() + " Error Description " + e);
            return true;
        }
        return false;
    }

    public static boolean isValidLength(String value, int minLength, int maxLength) {

        if (value == null) {
            return false;
        }

        int stringLength = value.trim().length();

        try {
            if (stringLength >= minLength && stringLength <= maxLength) {

                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Exception thrown by " + new Validator().getClass() + " at " + new java.util.Date() + " Error Description " + e);
            return false;
        }
    }

    //
    private static final String TEXT_REGX = "[a-z A-Z]*";
    //private static boolean expressionMatched;

    public static boolean isText(String value) {
        try {
            if (value == null) {
                return false;
            } else {
                expressionMatched = Pattern.matches(TEXT_REGX, value);
                if (!expressionMatched) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println("Exception thrown by " + new Validator().getClass() + " at " + new java.util.Date() + " Error Description " + e);
            return false;
        }
    }

    //Check input format
    public static boolean checkRegexp(String value, String Regexp) {

        try {
            if (value == null) {
                return false;
            } else {
                expressionMatched = Pattern.matches(Regexp, value);
                if (!expressionMatched) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println("Exception thrown by " + new Validator().getClass() + " at " + new java.util.Date() + " Error Description " + e);
            return false;
        }
    }

    public static boolean isValidDateRange(String sdate, String edate) {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date startdate = null;
        Date enddate = null;
        try {
            startdate = df.parse(sdate);
            enddate = df.parse(edate);
        } catch (Exception e) {
            System.out.println("Exception thrown by {isValidDateRange(String sdate, String edate)} at " + new java.util.Date() + " Error Description " + e);
            return false;
        }
        Calendar calstart = Calendar.getInstance();
        calstart.setTime(startdate);
        calstart.set(Calendar.HOUR, 0);
        calstart.set(Calendar.MINUTE, 0);
        calstart.set(Calendar.SECOND, 0);

        Calendar calend = Calendar.getInstance();
        calend.setTime(enddate);
        calend.set(Calendar.HOUR, 0);
        calend.set(Calendar.MINUTE, 0);
        calend.set(Calendar.SECOND, 0);

        if (((isDateEqual(calstart.getTime(), calend.getTime())) || (calstart.getTime().before(calend.getTime())))) {
            return true;
        } else {
            return false;
        }
    }
    /*  -  -  -  */
}
