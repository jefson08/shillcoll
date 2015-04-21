/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shg.bean;

import shg.valid.Validator;

/**
 *
 * @author B Mukhim
 */
public class searchPrevXii {

    private String cmbBoardID; 
    private String txtStream;
    
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
    public boolean isCmbBoardIDValid() {
        if (Validator.isNullValue(getCmbBoardID()) || getCmbBoardID().equals("-1")) {
            return false;
        }
        return true;
    }


    /**
     * @return the txtStream
     */
    public String getTxtStream() {
        return txtStream;
    }

    /**
     * @param txtStream the txtStream to set
     */
    public void setTxtStream(String txtStream) {
        this.txtStream = txtStream;
    }

    public boolean isTxtStreamValid() {
        return !((Validator.isNullValue(getTxtStream())) || Validator.containsIllegalCharacters(getTxtStream()) || !(Validator.isText(getTxtStream())));
    }
}
