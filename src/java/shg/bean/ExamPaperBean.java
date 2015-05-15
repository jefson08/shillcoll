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
public class ExamPaperBean {

    private String roll1;
    private String status;
    private String yos;
    private String msg1="";
    private String nehuroll;
    private String msg2="";
    private String msg3="";

    public String getMsg3() {
        return msg3;
    }

    public void setMsg3(String msg3) {
        this.msg3 = msg3;
    }

    private String subjectcode[];
    private String papercode[];
    public String getNehuroll() {
        return nehuroll;
    }

    public String getMsg2() {
        return msg2;
    }

    public void setMsg2(String msg2) {
        this.msg2 = msg2;
    }

    public void setNehuroll(String nehuroll) {
        this.nehuroll = nehuroll;
    }

    public String getMsg1() {
        return msg1;
    }

    public void setMsg1(String msg1) {
        this.msg1 = msg1;
    }

    public String[] getSubjectcode() {
        return subjectcode;
    }

    public void setSubjectcode(String[] subjectcode) {
        this.subjectcode = subjectcode;
    }

    public String[] getPapercode() {
        return papercode;
    }

    public void setPapercode(String[] papercode) {
        this.papercode = papercode;
    }
    

    public String getRoll1() {
        return roll1;
    }

    public void setRoll1(String roll1) {
        this.roll1 = roll1;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getYos() {
        return yos;
    }

    public void setYos(String yos) {
        this.yos = yos;
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
