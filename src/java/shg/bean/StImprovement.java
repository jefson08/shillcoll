/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shg.bean;

import shg.valid.Validator;

/**
 *
 * @author hp
 */
public class StImprovement {

    private String cmbcourseName;
    private String cmbpaperName;
    private String txtpaperId;
    private String cmbyearBt;
    private String cmbuniRollno;
    private String cmbsemImproveSem;
    private String cmbcourseCode;
    private String errorssuccmsg = " ";
    private String txtSemEdit;
    private String cmbpaperNameEdit;

    public String getCmbpaperNameEdit() {
        return cmbpaperNameEdit;
    }

    public void setCmbpaperNameEdit(String cmbpaperNameEdit) {
        this.cmbpaperNameEdit = cmbpaperNameEdit;
    }

    public String getTxtSemEdit() {
        return txtSemEdit;
    }

    public void setTxtSemEdit(String txtSemEdit) {
        this.txtSemEdit = txtSemEdit;
    }

    public String getErrorssuccmsg() {
        return errorssuccmsg;
    }

    public void setErrorssuccmsg(String errorssuccmsg) {
        this.errorssuccmsg = errorssuccmsg;
    }

    public String getCmbcourseName() {
        return cmbcourseName;
    }

    public void setCmbcourseName(String cmbcourseName) {
        //System.out.println("cname:"+cmbcourseName);
        this.cmbcourseName = cmbcourseName;
    }

    public String getCmbcourseCode() {
        return cmbcourseCode;
    }

    public void setCmbcourseCode(String cmbcourseCode) {
        //System.out.println("ccode:"+cmbcourseCode);
        this.cmbcourseCode = cmbcourseCode;
    }

    public String getCmbuniRollno() {
        return cmbuniRollno;
    }

    public void setCmbuniRollno(String cmbuniRollno) {
        this.cmbuniRollno = cmbuniRollno;
    }

    public String getCmbpaperName() {
        return cmbpaperName;
    }

    public void setCmbpaperName(String cmbpaperName) {
        // System.out.println(cmbpaperName);
        this.cmbpaperName = cmbpaperName;
    }

    public String gettxtpaperId() {
        return txtpaperId;
    }

    public void settxtpaperId(String txtpaperId) {
        this.txtpaperId = txtpaperId;
    }

    public String getCmbyearBt() {
        return cmbyearBt;
    }

    public void setCmbyearBt(String cmbyearBt) {
        this.cmbyearBt = cmbyearBt;
    }

    public String getCmbsemImproveSem() {
        return cmbsemImproveSem;
    }

    public void setCmbsemImproveSem(String cmbsemImproveSem) {
        //System.out.println("Sem:"+cmbsemImproveSem);
        this.cmbsemImproveSem = cmbsemImproveSem;
    }

    //calling for validation
    public boolean isTxtSemEditValid() {
        if (Validator.isNullValue(getTxtSemEdit()) || getTxtSemEdit().equals("-1")) {
            return false;
        }
        return true;
    }

    public boolean isCmbCourseNameValid() {
        if (Validator.isNullValue(getCmbcourseName()) || getCmbcourseName().equals("-1")) {
            return false;
        }
        return true;
    }

     public boolean isCmbPaperNameValid() {
        if (Validator.isNullValue(getCmbpaperName()) || getCmbpaperName().equals("-1")) {
            return false;
        }
        return true;
    }
     
    public boolean isCmbpaperNameEditValid() {
        if (Validator.isNullValue(getCmbpaperNameEdit()) || getCmbpaperNameEdit().equals("-1")) {
            return false;
        }
        return true;
    }

    public boolean isTxtpaperIdValid() {
        if (Validator.isNullValue(gettxtpaperId()) || gettxtpaperId().equals("-1")) {
            return false;
        }
        return true;
    }

    public boolean isCmbYearBatchValid() {
        if (Validator.isNullValue(getCmbyearBt()) || getCmbyearBt().equals("-1")) {
            return false;
        }
        return true;
    }

    public boolean isCmbuniversityRollNoValid() {
        if (Validator.isNullValue(getCmbuniRollno()) || getCmbuniRollno().equals("-1")) {
            return false;
        }
        return true;
    }

    public boolean isCmbsemImproveSemValid() {
        if (Validator.isNullValue(getCmbsemImproveSem()) || getCmbsemImproveSem().equals("-1")) {
            return false;
        }
        return true;
    }
}
