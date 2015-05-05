/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shg.bean;

import shg.valid.Validator;

/**
 *
 * @author Shgcomp
 */
public class AddSubHeadsAmt {
    private String txtAmount;
    private int cmbHead;

    /**
     * @return the txtAmount
     */
    public String getTxtAmount() {
        return txtAmount;
    }

    /**
     * @param txtAmount the txtAmount to set
     */
    public void setTxtAmount(String txtAmount) {
        this.txtAmount = txtAmount;
    }

    /**
     * @return the cmbHead
     */
    public int getCmbHead() {
        return cmbHead;
    }

    /**
     * @param cmbHead the cmbHead to set
     */
    public void setCmbHead(int cmbHead) {
        this.cmbHead = cmbHead;
    }
    public boolean isCmbHeadValid() {
        if ((getCmbHead()==-1)){
            return false;
        }
        return true;  
   }
     public boolean isTxtAmountValid() {

        if (Validator.isNullValue(getTxtAmount())) {
                return false;
        }
         else if (!Validator.isNumeric(getTxtAmount())) {
                return false;
            }
            
        
        return true;
}
}
