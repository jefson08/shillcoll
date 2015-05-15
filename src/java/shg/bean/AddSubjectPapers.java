/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shg.bean;

import shg.valid.Validator;

/**
 *
 * @author Shgcomp
 */
public class AddSubjectPapers {
 
    private String cmbCourseName;
    private String cmbStream;
    private String cmbSubjectName;
    private boolean []chkCategory;
    private boolean []chkPract;
    private String []cmbYearOrSemNo;
    private String [] txtPaperId;
    private String [] txtPaperName;

    /**
     * @return the cmbCourseName
  */
    public boolean isCmbCourseNameValid() {
        if (Validator.isNullValue(getCmbCourseName()) || getCmbCourseName().equals("-1")) {
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
    public boolean isCmbSubjectNameValid() {
        if (Validator.isNullValue(getCmbSubjectName()) || getCmbSubjectName().equals("-1")) {
            return false;
        }
        return true;
    }
  
    public boolean isCmbYearOrSemNoValid() {
        int itemsCount, i;
        String items[] = getCmbYearOrSemNo();
        itemsCount = getTxtPaperName().length;
        for (i = 0; i < itemsCount ; i++) {
            if (Validator.isNullValue(items[i]) || items[i].equals("-1")) {
                 return false;
             }
        }
        return true;
    }
    public boolean isTxtPaperNameValid() {
        int itemsCount, i;
        String items[] = getTxtPaperName();
        itemsCount = getTxtPaperName().length;
         
        for (i = 0; i < itemsCount ; i++) {
            if ((Validator.isNullValue(items[i]))) {
                return false;
            }
        }
        return true;
    }

    public boolean isTxtPaperIdValid() {
        
        int itemsCount, i;
        String items[] = getTxtPaperId();
        itemsCount = getTxtPaperId().length;
        for(i=0; i< itemsCount; i++){
         
            if ((Validator.isNullValue(items[i]))) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return the cmbCourseName
     */
    public String getCmbCourseName() {
        return cmbCourseName;
    }

    /**
     * @param cmbCourseName the cmbCourseName to set
     */
    public void setCmbCourseName(String cmbCourseName) {
        this.cmbCourseName = cmbCourseName;
    }

    /**
     * @return the cmbStream
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
     * @return the cmbSubjectName
     */
    public String getCmbSubjectName() {
        return cmbSubjectName;
    }

    /**
     * @param cmbSubjectName the cmbSubjectName to set
     */
    public void setCmbSubjectName(String cmbSubjectName) {
        this.cmbSubjectName = cmbSubjectName;
    }

    /**
     * @return the chkCategory
     */
    public boolean[] getChkCategory() {
        return chkCategory;
    }

    /**
     * @param chkCategory the chkCategory to set
     */
    public void setChkCategory(boolean[] chkCategory) {
        this.chkCategory = chkCategory;
    }

    /**
     * @return the chkPract
     */
    public boolean[] getChkPract() {
        return chkPract;
    }

    /**
     * @param chkPract the chkPract to set
     */
    public void setChkPract(boolean[] chkPract) {
        this.chkPract = chkPract;
    }

    /**
     * @return the cmbYearOrSemNo
     */
    public String[] getCmbYearOrSemNo() {
        return cmbYearOrSemNo;
    }

    /**
     * @param cmbYearOrSemNo the cmbYearOrSemNo to set
     */
    public void setCmbYearOrSemNo(String[] cmbYearOrSemNo) {
        this.cmbYearOrSemNo = cmbYearOrSemNo;
    }

    /**
     * @return the txtPaperId
     */
    public String[] getTxtPaperId() {
        return txtPaperId;
    }

    /**
     * @param txtPaperId the txtPaperId to set
     */
    public void setTxtPaperId(String[] txtPaperId) {
        this.txtPaperId = txtPaperId;
    }

    /**
     * @return the txtPaperName
     */
    public String[] getTxtPaperName() {
        return txtPaperName;
    }

    /**
     * @param txtPaperName the txtPaperName to set
     */
    public void setTxtPaperName(String[] txtPaperName) {
        this.txtPaperName = txtPaperName;
    }
}

