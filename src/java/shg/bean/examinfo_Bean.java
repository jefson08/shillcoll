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
    private String txtNehurollno;
    private String txtRegno;
    static private String seye;
    private String nri;
    private String errorssuccmsg = "";
    private String txtPmtDate;
    private String pmtstatus = "TRUE";
    static private String examId;
    static private int serial;
    static private String exmonth = "";
    static private String exyear = "";
    static private String regyear = "";

    public String getRegyear() {
        return regyear;
    }

    public void setRegyear(String Regyear) {
        this.regyear = Regyear;
    }

    public String getExmonth() {
        return exmonth;
    }

    public void setExmonth(String exmonth) {
        this.exmonth = exmonth;
    }

    public String getExyear() {
        return exyear;
    }

    public void setExyear(String exyear) {

        this.exyear = exyear;
    }

    public String getErrorssuccmsg() {
        return errorssuccmsg;
    }

    public void setErrorssuccmsg(String errorssuccmsg) {
        this.errorssuccmsg = errorssuccmsg;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        // System.out.println("examid:" + examId);
        this.examId = examId;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        // System.out.println("slno:" + serial);
        this.serial = serial;
    }

    public String getRadYearOrSem() {
        return radYearOrSem;
    }

    public void setRadYearOrSem(String radYearOrSem) {
        // System.out.println("sem/year:" + radYearOrSem);
        this.radYearOrSem = radYearOrSem;
    }

    public String getPmtstatus() {
        return pmtstatus;
    }

    public void setPmtstatus(String pmtstatus) {
        // System.out.println("paystat:" + pmtstatus);
        this.pmtstatus = pmtstatus;
    }

    public String getTxtPmtDate() {
        return txtPmtDate;
    }

    public void setTxtPmtDate(String txtPmtDate) {
        // System.out.println("paydate:" + txtPmtDate);
        this.txtPmtDate = txtPmtDate;
    }

    public String getNri() {
        return nri;
    }

    public void setNri(String nri) {
        // System.out.println("nri:" + nri);
        this.nri = nri;
    }

    public String getRollno() {

        return rollno;
    }

    public void setRollno(String rollno) {
        // System.out.println("roll:" + rollno);
        this.rollno = rollno;
    }

    public String getSeye() {
        return seye;
    }

    public void setSeye(String seye) {
        // System.out.println("seye:" + seye);
        this.seye = seye;
    }

    public String getTxtBatch() {
        return txtBatch;
    }

    public void setTxtBatch(String txtBatch) {
        //  System.out.println("batch:" + txtBatch);
        this.txtBatch = txtBatch;
    }

    public String getTxtRegno() {
        return txtRegno;
    }

    public void setTxtRegno(String txtRegno) {
        // System.out.println("REg:" + txtRegno);
        this.txtRegno = txtRegno;
    }

    public String getTxtNehurollno() {
        return txtNehurollno;
    }

    public void setTxtNehurollno(String txtNehurollno) {
        //System.out.println("nehu:" + txtNehurollno);
        this.txtNehurollno = txtNehurollno;
    }

    public boolean isRegyearValid() {
        System.out.println("ry::"+getRegyear());
        if (Validator.isNullValue(getRegyear()) || getRegyear().equals("")) {
            return false;
        }
        return true;
    }

    public boolean isExmonthValid() {
        if (Validator.isNullValue(getExmonth()) || getExmonth().equals("")) {
            return false;
        }
        return true;
    }

    public boolean isExyearValid() {
        if (Validator.isNullValue(getExyear()) || getExyear().equals("")) {
            return false;
        }
        return true;
    }

    public boolean isTxtNehurollnoValid() {
        if (Validator.isNullValue(getTxtNehurollno())) {
            return false;
        }
        return true;
    }

    public boolean isTxtNehurollnoEditValid() {
        if (Validator.isNullValue(getTxtNehurollno()) || getTxtNehurollno().equalsIgnoreCase("AF")) {
            return false;
        }
        return true;
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
        if (getPmtstatus().trim().length() == 0 || !(getPmtstatus().toLowerCase().equals("true") || getPmtstatus().toLowerCase().equals("false"))) {
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

    public boolean isTxtRegnoEditValid() {
        if (Validator.isNullValue(getTxtRegno()) || getTxtRegno().equalsIgnoreCase("AF")) {
            //if (Validator.isNumeric(getTxtRegno())) {
            return false;
            //}
        }
        return true;
    }

    public boolean isTxtRegnoValid() {
        if (Validator.isNullValue(getTxtRegno())) {
            // if (Validator.isNumeric(getTxtRegno())) {
            return false;
            // }
        }
        return true;
    }

    public boolean isNriValid() {
        if (Validator.isNullValue(getNri())) {
            return false;
        }
        if (getNri().trim().length() == 0 || !(getNri().toLowerCase().equals("r") || getNri().toLowerCase().equals("i") || getNri().toLowerCase().equals("n"))) {
            return false;
        }
        return true;
    }
}
