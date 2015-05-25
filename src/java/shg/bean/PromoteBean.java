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
    
    private String coursecode[];
    private String semoryear[];
   
    
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

  /**
   * @return the rollno
   */
  public String[] getRollno() {
    return rollno;
  }

  /**
   * @param rollno the rollno to set
   */
  public void setRollno(String[] rollno) {
    this.rollno = rollno;
  }

   
}
