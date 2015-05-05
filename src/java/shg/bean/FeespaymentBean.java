/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shg.bean;

import shg.valid.Validator;

/**
 *
 * @author teibor
 */
public class FeespaymentBean {

    private String stream;
    private String cmbhon;
    private String cmbrollno;
    private String txtFees;

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getCmbhon() {
        return cmbhon;
    }

    public void setCmbhon(String cmbhon) {
        this.cmbhon = cmbhon;
    }

   

    public String getTxtFees() {
        return txtFees;
    }

    public String getCmbrollno() {
        return cmbrollno;
    }

    public void setCmbrollno(String cmbrollno) {
        this.cmbrollno = cmbrollno;
    }

    public void setTxtFees(String txtFees) {
        this.txtFees = txtFees;
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
   public boolean isCmbrollnoValid() {
        if (Validator.isNullValue(getCmbrollno()) || getCmbrollno().equals("-1")) {
            return false;
        }
        return true;
    }
}
