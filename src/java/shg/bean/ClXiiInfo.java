/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shg.bean;

import java.util.Calendar;
import shg.valid.Validator;

/**
 *
 * @author Don Corleone
 */
public class ClXiiInfo {

    private String txtBoardRoll;
    private String cmbBoardID;
    private String txtYrPass;
    private String cmbStream;
    private String[] txtSubject;
    private String[] txtMarks;
    private String txtTotalMarks;

    /**
     * @return the txtBoardRoll
     */
    public String getTxtBoardRoll() {
        return txtBoardRoll;
    }

    /**
     * @param txtBoardRoll the txtBoardRoll to set
     */
    public void setTxtBoardRoll(String txtBoardRoll) {
        this.txtBoardRoll = txtBoardRoll;
    }

    /**
     * @return the txtDegRoll
     */

    /**
     * @param txtDegRoll the txtDegRoll to set
     */

    /**
     * @return the cmbBoardID
     */
    public String getCmbBoardID() {
        return cmbBoardID;
    }

    /**
     * @param cmbBoardID the cmbBoardID to set
     */
    public void setCmbBoardID(String cmbBoardID) {
        this.cmbBoardID = cmbBoardID;
    }

    /**
     * @return the txtYrPass
     */
    public String getTxtYrPass() {
        return txtYrPass;
    }

    /**
     * @param txtYrPass the txtYrPass to set
     */
    public void setTxtYrPass(String txtYrPass) {
        this.txtYrPass = txtYrPass;
    }

    /**
     * @return the cmdStream
     */
    public String getCmbStream() {
        return cmbStream;
    }

    /**
     * @param cmbStream the cmbStream to set
     */
    public void setCmbStream(String cmbStream) {
        this.cmbStream = cmbStream;
    }

    /**
     * @return the txtSubject
     */
    public String[] getTxtSubject() {
        return txtSubject;
    }

    /**
     * @param txtSubject the txtSubject to set
     */
    public void setTxtSubject(String[] txtSubject) {
        this.txtSubject = txtSubject;
    }

    public boolean isTxtBoardRollValid() {
        return !((Validator.isNullValue(getTxtBoardRoll())));
    }

    public boolean isTxtDegRollValid() {
        //return !((Validator.isNullValue(getTxtDegRoll())));
        return true;
    }

    public boolean isCmbBoardIDValid() {
        if (Validator.isNullValue(getCmbBoardID()) || getCmbBoardID().equals("-1")) {
            return false;
        }
        return true;
    }

    public boolean isTxtYrPassValid() {
        
       // System.out.println("Current Year " + pre_year);

        if (!Validator.isNullValue(getTxtYrPass())) {
            int year = Calendar.getInstance().get(Calendar.YEAR);
        int pre_year = year - 10;
            if ((Integer.parseInt(getTxtYrPass())) < pre_year) {
                return false;
            }
            if ((Integer.parseInt(getTxtYrPass())) > year) {
                return false;
            }
            if (!Validator.isNumeric(getTxtYrPass()) || !Validator.isValidLength(getTxtYrPass(), 4, 4)) {
                System.out.println("Error Here");
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    public boolean isCmbStreamValid() {
        if (Validator.isNullValue(getCmbStream()) || getCmbStream().equals("-1")) {
            return false;
        }
        return true;
    }

    public boolean isTxtSubjectValid() {
        String[] item = getTxtSubject();
        if (item != null) {
            for (String item1 : item) {
                //System.out.println("Subject"+item1);
                if ((Validator.isNullValue(item1)) || Validator.containsIllegalCharacters(item1) || !(Validator.isText(item1))) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * @return the txtMarks
     */
    public String[] getTxtMarks() {
        return txtMarks;
    }

    /**
     * @param txtMarks the txtMarks to set
     */
    public void setTxtMarks(String[] txtMarks) {
        this.txtMarks = txtMarks;
    }

    public boolean isTxtMarksValid() {
        String[] item = getTxtMarks();
        if (item != null) {
            for (String item1 : item) {
                System.out.println("Marks:" + item1);
                if (!Validator.isNullValue(item1)) {
                    if (!Validator.isNumeric(item1) || !Validator.isValidLength(item1, 1, 3)) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * @return the txtTotalMarks
     */
    public String getTxtTotalMarks() {
        return txtTotalMarks;
    }

    /**
     * @param txtTotalMarks the txtTotalMarks to set
     */
    public void setTxtTotalMarks(String txtTotalMarks) {
        this.txtTotalMarks = txtTotalMarks;
    }
    public boolean isTxtTotalMarksValid() {//txtTotalMarks
        
       // System.out.println("Current Year " + pre_year);

        if (!Validator.isNullValue(getTxtTotalMarks())) {
            if (!Validator.isNumeric(getTxtTotalMarks()) || !Validator.isValidLength(getTxtTotalMarks(), 1, 4)) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }
}
