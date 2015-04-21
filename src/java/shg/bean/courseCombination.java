/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shg.bean;

import DBConnection.ConnectionPool;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import shg.util.DatabaseUtility;

/**
 *
 * @author B Mukhim
 */
public class courseCombination {
    private String coursecode;
    private String combname;
    private String txteffectiveyear;
    private boolean txtstatus;
    private String subjectcode[];
    private String papercode[];
    private String msg="";
    private String msg1="";

    public String getMsg1() {
        return msg1;
    }

    public void setMsg1(String msg1) {
        this.msg1 = msg1;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    /**
     * @return the coursecode
     */
    public String getCoursecode() {
        return coursecode;
    }

    /**
     * @param coursecode the coursecode to set
     */
    public void setCoursecode(String coursecode) {
        this.coursecode = coursecode;
    }

    /**
     * @return the combname
     */
    public String getCombname() {
        return combname;
    }

    /**
     * @param combname the combname to set
     */
    public void setCombname(String combname) {
        this.combname = combname;
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
     * @return the txtstatus
     */
    public boolean getTxtstatus() {
        return txtstatus;
    }

    /**
     * @param txtstatus the txtstatus to set
     */
    public void setTxtstatus(boolean txtstatus) {
        this.txtstatus = txtstatus;
    }

    /**
     * @return the subjectcode
     */
    public String[] getSubjectcode() {
        return subjectcode;
    }

    /**
     * @param subjectcode the subjectcode to set
     */
    public void setSubjectcode(String[] subjectcode) {
        this.subjectcode = subjectcode;
    }

    /**
     * @return the papercode
     */
    public String[] getPapercode() {
        return papercode;
    }

    /**
     * @param papercode the papercode to set
     */
    public void setPapercode(String[] papercode) {
        this.papercode = papercode;
    }
    
    public boolean isSubjectcodeValid(ServletContext context){
        try {
            if(getPapercode()==null){
                return false;
            }
            Map hp = new DatabaseUtility().getSubjectPaperMapping(getPapercode(), context);
            for(String scode:getSubjectcode()){
                if(!hp.containsValue(scode)){
                    return false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(courseCombination.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
