package shg.bean;

import shg.valid.Validator;

/**
 *
 * @author B Mukhim
 */
public class ModifiedCourseBean {

    private String txtcoursename;
    private String stream;
    private String txtnoofseat;
    private String cmbhon;
    private String msg1 = "";
    private String msg2 = "";
    private String msg = "";
    private String msg3 = "";

    public String getMsg3() {
        return msg3;
    }

    public void setMsg3(String msg3) {
        this.msg3 = msg3;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTxtcoursename() {
        return txtcoursename;
    }

    public void setTxtcoursename(String txtcoursename) {
        this.txtcoursename = txtcoursename;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getTxtnoofseat() {
        return txtnoofseat;
    }

    public void setTxtnoofseat(String txtnoofseat) {
        this.txtnoofseat = txtnoofseat;
    }

    public String getCmbhon() {
        return cmbhon;
    }

    public void setCmbhon(String cmbhon) {
        this.cmbhon = cmbhon;
    }

    public String getMsg1() {
        return msg1;
    }

    public void setMsg1(String msg1) {
        this.msg1 = msg1;
    }

    public String getMsg2() {
        return msg2;
    }

    public void setMsg2(String msg2) {
        this.msg2 = msg2;
    }

    public boolean isStreamValid() {
        if (Validator.isNullValue(getStream()) || getStream().equals("-1")) {
            return false;
        }
        return true;
    }

    public boolean isCmbhonValid() {
        if (Validator.isNullValue(getCmbhon()) || getCmbhon().equals("-1")) {
            return false;
        }
        return true;
    }

    public boolean isTxtcoursenameValid() {
        if (Validator.isNullValue(getTxtcoursename()) || getTxtcoursename().equals("-1")||Validator.containsIllegalCharacters1(getTxtcoursename())) {
            return false;
        }
        return true;
    }

    public boolean isTxtnoofseatValid() {
        if (Validator.isNullValue(getTxtnoofseat())||(!Validator.isNumeric(getTxtnoofseat()))) {
            return false;
        }
        return true;
    }

}

/*}*/
