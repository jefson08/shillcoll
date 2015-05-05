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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    private String msg;

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
    private String subjectcode[];
    private String papercode[];

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
