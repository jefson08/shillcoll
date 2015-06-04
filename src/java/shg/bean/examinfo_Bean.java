/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shg.bean;

import shg.valid.Validator;

/**
 *
 * @author Ransly
 */
public class examinfo_Bean {

    private String radYearOrSem;
    private String rollno;
    private String txtBatch;
    private String txtUnirollno;
    private String txtRegno;
    private String seye;
    private String nri;
    private String errorssuccmsg = "";
    private String txtPmtDate;
    private String pmtstatus;
    private static String examId;
    private static int serial;

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        
        System.out.println("bean eid"+examId);
        this.examId = examId;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public String getRadYearOrSem() {
        return radYearOrSem;
    }

    public void setRadYearOrSem(String radYearOrSem) {
        this.radYearOrSem = radYearOrSem;
    }

    public String getPmtstatus() {
        return pmtstatus;
    }

    public void setPmtstatus(String pmtstatus) {
        this.pmtstatus = pmtstatus;
    }

    public String getTxtPmtDate() {
        return txtPmtDate;
    }

    public void setTxtPmtDate(String txtPmtDate) {
        this.txtPmtDate = txtPmtDate;
    }

    public String getErrorssuccmsg() {
        return errorssuccmsg;
    }

    public void setErrorssuccmsg(String errorssuccmsg) {
        this.errorssuccmsg = errorssuccmsg;
    }

    public String getNri() {
        return nri;
    }

    public void setNri(String nri) {
        this.nri = nri;
    }

    public String getRollno() {

        return rollno;
    }

    public void setRollno(String rollno) {

        this.rollno = rollno;
    }

    public String getSeye() {
        return seye;
    }

    public void setSeye(String seye) {
        this.seye = seye;
    }

    public String getTxtBatch() {
        return txtBatch;
    }

    public void setTxtBatch(String txtBatch) {
        this.txtBatch = txtBatch;
    }

    public String getTxtRegno() {
        return txtRegno;
    }

    public void setTxtRegno(String txtRegno) {
        this.txtRegno = txtRegno;
    }

    public String getTxtUnirollno() {
        return txtUnirollno;
    }

    public void setTxtUnirollno(String txtUnirollno) {
        this.txtUnirollno = txtUnirollno;
    }

    public boolean isRadYearOrSemValid() {
        if (Validator.isNullValue(getRadYearOrSem())) {
            return false;
        }
        if (getRadYearOrSem().trim().length() == 0 || !(getRadYearOrSem().toLowerCase().equals("s") || getRadYearOrSem().toLowerCase().equals("y"))) {
            return false;
        }
        return true;
    }

    public boolean isPmtStatusValid() {
        if (Validator.isNullValue(getPmtstatus())) {
            return false;
        }
        if (getPmtstatus().trim().length() == 0 || !(getPmtstatus().toLowerCase().equals("y") || getPmtstatus().toLowerCase().equals("n"))) {
            return false;
        }
        return true;
    }

    public boolean isTxtPmtDateValid() {
        if (Validator.isNullValue(getTxtPmtDate())
                || (!Validator.checkRegexp(getTxtPmtDate(), "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[012])-(19[0-9][0-9]|20[0-9][0-9]|21[0-9][0-9])"))
                || Validator.isDateAfterToday(getTxtPmtDate())) {
            return false;
        }
        return true;
    }

    public boolean isRollnoValid() {
        System.out.println("roll:" + getRollno());
        if (Validator.isNullValue(getRollno())) {
            return false;
        }
        return true;
    }

    public boolean isSeyeValid() {
        if (Validator.isNullValue(getSeye())) {
            return false;
        }
        return true;
    }

    public boolean isTxtBatchValid() {
        if (Validator.isNullValue(getTxtBatch())) {
            return false;
        }
        return true;
    }

    public boolean isTxtRegnoValid() {
        if (!Validator.isNullValue(getTxtRegno())) {
            if (!Validator.isNumeric(getTxtRegno())) {
                return false;
            }
        }
        return true;
    }

    public boolean isTxtUnirollnoValid() {
        if (Validator.isNullValue(getTxtUnirollno())) {
            return false;
        }
        return true;
    }

    public boolean isNriValid() {
        if (Validator.isNullValue(getNri())) {
            return false;
        }
//        if (getNri().trim().length() == 0 || !(getNri().toLowerCase().equals("r") || getNri().toLowerCase().equals("i") || getNri().toLowerCase().equals("n"))) {
//            return false;
//        }
        return true;
    }
}
