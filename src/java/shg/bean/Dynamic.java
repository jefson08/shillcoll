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
public class Dynamic {
    private String [] paperid;
    private String [] papername;
    

    /**
     * @return the paperid
     */
    public String[] getPaperid() {
        return paperid;
    }

    /**
     * @param paperid the paperid to set
     */
    public void setPaperid(String[] paperid) {
        this.paperid = paperid;
    }

    /**
     * @return the papername
     */
    public String[] getPapername() {
        return papername;
    }

    /**
     * @param papername the papername to set
     */
    public void setPapername(String[] papername) {
        this.papername = papername;
    }
    
    public boolean isPaperidValid(){
        int len=getPaperid().length;
        String id[]=getPaperid();
        for(int i=0; i< len; i++){
            if(Validator.isNullValue((id[i]))){
                return false;
            }
        }
        return true;
    }
}
