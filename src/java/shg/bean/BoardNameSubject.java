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
public class BoardNameSubject {

    private String cmbBoardID; 
    private String txtBoaName;
    private String[] txtSubName;
    private String txtStream;

    public String[] getTxtSubName() {
        return txtSubName;
    }

    /**
     * @param txtSubName the cmbBoardID to set
     */
    public void setTxtSubName(String[] txtSubName) {
        this.txtSubName = txtSubName;
    }
    
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

    /**
     * @return the txtBoaName
     */
    public String getTxtBoaName() {
        return txtBoaName;
    }

    /**
     * @param txtBoaName the txtBoaName to set
     */
    public void setTxtBoaName(String txtBoaName) {
        this.txtBoaName = txtBoaName;
    }

    public boolean isTxtSubNameValid() {
        String[] item = getTxtSubName();
        if (item != null) {
            for (String item1 : item) {
                if ((Validator.isNullValue(item1)) || Validator.containsIllegalCharacters(item1) || !(Validator.isText(item1))) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    public boolean isTxtBoaNameValid() {
        return !((Validator.isNullValue(getTxtBoaName())) || Validator.containsIllegalCharacters(getTxtBoaName()) || !(Validator.isText(getTxtBoaName())));
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
