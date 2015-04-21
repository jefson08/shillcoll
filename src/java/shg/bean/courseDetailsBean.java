package shg.bean;

import shg.valid.Validator;

/**
 *
 * @author B Mukhim
 */
public class courseDetailsBean 

{
    private String txtstatus;
    private String txtcombination;
    private String txteffectiveyear;
    private String txtcoursecode;
    private String radstatus;
     
     
    public String gettxtstatus() {
          return getTxtstatus();
    }
    public String gettxtcombination() {
          return getTxtcombination();
    }
    public String gettxteffectiveyear() {
          return getTxteffectiveyear();
    }
    public String gettxtcoursecode() {
          return getTxtcoursecode();
    }
     public String getRadStatus() {
        return getRadstatus();
    }
    
    
   public boolean isTxtcombinationValid() {
        if (!Validator.isEmpty(gettxtcombination())) {
            return false;
        }
        return true;
    }
    public boolean isTxteffectiveyearValid() {
        if (!Validator.isEmpty(gettxteffectiveyear())|| !Validator.isValidLength(gettxteffectiveyear(), 4, 4)) {
            return false;
        }
        return true;
    }
    public boolean isTxtstatusValid() {
        if (!Validator.isEmpty(gettxtstatus())) {
            return false;
        }
        return true;
    }
public boolean isRadstatusValid() {
        if (Validator.isNullValue(getRadStatus())) {
            return false;
        }
        if (getRadStatus().trim().length() == 0 || !(getRadStatus().toLowerCase().equals("y") || getRadStatus().toLowerCase().equals("n"))) {
            return false;
        }
        return true;
    }

    
    
    /**
     * @return the txtstatus
     */
    public String getTxtstatus() {
        return txtstatus;
    }

    /**
     * @param txtstatus the txtstatus to set
     */
    public void setTxtstatus(String txtstatus) {
        this.txtstatus = txtstatus;
    }

    /**
     * @return the txtcombination
     */
    public String getTxtcombination() {
        return txtcombination;
    }

    /**
     * @param txtcombination the txtcombination to set
     */
    public void setTxtcombination(String txtcombination) {
        this.txtcombination = txtcombination;
    }

    /**
     * @return the txteffectiveyear
     */
    public String getTxteffectiveyear() {
        return txteffectiveyear;
    }

    /**
     * @param txteffectiveyear the txteffectiveyear to set
     */
    public void setTxteffectiveyear(String txteffectiveyear) {
        this.txteffectiveyear = txteffectiveyear;
    }

    /**
     * @return the txtcoursecode
     */
    public String getTxtcoursecode() {
        return txtcoursecode;
    }

    /**
     * @param txtcoursecode the txtcoursecode to set
     */
    public void setTxtcoursecode(String txtcoursecode) {
        this.txtcoursecode = txtcoursecode;
    }

    /**
     * @return the radstatus
     */
    public String getRadstatus() {
        return radstatus;
    }

    /**
     * @param radstatus the radstatus to set
     */
    public void setRadstatus(String radstatus) {
        this.radstatus = radstatus;
    }

    /**
     * @param txtCourseSeat the txtCourseSeat to set
     */
  
}