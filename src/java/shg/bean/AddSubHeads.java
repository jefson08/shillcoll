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
public class AddSubHeads {

    private String[] txtSubHead;
    private int cmbHead;

    /**
     * @return the txtSubHead
     */
    public String[] getTxtSubHead() {
        return txtSubHead;
    }

    /**
     * @param txtSubHead the txtSubHead to set
     */
    public void setTxtSubHead(String[] txtSubHead) {
        this.txtSubHead = txtSubHead;
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

    /**
     * @return the txtSubHead
     */
  public boolean isTxtSubHeadValid() {

        int itemsCount, i;
        String items[] = getTxtSubHead();
        itemsCount = getTxtSubHead().length;
        for (i = 0; i < itemsCount; i++) {
            if ((Validator.isNullValue(items[i]))) {
                return false;
            }
        }
        return true;
    }
   public boolean isCmbHeadValid() {
        if ((getCmbHead()==-1)){
            return false;
        }
        return true;  
   }
}
