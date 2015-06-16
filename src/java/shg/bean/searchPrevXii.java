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

    private String rollno; 
    /**
     * @return the cmbBoardID
     */
    public String getRollno() {
        return rollno;
    }

    /**
     * @param cmbBoardID the cmbBoardID to set
     */
    public void setRollno(String rollno) {  
        this.rollno = rollno;
    }
    public boolean isRollnoValid() {
        if (Validator.isNullValue(getRollno()) || getRollno().equals("-1")) {
            return false;
        }
        return true;
    }
}
