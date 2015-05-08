/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shg.bean;

/**
 *
 * @author B Mukhim
 */
public class PromoteBean {
    private String rollno[];
    private String nehurollno[];
    private String regno[];
    private String coursecode[];
    private String semoryear[];
    private String txtPromote[];

    
    public String[] getTxtPromote() {
        return txtPromote;
    }

    /**
     * @param txtPromote the txtPromote to set
     */
    public void setTxtPromote(String[] txtPromote) {
        this.txtPromote = txtPromote;
    }

    /**
     * @return the nehurollno
     */
    public String[] getNehurollno() {
        return nehurollno;
    }

    /**
     * @param nehurollno the nehurollno to set
     */
    public void setNehurollno(String[] nehurollno) {
        this.nehurollno = nehurollno;
    }

    /**
     * @return the regno
     */
    public String[] getRegno() {
        return regno;
    }

    /**
     * @param regno the regno to set
     */
    public void setRegno(String[] regno) {
        this.regno = regno;
    }

    /**
     * @return the coursecode
     */
    public String[] getCoursecode() {
        return coursecode;
    }

    /**
     * @param coursecode the coursecode to set
     */
    public void setCoursecode(String[] coursecode) {
        this.coursecode = coursecode;
    }

    /**
     * @return the semoryear
     */
    public String[] getSemoryear() {
        return semoryear;
    }

    /**
     * @param semoryear the semoryear to set
     */
    public void setSemoryear(String[] semoryear) {
        this.semoryear = semoryear;
    }

   
}
