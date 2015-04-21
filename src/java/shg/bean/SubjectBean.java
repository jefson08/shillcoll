package shg.bean;

import shg.valid.Validator;

/**
 *
 * @author B Mukhim
 */
public class SubjectBean {

    private String txtsubjectcode;
    private String txtsubjectname;
    private String cmbcoursename;
    private String stream;

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }
    private String msg="";
    private String msg1="";
    private String msg2="";

    public String getMsg2() {
        return msg2;
    }

    public void setMsg2(String msg2) {
        this.msg2 = msg2;
    }

    public String getMsg1() {
        return msg1;
    }

    public void setMsg1(String msg1) {
        this.msg1 = msg1;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
   

    public String getTxtsubjectname() {
        return txtsubjectname;
    }

    public String getTxtsubjectcode() {
        return txtsubjectcode;
    }

    public String getcmbcoursename() {
        return cmbcoursename;
    }

    public boolean isTxtsubjectnameValid() {
        if (!Validator.isEmpty(getTxtsubjectname())) {
            return false;
        }
        return true;
    }

    public boolean isStreamValid() {
        if (Validator.isEmpty(getStream())) {
            return false;
        }
        return true;
    }

    public boolean isTxtsubjectcodeValid() {
        if (!Validator.isEmpty(getTxtsubjectcode())) {
            return false;
        }
        return true;
    }

    /**
     * @param txtsubjectcode the txtsubjectcode to set
     */
    public void setTxtsubjectcode(String txtsubjectcode) {
        this.txtsubjectcode = txtsubjectcode;
    }

    /**
     * @param txtsubjectname the txtsubjectname to set
     */
    public void setTxtsubjectname(String txtsubjectname) {
        this.txtsubjectname = txtsubjectname;
    }

    /**
     * @param cmbCoursename the cmbCoursename to set
     */
    public void setCmbCoursename(String cmbCoursename) {
        this.setCmbcoursename(cmbCoursename);
    }

    /**
     * @return the cmbcoursename
     */
    public String getCmbcoursename() {
        return cmbcoursename;
    }

    /**
     * @param cmbcoursename the cmbcoursename to set
     */
    public void setCmbcoursename(String cmbcoursename) {
        this.cmbcoursename = cmbcoursename;
    }

    /**
     * @param txtCourseSeat the txtCourseSeat to set
     */
}
