/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shg.bean;

import shg.valid.Validator;

/**
 *
 * @author teibor
 */
public class EditPapersBean {

    private String stream;
    private String cmbhon;
    private String cmbrollno;
    private String cmbexamid;
    private String subjectcode[];
    private String papercode[];

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

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getCmbhon() {
        return cmbhon;
    }

    public void setCmbhon(String cmbhon) {
        this.cmbhon = cmbhon;
    }

    public String getCmbrollno() {
        return cmbrollno;
    }

    public void setCmbrollno(String cmbrollno) {
        this.cmbrollno = cmbrollno;
    }

    public String getCmbexamid() {
        return cmbexamid;
    }

    public void setCmbexamid(String cmbexamid) {
        this.cmbexamid = cmbexamid;
    }

}
