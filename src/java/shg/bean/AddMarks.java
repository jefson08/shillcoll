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
public class AddMarks {
    private String txtNehuRollNo;
     private String txtRollNo;
    private String [] txtMarksTh;
    private String [] txtMarksPr;
    private String [] txtPaperCode;
     private String [] txtPaperName;
    private String [] txtSubjectCode;
     private String [] txtSubjectName;
    private String txtExamID;
    private String cmbDiv;
private String cmbYearOrSemNo;
private String cmbNR;

    /**
     * @return the txtNehuRollNo
     */
    public String getTxtNehuRollNo() {
        return txtNehuRollNo;
    }

    /**
     * @param txtNehuRollNo the txtNehuRollNo to set
     */
    public void setTxtNehuRollNo(String txtNehuRollNo) {
        this.txtNehuRollNo = txtNehuRollNo;
    }

    /**
     * @return the txtRollNo
     */
    public String getTxtRollNo() {
        return txtRollNo;
    }

    /**
     * @param txtRollNo the txtRollNo to set
     */
    public void setTxtRollNo(String txtRollNo) {
        this.txtRollNo = txtRollNo;
    }

    /**
     * @return the txtMarksTh
     */
    public String[] getTxtMarksTh() {
        return txtMarksTh;
    }

    /**
     * @param txtMarksTh the txtMarksTh to set
     */
    public void setTxtMarksTh(String[] txtMarksTh) {
        this.txtMarksTh = txtMarksTh;
    }

    /**
     * @return the txtMarksPr
     */
    public String[] getTxtMarksPr() {
        return txtMarksPr;
    }

    /**
     * @param txtMarksPr the txtMarksPr to set
     */
    public void setTxtMarksPr(String[] txtMarksPr) {
        this.txtMarksPr = txtMarksPr;
    }

    /**
     * @return the txtPaperCode
     */
    public String[] getTxtPaperCode() {
        return txtPaperCode;
    }

    /**
     * @param txtPaperCode the txtPaperCode to set
     */
    public void setTxtPaperCode(String[] txtPaperCode) {
        this.txtPaperCode = txtPaperCode;
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

    /**
     * @return the txtSubjectCode
     */
    public String[] getTxtSubjectCode() {
        return txtSubjectCode;
    }

    /**
     * @param txtSubjectCode the txtSubjectCode to set
     */
    public void setTxtSubjectCode(String[] txtSubjectCode) {
        this.txtSubjectCode = txtSubjectCode;
    }

    /**
     * @return the txtSubjectName
     */
    public String[] getTxtSubjectName() {
        return txtSubjectName;
    }

    /**
     * @param txtSubjectName the txtSubjectName to set
     */
    public void setTxtSubjectName(String[] txtSubjectName) {
        this.txtSubjectName = txtSubjectName;
    }

    /**
     * @return the txtExamID
     */
    public String getTxtExamID() {
        return txtExamID;
    }

    /**
     * @param txtExamID the txtExamID to set
     */
    public void setTxtExamID(String txtExamID) {
        this.txtExamID = txtExamID;
    }

    /**
     * @return the cmbDiv
     */
    public String getCmbDiv() {
        return cmbDiv;
    }

    /**
     * @param cmbDiv the cmbDiv to set
     */
    public void setCmbDiv(String cmbDiv) {
        this.cmbDiv = cmbDiv;
    }

    /**
     * @return the cmbYearOrSemNo
     */
    public String getCmbYearOrSemNo() {
        return cmbYearOrSemNo;
    }

    /**
     * @param cmbYearOrSemNo the cmbYearOrSemNo to set
     */
    public void setCmbYearOrSemNo(String cmbYearOrSemNo) {
        this.cmbYearOrSemNo = cmbYearOrSemNo;
    }

    /**
     * @return the cmbNR
     */
    public String getCmbNR() {
        return cmbNR;
    }

    /**
     * @param cmbNR the cmbNR to set
     */
    public void setCmbNR(String cmbNR) {
        this.cmbNR = cmbNR;
    }
public boolean isTxtMarksThValid()
{
      int itemsCount, i;
        String items[] = getTxtMarksTh();
        itemsCount = getTxtMarksTh().length;
        for(i=0; i< itemsCount; i++){
         
            if ((Validator.isNullValue(items[i]))) {
                return false;
            }
        }
        return true;
    }
public boolean isTxtMarksPrValid()
{
      int itemsCount, i;
        String items[] = getTxtMarksPr();
        itemsCount = getTxtMarksPr().length;
        for(i=0; i< itemsCount; i++){
         
            if (!(items[i].trim().equals("-1")) && (Validator.isNullValue(items[i]))) {
                return false;
            }
        }
        return true;
    }

    public boolean isCmbNRValid()
    {
         if (Validator.isNullValue(getCmbNR()) || getCmbNR().equals("-1")) {
            System.out.println(getCmbNR());
            return false;
        }
        return true;
    }

    
   
    public boolean isCmbYearOrSemNoValid() {
        if (Validator.isNullValue(getCmbYearOrSemNo()) || getCmbYearOrSemNo().equals("-1")) {
            System.out.println(getCmbYearOrSemNo());
            return false;
        }
        return true;
    }

   
}
