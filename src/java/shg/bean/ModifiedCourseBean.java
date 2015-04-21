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
    private String msg="";

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public String getTxtcoursename() {
        return txtcoursename;
    }
    
    
    public String getTxtnoofseat() {
        return txtnoofseat;
    }

    public boolean isTxtcoursenameValid() {
        if (!Validator.isNullValue(getTxtcoursename())) {
            return false;
        }
        return true;
    }

    public boolean isStreamValid() {
        if (!Validator.isNullValue(getStream())) {
            return false;
        }
        return true;
    }

    public boolean isTxtnoofseatValid() {
        if (!Validator.isEmpty(getTxtnoofseat())) {
            return false;
        }
        return true;
    }
      public boolean isCmbhonValid() {
        if (!Validator.isEmpty(getCmbhon())) {
            return false;
        }
        return true;
    }

    /**
     * @param txtcoursename the txtcoursename to set
     */
    public void setTxtcoursename(String txtcoursename) {
        this.txtcoursename = txtcoursename;
    }

    /**
     * @param txtsubject the txtsubject to set
     */
    /**
     * @param txtnoofseat the txtnoofseat to set
     */
    public void setTxtnoofseat(String txtnoofseat) {
        this.txtnoofseat = txtnoofseat;
    }

    /**
     * @return the stream
     */
    public String getStream() {
        return stream;
    }

    /**
     * @param stream the stream to set
     */
    public void setStream(String stream) {
        this.stream = stream;
    }

    /**
     * @return the cmbhon
     */
    public String getCmbhon() {
        return cmbhon;
    }

    /**
     * @param cmbhon the cmbhon to set
     */
    public void setCmbhon(String cmbhon) {
        this.cmbhon = cmbhon;
    }

}
